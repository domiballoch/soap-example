package dom.soapexample.utils;

import com.soap.jaxb.GetBookRequest;
import dom.soapexample.config.JaxbConfig;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ConverterUtilsTest {

    @Autowired
    private ConverterUtils converterUtils;

    private static final String REQUEST = "classpath:getBookRequest.xml";

    @Value(value = "classpath:getBookRequest.xml")
    private Resource request;

    @BeforeEach
    public void setUp() throws JAXBException {
        JaxbConfig jaxbConfig = new JaxbConfig();
        ReflectionTestUtils.setField(converterUtils, "bookJaxbContext", jaxbConfig.bookJaxbContext());
    }

    @SneakyThrows
    @Test
    public void convertXMLToObject_withBean_Test() {
        String request = TestUtils.getResourceFile(REQUEST);
        GetBookRequest result = ConverterUtils.convertXMLToObject_withBean(request);

        assertThat(result.getTitle()).isEqualTo("Harry Potter");
    }

    @SneakyThrows
    @Test
    public void convertObjectToXML_withBean_Test() {
        GetBookRequest requestObject = TestUtils.convertStringToObject(REQUEST);
        String result = ConverterUtils.convertObjectToXML_withBean(requestObject);

        assertThat(result).isEqualTo(getBookRequestString());
    }

    /**
     * Returns getBookResponse object in String form for test
     *
     * @return
     */
    private String getBookRequestString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("\n<getBookRequest xmlns=\"http://www.soap.com/jaxb\">")
                .append("\n    <title>Harry Potter</title>")
                .append("\n</getBookRequest>\n").toString();
    }

    /**
     * Not used -
     * Gets file from resources as Inputstream and converts to String
     * Cannot be accessed from a Utility Class (static context)
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    private String getResourceAsInputStream(String fileName) throws IOException {
        String contents = null;
        try (InputStream inputStream = getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            contents = reader.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        }
        return contents;
    }

}
