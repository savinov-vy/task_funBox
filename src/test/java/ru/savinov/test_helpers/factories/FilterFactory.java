package ru.savinov.test_helpers.factories;

import ru.savinov.app.controller.dto.VisitFilterDto;

import java.util.Date;

public class FilterFactory {

    private static long hourMillisecond = 3_600_000;

    public static VisitFilterDto of() {
        long from = new Date().getTime() - hourMillisecond;
        long to = new Date().getTime() + hourMillisecond;
        return new VisitFilterDto(from, to);
    }
}
