package lv.marmog.zone2.zone2.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import lv.marmog.zone2.zone2.models.inputs.CountryInput;

import lv.marmog.zone2.zone2.repositories.AuthorRepository;
import lv.marmog.zone2.zone2.services.CountryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CountryController.class})
@ExtendWith(SpringExtension.class)
class BookControllerTest {
    @Autowired
    private CountryController countryController;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private CountryService countryService;

    /**
     * Method under test: {@link CountryController#addCountry(List)}
     */
    @Test
    void testAddCountry() throws Exception {
        doNothing().when(countryService).addCountries((List<CountryInput>) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/countries/add")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(countryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("New Country was added"));
    }

    /**
     * Method under test: {@link CountryController#getAllCountries()}
     */
    @Test
    void testGetAllCountries() throws Exception {
        when(authorRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/countries/list");
        MockMvcBuilders.standaloneSetup(countryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CountryController#getAllCountries()}
     */
    @Test
    void testGetAllCountries2() throws Exception {
        when(authorRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/countries/list");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(countryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

