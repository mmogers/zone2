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


    /* to get all the authors */

    @GetMapping("/author")
    public ResponseEntity<List<AuthorDTO>> getAllauthors() {
        try {
            List<AuthorDTO> authorsDTO = authorService.getAuthors();

            if (authorsDTO.isEmpty() || authorsDTO.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(authorsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /* to get a particular author */

    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Integer id) {
        AuthorDTO authorDTO = authorService.getAuthorById(id);

        if (authorDTO != null) {
            return new ResponseEntity<AuthorDTO>(authorDTO, HttpStatus.OK);
        }

        return new ResponseEntity<AuthorDTO>(HttpStatus.NOT_FOUND);
    }


    /* Controller to handle adding a new author */

    @PostMapping("/author")
    public ResponseEntity<AuthorDTO> saveAuthor(@RequestBody AuthorDTO authorDTO) {
        try {
            return new ResponseEntity<AuthorDTO>(authorService.addAuthor(authorDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<AuthorDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /* Controller to handle updation of a author */

    @PutMapping("/author/{authorId}")
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO, @PathVariable Integer authorId) {
        try {
            //get author record to be updated
            AuthorDTO authorToUpdate = authorService.getAuthorById(authorId);

            //set the values
            authorToUpdate.setAuthorName(authorDTO.getAuthorName());

            //update the changes
            return new ResponseEntity<AuthorDTO>(authorService.addAuthor(authorToUpdate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<AuthorDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /* Controller to handle deleting a author */

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Integer id) {
        try {
            authorService.deleteAuthor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
