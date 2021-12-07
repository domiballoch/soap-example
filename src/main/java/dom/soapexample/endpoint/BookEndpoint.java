package dom.soapexample.endpoint;

import com.soap.jaxb.Book;
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

        GetBookResponse response = new GetBookResponse();

        Book book = bookRepository.findBook(request.getTitle());
        if (book == null) {
            throw new BookNotFoundException("Book not found " + request.getTitle());
        } //fix this - add better ex handling and logging
        response.setBook(book);

        return response;
    }

    //add get all books, create, delete and update
    //add service class
    //fix security
    //add tests
}
