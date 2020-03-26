package org.dr.security.users.control;

import org.dr.security.users.ServerUser;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Profile("server")
public interface UserRepository extends JpaRepository<ServerUser, Long> {

    Optional<ServerUser> findByUsername(String username);
}
