package lv.marmog.zone2.zone2.models.errors;

import lv.marmog.zone2.zone2.models.Author;

public class BookAlreadyExistsException extends Error{
    Integer bookCode;
    public BookAlreadyExistsException(Integer bookCode ) {
        super("The book with this code already exists: " + bookCode);
        this.bookCode = bookCode;
    }
}
