package com.github.bfh.study.slb.app;

import io.crnk.core.module.Module;

/**
 * Simple module for the sanction list backend. It encapsulate registration of exception mappers.
 *
 * @author Samuel Ackermann
 */
class SlbModule implements Module {

    private static final String MODULE_NAME = "slb-module";

    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }

    @Override
    public void setupModule(ModuleContext moduleContext) {
        moduleContext.addExceptionMapper(new JobStartExceptionMapper());
    }
}
