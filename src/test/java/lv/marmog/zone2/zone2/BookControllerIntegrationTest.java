package lv.marmog.zone2.zone2;


import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.controllers.BookController;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class BookControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    BookController bookController;

    @Test
     void test(){
        assertThat(bookController).isNotNull();
    }


    @Order(1)
    @Test
     void getAllBooksIntFailTest() throws JSONException {
        String response = this.restTemplate.getForObject("/books", String.class );
        JSONAssert.assertNotEquals("[{bookCode:1}, {bookCode:2}]", response, false);
    }
    @Order(2)
    @Test
     void getAllBooksIntTest() throws JSONException {
        String response = this.restTemplate.getForObject("/books", String.class );
        JSONAssert.assertEquals("[\n" +
                "    {\n" +
                "        \"bookCode\": 12121,\n" +
                "        \"bookName\": \"Peppi\",\n" +
                "        \"author\": \"8\",\n" +
                "        \"location\": \"home\",\n" +
                "        \"isRead\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"bookCode\": 1712,\n" +
                "        \"bookName\": \"I like my cat\",\n" +
                "        \"author\": \"Me\",\n" +
                "        \"location\": null,\n" +
                "        \"isRead\": null\n" +
                "    },\n" +
                "    {\n" +
                "        \"bookCode\": 112,\n" +
                "        \"bookName\": \"Its a test book\",\n" +
                "        \"author\": \"Me Again\",\n" +
                "        \"location\": null,\n" +
                "        \"isRead\": null\n" +
                "    },\n" +
                "    {\n" +
                "        \"bookCode\": 512,\n" +
                "        \"bookName\": \"Its a test book\",\n" +
                "        \"author\": \"Me Again\",\n" +
                "        \"location\": null,\n" +
                "        \"isRead\": false\n" +
                "    },\n" +
                "    {\n" +
                "        \"bookCode\": 712,\n" +
                "        \"bookName\": \"Its a test book\",\n" +
                "        \"author\": \"Me Again\",\n" +
                "        \"location\": null,\n" +
                "        \"isRead\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"bookCode\": 7452,\n" +
                "        \"bookName\": \"Its a test book\",\n" +
                "        \"author\": \"Me Again\",\n" +
                "        \"location\": \"home\",\n" +
                "        \"isRead\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"bookCode\": 1111,\n" +
                "        \"bookName\": \"Peppi\",\n" +
                "        \"author\": \"3\",\n" +
                "        \"location\": \"home\",\n" +
                "        \"isRead\": null\n" +
                "    },\n" +
                "    {\n" +
                "        \"bookCode\": 7432,\n" +
                "        \"bookName\": \"Its a new test book\",\n" +
                "        \"author\": \"Me Marina\",\n" +
                "        \"location\": \"home\",\n" +
                "        \"isRead\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"bookCode\": 7454345,\n" +
                "        \"bookName\": \"Its a new test book\",\n" +
                "        \"author\": \"Me Marina\",\n" +
                "        \"location\": \"home\",\n" +
                "        \"isRead\": true\n" +
                "    }\n" +
                "]", response, false);
    }

    @Order(3)
    @Test
     void return404AllBooksNotFound_incorrectURL(){
        ResponseEntity<String> err = restTemplate.getForEntity("/book", String.class);
        assertEquals(HttpStatus.NOT_FOUND, err.getStatusCode());
    }

    @Order(4)
    @Test
     void getBookIntTest() throws JSONException {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/book/{bookCode}", String.class, 12121 );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Order(5)
    @Test
     void getBookIntTest_NotExistingId() throws JSONException {
        ResponseEntity<String> err = restTemplate.getForEntity("/book/{bookCode}", String.class, 33333);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, err.getStatusCode());

    }
    @Order(6)
    @Test
     void getBooksByAuthorIntTest() throws JSONException {
        String response = this.restTemplate.getForObject("/books-by-author/{name}", String.class, "Me Marina" );
        JSONAssert.assertEquals("[\n" +
                "    {\n" +
                "        \"bookCode\": 7432,\n" +
                "        \"bookName\": \"Its a new test book\",\n" +
                "        \"author\": \"Me Marina\",\n" +
                "        \"location\": \"home\",\n" +
                "        \"isRead\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"bookCode\": 7454345,\n" +
                "        \"bookName\": \"Its a new test book\",\n" +
                "        \"author\": \"Me Marina\",\n" +
                "        \"location\": \"home\",\n" +
                "        \"isRead\": true\n" +
                "    }\n" +
                "]", response, false);
    }
    @Order(7)
    @Test
     void getBooksByAuthorIntTest_BadUrlAuthor(){
        ResponseEntity<String> err = restTemplate.getForEntity("/books-by-author/{name}", String.class, "Anything");
        assertEquals(HttpStatus.NOT_FOUND, err.getStatusCode());
    }

    @Order(8)
    @Test
     void createNewBookIntTest() throws JSONException {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookCode(9001);
        bookDTO.setBookName("Test Title");
        bookDTO.setAuthor("Test Author");
        bookDTO.setLocation("Test Location");
        bookDTO.setIsRead(true);

        ResponseEntity<BookDTO> responseEntity = this.restTemplate.postForEntity("/book-create", bookDTO, BookDTO.class );
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode()));
                //() -> assertEquals(bookDTO, responseEntity.getBody()));
    }

    @Order(9)
    @Test
     void createNewBookIntTest_incorrectInputCodeNull() throws JSONException {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookName("Test Title");
        bookDTO.setAuthor("Test Author");
        bookDTO.setLocation("Test Location");
        bookDTO.setIsRead(true);
        ResponseEntity<BookDTO> responseEntity = this.restTemplate.postForEntity("/book-create", bookDTO, BookDTO.class );
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Order(10)
    @Test
     void createNewBookIntTest_incorrectInputSameCode() throws JSONException {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookCode(9001);
        bookDTO.setBookName("Test Title 2");
        bookDTO.setAuthor("Test Author 2");
        bookDTO.setLocation("Test Location 2");
        bookDTO.setIsRead(true);
        ResponseEntity<BookDTO> responseEntity = this.restTemplate.postForEntity("/book-create", bookDTO, BookDTO.class );
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Order(11)
    @Test
     void updateBookIntTest() throws JSONException {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookCode(9001);
        bookDTO.setBookName("Test Title Update");
        bookDTO.setAuthor("Test Author");
        bookDTO.setLocation("Test Location");
        bookDTO.setIsRead(true);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<BookDTO> entity = new HttpEntity<BookDTO>(bookDTO,headers);
        ResponseEntity<BookDTO> responseEntity =this.restTemplate.exchange("/book/9001", HttpMethod.PUT, entity, BookDTO.class, bookDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Order(12)
    @Test
     void updateBookIntTest_badCode() throws JSONException {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookCode(9001);
        bookDTO.setBookName("Test Title Update");
        bookDTO.setLocation("Test Location");
        bookDTO.setIsRead(true);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<BookDTO> entity = new HttpEntity<BookDTO>(bookDTO,headers);
        ResponseEntity<BookDTO> responseEntity =this.restTemplate.exchange("/book/-5", HttpMethod.PUT, entity, BookDTO.class, bookDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Order(13)
    @Test
     void updateBookIntTest_CodeDoesNotExist() throws JSONException {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookCode(9001);
        bookDTO.setBookName("Test Title Update");
        bookDTO.setLocation("Test Location");
        bookDTO.setIsRead(true);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<BookDTO> entity = new HttpEntity<BookDTO>(bookDTO,headers);
        ResponseEntity<BookDTO> responseEntity =this.restTemplate.exchange("/book/20000", HttpMethod.PUT, entity, BookDTO.class, bookDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Order(14)
    @Test
     void deleteBook(){
        this.restTemplate.delete("/book/9001");
        ResponseEntity<String> list = restTemplate.getForEntity("/book/9001", String.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, list.getStatusCode());
    }
}
