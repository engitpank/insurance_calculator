package ru.itpank.travel.insurance.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itpank.travel.insurance.JsonFileReader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonFileReader jsonFileReader;

    @Test
    public void calculatePremium_allFieldsValid_shouldReturnNoErrors() throws Exception {
        String requestJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_success.json");
        String responseJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_success.json");
        sendRequestAndCompare(requestJson, responseJson);
    }

    @Test
    public void calculatePremium_firstNameNull_shouldReturnNoErrors() throws Exception {
        String requestJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_personFirstName_null.json");
        String responseJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_personFirstName_null.json");
        sendRequestAndCompare(requestJson, responseJson);
    }

    @Test
    public void calculatePremium_lastNameNull_shouldReturnNoErrors() throws Exception {
        String requestJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_personLastName_null.json");
        String responseJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_personLastName_null.json");
        sendRequestAndCompare(requestJson, responseJson);
    }

    @Test
    public void calculatePremium_agreementDateFromNull_shouldReturnNoErrors() throws Exception {
        String requestJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_agreementDateFrom_null.json");
        String responseJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_agreementDateFrom_null.json");
        sendRequestAndCompare(requestJson, responseJson);
    }

    @Test
    public void calculatePremium_agreementDateToNull_shouldReturnNoErrors() throws Exception {
        String requestJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_agreementDateTo_null.json");
        String responseJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_agreementDateTo_null.json");
        sendRequestAndCompare(requestJson, responseJson);
    }

    @Test
    public void calculatePremium_allFieldsNull_shouldReturnErrorsByEachField() throws Exception {
        String requestJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_allFields_null.json");
        String responseJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_allFields_null.json");
        sendRequestAndCompare(requestJson, responseJson);
    }

    @Test
    public void validate_dateToBeforeDateFrom_shouldReturnError() throws Exception {
        String requestJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_agreementDateToBeforeDateFrom_null.json");
        String responseJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_agreementDateToBeforeDateFrom_null.json");
        sendRequestAndCompare(requestJson, responseJson);
    }

    @Test
    public void validate_allFieldsNull_shouldReturnError() throws Exception {
        String requestJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_allFields_null.json");
        String responseJson = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_allFields_null.json");
        sendRequestAndCompare(requestJson, responseJson);
    }

    private void sendRequestAndCompare(String requestJson, String responseJson) throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(requestJson)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson))
                .andReturn();
    }

    @Test
    public void validate_invalidFormatAgreementDateFrom_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_agreementDateFrom_invalidFormat.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void validate_invalidFormatAgreementDateTo_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_agreementDateTo_invalidFormat.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}