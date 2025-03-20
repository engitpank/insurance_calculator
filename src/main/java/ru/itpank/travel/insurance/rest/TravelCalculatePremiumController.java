package ru.itpank.travel.insurance.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itpank.travel.insurance.core.TravelCalculatePremiumService;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumRequest;
import ru.itpank.travel.insurance.dto.TravelCalculatePremiumResponse;

@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {

    private final TravelCalculatePremiumService calculatePremiumService;

    TravelCalculatePremiumController(TravelCalculatePremiumService calculatePremiumService) {
        this.calculatePremiumService = calculatePremiumService;
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
        return calculatePremiumService.calculatePremium(request);
    }

}