package lv.marmog.zone2.zone2.mappers;

import lv.marmog.zone2.zone2.DTO.BookDTO;

import lv.marmog.zone2.zone2.mappers.mapperInterface.BookMapperInterface;
import lv.marmog.zone2.zone2.models.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper implements BookMapperInterface {

    public BookDTO BookToDTO(Book book)  {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setBookCode(book.getBookCode());
        bookDTO.setBookName(book.getBookName());
        bookDTO.setLocation(book.getLocation());
        bookDTO.setIsRead(book.getIsRead());
        return bookDTO;
    }


    public Book DTOToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setBookCode(bookDTO.getBookCode());
        book.setBookName(bookDTO.getBookName());
        book.setLocation(bookDTO.getLocation());
        book.setIsRead(bookDTO.getIsRead());
        return book;
    }
}
