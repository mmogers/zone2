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

import java.util.List;
import java.util.Optional;

import static lv.marmog.zone2.zone2.DataForTests.*;
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



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBookAlreadyExistsTest() {
        BookDTO bookDTO = bookDTO();

        when(bookRepository.existsByBookCode(anyInt())).thenReturn(true);
        assertThrows(BookAlreadyExists.class, ()-> bookService.addBook(bookDTO));
        verify(bookRepository, times(1)).existsByBookCode(anyInt());
    }

    @Test
    void addBookTest() {
        Book book1 = book();
        BookDTO bookDTO = bookDTO();

        when(bookRepository.save((Book) any())).thenReturn(book1);
        when(bookRepository.existsByBookCode(anyInt())).thenReturn(false);
        when(mapper.BookToDTO((Book) any())).thenReturn(bookDTO);
        when(mapper.DTOToBook((BookDTO) any())).thenReturn(book1);
        assertSame(bookDTO, bookService.addBook(new BookDTO()));
        verify(bookRepository).save((Book)any());
        verify(mapper).BookToDTO((Book)any());
        verify(mapper).DTOToBook((BookDTO) any());
    }

    @Test
    void getBooksTest() {
        BookDTO bookDTO = bookDTO();
        List<Book> bookList = bookList();

        assertTrue(bookService.getBooks().isEmpty());
        when(bookRepository.findAll()).thenReturn(new ArrayList<>());
        when(bookRepository.findAll()).thenReturn(bookList);
        when(mapper.BookToDTO((Book)any())).thenReturn(bookDTO);
        verify(bookRepository).findAll();
    }

    @Test
    void getBookByCode() {
        Book book = book();
        BookDTO bookDTO = bookDTO();

        Optional<Book> result = Optional.of(book);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(result); //Optional.of(book);
        when(mapper.BookToDTO((Book)any())).thenReturn(bookDTO);
        assertSame(bookDTO, bookService.getBookByCode(100));
        verify(bookRepository, times(1)).getBookByCode(anyInt());
        verify(mapper).BookToDTO(book);
    }

    @Test
    void updateBookTest() {
        Book book = book();
        BookDTO bookDTO = bookDTO();

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
        Book book = book();
        BookDTO bookDTO = bookDTO();

        when(bookRepository.save((Book)any())).thenReturn(book);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(Optional.empty());
        when(mapper.BookToDTO((Book)any())).thenReturn(new BookDTO());
        assertThrows(BookNotFound.class, ()-> bookService.updateBook(bookDTO, 1));
        verify(bookRepository).getBookByCode(anyInt());
    }

    @Test
    void deleteBookTest() {
        Book book = book();

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
        Book book = book();

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
        Book book1 = book();
        Book book2 = book();

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        book2.setBookName("Name1");
        bookList.add(book2);
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

        Book book1 = book();
        Book book2 = book();

        when(bookRepository.save((Book)any())).thenReturn(book1);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(Optional.empty());
        when(mapper.DTOToBook((BookDTO) any())).thenReturn(book2);
        when(mapper.BookToDTO((Book)any())).thenReturn(new BookDTO());
        assertThrows(BookNotFound.class,()-> bookService.setBookAsRead(1));
        verify(bookRepository).getBookByCode(anyInt());
    }
}


