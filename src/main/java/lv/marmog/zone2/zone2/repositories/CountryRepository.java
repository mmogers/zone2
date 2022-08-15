package lv.marmog.zone2.zone2.repositories;

import lv.marmog.zone2.zone2.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country, Integer> , JpaRepository<Country, Integer> {

    List<Country> findAll();

    boolean existsByCountryName(String name);
}
