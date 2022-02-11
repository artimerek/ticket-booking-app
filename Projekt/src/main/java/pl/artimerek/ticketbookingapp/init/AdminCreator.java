package pl.artimerek.ticketbookingapp.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.artimerek.ticketbookingapp.model.Role;
import pl.artimerek.ticketbookingapp.model.User;
import pl.artimerek.ticketbookingapp.service.UserService;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class AdminCreator implements ApplicationRunner {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args)  {
        User user = new User(
                "konrad@admin.com",
                passwordEncoder.encode("test"),
                Collections.singletonList(new Role("ROLE_ADMIN")));

        userService.save(user);

    }
}
