package lv.marmog.zone2.zone2.services;

import lv.marmog.zone2.zone2.models.Country;
import lv.marmog.zone2.zone2.models.Inputs.CountryInput;
import lv.marmog.zone2.zone2.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public void addCountries (List<CountryInput> countries){
        countries.stream().forEach(
                countryInput -> addCountry(countryInput.getName()));
    }

    public void addCountry (String name){
        if (!countryRepository.existsByCountryName(name)){
            Country country = new Country(name);
             countryRepository.save(country);
        }
    }
}
