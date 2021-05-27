package ru.savinov.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.savinov.app.controller.dto.DomainResponse;
import ru.savinov.app.controller.dto.StatusResponseDto;
import ru.savinov.app.controller.dto.VisitContainerDto;
import ru.savinov.app.controller.dto.VisitFilterDto;
import ru.savinov.app.service.VisitService;

import javax.validation.Valid;
import java.util.Set;


@RestController
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping(value = "/visited_links", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveVisitedLinks(@RequestBody VisitContainerDto visitContainerDTO){
        visitService.save(visitContainerDTO);
        return ResponseEntity.ok(StatusResponseDto.of("OK"));
    }

    @GetMapping(value = "/visited_domains", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getVisitedDomains(@Valid VisitFilterDto filterDto) {
        Set<String> result = visitService.getByFilter(filterDto);
        return ResponseEntity.ok(DomainResponse.of(result, "OK"));
    }
}