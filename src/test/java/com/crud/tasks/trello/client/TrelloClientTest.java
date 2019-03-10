package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TrelloClientTest {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloAppToken;

    @Value("${trello.app.username}")
    private String userName;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testTrelloClient() {
        //given
        TrelloClient trelloClient = new TrelloClient();

        //when
        URI url = getUrl();
        Mockito.when(restTemplate.getForObject(url, TrelloBoardDto[].class)).thenReturn(new TrelloBoardDto[0]);

        //then
        Assert.assertEquals(0, trelloClient.getTrelloBoards().size());
    }

    @Profile("test")
    @Configuration
    public class NameServiceTestConfiguration {
        @Bean
        @Primary
        public RestTemplate restTemplate() {
            return Mockito.mock(RestTemplate.class);
        }
    }

    private URI getUrl() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + userName + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloAppToken)
                .queryParam("fields", "name,id")
                .build().encode().toUri();
    }
}