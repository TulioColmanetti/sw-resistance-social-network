package br.com.tulio.swresistancesocialnetwork.controller;

import br.com.tulio.swresistancesocialnetwork.builder.RebelDTOBuilder;
import br.com.tulio.swresistancesocialnetwork.dto.RebelDTO;
import br.com.tulio.swresistancesocialnetwork.services.RebelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static br.com.tulio.swresistancesocialnetwork.utils.JsonConversionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RebelControllerTest {

    private static final String REBELS_API_PATH = "/api/v1/rebels";

    private MockMvc mockMvc;

    @Mock
    private RebelService rebelService;

    @InjectMocks
    private RebelController rebelController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(rebelController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenARebelIsCreated() throws Exception {
//        given
        RebelDTO rebelDTO = RebelDTOBuilder.builder().build().toRebelDTO();

//        when
        when(rebelService.createRebel(rebelDTO)).thenReturn(rebelDTO);

//        then
        mockMvc.perform(post(REBELS_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(rebelDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(rebelDTO.getName())))
                .andExpect(jsonPath("$.age", is(rebelDTO.getAge())))
                .andExpect(jsonPath("$.gender", is(rebelDTO.getGender())));
    }
}