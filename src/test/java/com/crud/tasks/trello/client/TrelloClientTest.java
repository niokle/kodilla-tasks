package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.dto.TrelloBoardDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private TrelloConfig trelloConfig;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testTrelloClient() {
        //given
        Mockito.when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        Mockito.when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        Mockito.when(trelloConfig.getTrelloAppToken()).thenReturn("test");
        Mockito.when(trelloConfig.getUserName()).thenReturn("test");
        URI url = trelloClient.getUrl();
        Mockito.when(restTemplate.getForObject(url, TrelloBoardDto[].class)).thenReturn(null);

        //when
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        //then
        Assert.assertEquals(0, trelloBoards.size());
    }
}