package lv.marmog.zone2.zone2.mappers;

import lv.marmog.zone2.zone2.DTO.AuthorDTO;
import lv.marmog.zone2.zone2.models.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorMapper {

    public AuthorDTO entityToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setAuthorId(author.getAuthorId());
        authorDTO.setAuthorName(author.getAuthorName());

        return authorDTO;
    }

    public Author DTOToEntity(AuthorDTO authorDTO) {
        Author author = new Author();

        author.setAuthorId(authorDTO.getAuthorId());
        author.setAuthorName(authorDTO.getAuthorName());

        return author;
    }
}
