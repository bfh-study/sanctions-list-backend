package com.github.bfh.study.slb.imports;

import com.github.bfh.study.slb.domain.entities.BirthInfo;
import com.github.bfh.study.slb.domain.entities.EntityInfo;
import com.github.bfh.study.slb.domain.entities.PassportInfo;
import com.github.bfh.study.slb.domain.entities.SanctionEntity;
import com.github.bfh.study.slb.imports.parser.ProcessingElement;
import com.github.bfh.study.slb.provider.seco.entities.Identity;
import com.github.bfh.study.slb.provider.seco.entities.Individual;
import com.github.bfh.study.slb.provider.seco.entities.LanguageType;
import com.github.bfh.study.slb.provider.seco.entities.Modification;
import com.github.bfh.study.slb.provider.seco.entities.Modification.ModificationType;
import com.github.bfh.study.slb.provider.seco.entities.NameEntity;
import com.github.bfh.study.slb.provider.seco.entities.SanctionProgram;
import com.github.bfh.study.slb.provider.seco.entities.SanctionSet;
import com.github.bfh.study.slb.provider.seco.entities.Target;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Processes the Swiss sanction list.
 *
 * @author Dario Carosella
 */
public class SecoProvider implements Import {

    @Override
    public BaseEntity convertEntity(Object object) {
        if (object instanceof SanctionProgram) {
            return convertToSanctionProgram((SanctionProgram) object);
        } else if (object instanceof Target) {
            return convertToSanctionEntity((Target) object);
        }

        return null;
    }

    @Override
    public List<ProcessingElement> getProcessingElements() {
        List<ProcessingElement> elements = new ArrayList<>();
        elements.add(new ProcessingElement("sanctions-program", SanctionProgram.class));
        elements.add(new ProcessingElement("target", Target.class));
        return elements;
    }

    private com.github.bfh.study.slb.domain.entities.SanctionProgram convertToSanctionProgram(
        SanctionProgram importedObject
    ) {
        Optional<SanctionSet> sanctionSetOptional = importedObject.getSanctionSets().stream()
            .filter(s -> s.getLanguage().equals("eng"))
            .findFirst();

        com.github.bfh.study.slb.domain.entities.SanctionProgram entity = null;
        if (sanctionSetOptional.isPresent()) {
            SanctionSet sanctionSet = sanctionSetOptional.get();
            entity = new com.github.bfh.study.slb.domain.entities.SanctionProgram(
                importedObject.getSsid(),
                Integer.toString(sanctionSet.getSsid())
            );

            for (LanguageType lang : importedObject.getProgramKeys()) {
                //entity.addShortDescription(
                // new TranslatedText(TYPES.SHORT_DESCRIPTION,lang.getLanguage(), lang.getValue()));
            }
            for (LanguageType lang : importedObject.getProgramNames()) {
                //entity.addLongDescription(
                // new TranslatedText(lang.getLanguage(), lang.getValue()));
            }
            XMLGregorianCalendar date = importedObject.getDate();
            entity.setDate(LocalDate.of(date.getYear(),date.getMonth(), date.getDay()));
            entity.setOrigin(importedObject.getOrigin().getValue());
        }

        return entity;
    }

    private SanctionEntity convertToSanctionEntity(Target importedObject) {
        // If a modification exists with type 'de-listed',   imported Object will be ignored
        Optional<Modification> optional = importedObject.getModifications().stream()
            .filter(
                m -> m.getModificationType() == ModificationType.DE_LISTED
                && m.getEffectiveDate().isBefore(LocalDate.now())
            )
            .findFirst();
        if (optional.isPresent()) {
            return null;
        }

        SanctionEntity sanctionEntity = null;
        for (Individual individual : importedObject.getIndividuals()) {
            Identity identity = individual.getIdentity();
            String place = "";
            if (identity.getBirthplace() != null) {
                place = String.valueOf(identity.getBirthplace().getPlaceId());
            }
            if (sanctionEntity == null) {
                sanctionEntity = new SanctionEntity(
                    importedObject.getSsid(),
                    String.valueOf(importedObject.getSanctionsSetId()),
                    place
                );
            }
            NameEntity nameEntity = identity.getNameEntity();
            EntityInfo entityInfo = new EntityInfo(nameEntity.getSsid());

            // Add name parts
            nameEntity.getNameParts().forEach(part -> {
                switch (part.getNamePartType()) {
                    case "given-name":
                        entityInfo.setFirstName(part.getValue());
                        break;
                    case "family-name":
                        entityInfo.setLastName(part.getValue());
                        break;
                    case "father-name":
                        entityInfo.setFirstName(entityInfo.getFirstName() + " " + part.getValue());
                        break;
                    case "whole-name":
                        entityInfo.setWholeName(part.getValue());
                        break;
                    case "title":
                        entityInfo.setTitle(part.getValue());
                        break;
                    default:
                        //TODO: Log no matching
                        break;
                }
            });
            if (entityInfo.getWholeName() == null) {
                entityInfo.setWholeName(entityInfo.getFirstName() + entityInfo.getFirstName());
            }
            sanctionEntity.addEntityInfo(entityInfo);

            if (identity.getBirthday() != null) {
                sanctionEntity.addBirthInfo(
                    new BirthInfo(identity.getBirthday().getBirthday(), place, "")
                );
            }

            com.github.bfh.study.slb.provider.seco.entities.PassportInfo pi =
                identity.getPassportInfo();
            if (pi != null) {
                sanctionEntity.addPassportInfo(new PassportInfo(pi.getNumber(), pi.getIssuer()));
            }
        }

        return sanctionEntity;
    }
}
