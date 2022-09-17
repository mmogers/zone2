package lv.marmog.zone2.zone2.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthorDTO {

    private static final long serialVersionUID = 1L;

    private Integer authorId;

    private String authorName;

    public AuthorDTO(Integer authorId, String authorName) {
        this.authorId = authorId;
        this.authorName = authorName;
    }
}
