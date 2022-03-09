package dom.soapexample.service;

import dom.soapexample.model.Book;
import dom.soapexample.model.Status;
import dom.soapexample.repository.BookRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    /**
     * Finds book by title
     *
     * @param title
     * @return
     */
    @Override
    public Book findBook(@NonNull String title) {
        return BookRepository.books.get(title);
    }

    /**
     * finds all books
     *
     * @return
     */
    @Override
    public List<Book> findAllBooks() {
        return BookRepository.books.entrySet().stream()
                .map(book -> book.getValue()).collect(Collectors.toList());
    }

    /**
     * deletes book by isbn and returns status
     *
     * @param isbn
     * @return
     */
    @Override
    public Status deleteBookByIsbn(Long isbn) {
        for (Map.Entry<String, Book> entry : BookRepository.books.entrySet()) {
            if (entry.getValue().getIsbn() == isbn) {
                BookRepository.books.remove(entry);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

}
