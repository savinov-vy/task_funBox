package ru.savinov.app.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitFilterDto {

    @NotNull
    private Long from;

    @NotNull
    private Long to;
}
