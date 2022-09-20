package lv.marmog.zone2.zone2.models.errors;

import lv.marmog.zone2.zone2.models.Author;

public class AuthorNotFoundException extends Error{

    Integer authorId;

    public AuthorNotFoundException(Integer authorId) {
        super("The author with this id was not found: " + authorId);
        this.authorId = authorId;
    }
}
