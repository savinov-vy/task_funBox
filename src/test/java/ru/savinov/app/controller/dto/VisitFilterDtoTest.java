package ru.savinov.app.controller.dto;

import org.junit.jupiter.api.Test;
import ru.savinov.test_helpers.factories.FilterFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitFilterDtoTest {

    private static final Long EXPECTED_FROM = 3L;
    private static final Long EXPECTED_TO = 7L;

    VisitFilterDto subject;

    @Test
    void getFrom_forwardPeriod() {
        subject = FilterFactory.of_forwardPeriod();
        assertEquals(EXPECTED_FROM, subject.getFrom());
    }

    @Test
    void getFrom_invertedPeriod() {
        subject = FilterFactory.of_invertedPeriod();
        assertEquals(EXPECTED_FROM, subject.getFrom());
    }

    @Test
    void getTo_forwardPeriod() {
        subject = FilterFactory.of_forwardPeriod();
        assertEquals(EXPECTED_TO, subject.getTo());
    }

    @Test
    void getTo_invertedPeriod() {
        subject = FilterFactory.of_invertedPeriod();
        assertEquals(EXPECTED_TO, subject.getTo());
    }


}
