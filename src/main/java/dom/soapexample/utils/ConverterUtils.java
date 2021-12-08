package dom.soapexample.utils;

import com.soap.jaxb.Book;
import dom.soapexample.config.JaxbConfig;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Slf4j
@UtilityClass
public class ConverterUtils extends ApplicationContextUtils {

    //Simple way - one type
    public static StringReader convertXMLToBook(String xml) throws JAXBException {
        log.info("Unmarshalling Book to Object: ", xml);
        JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader stringReader = new StringReader(xml);
        unmarshaller.unmarshal(stringReader);
        log.info("Unmarshalled XML as Book: {}", stringReader);
        return stringReader;
    }

    //Simple way - one type
    public static String convertBookToXML(Book book) throws JAXBException {
        log.info("Marshalling Book to XML: {}", book);
        StringWriter stringWriter = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(book.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(book, stringWriter);
        log.info("Marshalled Book as String: {}", stringWriter.toString());
        return stringWriter.toString();
    }

    // ----- GENERIC WAY -----//

    /**
     * Unmarshal XML to Object - Generic
     * Can unmarshal to object class, stringReader, byteArrayOutputStream etc
     *
     * @param clazz
     * @param xml
     * @param <T>
     * @return
     * @throws JAXBException
     */
    public static <T> T convertXMLToObject(Class<T> clazz, String xml) throws JAXBException {
        log.info("Unmarshalling XML to Object: ", xml);
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader stringReader = new StringReader(xml);
        unmarshaller.unmarshal(stringReader);
        log.info("Unmarshalled XML as Object: {}", stringReader);
        return (T) stringReader;
    }

    /**
     * Marshal Object to XML - Generic
     * Can marshal to file, stringWriter or system.out etc
     *
     * @param object
     * @param <T>
     * @return
     * @throws JAXBException
     */
    public static <T> String convertObjectToXML(T object) throws JAXBException {
        log.info("Marshalling Object to XML: {}", object);
        StringWriter stringWriter = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(object, stringWriter);
        log.info("Marshalled Object as String: {}", stringWriter.toString());
        return stringWriter.toString();
    }

    //----- METHODS USING JAXB CONTEXT BEAN -----//

    @Qualifier("book")
    @Autowired
    private JaxbConfig jaxbConfig;

    public static <T> T convertXMLToObject_withBean(String xml) throws JAXBException {
        log.info("Unmarshalling XML to Object: ", xml);

        Unmarshaller unmarshallerNonStatic = jaxbConfig.bookJaxbContext().createUnmarshaller();
        Unmarshaller unmarshallerStatic = applicationContext.getBean(JaxbConfig.class).bookJaxbContext().createUnmarshaller();

        StringReader stringReader = new StringReader(xml);
        unmarshallerStatic.unmarshal(stringReader);
        log.info("Unmarshalled XML as Object: {}", stringReader);
        return (T) stringReader;
    }

    public static <T> String convertObjectToXML_withBean(T object) throws JAXBException {
        log.info("Marshalling Object to XML: {}", object);
        StringWriter stringWriter = new StringWriter();

        Marshaller marshallerNonStatic = jaxbConfig.bookJaxbContext().createMarshaller();
        Marshaller marshallerStatic = applicationContext.getBean(JaxbConfig.class).bookJaxbContext().createMarshaller();
        marshallerStatic.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshallerStatic.marshal(object, stringWriter);
        log.info("Marshalled Object as String: {}", stringWriter.toString());
        return stringWriter.toString();
    }

}
