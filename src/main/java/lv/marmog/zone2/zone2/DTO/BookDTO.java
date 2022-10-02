package lv.marmog.zone2.zone2.DTO;

import lombok.*;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class BookDTO {

    private Integer bookCode;

    public String bookName;

    private String author;

    private String location;

    private Boolean isRead;

}
