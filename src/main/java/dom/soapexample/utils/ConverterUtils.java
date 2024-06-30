package dom.soapexample.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

@Slf4j
@Component
public class ConverterUtils extends ApplicationContextUtils {

    /**
     * Using JaxbConfig class with or without qualifier for non-static method
     * For static methods use JaxbConfigFactory over ApplicationContextUtils as per example
     */

    @Qualifier("book")
    @Autowired
    private JAXBContext bookJaxbContext;

    public static <T> T convertXMLToObject_withBean(String xml) throws JAXBException, SOAPException, IOException {
       log.info("Unmarshalling XML to Object: ", xml);
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(xml.getBytes()));
        //Using static implementation - preferred!
        Unmarshaller unmarshallerStatic = JaxbContextFactory.createJAXBContextStaticBlock().createUnmarshaller();
        //Using app context - not recommended!
        //Unmarshaller unmarshallerStatic = getApplicationContext().getBean(JaxbConfig.class).jaxbContext().createUnmarshaller();
        Object object = unmarshallerStatic.unmarshal(message.getSOAPBody().extractContentAsDocument());
        log.info("Unmarshalled XML as Object: {}", object);
        return (T) object;
    }

    public static <T> String convertObjectToXML_withBean(T object) throws JAXBException {
        log.info("Marshalling Object to XML: {}", object);
        StringWriter stringWriter = new StringWriter();
        //Using static implementation - preferred!
        Marshaller marshallerStatic = JaxbContextFactory.createJAXBContextStaticBlock().createMarshaller();
        //Using app context - not recommended!
        //Marshaller marshallerStatic = getApplicationContext().getBean(JaxbConfig.class).jaxbContext().createMarshaller();
        marshallerStatic.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshallerStatic.marshal(object, stringWriter);
        log.info("Marshalled Object as String: {}", stringWriter);
        return stringWriter.toString();
    }

}
