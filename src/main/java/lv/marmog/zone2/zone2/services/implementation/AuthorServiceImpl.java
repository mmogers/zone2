package lv.marmog.zone2.zone2.services.implementation;

import lv.marmog.zone2.zone2.DTO.AuthorDTO;
import lv.marmog.zone2.zone2.mappers.AuthorMapper;
import lv.marmog.zone2.zone2.models.Author;
import lv.marmog.zone2.zone2.models.errors.AuthorAlreadyExistException;
import lv.marmog.zone2.zone2.models.errors.AuthorNotFoundException;
import lv.marmog.zone2.zone2.repositories.AuthorRepository;
import lv.marmog.zone2.zone2.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper mapper;

    /**
     *
     * @param authorDTO
     * @return
     */
    @Override
    public AuthorDTO addAuthor(AuthorDTO authorDTO) {
        Author author = mapper.DTOToEntity(authorDTO);
        if(authorRepository.existsByAuthorName(authorDTO.getAuthorName())){
            throw new AuthorAlreadyExistException(author);
        }
        authorRepository.save(author);
        return mapper.entityToDTO(author);
    }

    /**
     *
     * @return
     */
    @Override
    public List<AuthorDTO> getAuthors() {
        List<Author> authors = authorRepository.findAll();
        List <AuthorDTO> authorsDTO = authors.stream().map(
                author -> mapper.entityToDTO(author)).collect(Collectors.toList());
        return authorsDTO;
    }

    /**
     *
     * @param authorId
     * @return
     */
    @Override
    public AuthorDTO getAuthorById(Integer authorId) {

        Optional<Author> foundAuthor = authorRepository.findById(authorId);
        if(foundAuthor.isEmpty()){
            throw new AuthorNotFoundException(authorId);
        }

        return mapper.entityToDTO(foundAuthor.get());
    }


    /**
     *
     * @param authorId
     */
    @Override
    public void deleteAuthor(Integer authorId) {
        if(authorRepository.existsById(authorId)){
            authorRepository.deleteById(authorId);
        }
        else{
            throw new AuthorNotFoundException(authorId);
        }
    }

    /**
     *
     * @param authorName
     * @return
     */
    @Override
    public List<AuthorDTO> getAuthorByName(String authorName){
        List<Author> authorsWithTheName = authorRepository.findByName(authorName);
        List<AuthorDTO> authorsToReturn = authorsWithTheName.stream().map(author -> mapper.entityToDTO(author)).collect(Collectors.toList());
        return authorsToReturn;
    }
}
