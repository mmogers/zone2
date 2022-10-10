package lv.marmog.zone2.zone2.services.implementation;

import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.mappers.BookMapper;
import lv.marmog.zone2.zone2.models.Book;
import lv.marmog.zone2.zone2.models.errors.BookAlreadyExists;
import lv.marmog.zone2.zone2.models.errors.BookNotFound;
import lv.marmog.zone2.zone2.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookServiceImpl.class})
class BookServiceTest {


    @Autowired
    //@InjectMocks
    BookServiceImpl bookService;

    @MockBean
    BookRepository bookRepository;

    @MockBean
    BookMapper mapper;


    private ArrayList<Book> bookList() {
        ArrayList<Book> testList = new ArrayList<>();
        Date date = new Date();
        testList.add(new Book(1, 100, "Name1", date, "Author1", true, "home"));
        testList.add(new Book(2, 200, "Name2", date, "Author2", false, "home"));
        return testList;
    }

    private ArrayList<BookDTO> bookDTOList() {
        ArrayList<BookDTO> testList = new ArrayList<>();
        testList.add(new BookDTO(100, "Name1", "Author1", "home", true));
        testList.add(new BookDTO(200, "Name2", "Author2", "home", false));
        return testList;
    }

    Date date = new Date();
    private Book book = new Book(1, 100, "Name1", date, "Author1", true, "home");
    private Book anotherBook = new Book(1, 100, "Name1", date, "Author1", true, "home");

    private BookDTO bookDTO = new BookDTO(100, "Name1", "Author1", "home", true);


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addBookAlreadyExistsTest() {
        when(bookRepository.existsByBookCode(anyInt())).thenReturn(true);
        assertThrows(BookAlreadyExists.class, ()-> bookService.addBook(bookDTO));
        verify(bookRepository, times(1)).existsByBookCode(anyInt());
    }

    @Test
    void addBookTest() {
        when(bookRepository.save((Book) any())).thenReturn(book);
        when(bookRepository.existsByBookCode(anyInt())).thenReturn(false);
        when(mapper.BookToDTO((Book) any())).thenReturn(bookDTO);
        when(mapper.DTOToBook((BookDTO) any())).thenReturn(book);
        assertSame(bookDTO, bookService.addBook(new BookDTO()));
        verify(bookRepository).save((Book)any());
        verify(mapper).BookToDTO((Book)any());
        verify(mapper).DTOToBook((BookDTO) any());
    }

    @Test
    void getBooksTest() {

        assertTrue(bookService.getBooks().isEmpty());
        when(bookRepository.findAll()).thenReturn(new ArrayList<>());
        when(bookRepository.findAll()).thenReturn(bookList());
        when(mapper.BookToDTO((Book)any())).thenReturn(bookDTO);
        verify(bookRepository).findAll();
    }

    @Test
    void getBookByCode() {


        Optional<Book> result = Optional.of(book);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(result); //Optional.of(book);
        when(mapper.BookToDTO((Book)any())).thenReturn(bookDTO);
        assertSame(bookDTO, bookService.getBookByCode(100));
        verify(bookRepository, times(1)).getBookByCode(anyInt());
        verify(mapper).BookToDTO(book);
    }

    @Test
    void updateBookTest() {
        Optional<Book> result = Optional.of(book);
        when(bookRepository.save((Book) any())).thenReturn(book);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(result);
        when(mapper.BookToDTO((Book)any())).thenReturn(bookDTO);
        assertSame(bookDTO,bookService.updateBook(new BookDTO(),1));
        verify(bookRepository).save((Book) any());
        verify(bookRepository).getBookByCode(anyInt());
        verify(mapper).BookToDTO((Book) any());
    }
    @Test
    void updateBook_NoBookFoundTest() {
        when(bookRepository.save((Book)any())).thenReturn(book);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(Optional.empty());
        when(mapper.BookToDTO((Book)any())).thenReturn(new BookDTO());
        assertThrows(BookNotFound.class, ()-> bookService.updateBook(bookDTO, 1));
        verify(bookRepository).getBookByCode(anyInt());
    }

    @Test
    void deleteBookTest() {
        doNothing().when(bookRepository).delete((Book) any());
        Optional<Book> result = Optional.of(book);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(result);
        bookService.deleteBook(1);
        verify(bookRepository).getBookByCode(anyInt());
        verify(bookRepository).delete((Book) any());
        assertTrue(bookService.getBooks().isEmpty());
    }
    @Test
    void deleteBook_BookNotFoundTest() {
        doNothing().when(bookRepository).delete((Book) any());
        when(bookRepository.getBookByCode(anyInt())).thenReturn(Optional.empty());
        assertThrows(BookNotFound.class, ()->bookService.deleteBook(1));
        verify(bookRepository).getBookByCode(anyInt());
    }

    @Test //????
    void getBooksByNameTest() {
        when(bookRepository.getBooksByName(anyString())).thenReturn(Optional.of(new ArrayList<>()));
        assertTrue(bookService.getBooksByName("Name").isEmpty());
        verify(bookRepository).getBooksByName(anyString());

    }
    @Test
    void getBooksByName_OneBookTest() {
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        Optional<List<Book>> result = Optional.of(bookList);
        when(bookRepository.getBooksByName(anyString())).thenReturn(result);
        when(mapper.BookToDTO((Book)any())).thenReturn(new BookDTO());
        assertEquals(1,bookService.getBooksByName("Name1").size());
        verify(bookRepository).getBooksByName(anyString());
        verify(mapper).BookToDTO((Book)any());

    }
    @Test
    void getBooksByName_TwoBookTest() {
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        anotherBook.setBookName("Name1");
        bookList.add(anotherBook);
        Optional<List<Book>> result = Optional.of(bookList);
        when(bookRepository.getBooksByName(anyString())).thenReturn(result);
        when(mapper.BookToDTO((Book)any())).thenReturn(new BookDTO());
        assertEquals(2,bookService.getBooksByName("Name1").size());
        verify(bookRepository).getBooksByName(anyString());
        verify(mapper,atLeast(1)).BookToDTO((Book)any());

    }
    @Test
    void getBooksByName_BookNotFoundTest() {

        when(bookRepository.getBooksByName(anyString())).thenReturn(Optional.empty());
        when(mapper.BookToDTO((Book)any())).thenReturn(new BookDTO());
        assertThrows(BookNotFound.class,()->bookService.getBooksByName("Name1"));
        verify(bookRepository).getBooksByName(anyString());
    }

    @Test
    void setBookAsRead_BookNotFoundTest() {
        when(bookRepository.save((Book)any())).thenReturn(book);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(Optional.empty());
        when(mapper.DTOToBook((BookDTO) any())).thenReturn(anotherBook);
        when(mapper.BookToDTO((Book)any())).thenReturn(new BookDTO());
        assertThrows(BookNotFound.class,()-> bookService.setBookAsRead(1));
        verify(bookRepository).getBookByCode(anyInt());
    }
}


