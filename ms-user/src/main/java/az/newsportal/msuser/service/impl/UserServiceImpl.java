package az.newsportal.msuser.service.impl;

import az.newsportal.msuser.dao.entity.User;
import az.newsportal.msuser.dao.repo.UserRepository;
import az.newsportal.msuser.dto.UserDto;
import az.newsportal.msuser.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getUserByUsername(String username) {
        var user = repository.findByUsername(username).orElseThrow();
        return Optional.of(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, UserDto userDto) {
        this.repository.findById(id)
                .ifPresent(user -> {
                    user.setUsername(userDto.getUsername());
                    user.setEmail(userDto.getEmail());
                    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                    repository.save(user);
                });
    }

    @Override
    public void deleteUser(Long id) {
        this.repository.deleteById(id);
    }
}