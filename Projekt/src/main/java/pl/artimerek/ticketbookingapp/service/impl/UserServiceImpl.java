package pl.artimerek.ticketbookingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.artimerek.ticketbookingapp.model.Role;
import pl.artimerek.ticketbookingapp.model.User;
import pl.artimerek.ticketbookingapp.repository.UserRepository;
import pl.artimerek.ticketbookingapp.service.UserService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("Invalid data");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuth(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuth(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long userId) {
        return userRepository.getById(userId);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
