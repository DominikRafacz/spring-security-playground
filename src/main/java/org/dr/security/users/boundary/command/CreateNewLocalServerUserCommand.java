package org.dr.security.users.boundary.command;

import lombok.*;
import org.springframework.context.annotation.Profile;

@Profile("server")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewLocalServerUserCommand {
    private String username;
    private String password;
}
