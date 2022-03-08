package dom.soapexample.utils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.soap.jaxb.Book;
import com.soap.jaxb.Category;
import com.soap.jaxb.GetBookRequest;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;

@Slf4j
@UtilityClass
public class TestUtils {

    /**
     * Not used -
     * Creates one Book (JAXB Object) for testing
     *
     * @return
     */
    protected Book createBook() {
        Book book = new Book();
        book.setTitle("1984");
        book.setAuthor("George Orwell");
        book.setCategory(Category.FICTION);
        book.setPrice(new BigDecimal("5.99"));
        return book;
    }

    /**
     * Returns String from InputStream
     *
     * @param is
     * @return
     * @throws IOException - unhandled for readability (learning purposes)
     */
    protected String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    /**
     * Returns XML from classpath as String
     *
     * @param request
     * @return
     * @throws IOException - unhandled for readability (learning purposes)
     */
    protected String getXmlFromClasspath(Resource request) throws IOException {
        File file = new File(String.valueOf(request));
        String xml = TestUtils.inputStreamToString(new FileInputStream(file));

        return xml;
    }

    /**
     * Gets file from resources and converts to String
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    protected String getResourceFile(String fileName) throws IOException {
        File file = ResourceUtils.getFile(fileName);
        return new String(Files.readAllBytes(file.toPath()));
    }

    /**
     * Not used -
     * Returns Book object from XML
     *
     * @param request
     * @return
     * @throws IOException - unhandled for readability (learning purposes)
     */
    protected Book convertResourceToObject(Resource request) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = getXmlFromClasspath(request);
        Book value = xmlMapper.readValue(xml, Book.class);

        return value;
    }

    /**
     * Returns Book object from XML using getResourceFile
     *
     * @param request
     * @return
     * @throws IOException - unhandled for readability (learning purposes)
     */
    protected GetBookRequest convertStringToObject(String request) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = getResourceFile(request);
        String amendedXml = removeElements(xml);
        GetBookRequest value = xmlMapper.readValue(amendedXml, GetBookRequest.class);

        return value;
    }

    /**
     * Removes unwanted elements from xml to comply with XmlMapper
     *
     * @param xml
     * @return
     */
    private String removeElements(String xml) {
        String amendedXml = xml.replace("<soapenv:Header/>", "")
                .replace("<soapenv:Body>", "")
                .replace("</soapenv:Body>", "")
                .replace("<gs:getBookRequest>", "")
                .replace("</gs:getBookRequest>", "");
        return amendedXml;
    }

}
