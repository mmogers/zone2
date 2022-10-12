package lv.marmog.zone2.zone2.mappers;

import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lv.marmog.zone2.zone2.DataForTests.book;
import static lv.marmog.zone2.zone2.DataForTests.bookDTO;
import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    private BookMapper bookMapper;

    @BeforeEach
    public void setUp(){
        bookMapper = new BookMapper();
    }

    @Test
    void bookToDTOTest() {
        Book book = book();
        BookDTO bookDTO = bookMapper.bookToDTO(book);
        assertEquals(bookDTO.getBookCode(), book.getBookCode());
        assertEquals(bookDTO.getBookName(), book.getBookName());
        assertEquals(bookDTO.getAuthor(), book.getAuthor());
        //assertEquals(bookDTO.getIsRead(), book.getIsRead());
        assertEquals(bookDTO.getLocation(), book.getLocation());
    }

    @Test
    void DTOToBook() {
        BookDTO bookDTO = bookDTO();
        Book book = bookMapper.DTOToBook(bookDTO);
        assertEquals(book.getBookCode(), bookDTO.getBookCode());
        assertEquals(book.getBookName(),bookDTO.getBookName());
        //assertEquals(book.getIsRead(),bookDTO.getIsRead());
        assertEquals(book.getAuthor(),bookDTO.getAuthor());
        assertEquals(book.getLocation(),bookDTO.getLocation());
    }
}