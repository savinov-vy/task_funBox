package ru.savinov.test_helpers.factories.visit;

import ru.savinov.app.controller.dto.VisitContainerDto;
import ru.savinov.app.controller.dto.VisitDto;

import java.util.List;

public class VisitContainerDtoFactory {

    public static VisitContainerDto of() {
        return of(VisitDtoFactory.of());
    }

    public static VisitContainerDto of(List<VisitDto> visits) {
        return new VisitContainerDto(visits);
    }

}
