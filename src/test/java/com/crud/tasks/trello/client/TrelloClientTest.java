package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.dto.TrelloBadgesDto;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private TrelloConfig trelloConfig;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void init() {
        Mockito.when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        Mockito.when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        Mockito.when(trelloConfig.getTrelloAppToken()).thenReturn("test");
        Mockito.when(trelloConfig.getUserName()).thenReturn("klenio");
    }

    @Test
    public void testNullTrelloClient() {
        //given
        URI url = trelloClient.getUrl();
        Mockito.when(restTemplate.getForObject(url, TrelloBoardDto[].class)).thenReturn(null);

        //when
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        //then
        Assert.assertEquals(0, trelloBoards.size());
    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        //given
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        URI uri = new URI("http://test.com/members/klenio/boards?key=test&token=test&fields=name,id&lists=all");

        Mockito.when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //when
        List<TrelloBoardDto> fetchedTrelloBoard = trelloClient.getTrelloBoards();

        //then
        Assert.assertEquals(1, fetchedTrelloBoard.size());
        Assert.assertEquals("test_id", fetchedTrelloBoard.get(0).getId());
        Assert.assertEquals("test_board", fetchedTrelloBoard.get(0).getName());
        Assert.assertEquals(new ArrayList<>(), fetchedTrelloBoard.get(0).getLists());
    }

    @Test
    public void shouldCreateCard() throws URISyntaxException {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test task", "Test Description", "top", "Test id");

        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard("1", "Test task", "http://test.com", new TrelloBadgesDto());

        Mockito.when(restTemplate.postForObject(uri, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);

        //when
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        //then
        Assert.assertEquals("1", newCard.getId());
        Assert.assertEquals("Test task", newCard.getName());
        Assert.assertEquals("http://test.com", newCard.getShortUrl());

    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException{
        //given
        URI uri = new URI("http://test.com/members/klenio/boards?key=test&token=test&fields=name,id&lists=all");

        Mockito.when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);

        //when
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        //then
        Assert.assertEquals(0, trelloBoards.size());
    }
}