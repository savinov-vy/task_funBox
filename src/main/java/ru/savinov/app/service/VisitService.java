package ru.savinov.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.savinov.app.controller.dto.VisitContainerDto;
import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final RedisTemplate redisTemplate;

    public Boolean save(VisitContainerDto containerDTO) {
        long time = new Date().getTime();
        Set<String> visits = containerDTO.getDomains();
        return redisTemplate.opsForZSet().add("VISITS", visits, time);
    }
}
