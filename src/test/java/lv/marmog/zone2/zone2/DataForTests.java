
package lv.marmog.zone2.zone2;

import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.models.Book;

import java.util.ArrayList;
import java.util.Date;
public class DataForTests {

    public static ArrayList<Book> bookList() {
        ArrayList<Book> testList = new ArrayList<>();
        Date date = new Date();
        testList.add(new Book(1, 100, "Name1", date, "Author1", true, "home"));
        testList.add(new Book(2, 200, "Name2", date, "Author2", false, "home"));
        return testList;
    }

    static Date date = new Date();
    public static Book book(){
        return new Book(1, 100, "Name1", date, "Author1", true, "home");

    }


    public static BookDTO bookDTO(){
        return new BookDTO(100, "Name1", "Author1", "home", true);

    }
}

