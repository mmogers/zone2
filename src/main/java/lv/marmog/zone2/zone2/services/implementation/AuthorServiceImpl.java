package lv.marmog.zone2.zone2.services.implementation;

import lv.marmog.zone2.zone2.models.Author;
import lv.marmog.zone2.zone2.repositories.AuthorRepository;
import lv.marmog.zone2.zone2.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAuthors() {
        return null;
    }

    @Override
    public Author getAuthorById(Integer authorId) {
        return null;
    }

    @Override
    public void deleteAuthor(Integer authorId) {

    }


}
