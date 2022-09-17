package lv.marmog.zone2.zone2.services.interfaces;

import lv.marmog.zone2.zone2.models.Author;

import java.util.List;

public interface AuthorService {

    public Author addAuthor(Author author);

    public List<Author> getAuthors();

    public Author getAuthorById(Integer authorId);

    public void deleteAuthor(Integer authorId);

}
