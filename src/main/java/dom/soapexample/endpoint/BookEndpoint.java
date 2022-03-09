package dom.soapexample.endpoint;

import com.soap.jaxb.DeleteBookRequest;
import com.soap.jaxb.DeleteBookResponse;
import com.soap.jaxb.GetAllBooksRequest;
import com.soap.jaxb.GetAllBooksResponse;
import com.soap.jaxb.GetBookRequest;
import com.soap.jaxb.GetBookResponse;

import dom.soapexample.exception.BookNotFoundException;
import dom.soapexample.model.Book;
import dom.soapexample.model.Status;
import dom.soapexample.repository.BookRepository;
import dom.soapexample.service.BookService;
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

    private final BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    public BookEndpoint(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    /**
     * Accept soap request for getBookRequest and return the soap response
     *
     * @param request
     * @return respone
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getBookRequest(@RequestPayload GetBookRequest request) {
        log.info("Received book request: {}", request.getTitle());
        Book book = bookService.findBook(request.getTitle());

        if (book == null) {
            log.info("Book not found: {}", request.getTitle());
            throw new BookNotFoundException("Book not found: " + request.getTitle());
        }
        log.info("Returning book response: {}", book.toString());

        return mapBookSchemaToResponse(book);
    }

    /**
     * Maps book schema to response object
     *
     * @param book
     * @return
     */
    private GetBookResponse mapBookSchemaToResponse(Book book) {
        GetBookResponse response = new GetBookResponse();
        response.setBook(mapBookPojoToSchema(book));
        return response;
    }

    /**
     * Maps book pojo to schema
     *
     * @param bookPojo
     * @return
     */
    private com.soap.jaxb.Book mapBookPojoToSchema(Book bookPojo) {
        com.soap.jaxb.Book bookSchema = new com.soap.jaxb.Book();

        bookSchema.setIsbn(bookPojo.getIsbn());
        bookSchema.setTitle(bookPojo.getTitle());
        bookSchema.setAuthor(bookPojo.getAuthor());
        bookSchema.setCategory(bookPojo.getCategory());
        bookSchema.setPrice(bookPojo.getPrice());
        bookSchema.setStock(bookPojo.getStock());

        return bookSchema;
    }

    /**
     * Accept soap request for getAllBooksRequest and return the soap response
     *
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBooksRequest")
    @ResponsePayload
    public GetAllBooksResponse getAllBooksRequest(@RequestPayload GetAllBooksRequest request) {
        log.info("Received find all books request");
        List<Book> books = bookService.findAllBooks();

        log.info("Returning find all books response");
        return mapAllBooks(books);
    }

    /**
     * Maps all books from schema to response object
     *
     * @param books
     * @return
     */
    private GetAllBooksResponse mapAllBooks(List<Book> books) {
        GetAllBooksResponse response = new GetAllBooksResponse();
        for (Book book : books) {
            com.soap.jaxb.Book mappedBook = mapBookPojoToSchema(book);
            response.getBook().add(mappedBook);
        }
        return response;
    }

    /**
     * Accept soap request for deleteBookRequest and return the soap response
     *
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteBookRequest")
    @ResponsePayload
    public DeleteBookResponse deleteBookRequest(@RequestPayload DeleteBookRequest request) {
        log.info("Received delete book request: {}", request.getIsbn());
        Status status = bookService.deleteBookByIsbn(request.getIsbn());

        DeleteBookResponse response = new DeleteBookResponse();
        response.setStatus(mapStatus(status));

        log.info("Returning delete book request: {}", request.getIsbn() + " " + status);
        return response;
    }

    /**
     * Maps status enum of delete status
     *
     * @param status
     * @return
     */
    private com.soap.jaxb.Status mapStatus(Status status) {
       return status == Status.FAILURE ? com.soap.jaxb.Status.FAILURE : com.soap.jaxb.Status.SUCCESS;
    }

    //TODO:add xml validation for request
    //TODO:add update book?
    //TODO:fix security
    //TODO:add more tests
}
