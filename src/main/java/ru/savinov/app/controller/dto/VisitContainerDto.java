package ru.savinov.app.controller.dto;

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
    private List<VisitDto> links;

    public Set<String> getDomains() {
        return links.stream().map(VisitDto::getDomain).collect(Collectors.toSet());
    }
}
