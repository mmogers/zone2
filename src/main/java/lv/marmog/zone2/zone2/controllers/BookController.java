package lv.marmog.zone2.zone2.controllers;

import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.models.Book;
import lv.marmog.zone2.zone2.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        try {
            List<BookDTO> books = bookService.getBooks();

            if ( books.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookDTO> getBook(@Valid @PathVariable Integer id) {
        BookDTO book = bookService.getBookById(id);

        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/book-create")
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<BookDTO> createNewBook(@Valid @RequestBody BookDTO book) {
        try {
            BookDTO bookSaved = bookService.addBook(book);

            if (bookSaved != null) {
                return new ResponseEntity<>(bookSaved, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/book/{id}")
    public BookDTO updateBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable @Min(1) Integer id) {
        BookDTO bookUpdated = null;

        try {
            bookUpdated = bookService.updateBook(bookDTO, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookUpdated;
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable @Min(1) Integer id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@GetMapping("/books-by-author")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@RequestParam String name) {
        try {
            List<BookDTO> books = bookService.getBooksByName(name);

            if ( books.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


}
