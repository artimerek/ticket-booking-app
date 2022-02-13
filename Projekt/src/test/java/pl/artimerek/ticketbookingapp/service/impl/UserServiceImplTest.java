package pl.artimerek.ticketbookingapp.service.impl;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.artimerek.ticketbookingapp.model.Role;
import pl.artimerek.ticketbookingapp.model.User;
import pl.artimerek.ticketbookingapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private User user1;

    List<User> users;

    @BeforeEach
    public void setUp() {
        users = new ArrayList<>();
        user = new User("test@test", "test",
                Collections.singletonList(new Role("ROLE_ADMIN")));
        user1 = new User("test1@test", "test",
                Collections.singletonList(new Role("ROLE_USER")));
        users.add(user);
        users.add(user1);
    }
    @AfterEach
    public void tearDown() {
        user1 = user = null;
        users = null;
    }


    @Test
    public void givenIdThenShouldReturnUserOfThatId() {
        //  given
        user.setId(1L);

        String expected = "test@test";

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //  when
        String actual = userService.findById(1L).getEmail();


        //  then
        assertEquals(expected, actual);

    }

    @Test
    public void givenIdThenShouldThrowExceptionIfUserWithGivenIdNotFounded() {

        Mockito.when(userRepository.findById(3L)).thenReturn(Optional.empty());

        //  when + then
        assertThrows(IllegalArgumentException.class,
                () -> userService.findById(3L));

    }

    @Test
    public void loadUserByUsernameShouldThrowUsernameNotFoundException(){

        Mockito.when(userRepository.findByEmail("test@test"))
                .thenReturn(null);

        //  when + then
        assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("test@test"));

    }

    @Test
    public void loadUserByUsernameShouldReturnUserWithGivenEmail(){
        user.setId(1L);

        Mockito.when(userRepository.findByEmail("test@test"))
                .thenReturn(user);

        //  when
        User user2 = userService.getUserByEmail("test@test");

        long expected = user.getId();
        long actual = user2.getId();


        //  then
        assertEquals(expected, actual);

    }

    @Test
    public void findAllShouldReturnListWithGoodSize(){

        int expected = 2;

        Mockito.when(userRepository.findAll()).thenReturn(users);

        //   when
        int actual = userService.findAll().size();

        //   then
        assertEquals(expected, actual);
    }

}