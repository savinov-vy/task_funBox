package ru.savinov.app.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor(staticName = "of")
public class DomainResponse {

    private Set<String> domains;

    private String status;
}
