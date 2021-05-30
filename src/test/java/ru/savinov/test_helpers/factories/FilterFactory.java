package ru.savinov.test_helpers.factories;

import ru.savinov.app.controller.dto.VisitFilterDto;

import java.util.Date;

public class FilterFactory {

    private static long hourSecond = 3_600;

    public static VisitFilterDto of() {
        long from = new Date().getTime() - hourSecond;
        long to = new Date().getTime() + hourSecond;
        return new VisitFilterDto(from, to);
    }

    public static VisitFilterDto of_forwardPeriod() {
        long from = 3L;
        long to = 7L;
        return new VisitFilterDto(from, to);
    }

    public static VisitFilterDto of_invertedPeriod() {
        long from = 7L;
        long to = 3L;
        return new VisitFilterDto(from, to);
    }
}
