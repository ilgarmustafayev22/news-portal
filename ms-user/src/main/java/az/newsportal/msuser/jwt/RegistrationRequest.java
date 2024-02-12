package az.newsportal.msuser.jwt;

import az.newsportal.msuser.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequest {

    String username;

    String email;

    String password;

    Role role;
}
