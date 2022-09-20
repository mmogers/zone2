package lv.marmog.zone2.zone2.models.errors;

public class BookNotFoundException extends Error{
    Integer bookId;

    public BookNotFoundException(Integer bookId) {
        super("The book with this id was not found : " + bookId);
        this.bookId = bookId;
    }

}
