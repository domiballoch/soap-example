package dom.soapexample.endpoint;

import com.soap.jaxb.GetBookRequest;
import com.soap.jaxb.GetBookResponse;
import dom.soapexample.exception.BookNotFoundException;
import dom.soapexample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BookEndpoint {

    private static final String NAMESPACE_URI = "http://www.soap.com/jaxb";

    private final BookRepository bokRepository;

    @Autowired
    public BookEndpoint(BookRepository bokRepository) {
        this.bokRepository = bokRepository;
    }

    /**
     * Accept soap request and return the soap response
     *
     * @param request
     * @return respone
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getBook(@RequestPayload GetBookRequest request) {
        GetBookResponse response = new GetBookResponse();
        response.setBook(bokRepository.findBook(request.getTitle()));

        if (response == null)
            throw new BookNotFoundException("Book not found " + request.getTitle());

        return response;
    }
}
