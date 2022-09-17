package lv.marmog.zone2.zone2.services.implementation;

import lv.marmog.zone2.zone2.DTO.AuthorDTO;
import lv.marmog.zone2.zone2.mappers.AuthorMapper;
import lv.marmog.zone2.zone2.models.Author;
import lv.marmog.zone2.zone2.repositories.AuthorRepository;
import lv.marmog.zone2.zone2.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper mapper;

    @Override
    public AuthorDTO addAuthor(AuthorDTO authorDTO) {
        Author author = mapper.DTOToEntity(authorDTO);
        authorRepository.save(author);
        return mapper.entityToDTO(author);
    }

    @Override
    public List<AuthorDTO> getAuthors() {
        List<Author> authors = authorRepository.findAll();
        List <AuthorDTO> authorsDTO = authors.stream().map(
                author -> mapper.entityToDTO(author)).collect(Collectors.toList());
        return authorsDTO;
    }

    @Override
    public AuthorDTO getAuthorById(Integer authorId) {
        AuthorDTO authorDTO = null;
        Optional<Author> foundBook = authorRepository.findById(authorId);
        if(foundBook.isEmpty()){
            throw new NoSuchElementException();
        }

        return mapper.entityToDTO(foundBook.get());
    }

    @Override
    public void deleteAuthor(Integer authorId) {
        if(authorRepository.existsById(authorId)){
            authorRepository.deleteById(authorId);
        }
        else{
            throw new NoSuchElementException();
        }
    }
}
