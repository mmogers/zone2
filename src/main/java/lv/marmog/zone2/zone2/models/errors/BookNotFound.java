package lv.marmog.zone2.zone2.models.errors;



public class BookNotFound extends RuntimeException{

    Integer bookCode;

    String bookName;

    public BookNotFound(Integer bookCode) {
        super("The book with this id was not found : " + bookCode);
        this.bookCode = bookCode;
    }

    public BookNotFound(String bookName) {
        super("The book with this name was not found : " + bookName);
        this.bookName = bookName;
    }

}
