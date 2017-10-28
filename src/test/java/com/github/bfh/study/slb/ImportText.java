package com.github.bfh.study.slb;

import com.github.bfh.study.slb.imports.EuProvider;
import com.github.bfh.study.slb.imports.SecoProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@Test
public class ImportText {

    public void testImport(){
        ImportContext context = new ImportContext(new SecoProvider());
        assertEquals(15, context.executeImport(10, 5));

        context = new ImportContext(new EuProvider());
        assertEquals(5, context.executeImport(10, 5));
    }
}
