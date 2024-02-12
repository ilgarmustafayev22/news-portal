package az.newsportal.msuser.dto;

import az.newsportal.msuser.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto{

    String username;

    String email;

    String password;

    List<Role> roles;
}
