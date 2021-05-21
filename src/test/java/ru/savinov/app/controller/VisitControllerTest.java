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
import ru.savinov.app.controller.dto.VisitContainerDto;
import ru.savinov.app.service.VisitService;
import ru.savinov.test_helpers.config.AbstractWebMvcSpringBootTest;
import ru.savinov.test_helpers.factories.visit.VisitContainerDtoFactory;
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

    @MockBean
    private VisitService visitService;

    private VisitContainerDto visitContainerDto;

    @BeforeEach
    public void before() {
        visitContainerDto = VisitContainerDtoFactory.of();
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
}
