package dom.soapexample.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Collections;
import java.util.List;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
    }

    //WSDL: http://localhost:8080/ws/books?wsdl
    //Request URL: http://localhost:8080/ws
    @Bean(name = "books")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema booksSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("BookPort");
        definition.setTargetNamespace("http://www.soap.com/jaxb");
        definition.setLocationUri("/ws");
        definition.setSchema(booksSchema);
        return definition;
    }

    @Bean
    public XsdSchema booksSchema() {
        return new SimpleXsdSchema(new ClassPathResource("book-details.xsd"));
    }

//   @Bean
//    public XwsSecurityInterceptor securityInterceptor(){
//        XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
//        //Callback Handler -> SimplePasswordValidationCallbackHandler
//        securityInterceptor.setCallbackHandler(callbackHandler());
//        //Security Policy -> securityPolicy.xml
//        securityInterceptor.setPolicyConfiguration(new ClassPathResource("unused/securityPolicy.xml"));
//        return securityInterceptor;
//    }
//
//    @Bean
//    public SimplePasswordValidationCallbackHandler callbackHandler() {
//        SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
//        handler.setUsersMap(Collections.singletonMap("user", "password"));
//        return handler;
//    }
//
//    //Interceptors.add -> XwsSecurityInterceptor
//    @Override
//    public void addInterceptors(List<EndpointInterceptor> interceptors) {
//        interceptors.add(securityInterceptor());
//    }

}
