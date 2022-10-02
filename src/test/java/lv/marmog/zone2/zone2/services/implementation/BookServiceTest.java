package lv.marmog.zone2.zone2.services.implementation;

import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.mappers.BookMapper;
import lv.marmog.zone2.zone2.models.Book;
import lv.marmog.zone2.zone2.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private Book book;
    private BookDTO bookDTO;
    private List<Book> bookList;
    private List<BookDTO> bookDTOList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    void init() {
        bookDTO = createBookDTO();
        book = createBook();
        bookDTOList = createBookDTOList();
        bookList = createBookList();
    }

    private List<BookDTO> createBookDTOList() {
        List<BookDTO> listDTO = new ArrayList<>();
        BookDTO bookDTO1 = createBookDTO();
        BookDTO bookDTO2 = createBookDTO();
        listDTO.add(bookDTO1);
        listDTO.add(bookDTO2);
        return listDTO;
    }
    private BookDTO createBookDTO() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookCode(10);
        bookDTO.setBookName("Name1");
        bookDTO.setAuthor("Author1");
        bookDTO.setLocation("location1");
        bookDTO.setIsRead(true);
        return bookDTO;
    }

    private List<Book> createBookList() {
        List<Book> list = new ArrayList<>();
        Book book1 = createBook();
        Book book2 = createBook();
        list.add(book1);
        list.add(book2);
        return list;
    }

    private Book createBook() {
        Date date = new Date();
        Book book = new Book();
        book.setAuthor("Author1");
        book.setBookCode(10);
        book.setBookName("Name1");
        book.setAddedOn(date);
        book.setIsRead(true);
        book.setLocation("location1");
        return book;
    }




    @Test
    void addBook() {
    }

    @Test
    void getBooks() {
        when(bookRepository.findAll()).thenReturn(bookList);
        when(mapper.BookToDTO(book)).thenReturn(bookDTO);
        List<BookDTO> books = bookService.getBooks();
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookByCode() {
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


