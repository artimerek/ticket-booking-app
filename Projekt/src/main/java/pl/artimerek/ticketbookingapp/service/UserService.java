package pl.artimerek.ticketbookingapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.artimerek.ticketbookingapp.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getUserByEmail(String email);

    List<User> findAll();

    User getById(Long userId);

    void save(User user);

    User saveUser(User user);

    User findById(Long userId);

    void deleteById(Long userId);

}
