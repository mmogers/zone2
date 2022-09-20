package lv.marmog.zone2.zone2.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.marmog.zone2.zone2.models.Author;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookDTO {

    private Integer bookCode;

    public String bookName;

    private Date addedOn;

    private Author author;

    public BookDTO(Integer bookCode, String bookName, Date addedOn, Author author) {
        super();
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.addedOn = addedOn;
        this.author = author;
    }

}
