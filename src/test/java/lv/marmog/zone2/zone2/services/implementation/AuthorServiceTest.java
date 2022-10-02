/*
package lv.marmog.zone2.zone2.services.implementation;

import lv.marmog.zone2.zone2.DTO.AuthorDTO;
import lv.marmog.zone2.zone2.models.Author;
import lv.marmog.zone2.zone2.repositories.AuthorRepository;
import lv.marmog.zone2.zone2.services.interfaces.AuthorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AuthorServiceTest {

    private static AuthorService service;
    private static AuthorRepository repository;

    @BeforeAll
    static void init() {
        service = new AuthorServiceImpl();
        repository = Mockito.mock(AuthorRepository.class);
        ReflectionTestUtils.setField(service, "authorRepository", repository);
    }


    @Test
    void getAuthorByIdTest() {
        AuthorDTO authorDTO = new AuthorDTO(909 , "Marina Mogers");


     */
/*  when(repository.findById(909)).thenReturn(Optional.of(authorDTO));
       Optional<Author> returnedAuthor = service.getAuthorById(909);
       returnedAuthor.ifPresent(author -> assertEquals("Marina Mogers" , author.getAuthorName()));*//*

    }





    @Test
    void addAuthor() {
    }

    @Test
    void getAuthors() {
    }



    @Test
    void deleteAuthor() {
    }

    @Test
    void getAuthorByName() {
    }
}*/
