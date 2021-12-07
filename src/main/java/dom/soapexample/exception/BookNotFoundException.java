package dom.soapexample.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://www.soap.com/jaxb}001_BOOK_NOT_FOUND")
public class BookNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3518170101751491969L;

    public BookNotFoundException(String message) {
        super(message);
    }
}