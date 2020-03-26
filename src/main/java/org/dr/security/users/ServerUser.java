package org.dr.security.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;

@Profile("server")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "server_user")
public class ServerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String role;
}
