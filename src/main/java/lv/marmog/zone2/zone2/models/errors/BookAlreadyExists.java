package lv.marmog.zone2.zone2.models.errors;

public class BookAlreadyExists extends RuntimeException{

   Integer bookCode;
    public BookAlreadyExists(Integer bookCode ) {
        super("The book with this code already exists: " + bookCode);
        this.bookCode = bookCode;
    }
}
