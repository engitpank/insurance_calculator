package ru.itpank.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itpank.travel.insurance.core.TravelCalculatePremiumService;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumResponse;
import ru.itpank.travel.insurance.dto.ValidationError;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TravelCalculatePremiumController.class)
class TravelCalculatePremiumControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    TravelCalculatePremiumService calculatorService;
    @Autowired
    ObjectMapper objectMapper;
    private TravelCalculatePremiumRequest request;
    private TravelCalculatePremiumResponse validResponse;

    @BeforeEach
    void setUp() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        request = new TravelCalculatePremiumRequest(
                "Ivan",
                "Ivanov",
                formatter.parse("2025-03-03"),
                formatter.parse("2025-03-08")
        );
        validResponse = new TravelCalculatePremiumResponse(
                request.getPersonFirstName(),
                request.getPersonLastName(),
                request.getAgreementDateFrom(),
                request.getAgreementDateTo(),
                BigDecimal.valueOf(8)
        );
    }

    @Test
    void calculatePremium_validRequest_responseShouldHasNoErrors() throws Exception {
        Mockito.when(calculatorService.calculatePremium(any())).thenReturn(validResponse);
        mockMvc.perform(post("/insurance/travel/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andReturn();
    }

    @Test
    void calculatePremium_validRequest_responseShouldContainsAllRequestFields() throws Exception {
        Mockito.when(calculatorService.calculatePremium(any())).thenReturn(validResponse);
        mockMvc.perform(post("/insurance/travel/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName").exists())
                .andExpect(jsonPath("personLastName").exists())
                .andExpect(jsonPath("agreementDateFrom").exists())
                .andExpect(jsonPath("agreementDateTo").exists())
                .andReturn();
    }

    @Test
    void calculatePremium_validRequest_responseShouldContainsAgreementPrice() throws Exception {
        Mockito.when(calculatorService.calculatePremium(any())).thenReturn(validResponse);
        mockMvc.perform(post("/insurance/travel/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("agreementPrice").exists())
                .andReturn();
    }

    @Test
    void calculatePremium_inValidRequest_responseShouldContainsAgreementPrice() throws Exception {
        TravelCalculatePremiumResponse invalidResponse = new TravelCalculatePremiumResponse(List.of(new ValidationError("field", "message")));
        Mockito.when(calculatorService.calculatePremium(any())).thenReturn(invalidResponse);
        mockMvc.perform(post("/insurance/travel/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors[0].field", is("field")))
                .andExpect(jsonPath("$.errors[0].message", is("message")))
                .andReturn();
    }
}