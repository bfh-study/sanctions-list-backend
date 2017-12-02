package com.github.bfh.study.slb;

import static org.testng.AssertJUnit.assertEquals;

import com.github.bfh.study.slb.imports.ImportContext;
import com.github.bfh.study.slb.imports.SourceNotFoundException;
import org.testng.annotations.Test;

@Test
public class ImportTest {

    ImportContext context;

    public void secoProviderTest(){
        context = ImportContext.importContextFactory("SECO");
        assertEquals(15, context.executeImport(10, 5));
    }

    @Test(expectedExceptions = SourceNotFoundException.class)
    public void euProviderTest(){
        context = ImportContext.importContextFactory("EU");
        assertEquals(5, context.executeImport(10, 5));
    }
}
