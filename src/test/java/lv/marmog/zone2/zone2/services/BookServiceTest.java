package lv.marmog.zone2.zone2.services;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import lv.marmog.zone2.zone2.models.Book;
import lv.marmog.zone2.zone2.models.inputs.CountryInput;

import lv.marmog.zone2.zone2.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CountryService.class})
@ExtendWith(SpringExtension.class)
class BookServiceTest {
    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private CountryService countryService;

    /**
     * Method under test: {@link CountryService#addCountries(List)}
     */
    @Test
    void testAddCountries() {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by addCountries(List)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        countryService.addCountries(new ArrayList<>());
    }

    /**
     * Method under test: {@link CountryService#addCountries(List)}
     */
    @Test
    void testAddCountries2() {
        Book book = new Book();
        book.setCountryName("GB");
        book.setIdcountries(1);
        when(authorRepository.existsByCountryName((String) any())).thenReturn(true);
        when(authorRepository.save((Book) any())).thenReturn(book);

        CountryInput countryInput = new CountryInput();
        countryInput.setName("Name");

        ArrayList<CountryInput> countryInputList = new ArrayList<>();
        countryInputList.add(countryInput);
        countryService.addCountries(countryInputList);
        verify(authorRepository).existsByCountryName((String) any());
    }

    /**
     * Method under test: {@link CountryService#addCountries(List)}
     */
    @Test
    void testAddCountries3() {
        Book book = new Book();
        book.setCountryName("GB");
        book.setIdcountries(1);
        when(authorRepository.existsByCountryName((String) any())).thenReturn(false);
        when(authorRepository.save((Book) any())).thenReturn(book);

        CountryInput countryInput = new CountryInput();
        countryInput.setName("Name");

        ArrayList<CountryInput> countryInputList = new ArrayList<>();
        countryInputList.add(countryInput);
        countryService.addCountries(countryInputList);
        verify(authorRepository).existsByCountryName((String) any());
        verify(authorRepository).save((Book) any());
    }

    /**
     * Method under test: {@link CountryService#addCountries(List)}
     */
    @Test
    void testAddCountries4() {
        Book book = new Book();
        book.setCountryName("GB");
        book.setIdcountries(1);
        when(authorRepository.existsByCountryName((String) any())).thenReturn(true);
        when(authorRepository.save((Book) any())).thenReturn(book);

        CountryInput countryInput = new CountryInput();
        countryInput.setName("Name");

        CountryInput countryInput1 = new CountryInput();
        countryInput1.setName("Name");

        ArrayList<CountryInput> countryInputList = new ArrayList<>();
        countryInputList.add(countryInput1);
        countryInputList.add(countryInput);
        countryService.addCountries(countryInputList);
        verify(authorRepository, atLeast(1)).existsByCountryName((String) any());
    }

    /**
     * Method under test: {@link CountryService#addCountry(String)}
     */
    @Test
    void testAddCountry() {
        Book book = new Book();
        book.setCountryName("GB");
        book.setIdcountries(1);
        when(authorRepository.existsByCountryName((String) any())).thenReturn(true);
        when(authorRepository.save((Book) any())).thenReturn(book);
        countryService.addCountry("Name");
        verify(authorRepository).existsByCountryName((String) any());
    }

    /**
     * Method under test: {@link CountryService#addCountry(String)}
     */
    @Test
    void testAddCountry2() {
        Book book = new Book();
        book.setCountryName("GB");
        book.setIdcountries(1);
        when(authorRepository.existsByCountryName((String) any())).thenReturn(false);
        when(authorRepository.save((Book) any())).thenReturn(book);
        countryService.addCountry("Name");
        verify(authorRepository).existsByCountryName((String) any());
        verify(authorRepository).save((Book) any());
    }
}

