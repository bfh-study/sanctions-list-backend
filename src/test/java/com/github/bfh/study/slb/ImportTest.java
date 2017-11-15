package com.github.bfh.study.slb;

import com.github.bfh.study.slb.imports.EuProvider;
import com.github.bfh.study.slb.imports.ImportContext;
import com.github.bfh.study.slb.imports.SecoProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@Test
public class ImportTest {

    ImportContext context;

    public void secoProviderTest(){
        context = ImportContext.importContextFactory("SECO");
        assertEquals(15, context.executeImport(10, 5));
    }

    public void euProviderTest(){
        context = ImportContext.importContextFactory("EU");
        assertEquals(5, context.executeImport(10, 5));
    }
}
