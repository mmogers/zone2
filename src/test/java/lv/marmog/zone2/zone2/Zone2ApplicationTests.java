package lv.marmog.zone2.zone2;
import lv.marmog.zone2.zone2.controllers.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest

class Zone2ApplicationTests {
    @Autowired
    BookController bookController;
	@Test
   void contextLoads() {  assertThat(bookController).isNotNull();}
}

