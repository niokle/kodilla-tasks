package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.dto.TrelloBoardDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private TrelloConfig trelloConfig;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void beforeRun() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTrelloClient() {
        //given
        trelloClient = new TrelloClient(trelloConfig, restTemplate);
        Mockito.when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        Mockito.when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        Mockito.when(trelloConfig.getTrelloAppToken()).thenReturn("test");
        Mockito.when(trelloConfig.getUserName()).thenReturn("test");

        URI url = trelloClient.getUrl();

        //when
        Mockito.when(restTemplate.getForObject(url, TrelloBoardDto[].class)).thenReturn(null);

        //then
        Assert.assertEquals(0, trelloClient.getTrelloBoards().size());
    }
}