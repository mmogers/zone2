package lv.marmog.zone2.zone2.models.errors;

public class BookNotFoundException extends Error{
    Integer bookCode;

    public BookNotFoundException(Integer bookCode) {
        super("The book with this id was not found : " + bookCode);
        this.bookCode = bookCode;
    }

}
