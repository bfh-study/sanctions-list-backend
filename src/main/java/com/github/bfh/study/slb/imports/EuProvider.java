package com.github.bfh.study.slb.imports;

import com.github.bfh.study.slb.domain.entities.BirthInfo;
import com.github.bfh.study.slb.domain.entities.EntityInfo;
import com.github.bfh.study.slb.domain.entities.PassportInfo;
import com.github.bfh.study.slb.domain.entities.SanctionEntity;
import com.github.bfh.study.slb.domain.entities.SanctionProgram;
import com.github.bfh.study.slb.imports.parser.ProcessingElement;
import com.github.bfh.study.slb.provider.eu.entities.Entity;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Processes the EU sanction list.
 *
 * @author Dario Carosella
 */
public class EuProvider implements Import {

    private static final String SUPPORTED_LANG = "eng";

    private static final String SUPPORTED_ORIGIN = "EU";

    @Override
    public BaseEntity convertEntity(Object object) {
        if (object instanceof Entity) {
            return convertToSanctionProgram((Entity) object);
        }

        return null;
    }

    @Override
    public List<ProcessingElement> getProcessingElements() {
        List<ProcessingElement> elements = new ArrayList<>();
        elements.add(new ProcessingElement("ENTITY", Entity.class));
        return elements;
    }

    private SanctionProgram convertToSanctionProgram(Entity obj) {
        SanctionProgram sanctionProgram = new SanctionProgram(
            Integer.getInteger(obj.getProgramme(), -1),
            obj.getProgramme()
        );
        //sanctionProgram.addShortDescription(
        // new TranslatedText(TYPES.SHORT_DESCRIPTION, SUPPORTED_LANG, obj.getProgramme()));
        /*sanctionProgram.(
            new TranslatedText(TYPES.SHORT_DESCRIPTION, SUPPORTED_LANG, obj.getPdfLink().toString())
        );*/
        XMLGregorianCalendar date = obj.getRegDate();
        sanctionProgram.setDate(LocalDate.of(date.getYear(),date.getMonth(), date.getDay()));
        sanctionProgram.setOrigin(SUPPORTED_ORIGIN);

        String countryCode = "no country found";
        if (obj.getCitizenInfo() != null) {
            countryCode = obj.getCitizenInfo().getCountryIsoCode();
        }
        SanctionEntity sanctionEntity = new SanctionEntity(
            obj.getId(),
            obj.getProgramme(),
            countryCode
        );
        obj.getNames().forEach(name -> {
            EntityInfo entityInfo = new EntityInfo(name.getId());
            entityInfo.setFirstName(name.getFirstName());
            entityInfo.setLastName(name.getLastName());
            entityInfo.setWholeName(name.getWholeName());
            entityInfo.setFunction(name.getFunction());
            entityInfo.setGender(name.getGender());
            entityInfo.setLanguage(name.getLanguage());

            sanctionEntity.addEntityInfo(entityInfo);
        });
        obj.getBirthInfoList().forEach(b -> {
            BirthInfo birthInfo = new BirthInfo(b.getDate(), b.getPlace(), b.getCountry());
            sanctionEntity.addBirthInfo(birthInfo);
        });
        obj.getPassportInfoList().forEach(p -> {
            sanctionEntity.addPassportInfo(new PassportInfo(p.getNumber(), p.getCountry()));
        });

        sanctionProgram.addEntity(sanctionEntity);
        return sanctionProgram;
    }
}
