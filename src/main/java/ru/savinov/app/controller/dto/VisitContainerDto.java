package ru.savinov.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitContainerDto {

    @NotBlank
    @JsonProperty("links")
    private List<VisitDto> visits;

    public Set<String> getDomains() {
        return visits.stream().map(VisitDto::getDomain).collect(Collectors.toSet());
    }
}
