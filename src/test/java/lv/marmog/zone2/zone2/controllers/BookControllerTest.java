
package lv.marmog.zone2.zone2.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.models.errors.BookNotFound;
import lv.marmog.zone2.zone2.services.interfaces.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;


import static lv.marmog.zone2.zone2.DataForTests.bookDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;


@ContextConfiguration(classes = {BookController.class})
@ExtendWith(SpringExtension.class)
class BookControllerTest {

    @Autowired
    private BookController bookController;

    @MockBean
    BookService bookService;

    @BeforeEach
    void setUp() {
    }



/*
 @Test
    void getAllBooks() {
    }
*/



/*@Test
    void getBook() {
    }*/



@Test
    void createNewBookTest_Success() throws Exception {
        BookDTO bookDTO = bookDTO();
        when(bookService.addBook((BookDTO) any())).thenReturn(new BookDTO());
        String content = (new ObjectMapper()).writeValueAsString(bookDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                        .post("/book-create")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                        .accept(APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(bookController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content()
                   .string("{\"bookCode\":null,\"bookName\":null,\"author\":null,\"location\":null,\"isRead\":null}"));
        verify(bookService, times(1)).addBook((BookDTO) any());
    }
    @Test
    void createNewBookTest_Invalid() throws Exception {
        BookDTO bookDTO = bookDTO();
        when(bookService.addBook((BookDTO) any())).thenReturn( null);
        String content = (new ObjectMapper()).writeValueAsString(bookDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/book-create")
                .content(content)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(409));
        verify(bookService, times(0)).addBook(bookDTO);
    }
    @Test
    void createNewBookTest_Throw1() throws Exception {
        BookDTO bookDTO = bookDTO();
        when(bookService.addBook((BookDTO) any())).thenThrow(new HttpClientErrorException(HttpStatus.CONTINUE));
        String content = (new ObjectMapper()).writeValueAsString(bookDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/book-create")
                .content(content)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(409));
        verify(bookService, times(0)).addBook(bookDTO);

    }
    @Test
    void updateBookTest() throws Exception {
        BookDTO bookDTO = bookDTO();
        String content = (new ObjectMapper()).writeValueAsString(bookDTO);
        when(bookService.updateBook((BookDTO) any(), anyInt())).thenReturn(new BookDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/book/{bookCode}", 100)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));

                //.string("{\"bookCode\":null,\"bookName\":null,\"author\":null,\"location\":null,\"isRead\":null}"));

    }
    @Test
    void updateBookTest_Invalid() throws Exception {
        BookDTO bookDTO = bookDTO();
        bookDTO.setBookCode(0);

        String content = (new ObjectMapper()).writeValueAsString(bookDTO);
        when(bookService.updateBook(bookDTO, 0)).thenThrow(new BookNotFound(0));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                        .put("/book" + "/")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                        .accept(APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(bookController)
                        .build()
                        .perform(requestBuilder)
                        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        verify(bookService, times(0)).updateBook(bookDTO, bookDTO.getBookCode());

    }

@Test
    void deleteBookTest_NoContent() throws Exception {
        doNothing().when(bookService).deleteBook(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/book/{bookCode}", 1);
    MockMvcBuilders.standaloneSetup(bookController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void deleteBookTest_InternalError() throws Exception {
        doThrow(new HttpClientErrorException(HttpStatus.CONTINUE)).when(bookService).deleteBook(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/book/{bookCode}", 1);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(500));
    }

    @Test
    void deleteBookTest() throws Exception {
        doNothing().when(bookService).deleteBook(anyInt());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders
                .delete("/book/{bookCode}", 1)
                        .characterEncoding("Encoding"); //????????
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
/*@Test
    void getBooksByAuthor() {
    }
*/
}

