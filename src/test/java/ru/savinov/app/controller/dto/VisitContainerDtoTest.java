package ru.savinov.app.controller.dto;

import org.junit.jupiter.api.Test;
import ru.savinov.test_helpers.factories.visit.VisitContainerDtoFactory;
import ru.savinov.test_helpers.factories.visit.VisitDtoFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitContainerDtoTest {

    @Test
    void getDomains() {
        Set<String> expected = new HashSet<>(VisitDtoFactory.ofDomain());
        VisitContainerDto visitContainerDto = VisitContainerDtoFactory.of();
        Set<String> domains = visitContainerDto.getDomains();
        assertEquals(expected, domains);
    }
}

