package dom.soapexample.utils;

import com.soap.jaxb.GetBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Static block - active initialisation controlled by JVM, activated upon class loading
 * 2. Static method - lazy initialisation controlled by user, activated upon using the method
 * 3. Syncronised - for multi-threaded environments (can be used with other examples
 * 4. Singleton Pattern
 * 5. Context Map - Using multiple class types
 */
@Slf4j
@Component
public class JaxbContextFactory {

    /**
     *  1. static block
     */
    private static JAXBContext jaxbContext1;

    static {
        try {
            jaxbContext1 = JAXBContext.newInstance(GetBookRequest.class);
        } catch (JAXBException e) {
            log.error("Error creating JAXBContext");
            throw new AssertionError(e);
        }
    }

    public static JAXBContext createJAXBContextStaticBlock() {
        return jaxbContext1;
    }


    /**
     * 2. static method
     */
    private static JAXBContext jaxbContext2;

    public static JAXBContext createJAXBContextStaticMethod() throws JAXBException {
        if(jaxbContext2 == null){
            jaxbContext2 = JAXBContext.newInstance(GetBookRequest.class);
        }
        return jaxbContext2;
    }


    /**
     * 3. Syncronised
     */

    private static JAXBContext jaxbContext3 = null;
    public static synchronized JAXBContext createJAXBContextSyncronised() throws JAXBException {
        if(jaxbContext3 == null){
            jaxbContext3 = JAXBContext.newInstance(GetBookRequest.class);
        }
        return jaxbContext3;
    }


    /**
     * 4. Singleton pattern
     */
    private static JAXBContext INSTANCE;

    public static JAXBContext getJAXBContextSingleton() throws JAXBException {
        if (JaxbContextFactory.INSTANCE == null) {
            INSTANCE = JAXBContext.newInstance(GetBookRequest.class);
        }
        return INSTANCE;
    }


    /**
     * 5. Multiple class initialisation - stores new instance of JAXBContext class type
     * or alternatively you can create and put in a map manually
     */

    private static Map<Class, JAXBContext> contextMap = new HashMap<>();

    private JaxbContextFactory() {
    }

    public static JAXBContext getInstance(Class clazz) {
        JAXBContext contextObj = contextMap.get(clazz);
        try {
            if (contextObj == null) {
                contextObj = JAXBContext.newInstance(clazz);
                contextMap.put(clazz, contextObj);
            }
        } catch (JAXBException e) {
            throw new IllegalStateException("Unable to initialise");
        }
        return contextObj;
    }

}
