package ru.savinov.app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.savinov.app.controller.dto.VisitContainerDto;
import ru.savinov.app.controller.dto.VisitFilterDto;

import java.time.Clock;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VisitService {

    private Clock clock;

    private final RedisTemplate redisTemplate;

    public Boolean save(VisitContainerDto containerDTO) {
        long time = clock.instant().toEpochMilli();
        Set<String> visits = containerDTO.getDomains();
        return redisTemplate.opsForZSet().add("VISITS", visits, time);
    }

    public Set<String> getByFilter(VisitFilterDto filter) {
        Set<Set<String>> rateSet = redisTemplate.opsForZSet().rangeByScore("VISITS", Double.valueOf(filter.getFrom()), Double.valueOf(filter.getTo()));
        Set<String> result = rateSet.stream().flatMap(v -> v.stream()).collect(Collectors.toSet());
        return result;
    }
}
