package lv.marmog.zone2.zone2.services.implementation;

import lv.marmog.zone2.zone2.DTO.AuthorDTO;
import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.mappers.AuthorMapper;
import lv.marmog.zone2.zone2.mappers.BookMapper;
import lv.marmog.zone2.zone2.models.Author;
import lv.marmog.zone2.zone2.models.Book;
import lv.marmog.zone2.zone2.repositories.BookRepository;
import lv.marmog.zone2.zone2.services.interfaces.AuthorService;
import lv.marmog.zone2.zone2.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookMapper mapper;

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public BookDTO addBook(BookDTO book) {
        BookDTO bookDTO = null;

        // check if a book with the same bookCode exists in table
        BookDTO bookInTable = getBookById(book.getBookCode());

        if (bookInTable == null) {

            // get author of that particular id
            AuthorDTO authorDTO = authorService.getAuthorById(book.getAuthorId());

            // convert dto to entity
            Book bookToSave = mapper.DTOToEntity(book, authorMapper.DTOToEntity(authorDTO));

            // save the entity
            bookRepository.save(bookToSave);

            // convert entity to dto
            bookDTO = mapper.entityToDTO(bookToSave);
        }

        return bookDTO;
    }

    @Override
    public List<BookDTO> getBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> booksToReturn = books.stream()
                .map(book -> mapper.entityToDTO(book))
                .collect(Collectors.toList());

        return booksToReturn;
    }

    @Override
    public BookDTO getBookById(Integer id) {
        BookDTO bookToReturn = null;

        try {
            Optional<Book> bookFound = bookRepository.findById(id);

            if (bookFound.isPresent()) {
                bookToReturn = mapper.entityToDTO(bookFound.get());
            }

        } catch (NoSuchElementException e) {
            System.out.println("No book found in the table having this book code");
            e.printStackTrace();
        }
        return bookToReturn;
    }

    @Override
    public BookDTO updateBook(BookDTO book, Integer id) {
        // get book to be updated
        BookDTO bookInTable = getBookById(id);

        if (bookInTable != null) {

            //update the attributes
            bookInTable.setBookName(book.getBookName());
            bookInTable.setAuthorId(book.getAuthorId());

            // get author of that particular id
            AuthorDTO authorDTO = authorService.getAuthorById(bookInTable.getAuthorId());

            // convert dto to entity
            Book bookEntity = mapper.DTOToEntity(bookInTable, authorMapper.DTOToEntity(authorDTO));

            // save the entity
            bookRepository.save(bookEntity);

            // convert entity to dto
            bookInTable = mapper.entityToDTO(bookEntity);
        }
        return bookInTable;
    }

    @Override
    public void deleteBook(Integer id) {
        if (getBookById(id) != null) {

            BookDTO bookInTable = getBookById(id);

            // get author of that particular id
            AuthorDTO authorDTO = authorService.getAuthorById(bookInTable.getAuthorId());

            bookRepository.delete(mapper.DTOToEntity(getBookById(id), authorMapper.DTOToEntity(authorDTO)));
        }
    }
}
