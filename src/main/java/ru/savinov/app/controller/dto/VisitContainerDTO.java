package ru.savinov.app.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitContainerDTO {

    @NotBlank
    private VisitDTO links;

    public String getDomains() throws Exception {
        return links.getDomain();
    }
}
