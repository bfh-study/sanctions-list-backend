package com.github.bfh.study.slb.imports.job;

import com.github.bfh.study.slb.domain.entities.SanctionEntity;
import com.github.bfh.study.slb.domain.entities.SanctionProgram;
import com.github.bfh.study.slb.imports.ImportContext;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.batch.api.chunk.ItemProcessor;

/**
 * convert items from the different sources into domain entities.
 *
 * @author Samuel Ackermann
 */
public class EntityProcessorStep extends BaseStep implements ItemProcessor {

    private List<SanctionProgram> sanctionPrograms;

    public EntityProcessorStep() {
        sanctionPrograms = new ArrayList<>();
    }

    @Override
    public Object processItem(Object item) throws Exception {
        if (context == null) {
            context = (ImportContext) jobContext.getTransientUserData();
        }

        BaseEntity baseEntity = context.convertEntity(item);
        if (baseEntity instanceof SanctionProgram) {
            SanctionProgram sanctionProgram = (SanctionProgram) baseEntity;
            sanctionProgram.getEntities().forEach(entity -> {
                entity.setSanctionProgram(sanctionProgram);
            });
            sanctionPrograms.add(sanctionProgram);
        } else if (baseEntity instanceof SanctionEntity) {
            SanctionEntity entity = (SanctionEntity) baseEntity;
            Optional<SanctionProgram> optional = sanctionPrograms.stream()
                .filter(p -> p.getSanctionIdentifier().equals(entity.getSanctionIdentifier()))
                .findFirst();

            if (optional.isPresent()) {
                entity.setSanctionProgram(optional.get());
                return entity;
            }
        }

        return baseEntity;
    }
}
