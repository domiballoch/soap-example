package dom.soapexample.utils;

import com.soap.jaxb.Book;
import dom.soapexample.config.JaxbConfig;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import javax.xml.bind.JAXBException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ConverterUtilsTest {

    @Autowired
    private ConverterUtils converterUtils;

    //TODO: file not being found
    @Value(value = "classpath:test_data/bookRequest.xml")
    private Resource request;

    @BeforeEach
    public void setUp() throws JAXBException {
        JaxbConfig jaxbConfig = new JaxbConfig();
        ReflectionTestUtils.setField(converterUtils, "bookJaxbContext", jaxbConfig.bookJaxbContext());
    }

    @Disabled
    @SneakyThrows
    @Test
    public void convertXMLToObject_withBean_Test() {
        String actual = TestUtils.getXmlFromClasspath(request);
        Book result = ConverterUtils.convertXMLToObject_withBean(actual);

        assertThat(result).isEqualTo(actual);
    }

    @Disabled
    @SneakyThrows
    @Test
    public void convertObjectToXML_withBean_Test() {
        Book actual = TestUtils.getXmlFromClasspath_ConvertToObject(request);
        String result = ConverterUtils.convertObjectToXML_withBean(TestUtils.createBook());

        assertThat(result).isEqualTo(actual);
    }

}
