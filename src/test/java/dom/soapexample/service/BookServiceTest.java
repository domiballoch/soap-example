package dom.soapexample.service;

import dom.soapexample.model.Book;
import dom.soapexample.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookServiceImpl bookService;

    @Test
    public void shouldFindOneBookByTitle() {
        final Book result = bookService.findBook("Frankenstein");
        assertThat(result.getTitle()).isEqualTo("Frankenstein");
        assertThat(result.getAuthor()).isEqualTo("Mary Shelley");
        assertThat(result.getCategory()).isEqualTo(com.soap.jaxb.Category.HORROR);
        assertThat(result.getPrice()).isEqualTo(new BigDecimal("5.39"));
        assertThat(result.getStock()).isEqualTo(100);
    }

    @Test
    public void shouldFindAllBooksBy() {
        final List<Book> result = bookService.findAllBooks();
        assertThat(result).hasSize(12);
    }

    @Test
    public void shouldDeleteOneBook() {
        bookService.deleteBookByIsbn(1L);
        assertThat(BookRepository.books.get(1)).isNull();
    }
}
