package com.crud.tasks.controller;

import com.crud.tasks.dto.TaskDto;
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
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskController taskController;

    @Test
    public void getEmptyTasksListTest() throws Exception{
        //given
        List<TaskDto> tasks = new ArrayList<>();
        Mockito.when(taskController.getTasks()).thenReturn(tasks);

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void getTasksTest() throws Exception{
        //given
        List<TaskDto> tasks = new ArrayList<>();
        tasks.add(new TaskDto(1L, "title 1", "content 1"));
        tasks.add(new TaskDto(2L, "title 2", "content 2"));
        tasks.add(new TaskDto(3L, "title 3", "content 3"));
        Mockito.when(taskController.getTasks()).thenReturn(tasks);

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("title 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("content 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("title 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].content", Matchers.is("content 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].title", Matchers.is("title 3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].content", Matchers.is("content 3")));
    }


    @Test
    public void getTaskTest() throws Exception{
        //given
        TaskDto taskDto = new TaskDto(1L, "title 1", "content 1");
        Mockito.when(taskController.getTask(1L)).thenReturn(taskDto);

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/task/getTask")
                .param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content 1")));
    }

    @Test
    public void deleteTaskTest() throws Exception{
        //given
        TaskDto taskDto = new TaskDto(1L, "title 1", "content 1");

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/task/deleteTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("taskId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateTaskTest() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "title updated", "content 1");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
        }

    @Test
    public void createTaskTest() throws Exception{
        TaskDto taskDto = new TaskDto(1L, "title 1", "content 1");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}