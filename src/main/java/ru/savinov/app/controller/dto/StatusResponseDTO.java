package ru.savinov.app.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class StatusResponseDTO {

    private String status;

}