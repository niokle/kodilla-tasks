package com.crud.tasks.controller;

import com.crud.tasks.dto.*;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    public void shouldFetchEmptyTrelloBoards() throws Exception {
        //given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        Mockito.when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void shouldFetchTrelloBoards() throws Exception {
        //given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "Test List", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "Test Task", trelloLists));

        Mockito.when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Test Task")))

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].name", Matchers.is("Test List")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].closed", Matchers.is(false)));
    }

    @Test
    public void shouldCreateTrelloCard() throws Exception {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test", "Test description", "top", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("323", "Test", "http://test.com", new TrelloBadgesDto());

        Mockito.when(trelloFacade.createCard(org.mockito.Matchers.any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(trelloCardDto);

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/trello/createTrelloCard")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("323")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortUrl", Matchers.is("http://test.com")));
    }
}

