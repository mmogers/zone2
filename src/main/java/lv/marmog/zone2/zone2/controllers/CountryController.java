package lv.marmog.zone2.zone2.controllers;



import lv.marmog.zone2.zone2.models.Country;
import lv.marmog.zone2.zone2.models.Inputs.CountryInput;
import lv.marmog.zone2.zone2.repositories.CountryRepository;
import lv.marmog.zone2.zone2.services.CountryService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CountryService countryService;


    @GetMapping("/countries/list")
    public List<Country> getAllCountries (){
        return countryRepository.findAll();
    }

    @PostMapping("/countries/add")
    public ResponseEntity<String> addCountry(
            @RequestBody List<@Valid CountryInput> countries
    ){

        countryService.addCountries(countries);

        return new ResponseEntity<>("New Country was added" , HttpStatus.CREATED);
    }

}
