package lv.marmog.zone2.zone2.repositories;

import lv.marmog.zone2.zone2.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> , JpaRepository<Author, Integer> {


}
