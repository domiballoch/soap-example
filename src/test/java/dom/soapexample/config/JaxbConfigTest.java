package dom.soapexample.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JaxbConfigTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void beansPresentInContext() {
        List<String> beans = new ArrayList<>();
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : beanNames) {
            Object beanClass = applicationContext.getBean(beanName).getClass();
            if (beanClass.toString().contains("JAXBContextImpl")) {
                beans.add(beanName);
            }
        }
        assertThat(beans).isNotNull()
                .contains("book")
                .contains("jaxbContext");
        assertThat(beans.size()).isEqualTo(2);
    }
}
