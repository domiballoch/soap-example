package dom.soapexample.repository;

import com.soap.jaxb.Book;
import com.soap.jaxb.Category;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class BookRepository {

        private static final Map<String, Book> books = new HashMap<>();

    /**
     * Dummy repo data
     */
    @PostConstruct
        public void initData() {
            Book book1 = new Book();
            book1.setTitle("1984");
            book1.setAuthor("George Orwell");
            book1.setCategory(Category.FICTION);
            book1.setPrice(new BigDecimal("5.99"));

            Book book2 = new Book();
            book1.setTitle("Harry Potter");
            book1.setAuthor("J.K. Rowling");
            book1.setCategory(Category.FANTASY);
            book1.setPrice(new BigDecimal("4.50"));

            Book book3 = new Book();
            book1.setTitle("The Lord of the Rings");
            book1.setAuthor("J.R.R. Tolkien");
            book1.setCategory(Category.FANTASY);
            book1.setPrice(new BigDecimal("16.33"));

            Book book4 = new Book();
            book1.setTitle("The Lion, The Witch, and the Wardrobe");
            book1.setAuthor("C.S. Lewis");
            book1.setCategory(Category.KIDS);
            book1.setPrice(new BigDecimal("6.99"));

            Book book5 = new Book();
            book1.setTitle("To Kill a Mockingbird");
            book1.setAuthor("Harper Lee");
            book1.setCategory(Category.FICTION);
            book1.setPrice(new BigDecimal("6.55"));

            Book book6 = new Book();
            book1.setTitle("Wuthering Heights");
            book1.setAuthor("Emily Bronte");
            book1.setCategory(Category.ROMANCE);
            book1.setPrice(new BigDecimal("2.25"));

            Book book7 = new Book();
            book1.setTitle("Frankenstein");
            book1.setAuthor("Mary Shelley");
            book1.setCategory(Category.HORROR);
            book1.setPrice(new BigDecimal("5.39"));

            Book book8 = new Book();
            book1.setTitle("Charlotte's Web");
            book1.setAuthor("E.B. White");
            book1.setCategory(Category.KIDS);
            book1.setPrice(new BigDecimal("5.94"));

            Book book9 = new Book();
            book1.setTitle("Of Mice and Men");
            book1.setAuthor("John Stienbeck");
            book1.setCategory(Category.FICTION);
            book1.setPrice(new BigDecimal("5.99"));

            Book book10 = new Book();
            book1.setTitle("The Hitchhikers Guide to the Galaxy");
            book1.setAuthor("Douglas Adams");
            book1.setCategory(Category.SCIENCE_FICTION);
            book1.setPrice(new BigDecimal("4.99"));

            Book book11 = new Book();
            book1.setTitle("The Great Gatsby");
            book1.setAuthor("F. Scott Fitzgerald");
            book1.setCategory(Category.FICTION);
            book1.setPrice(new BigDecimal("6.99"));

            Book book12 = new Book();
            book1.setTitle("Little Women");
            book1.setAuthor("Louisa May Alcott");
            book1.setCategory(Category.FICTION);
            book1.setPrice(new BigDecimal("8.77"));

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

        public Book findBook(@NonNull String title) {
            return books.get(title);
        }
}
