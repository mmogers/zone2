package lv.marmog.zone2.zone2.services.interfaces;

import lv.marmog.zone2.zone2.DTO.AuthorDTO;


import java.util.List;

public interface AuthorService {

    public AuthorDTO addAuthor(AuthorDTO author);

    public List<AuthorDTO> getAuthors();

    public AuthorDTO getAuthorById(Integer authorId);

    public void deleteAuthor(Integer authorId);

    public List<AuthorDTO> getAuthorByName (String authorName);


}
