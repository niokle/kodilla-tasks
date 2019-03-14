package com.crud.tasks.trello.client;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TrelloClientTest {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloAppToken;

    @Value("${trello.app.username}")
    private String userName;

    //@Autowired
    //private RestTemplate restTemplate;


    @Test
    public void testTrelloClient() {
        //given
        //TrelloClient trelloClient = new TrelloClient();

        //when
        //URI url = getUrl();
        //Mockito.when(restTemplate.getForObject(url, TrelloBoardDto[].class)).thenReturn(new TrelloBoardDto[0]);

        //then
        //Assert.assertEquals(0, trelloClient.getTrelloBoards().size());
        //Assert.assertEquals(1,1);
    }

    //@Profile("test")
    //@Configuration
    //public class NameServiceTestConfiguration {
    //    @Bean
    //    @Primary
    //    public RestTemplate restTemplate() {
    //        return Mockito.mock(RestTemplate.class);
    //    }
    //}

    private URI getUrl() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + userName + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloAppToken)
                .queryParam("fields", "name,id")
                .build().encode().toUri();
    }
}