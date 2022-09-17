package lv.marmog.zone2.zone2.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookDTO {

    private Integer bookCode;

    public String bookName;

    private Date addedOn;

    private Integer authorId;

    public BookDTO(Integer bookCode, String bookName, Date addedOn, Integer authorId) {
        super();
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.addedOn = addedOn;
        this.authorId = authorId;
    }

}
