package ru.itpank.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoreResponse {

    private List<ValidationError> errors;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}
