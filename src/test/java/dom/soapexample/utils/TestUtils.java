package dom.soapexample.utils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.soap.jaxb.Book;
import com.soap.jaxb.Category;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@UtilityClass
public class TestUtils {

    /**
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
     * Returns Book object from XML
     *
     * @param request
     * @return
     * @throws IOException - unhandled for readability (learning purposes)
     */
    protected Book getXmlFromClasspath_ConvertToObject(Resource request) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = getXmlFromClasspath(request);
        Book value = xmlMapper.readValue(xml, Book.class);

        return value;
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
}
