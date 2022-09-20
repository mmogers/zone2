package lv.marmog.zone2.zone2.services.implementation;

import lv.marmog.zone2.zone2.DTO.AuthorDTO;
import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.mappers.AuthorMapper;
import lv.marmog.zone2.zone2.mappers.BookMapper;
import lv.marmog.zone2.zone2.models.Book;
import lv.marmog.zone2.zone2.models.errors.BookAlreadyExistsException;
import lv.marmog.zone2.zone2.models.errors.BookNotFoundException;
import lv.marmog.zone2.zone2.repositories.BookRepository;
import lv.marmog.zone2.zone2.services.interfaces.AuthorService;
import lv.marmog.zone2.zone2.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    /**
     *
     * @param book
     * @return
     */
    @Override
    public BookDTO addBook(BookDTO book) {

        if (getBookById(book.getBookCode()) == null) {

            AuthorDTO authorDTO = authorService.getAuthorById(book.getAuthor().getAuthorId());

            Book bookToSave = mapper.DTOToEntity(book, authorMapper.DTOToEntity(authorDTO));

            bookRepository.save(bookToSave);

            return mapper.entityToDTO(bookToSave);
        } else {
            throw new BookAlreadyExistsException(book.getBookCode());
        }

    }

    /**
     *
     * @return
     */
    @Override
    public List<BookDTO> getBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> booksDTO = books.stream()
                .map(book -> mapper.entityToDTO(book))
                .collect(Collectors.toList());

        return booksDTO;
    }

    /**
     *
     * @param BookId
     * @return
     */
    @Override
    public BookDTO getBookById(Integer BookId) {

            Optional<Book> book = bookRepository.findById(BookId);

            if (book.isPresent()) {
                return mapper.entityToDTO(book.get());
            } else {
                return null;
            }

    }

    /**
     *
     * @param book
     * @param bookId
     * @return
     */
    @Override
    public BookDTO updateBook(BookDTO book, Integer bookId) {

        BookDTO existingBook = getBookById(bookId);

        if (existingBook != null) {

            existingBook.setBookName(book.getBookName());
            existingBook.setAuthor(book.getAuthor());

            AuthorDTO authorDTO = authorService.getAuthorById(existingBook.getAuthor().getAuthorId());

            Book bookToSave = mapper.DTOToEntity(existingBook, authorMapper.DTOToEntity(authorDTO));

            bookRepository.save(bookToSave);

            existingBook = mapper.entityToDTO(bookToSave);
        }
        return existingBook;
    }

    /**
     *
     * @param bookId
     */
    @Override
    public void deleteBook(Integer bookId) {
        if (getBookById(bookId) != null) {

            BookDTO book = getBookById(bookId);

            AuthorDTO authorDTO = authorService.getAuthorById(book.getAuthor().getAuthorId());

            bookRepository.delete(mapper.DTOToEntity(getBookById(bookId), authorMapper.DTOToEntity(authorDTO)));
        }
        else {
            throw new BookNotFoundException(bookId);
        }
    }
    @Override
        public List<BookDTO> getBooksByName(String name){
        List<Book> books=  bookRepository.getBooksByName(name).orElseThrow();
        List <BookDTO> booksToReturn = books.stream().map(book -> mapper.entityToDTO(book)).collect(Collectors.toList());
        return booksToReturn;


    }
}
