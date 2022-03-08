package dom.soapexample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"isbn"})
@ToString(of = {"isbn", "category", "title", "author", "price", "stock"})
public class Book implements Serializable {

    private Long isbn;
    private com.soap.jaxb.Category category;
    private String title;
    private String author;
    private BigDecimal price;
    private BigInteger stock;
}
