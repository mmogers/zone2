package lv.marmog.zone2.zone2.mappers;

import lv.marmog.zone2.zone2.DTO.BookDTO;

import lv.marmog.zone2.zone2.models.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public BookDTO BookToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setBookCode(book.getBookCode());
        bookDTO.setBookName(book.getBookName());
        bookDTO.setAddedOn(book.getAddedOn());
        return bookDTO;
    }


    public Book DTOToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setBookCode(bookDTO.getBookCode());
        book.setBookName(bookDTO.getBookName());
        book.setAddedOn(bookDTO.getAddedOn());
        return book;
    }
}
