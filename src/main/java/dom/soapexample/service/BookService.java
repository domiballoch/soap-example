package dom.soapexample.service;

import dom.soapexample.model.Book;
import dom.soapexample.model.Status;
import lombok.NonNull;

import java.util.List;

public interface BookService {

    Book findBook(@NonNull String title);

    List<Book> findAllBooks();

    Status deleteBookByIsbn(Long isbn);
}
