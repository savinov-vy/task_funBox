package ru.savinov.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.savinov.app.controller.dto.VisitContainerDTO;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final RedisTemplate redisTemplate;

    public Boolean save(VisitContainerDTO containerDto) throws Exception {
        long time = new Date().getTime();
        String link = containerDto.getDomains();
        Boolean status = redisTemplate.opsForZSet().add("LINKS", link, time);
        return status;
    }
}
