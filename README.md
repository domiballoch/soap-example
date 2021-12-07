<h1>Simple Soap Example with JAXB</h1>
<h4>Author: Dominic Balloch</h4>

<h3>Description</h3>
A Soap (XSD to Java) web-service project that uses dummy, static data
<br/>JAXB Context is also passed into the Application Context for performance

<br/>Note:
<br/>From Java 11 javax.activation is no longer supported. 
<br/>javax.xml.bind, org.glassfish.jaxb & com.sun.activation(jakata) is required
<br/>jaxb2-maven-plugin -> org.codehaus.mojo no longer works with Java 11 and above. cxf-xjc-plugin is required.

<h3>Prerequisites</h3>
Java 11
<br/>Spring Boot 2.6.1
<br/>Maven 3.6.3
<br/>No SQL required

<h3>Generate JAXB classes from XSD</h3>
mvn clean compile (this will create the objects in target/generated folder)
<br/>MAVEN -> Generate Sources and Update Folders

<h3>Build project</h3>
mvn clean install
<br/>spring-boot:run

<h3>Postman Soap URL and WSDL</h3>
URL -> http://localhost:8080/ws
<br/>Send xml request as HTTP POST with header as text/xml OR
<br/>curl --header "content-type: text/xml" -d @bookRequest.xml http://localhost:8080/ws
<br/>WSDL location at http://localhost:8080/ws/books?wsdl

<h3>Logging level</h3>
Debug