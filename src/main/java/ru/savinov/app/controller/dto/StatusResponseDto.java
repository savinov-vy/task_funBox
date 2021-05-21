package ru.savinov.app.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class StatusResponseDto {

    private String status;

}