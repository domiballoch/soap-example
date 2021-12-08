package dom.soapexample.config;

import com.soap.jaxb.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Configuration
public class JaxbConfig {

    /**
     * Bean that can return all instances - passed to App Context
     *
     * @return
     * @throws JAXBException
     */
    @Bean
    public JAXBContext jaxbContext() throws JAXBException {
        return JAXBContext.newInstance(
                Book.class
                //add more here...
        );
    }

    /**
     * Individual Bean with name attribute - separates instances
     *
     * @return
     * @throws JAXBException
     */
    @Bean(name = "book")
    public JAXBContext bookJaxbContext() throws JAXBException {
        return JAXBContext.newInstance(Book.class);
    }
}
