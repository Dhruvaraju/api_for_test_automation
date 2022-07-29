package com.alpha.api_for_test_automation.Controller;

import com.alpha.api_for_test_automation.Entity.User;
import com.alpha.api_for_test_automation.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;
    private User requestUser;
    private User responseUser;
    private List<User> userList = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        requestUser = new User();
        requestUser.setUserName("Alpha");
        requestUser.setEmail("alpha@alpha.com");
        requestUser.setPassword("Ab43R5ce");

        responseUser = new User(1, "Alpha", "alpha@alpha.com", "Ab43R5ce");
        userList.add(responseUser);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @Tag("happy-path")
    @DisplayName("Should get list of all users.")
    void getAllUsers() throws Exception {
        when(userService.fetchAllUsers()).thenReturn(userList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestUser)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userName").value("Alpha"))
                .andExpect(jsonPath("$[0].email").value("alpha@alpha.com"));
        verify(userService, times(1)).fetchAllUsers();
    }

    @Test
    void registerUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void removeUser() {
    }

    private static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}