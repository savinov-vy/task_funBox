package ru.savinov.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.savinov.app.controller.dto.DomainResponse;
import ru.savinov.app.controller.dto.VisitContainerDto;
import ru.savinov.app.controller.dto.VisitFilterDto;
import ru.savinov.app.service.VisitService;
import ru.savinov.test_helpers.config.AbstractWebMvcSpringBootTest;
import ru.savinov.test_helpers.factories.FilterFactory;
import ru.savinov.test_helpers.factories.visit.VisitContainerDtoFactory;
import ru.savinov.test_helpers.factories.visit.VisitDtoFactory;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VisitController.class)
class VisitControllerTest extends AbstractWebMvcSpringBootTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private VisitFilterDto visitFilterDto;

    @MockBean
    private VisitService visitService;

    private VisitContainerDto visitContainerDto;

    @BeforeEach
    public void before() {
        visitContainerDto = VisitContainerDtoFactory.of();
        visitFilterDto = FilterFactory.of();
        when(visitService.getByFilter(visitFilterDto)).thenReturn(VisitDtoFactory.ofDomain());
        when(visitService.save(visitContainerDto)).thenReturn(Boolean.TRUE);
    }


    @Test
    void saveVisited_success() throws Exception {
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/visited_links")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(visitContainerDto))

        );
        result
                .andExpect(jsonPath("$.status", Matchers.equalTo("OK")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void saveVisitedLinks_whenNoBody_thenBadRequest() throws Exception {
        mockMvc.perform(post("/visited_links"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getVisitedDomains() throws Exception {
        System.out.println(DomainResponse.of(visitService.getByFilter(visitFilterDto), "ok"));
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get("/visited_domains")
                        .param("from", String.valueOf(visitFilterDto.getFrom()))
                        .param("to", String.valueOf(visitFilterDto.getTo()))
                        .content(objectMapper.writeValueAsString(DomainResponse.of(visitService.getByFilter(visitFilterDto), "ok")))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        result
                .andExpect(jsonPath("$.domains", hasSize(3)))
                .andExpect(jsonPath("$.status", Matchers.equalTo("OK")))
                .andExpect(status().isOk());
    }
}
