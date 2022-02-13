package pl.artimerek.ticketbookingapp.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.artimerek.ticketbookingapp.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void clear(){
        userRepository.deleteAll();
    }

    @Test
    public void findByIdShouldReturnGoodObject() {
        // given
        User user = new User("test@test", "test1");
        String expected = user.getEmail();
        entityManager.persist(user);
        entityManager.flush();

        // when
        String actual = userRepository.findById(user.getId()).get().getEmail();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void saveTest() {
        // given
        User user = new User("test@tests", "test1");
        User user1 = userRepository.save(user);

        // when
        String actual = userRepository.findById(user1.getId()).get().getEmail();
        String expected = user.getEmail();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void findByEmailTest(){
        //  given
        User user = new User("test1@test", "test1");
        User user1 = userRepository.save(user);

        // when
        long actual = userRepository.findByEmail("test1@test").getId();
        long expected = user.getId();

        // then
        assertEquals(expected, actual);
    }

}