package lv.marmog.zone2.zone2.repositories;

import lv.marmog.zone2.zone2.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(
            "SELECT b FROM Book b " +
                    "JOIN b.author ba " +
                    "WHERE ba.authorName LIKE %:name%"
    )
    Optional<List<Book>> getBooksByName(String name);
}
