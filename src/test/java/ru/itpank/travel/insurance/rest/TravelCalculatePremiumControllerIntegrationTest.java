package ru.itpank.travel.insurance.rest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calculatePremium_allFieldsValid_shouldReturnNoErrors() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {\
                                "personFirstName" : "Vasja",
                                "personLastName" : "Pupkin",
                                "agreementDateFrom" : "2021-05-25",
                                "agreementDateTo" : "2021-05-29"
                                }""")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Vasja")))
                .andExpect(jsonPath("personLastName", is("Pupkin")))
                .andExpect(jsonPath("agreementDateFrom", is("2021-05-25")))
                .andExpect(jsonPath("agreementDateTo", is("2021-05-29")))
                .andExpect(jsonPath("agreementPrice", is(4)))
                .andReturn();
    }

    @Test
    public void calculatePremium_firstNameNull_shouldReturnNoErrors() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {\
                                "personFirstName" : null,
                                "personLastName" : "Pupkin",
                                "agreementDateFrom" : "2021-05-25",
                                "agreementDateTo" : "2021-05-29"
                                }""")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    public void calculatePremium_lastNameNull_shouldReturnNoErrors() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {\
                                "personFirstName" : "Vasja",
                                "personLastName" : null,
                                "agreementDateFrom" : "2021-05-25",
                                "agreementDateTo" : "2021-05-29"
                                }""")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors[0].field", is("personLastName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    public void calculatePremium_agreementDateFromNull_shouldReturnNoErrors() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {\
                                "personFirstName" : "Vasja",
                                "personLastName" : "Pupkin",
                                "agreementDateFrom" : null,
                                "agreementDateTo" : "2021-05-29"
                                }""")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    public void calculatePremium_agreementDateToNull_shouldReturnNoErrors() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {\
                                "personFirstName" : "Vasja",
                                "personLastName" : "Pupkin",
                                "agreementDateFrom" : "2021-05-25",
                                "agreementDateTo" : null
                                }""")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors[0].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    public void calculatePremium_allFieldsNull_shouldReturnErrorsByEachField() throws Exception {
        String[] fields = {"personFirstName", "personLastName",
                "agreementDateFrom", "agreementDateTo"};
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {\
                                "personFirstName" : null,
                                "personLastName" : null,
                                "agreementDateFrom" : null,
                                "agreementDateTo" : null
                                }""")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", Matchers.hasSize(4)))
                .andExpect(jsonPath("errors[*].field", contains(fields)))
                .andExpect(jsonPath("errors[*].message", everyItem(is("Must not be empty!"))))
                .andReturn();
    }

    @Test
    public void validate_dateToBeforeDateFrom_shouldReturnError() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {\
                                "personFirstName" : "Vasja",
                                "personLastName" : "Pupkin",
                                "agreementDateFrom" : "2021-05-25",
                                "agreementDateTo" : "2021-05-25"
                                }""")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors[0].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[0].message", is("Must be after agreementDateFrom!")))
                .andReturn();
    }

    @Test
    public void validate_invalidFormatAgreementDateFrom_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {\
                                "personFirstName" : "Vasja",
                                "personLastName" : "Pupkin",
                                "agreementDateFrom" : "2021-99-99",
                                "agreementDateTo" : "2021-05-25"
                                }""")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void validate_invalidFormatAgreementDateTo_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {\
                                "personFirstName" : "Vasja",
                                "personLastName" : "Pupkin",
                                "agreementDateFrom" : "2021-05-25",
                                "agreementDateTo" : "05-01-2025"
                                }""")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}