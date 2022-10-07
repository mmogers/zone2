package lv.marmog.zone2.zone2.services.implementation;



import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.mappers.BookMapper;
import lv.marmog.zone2.zone2.models.Book;
import lv.marmog.zone2.zone2.models.errors.BookAlreadyExists;
import lv.marmog.zone2.zone2.models.errors.BookNotFound;
import lv.marmog.zone2.zone2.repositories.BookRepository;
import lv.marmog.zone2.zone2.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
            throw new BookAlreadyExists(book.getBookCode());
        }
            Book bookToSave = mapper.DTOToBook(book);
            Date date = new Date();
            bookToSave.setAddedOn(date);
            bookRepository.save(bookToSave);
            return mapper.BookToDTO(bookToSave);

        }

    @Override
    public List<BookDTO> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(mapper::BookToDTO)
                .collect(Collectors.toList());

    }


    @Override
    public BookDTO getBookByCode(Integer bookCode) {
            Book book = bookRepository.getBookByCode(bookCode).orElseThrow(()->new BookNotFound(bookCode));
            return  mapper.BookToDTO(book);
    }


    @Override
    public BookDTO updateBook(BookDTO book, Integer bookCode) {

        Book updateBook = bookRepository.getBookByCode(bookCode).orElseThrow(()-> new BookNotFound(bookCode));
        updateBook.setBookName(book.getBookName());
        updateBook.setAuthor(book.getAuthor());
        updateBook.setLocation(book.getLocation());
        updateBook.setIsRead(book.getIsRead());
        BookDTO returnBook = mapper.BookToDTO(updateBook);
        bookRepository.save(updateBook);
        return returnBook;
    }

    @Override
    public void deleteBook(Integer bookCode) {

     Book book = bookRepository.getBookByCode(bookCode).orElseThrow( ()-> new BookNotFound(bookCode));

     bookRepository.delete(book);

    }
    @Override
        public List<BookDTO> getBooksByName(String bookName){
        List<Book> books=  bookRepository.getBooksByName(bookName).orElseThrow(()->new BookNotFound(bookName));
        return books.stream().map(book -> mapper.BookToDTO(book)).collect(Collectors.toList());

    }

    @Override
    public void setBookAsRead(Integer bookCode) {
        BookDTO existingBook = getBookByCode(bookCode);

        if (existingBook != null) {

            Book bookToSave = mapper.DTOToBook(existingBook);
            bookToSave.setIsRead(true);

            bookRepository.save(bookToSave);

        } else{
            throw new BookNotFound(bookCode);
        }
    }
}
