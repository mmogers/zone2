package lv.marmog.zone2.zone2.repositories;

import lv.marmog.zone2.zone2.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
