package dom.soapexample.endpoint;

import com.soap.jaxb.Book;
import com.soap.jaxb.GetBookRequest;
import com.soap.jaxb.GetBookResponse;
import dom.soapexample.exception.BookNotFoundException;
import dom.soapexample.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Slf4j
@Endpoint
public class BookEndpoint {

    private static final String NAMESPACE_URI = "http://www.soap.com/jaxb";

    private final BookRepository bookRepository;

    @Autowired
    public BookEndpoint(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
        log.info("Received book request: {}", request.getTitle());
        GetBookResponse response = new GetBookResponse();
        Book book = bookRepository.findBook(request.getTitle());

        if (book == null) {
            throw new BookNotFoundException("Book not found: " + request.getTitle());
        }
        response.setBook(book);
        log.info("Returning book response: {}", response.getBook());

        return response; //TODO:fix this - Book response is null?
    }

    //add get all books, create, delete and update
    //add service class
    //fix security
    //add tests
}
