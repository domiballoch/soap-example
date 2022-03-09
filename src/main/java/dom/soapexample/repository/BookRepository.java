package dom.soapexample.repository;

import dom.soapexample.model.Book;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class BookRepository {

    public static final Map<String, Book> books = new HashMap<>();

    /**
     * Dummy repo data
     */
    @PostConstruct
    public void initData() {
        Book book1 = Book.builder()
                .isbn(1L)
                .title("1984")
                .author("George Orwell")
                .category(com.soap.jaxb.Category.FICTION)
                .price(new BigDecimal("5.99"))
                .stock(100).build();

        Book book2 = Book.builder()
                .isbn(2L)
                .title("Harry Potter")
                .author("J.K. Rowling")
                .category(com.soap.jaxb.Category.FANTASY)
                .price(new BigDecimal("4.50"))
                .stock(100).build();

        Book book3 = Book.builder()
                .isbn(3L)
                .title("The Lord of the Rings")
                .author("J.R.R. Tolkien")
                .category(com.soap.jaxb.Category.FANTASY)
                .price(new BigDecimal("16.33"))
                .stock(100).build();

        Book book4 = Book.builder()
                .isbn(4L)
                .title("The Lion, The Witch, and the Wardrobe")
                .author("C.S. Lewis")
                .category(com.soap.jaxb.Category.KIDS)
                .price(new BigDecimal("6.99"))
                .stock(100).build();

        Book book5 = Book.builder()
                .isbn(5L)
                .title("To Kill a Mockingbird")
                .author("Harper Lee")
                .category(com.soap.jaxb.Category.FICTION)
                .price(new BigDecimal("6.55"))
                .stock(100).build();

        Book book6 = Book.builder()
                .isbn(6L)
                .title("Wuthering Heights")
                .author("Emily Bronte")
                .category(com.soap.jaxb.Category.ROMANCE)
                .price(new BigDecimal("2.25"))
                .stock(100).build();

        Book book7 = Book.builder()
                .isbn(7L)
                .title("Frankenstein")
                .author("Mary Shelley")
                .category(com.soap.jaxb.Category.HORROR)
                .price(new BigDecimal("5.39"))
                .stock(100).build();

        Book book8 = Book.builder()
                .isbn(8L)
                .title("Charlotte's Web")
                .author("E.B. White")
                .category(com.soap.jaxb.Category.KIDS)
                .price(new BigDecimal("5.94"))
                .stock(100).build();

        Book book9 = Book.builder()
                .isbn(9L)
                .title("Of Mice and Men")
                .author("John Stienbeck")
                .category(com.soap.jaxb.Category.FICTION)
                .price(new BigDecimal("5.99"))
                .stock(100).build();

        Book book10 = Book.builder()
                .isbn(10L)
                .title("The Hitchhikers Guide to the Galaxy")
                .author("Douglas Adams")
                .category(com.soap.jaxb.Category.SCIENCE_FICTION)
                .price(new BigDecimal("4.99"))
                .stock(100).build();

        Book book11 = Book.builder()
                .isbn(11L)
                .title("The Great Gatsby")
                .author("F. Scott Fitzgerald")
                .category(com.soap.jaxb.Category.FICTION)
                .price(new BigDecimal("6.99"))
                .stock(100).build();

        Book book12 = Book.builder()
                .isbn(12L)
                .title("Little Women")
                .author("Louisa May Alcott")
                .category(com.soap.jaxb.Category.FICTION)
                .price(new BigDecimal("8.77"))
                .stock(100).build();

        books.put(book1.getTitle(), book1);
        books.put(book2.getTitle(), book2);
        books.put(book3.getTitle(), book3);
        books.put(book4.getTitle(), book4);
        books.put(book5.getTitle(), book5);
        books.put(book6.getTitle(), book6);
        books.put(book7.getTitle(), book7);
        books.put(book8.getTitle(), book8);
        books.put(book9.getTitle(), book9);
        books.put(book10.getTitle(), book10);
        books.put(book11.getTitle(), book11);
        books.put(book12.getTitle(), book12);
    }

}
