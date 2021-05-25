package ru.savinov.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import ru.savinov.app.controller.dto.VisitContainerDto;
import ru.savinov.app.controller.dto.VisitFilterDto;
import ru.savinov.test_helpers.factories.FilterFactory;
import ru.savinov.test_helpers.factories.visit.VisitContainerDtoFactory;
import ru.savinov.test_helpers.factories.visit.VisitDtoFactory;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VisitServiceTest {

    @Mock
    private RedisTemplate redisTemplate;

    @Mock
    ZSetOperations zSetOperations;

    private VisitService subject;
    VisitContainerDto containerDto;
    VisitFilterDto filter;
    Set<String> visits;
    Clock fixedClock;


    @BeforeEach
    void setUp() {
        String now = "2021-05-30T20:00:00Z";
        ZoneId irkutskTimeZoneId = ZoneId.of("Asia/Irkutsk");
        fixedClock = Clock.fixed(Instant.parse(now), irkutskTimeZoneId);
        filter = FilterFactory.of();

        subject = new VisitService(fixedClock, redisTemplate);
        containerDto = VisitContainerDtoFactory.of();
        visits = VisitDtoFactory.ofDomain();

        when(redisTemplate.opsForZSet()).thenReturn(zSetOperations);
    }

    @Test
    void testSave() {
        when(zSetOperations.add("VISITS", visits, fixedClock.instant().toEpochMilli())).thenReturn(true);
        assertTrue(subject.save(containerDto));
    }

    @Test
    void testGetByFilter() {
        VisitFilterDto filter = FilterFactory.of();
        Set<HashSet> collect = visits.stream().map(visit -> new HashSet(Arrays.asList(visit))).collect(Collectors.toSet());
        when(zSetOperations.rangeByScore("VISITS", Double.valueOf(filter.getFrom()), Double.valueOf(filter.getTo())))
                .thenReturn(collect);

        TreeSet<String> actualTree = new TreeSet<>(subject.getByFilter(filter));
        TreeSet<String> expectedTree = new TreeSet(visits);
        assertIterableEquals(expectedTree, actualTree);
    }
}
