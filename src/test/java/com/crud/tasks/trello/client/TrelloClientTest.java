package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


//@ActiveProfiles("test")
//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    //@Autowired
    private TrelloClient trelloClient;

    @Test
    public void testTrelloClient() {
        //given
        trelloClient = new TrelloClient();
        //URI url = URI.create("https://api.trello.com/1/members/michakleszczewski/boards?key=a64377a3d776abed4f9f94f30a8ad114&token=32e7588fad441b9076c0d8696e7111e0758bd64cf3e3ab9135567530ea2284d9&fields=name,id");

        //when
        URI url = trelloClient.getUrl();
        //System.out.println(url);
        Mockito.when(restTemplate.getForObject(url, TrelloBoardDto[].class)).thenReturn(null);

        //then
        //trelloClient.getTrelloBoards().stream()
        //        .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getName()));
        Assert.assertEquals(0, trelloClient.getTrelloBoards().size());
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
}