package lv.marmog.zone2.zone2.services.implementation;

import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.mappers.BookMapper;
import lv.marmog.zone2.zone2.models.Book;
import lv.marmog.zone2.zone2.models.errors.BookNotFoundException;
import lv.marmog.zone2.zone2.repositories.BookRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class BookServiceTest {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;

    @Spy
    BookMapper mapper;



    private List <Book> bookList (){
        List<Book> testList  = new ArrayList<>();
        Date date = new Date();
        testList.add(new Book(1, 100, "Name1", date, "Author1", true, "home"));
        testList.add(new Book(2, 200, "Name2", date, "Author2", false, "home"));
        return testList;
    }
    private List <BookDTO> bookDTOList (){
        List<BookDTO> testList  = new ArrayList<>();
        testList.add(new BookDTO(100, "Name1", "Author1", "home", true));
        testList.add(new BookDTO(200, "Name2", "Author2", "home", false));
        return testList;
    }
    Date date = new Date();
    private Book book = new Book(1, 100, "Name1", date, "Author1", true, "home");
    private BookDTO bookDTO = new BookDTO(100, "Name1", "Author1", "home", true);


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addBook() {
    }

    @Test
    void getBooks() {
        List<Book> testList = bookList();
        when(bookRepository.findAll()).thenReturn(testList);
        when(mapper.BookToDTO(book)).thenReturn(bookDTO);
        List<BookDTO> books = bookService.getBooks();
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookByCode() {

        when(bookRepository.getBookByCode(anyInt())).thenReturn(Optional.of(book));
        when(mapper.BookToDTO(book)).thenReturn(bookDTO);
        BookDTO bookByCode = bookService.getBookByCode(book.getBookCode());
        assertEquals(bookDTO.getBookCode(), bookByCode.getBookCode());
        assertEquals(bookDTO.getBookName(), bookByCode.getBookName());
        assertEquals(bookDTO.getAuthor(), bookByCode.getAuthor());
        assertEquals(bookDTO.getBookName(), bookByCode.getBookName());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void getBooksByName() {
    }

    @Test
    void setBookAsRead() {
    }
}


