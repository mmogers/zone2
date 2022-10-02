package lv.marmog.zone2.zone2.services.implementation;


import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.mappers.BookMapper;
import lv.marmog.zone2.zone2.models.Book;
import lv.marmog.zone2.zone2.models.errors.BookAlreadyExistsException;
import lv.marmog.zone2.zone2.models.errors.BookNotFoundException;
import lv.marmog.zone2.zone2.repositories.BookRepository;
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
    private BookMapper mapper;

    /**
     *
     * @param book
     * @return
     */
    @Override
    public BookDTO addBook(BookDTO book) {

        if (getBookById(book.getBookCode()) == null) {


            Book bookToSave = mapper.DTOToBook(book);

            bookRepository.save(bookToSave);

            return mapper.BookToDTO(bookToSave);
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
                .map(book -> mapper.BookToDTO(book))
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
                return mapper.BookToDTO(book.get());
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

            Book bookToSave = mapper.DTOToBook(existingBook);

            bookRepository.save(bookToSave);

            existingBook = mapper.BookToDTO(bookToSave);
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

            bookRepository.delete(mapper.DTOToBook(getBookById(bookId)));
        }
        else {
            throw new BookNotFoundException(bookId);
        }
    }
    @Override
        public List<BookDTO> getBooksByName(String name){
        List<Book> books=  bookRepository.getBooksByName(name).orElseThrow();
        List <BookDTO> booksToReturn = books.stream().map(book -> mapper.BookToDTO(book)).collect(Collectors.toList());
        return booksToReturn;

    }
}
