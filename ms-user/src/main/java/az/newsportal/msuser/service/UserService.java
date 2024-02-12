package az.newsportal.msuser.service;

import az.newsportal.msuser.dao.entity.User;
import az.newsportal.msuser.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByUsername(String username);

    void updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
    //TODO: Cascading deletion
    //TODO: Password Reconfirmation
}
