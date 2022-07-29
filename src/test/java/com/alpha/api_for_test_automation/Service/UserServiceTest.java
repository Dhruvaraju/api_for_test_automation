package com.alpha.api_for_test_automation.Service;

import com.alpha.api_for_test_automation.Entity.User;
import com.alpha.api_for_test_automation.Repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    private User requestUser;
    private User responseUser;
    private List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        requestUser = new User();
        requestUser.setUserName("Alpha");
        requestUser.setEmail("alpha@alpha.com");
        requestUser.setPassword("Ab43R5ce");

        responseUser = new User(1, "Alpha", "alpha@alpha.com", "Ab43R5ce");
        userList.add(responseUser);
    }

    @Test
    @Tag("happy-path")
    @DisplayName("Should add a new user and return it.")
    void addOrUpdateUser() {
        when(userRepository.save(any())).thenReturn(responseUser);
        User actualResult = userService.addOrUpdateUser(requestUser);
        assertEquals("Alpha", actualResult.getUserName());
        verify(userRepository, times(1)).save(requestUser);
    }

    @Test
    @Tag("happy-path")
    @DisplayName("Should return all the entities available from database")
    void fetchAllUsers() {
        when(userRepository.findAll()).thenReturn(userList);
        List<User> actualResult = userService.fetchAllUsers();
        assertEquals(1, actualResult.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @Tag("happy-path")
    @DisplayName("Should remove a user")
    void removeUser() {
        doNothing().when(userRepository).delete(requestUser);
        userService.removeUser(requestUser);
        verify(userRepository, times(1)).delete(requestUser);
    }
}