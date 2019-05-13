package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.dto.TrelloListDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void mapToBoard() {
        //given
        TrelloListDto trelloListDto1 = new TrelloListDto("L1", "list 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("L2", "list 2", true);
        TrelloListDto trelloListDto3 = new TrelloListDto("L3", "list 3", false);
        TrelloListDto trelloListDto4 = new TrelloListDto("L4", "list 4", true);
        List<TrelloListDto> trelloListDtos1 = new ArrayList<>();
        List<TrelloListDto> trelloListDtos2 = new ArrayList<>();
        trelloListDtos1.add(trelloListDto1);
        trelloListDtos2.add(trelloListDto2);
        trelloListDtos2.add(trelloListDto3);
        trelloListDtos2.add(trelloListDto4);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("B1", "board 1", trelloListDtos1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("B2", "board 2", trelloListDtos2);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto1);
        trelloBoardDtos.add(trelloBoardDto2);

        //when
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoard(trelloBoardDtos);

        //then
        Assert.assertEquals(2, trelloBoards.size());
        Assert.assertEquals("B1", trelloBoards.get(0).getId());
        Assert.assertEquals("B2", trelloBoards.get(1).getId());
        Assert.assertEquals("board 1", trelloBoards.get(0).getName());
        Assert.assertEquals("board 2", trelloBoards.get(1).getName());
        Assert.assertEquals(1, trelloBoards.get(0).getLists().size());
        Assert.assertEquals(3, trelloBoards.get(1).getLists().size());
        Assert.assertEquals("L1", trelloBoards.get(0).getLists().get(0).getId());
        Assert.assertEquals("list 1", trelloBoards.get(0).getLists().get(0).getName());
        Assert.assertEquals(false, trelloBoards.get(0).getLists().get(0).isClosed());
        Assert.assertEquals("L2", trelloBoards.get(1).getLists().get(0).getId());
        Assert.assertEquals("list 2", trelloBoards.get(1).getLists().get(0).getName());
        Assert.assertEquals(true, trelloBoards.get(1).getLists().get(0).isClosed());
        Assert.assertEquals("L3", trelloBoards.get(1).getLists().get(1).getId());
        Assert.assertEquals("list 3", trelloBoards.get(1).getLists().get(1).getName());
        Assert.assertEquals(false, trelloBoards.get(1).getLists().get(1).isClosed());
        Assert.assertEquals("L4", trelloBoards.get(1).getLists().get(2).getId());
        Assert.assertEquals("list 4", trelloBoards.get(1).getLists().get(2).getName());
        Assert.assertEquals(true, trelloBoards.get(1).getLists().get(2).isClosed());
    }

    @Test
    public void mapToList() {
        //given
        TrelloListDto trelloListDto1 = new TrelloListDto("L1", "list 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("L2", "list 2", true);
        TrelloListDto trelloListDto3 = new TrelloListDto("L3", "list 3", false);
        TrelloListDto trelloListDto4 = new TrelloListDto("L4", "list 4", true);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);
        trelloListDtos.add(trelloListDto3);
        trelloListDtos.add(trelloListDto4);

        //when
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //then
        Assert.assertEquals(4, trelloLists.size());
        Assert.assertEquals("L1", trelloLists.get(0).getId());
        Assert.assertEquals("list 1", trelloLists.get(0).getName());
        Assert.assertEquals(false, trelloLists.get(0).isClosed());
        Assert.assertEquals("L2", trelloLists.get(1).getId());
        Assert.assertEquals("list 2", trelloLists.get(1).getName());
        Assert.assertEquals(true, trelloLists.get(1).isClosed());
        Assert.assertEquals("L3", trelloLists.get(2).getId());
        Assert.assertEquals("list 3", trelloLists.get(2).getName());
        Assert.assertEquals(false, trelloLists.get(2).isClosed());
        Assert.assertEquals("L4", trelloLists.get(3).getId());
        Assert.assertEquals("list 4", trelloLists.get(3).getName());
        Assert.assertEquals(true, trelloLists.get(3).isClosed());
    }

    @Test
    public void mapToBoardDto() {
        //given
        TrelloList trelloList1 = new TrelloList("L1", "list 1", false);
        TrelloList trelloList2 = new TrelloList("L2", "list 2", true);
        TrelloList trelloList3 = new TrelloList("L3", "list 3", false);
        TrelloList trelloList4 = new TrelloList("L4", "list 4", true);
        List<TrelloList> trelloLists1 = new ArrayList<>();
        List<TrelloList> trelloLists2 = new ArrayList<>();
        trelloLists1.add(trelloList1);
        trelloLists2.add(trelloList2);
        trelloLists2.add(trelloList3);
        trelloLists2.add(trelloList4);
        TrelloBoard trelloBoard1 = new TrelloBoard("B1", "board 1", trelloLists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("B2", "board 2", trelloLists2);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);

        //when
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardDto(trelloBoards);

        //then
        Assert.assertEquals(2, trelloBoardDtos.size());
        Assert.assertEquals("B1", trelloBoardDtos.get(0).getId());
        Assert.assertEquals("B2", trelloBoardDtos.get(1).getId());
        Assert.assertEquals("board 1", trelloBoardDtos.get(0).getName());
        Assert.assertEquals("board 2", trelloBoardDtos.get(1).getName());
        Assert.assertEquals(1, trelloBoardDtos.get(0).getLists().size());
        Assert.assertEquals(3, trelloBoardDtos.get(1).getLists().size());
        Assert.assertEquals("L1", trelloBoardDtos.get(0).getLists().get(0).getId());
        Assert.assertEquals("list 1", trelloBoardDtos.get(0).getLists().get(0).getName());
        Assert.assertEquals(false, trelloBoardDtos.get(0).getLists().get(0).isClosed());
        Assert.assertEquals("L2", trelloBoardDtos.get(1).getLists().get(0).getId());
        Assert.assertEquals("list 2", trelloBoardDtos.get(1).getLists().get(0).getName());
        Assert.assertEquals(true, trelloBoardDtos.get(1).getLists().get(0).isClosed());
        Assert.assertEquals("L3", trelloBoardDtos.get(1).getLists().get(1).getId());
        Assert.assertEquals("list 3", trelloBoardDtos.get(1).getLists().get(1).getName());
        Assert.assertEquals(false, trelloBoardDtos.get(1).getLists().get(1).isClosed());
        Assert.assertEquals("L4", trelloBoardDtos.get(1).getLists().get(2).getId());
        Assert.assertEquals("list 4", trelloBoardDtos.get(1).getLists().get(2).getName());
        Assert.assertEquals(true, trelloBoardDtos.get(1).getLists().get(2).isClosed());
    }

    @Test
    public void mapToListDto() {
        //given
        TrelloList trelloList1 = new TrelloList("L1", "list 1", false);
        TrelloList trelloList2 = new TrelloList("L2", "list 2", true);
        TrelloList trelloList3 = new TrelloList("L3", "list 3", false);
        TrelloList trelloList4 = new TrelloList("L4", "list 4", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        trelloLists.add(trelloList3);
        trelloLists.add(trelloList4);

        //when
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //then
        Assert.assertEquals(4, trelloListDtos.size());
        Assert.assertEquals("L1", trelloListDtos.get(0).getId());
        Assert.assertEquals("list 1", trelloListDtos.get(0).getName());
        Assert.assertEquals(false, trelloListDtos.get(0).isClosed());
        Assert.assertEquals("L2", trelloListDtos.get(1).getId());
        Assert.assertEquals("list 2", trelloListDtos.get(1).getName());
        Assert.assertEquals(true, trelloListDtos.get(1).isClosed());
        Assert.assertEquals("L3", trelloListDtos.get(2).getId());
        Assert.assertEquals("list 3", trelloListDtos.get(2).getName());
        Assert.assertEquals(false, trelloListDtos.get(2).isClosed());
        Assert.assertEquals("L4", trelloListDtos.get(3).getId());
        Assert.assertEquals("list 4", trelloListDtos.get(3).getName());
        Assert.assertEquals(true, trelloListDtos.get(3).isClosed());
    }

    @Test
    public void mapToCardDto() {
        //given
        TrelloCard trelloCard = new TrelloCard("card", "card desc", "pos", "id");

        //when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //then
        Assert.assertEquals("card", trelloCardDto.getName());
        Assert.assertEquals("card desc", trelloCardDto.getDescription());
        Assert.assertEquals("pos", trelloCardDto.getPos());
        Assert.assertEquals("id", trelloCardDto.getListId());
    }

    @Test
    public void mapToCard() {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "card desc", "pos", "id");

        //when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //then
        Assert.assertEquals("card", trelloCard.getName());
        Assert.assertEquals("card desc", trelloCard.getDescription());
        Assert.assertEquals("pos", trelloCard.getPos());
        Assert.assertEquals("id", trelloCard.getListId());
    }
}