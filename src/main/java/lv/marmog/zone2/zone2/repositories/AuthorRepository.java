package lv.marmog.zone2.zone2.repositories;

import lv.marmog.zone2.zone2.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query(
            "SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
                    "FROM Author a " +
                    "WHERE a.authorName = :#{#authorName}"
    )
    boolean existsByAuthorName(String authorName);

    @Query(
            "SELECT a from Author a WHERE a.authorName LIKE %:authorName% "
    )
    List<Author> findByName(String authorName);
}
