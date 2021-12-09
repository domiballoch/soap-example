package dom.soapexample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"isbn"})
@ToString(of = {"isbn", "category", "title", "author", "price", "stock"})
public class Book implements Serializable {

    //for when service is implemented
    private Long isbn;
    private Category category;
    private String title;
    private String author;
    private BigDecimal price;
    private int stock;
}
