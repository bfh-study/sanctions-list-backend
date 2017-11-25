package com.github.bfh.study.slb.imports.parser;

import com.github.bfh.study.slb.provider.eu.entities.Entity;
import com.github.bfh.study.slb.provider.seco.entities.SanctionProgram;
import com.github.bfh.study.slb.provider.seco.entities.SwissSanctionsList;
import java.net.URI;
import java.net.URL;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Test for Seco parser.
 */
@Test
public class PartialXmlParserTest {

    private static final String FILE_PATH = "src/test/resources/test-seco.xml";

    public void testParsing() {
        SwissSanctionsList list = null;
        try {
            JAXBContext context = JAXBContext.newInstance(SwissSanctionsList.class);
            list = (SwissSanctionsList) context.createUnmarshaller()
                    .unmarshal(new File(FILE_PATH));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        assert list != null : "sanction list is null";
        assert list.getSanctionPrograms().size() == 2 : "List of sanction must have a size of 2";
    }

    public void testXmlParserClass() throws Exception {
        List<ProcessingElement> elements = new ArrayList<>();
        elements.add(new ProcessingElement("sanctions-program", SanctionProgram.class));
        PartialXmlParser parser = new PartialXmlParser(elements);
        parser.open(FILE_PATH);
        Object o;
        int count = 0;
        while ((o = parser.parse()) != null) {
            count++;
            assert o instanceof SanctionProgram : "parsed object is not a instance of SanctionProgram";
        }
        parser.close();
        assert count == 2 : "there are more or less then 2 sanction programs";
    }

    public void testParserWithoutElements() throws Exception {
        PartialXmlParser parser = new PartialXmlParser(new ArrayList<>());
        parser.open(FILE_PATH);
        int count = 0;
        while ((parser.parse()) != null) {
            count++;
        }
        parser.close();
        assert count == 0 : "the count value should be zero";
    }

    public void testEuListParsing() throws Exception {
        List<ProcessingElement> elements = new ArrayList<>(1);
        elements.add(new ProcessingElement("ENTITY", Entity.class));
        PartialXmlParser parser = new PartialXmlParser(elements);

        parser.open(URI.create("https://webgate.ec.europa.eu/europeaid/fsd/fsf/public/files/dtdFullSanctionsList/content?token=dG9rZW4tMjAxNw").toURL());
        Object parsedObject = null;
        int count = 0;
        while ((parsedObject = parser.parse()) != null) {
            assert parsedObject instanceof Entity : "parsed object must be an instance of Entity";
            count++;
        }
        parser.close();
        assert count > 0 : "the count value should not be zero";
    }
}
