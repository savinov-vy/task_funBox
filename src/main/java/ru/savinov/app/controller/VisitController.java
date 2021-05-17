package ru.savinov.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.savinov.app.controller.dto.StatusResponseDTO;
import ru.savinov.app.controller.dto.VisitContainerDTO;
import ru.savinov.app.service.VisitService;


@RestController
@RequiredArgsConstructor
public class VisitController {

    VisitService visitService;

    @PostMapping(value = "/visited_links", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveVisitedLinks(@RequestBody VisitContainerDTO visitContainerDTO) throws Exception {
        visitService.save(visitContainerDTO);
        return ResponseEntity.ok(StatusResponseDTO.of("OK"));
    }
}