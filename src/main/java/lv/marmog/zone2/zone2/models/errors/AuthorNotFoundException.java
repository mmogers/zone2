package lv.marmog.zone2.zone2.models.errors;

public class AuthorNotFoundException extends Error{

    Integer authorId;

    public AuthorNotFoundException(Integer authorId) {
        super("The author with this id was not found: " + authorId);
        this.authorId = authorId;
    }
}
