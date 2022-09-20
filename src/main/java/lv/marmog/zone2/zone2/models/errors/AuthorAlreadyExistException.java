package lv.marmog.zone2.zone2.models.errors;


import lv.marmog.zone2.zone2.models.Author;

public class AuthorAlreadyExistException extends Error {
    Author author;
    public AuthorAlreadyExistException(Author author) {
        super("This author already exists: " + author.getAuthorName());
        this.author = author;
    }
}
