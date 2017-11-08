package com.github.bfh.study.slb.provider.seco;

import com.github.bfh.study.slb.provider.seco.entities.SwissSanctionsList;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Test for Seco parser.
 */
@Test
public class SecoParserTest {

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
        assert list.getSanctionPrograms().size() == 1 : "List of sanction must have a size of 1";
    }
}
