package lv.marmog.zone2.zone2.services.implementation;



import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.mappers.BookMapper;
import lv.marmog.zone2.zone2.models.Book;
import lv.marmog.zone2.zone2.models.constants.Constants;
import lv.marmog.zone2.zone2.models.errors.BookAlreadyExistsException;
import lv.marmog.zone2.zone2.models.errors.BookNotFoundException;
import lv.marmog.zone2.zone2.repositories.BookRepository;
import lv.marmog.zone2.zone2.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {


    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMapper mapper;


    @Override
    public BookDTO addBook(BookDTO book) {

        if (bookRepository.existsByBookCode(book.getBookCode())){
            throw new BookAlreadyExistsException(HttpStatus.CONFLICT, Constants.CODE_ALREADY_EXISTS);
        }
            Book bookToSave = mapper.DTOToBook(book);
            Date date = new Date();
            bookToSave.setAddedOn(date);
            bookRepository.save(bookToSave);
            return mapper.bookToDTO(bookToSave);

        }

    @Override
    public List<BookDTO> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(mapper::bookToDTO)
                .collect(Collectors.toList());

    }


    @Override
    public Optional<BookDTO> getBookByCode(Integer bookCode) {
        if (bookCode <= 0) {

            throw new BookNotFoundException(HttpStatus.BAD_REQUEST, Constants.CODE_NOT_FOUND);
        }
            Optional<BookDTO> bookDTO = bookRepository.getBookByCode(bookCode).flatMap(book ->
                 Optional.ofNullable(mapper.bookToDTO(book)));


            if(!bookDTO.isPresent()){
                throw new BookNotFoundException(HttpStatus.NOT_FOUND, Constants.CODE_NOT_FOUND);
            }
            return bookDTO;
    }


    @Override
    public BookDTO updateBook(BookDTO book, Integer bookCode) {

        Book updateBook = bookRepository.getBookByCode(bookCode).orElseThrow(()-> new BookNotFoundException(HttpStatus.NOT_FOUND, Constants.CODE_NOT_FOUND));
        updateBook.setBookName(book.getBookName());
        updateBook.setAuthor(book.getAuthor());
        updateBook.setLocation(book.getLocation());
        updateBook.setIsRead(book.getIsRead());
        BookDTO returnBook = mapper.bookToDTO(updateBook);
        bookRepository.save(updateBook);
        return returnBook;
    }

    @Override
    public void deleteBook(Integer bookCode) {

     Book book = bookRepository.getBookByCode(bookCode).orElseThrow( ()-> new BookNotFoundException(HttpStatus.NOT_FOUND, Constants.CODE_NOT_FOUND));
     bookRepository.delete(book);

    }
    @Override
        public List<BookDTO> getBooksByAuthor(String author){
        Optional<List<Book>> books=  bookRepository.getBooksByAuthor(author);
        if(!books.isPresent()){
            throw new BookNotFoundException(HttpStatus.NOT_FOUND, Constants.NAME_NOT_FOUND);
        }
        return books.get().stream().map(book -> mapper.bookToDTO(book)).collect(Collectors.toList());

    }
}
