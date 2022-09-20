package lv.marmog.zone2.zone2.controllers;

import lv.marmog.zone2.zone2.DTO.AuthorDTO;
import lv.marmog.zone2.zone2.models.Author;
import lv.marmog.zone2.zone2.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        try {
            List<AuthorDTO> authorsDTO = authorService.getAuthors();

            if (authorsDTO.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(authorsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Integer id) {
        AuthorDTO authorDTO = authorService.getAuthorById(id);

        if (authorDTO != null) {
            return new ResponseEntity<AuthorDTO>(authorDTO, HttpStatus.OK);
        }

        return new ResponseEntity<AuthorDTO>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/author-create")
    public ResponseEntity<AuthorDTO> createNewAuthor(@RequestBody AuthorDTO authorDTO) {
        try {
            return new ResponseEntity<AuthorDTO>(authorService.addAuthor(authorDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<AuthorDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/author/{authorId}")
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO, @PathVariable Integer authorId) {
        try {

            AuthorDTO authorToUpdate = authorService.getAuthorById(authorId);

            authorToUpdate.setAuthorName(authorDTO.getAuthorName());

            return new ResponseEntity<AuthorDTO>(authorService.addAuthor(authorToUpdate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<AuthorDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Integer id) {
        try {
            AuthorDTO authorToUpdate = authorService.getAuthorById(id);
            if(authorToUpdate==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            authorService.deleteAuthor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping ("/authors-find-by-name")
     public ResponseEntity <List<AuthorDTO>> getAuthorWithName (@RequestParam String name) {
        try {
            List<AuthorDTO> authorsDTO = authorService.getAuthorByName(name);

            if (authorsDTO.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(authorsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
