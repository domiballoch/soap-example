package dom.soapexample.endpoint;

import com.soap.jaxb.Category;
import com.soap.jaxb.GetBookRequest;
import com.soap.jaxb.GetBookResponse;
import dom.soapexample.exception.BookNotFoundException;
import dom.soapexample.model.Book;
import dom.soapexample.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

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
    public GetBookResponse processGetBookRequest(@RequestPayload GetBookRequest request) {
        log.info("Received book request: {}", request.getTitle());
        Book book = bookRepository.findBook(request.getTitle());

        if (book == null) {
            log.info("Book not found: {}", request.getTitle());
            throw new BookNotFoundException("Book not found: " + request.getTitle());
        }
        log.info("Returning book response: {}", book.toString());

        return mapBookSchemaToResponse(book);
    }

    //add xml validation for request
    //add get all books, create, delete and update
    //add service class
    //fix security
    //add tests

    private GetBookResponse mapBookSchemaToResponse(Book book) {
        GetBookResponse response = new GetBookResponse();
        response.setBook(mapBookPojoToSchema(book));
        return response;
    }

    private com.soap.jaxb.Book mapBookPojoToSchema(Book bookPojo) {
        com.soap.jaxb.Book bookSchema = new com.soap.jaxb.Book();

        //bookSchema.setIsbn(bookPojo.getIsbn());
        bookSchema.setTitle(bookPojo.getTitle());
        bookSchema.setAuthor(bookPojo.getAuthor());
        bookSchema.setCategory(bookPojo.getCategory());
        bookSchema.setPrice(bookPojo.getPrice());
        //bookSchema.setStock(bookPojo.getStock());

        return bookSchema;
    }

//    private GetAllBooksResponse mapAllBooks(List<Book> books) {
//        GetAllBooksResponse response = new GetAllBooksResponse();
//        for (Book book : books) {
//            com.soap.jaxb.Book mapBook = mapBook(book);
//            response.com.soap.jaxb.getBook().add(mapBook);
//        }
//        return response;
//    }
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllBooksRequest")
//    @ResponsePayload
//    public GetAllBooksResponse processGetAllBooksRequest(
//            @RequestPayload GetAllBooksRequest request) {
//
//        List<Book> books = service.findAllBooks();
//
//        return mapAllBooks(books);
//    }
//
//    @PayloadRoot(namespace = "NAMESPACE_URI", localPart = "DeleteBookRequest")
//    @ResponsePayload
//    public DeleteBookResponse deleteBookRequest(@RequestPayload DeleteBookRequest request) {
//
//        Status status = service.deleteByIsbn(request.getIsbn());
//
//        DeleteBookResponse response = new DeleteBookResponse();
//        response.setStatus(mapStatus(status));
//
//        return response;
//    }
//
//    private Status mapStatus(Status status) {
//        if (status == Status.FAILURE)
//            return Status.FAILURE;
//        return Status.SUCCESS;
//    }
}
