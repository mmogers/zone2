package lv.marmog.zone2.zone2.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.services.interfaces.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import static lv.marmog.zone2.zone2.DataForTests.bookDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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


   /* @Test
    void getAllBooks() {
    }*/

    /*@Test
    void getBook() {
    }*/

    /*@Test
    void createNewBook() {
    }*/

    @Test
    void updateBook() throws Exception {
        when(bookService.updateBook((BookDTO) any(), anyInt())).thenReturn(new BookDTO());
        BookDTO bookDTO = bookDTO();
        String content = (new ObjectMapper()).writeValueAsString(bookDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/book/{bookCode}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                .string("{\"bookCode\":null,\"bookName\":null,\"author\":null,\"location\":null,\"isRead\":null}"));
    }

    /*@Test
    void deleteBook() {
    }*/

    /*@Test
    void getBooksByAuthor() {
    }*/
}
