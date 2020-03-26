package org.dr.security.users.boundary;

import org.dr.security.users.ServerUser;
import org.dr.security.users.boundary.command.CreateNewLocalServerUserCommand;
import org.dr.security.users.control.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Profile("server")
@Service
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void registerNewLocalServerUser(@NotNull CreateNewLocalServerUserCommand command) {
        ServerUser user = ServerUser.builder()
                .username(command.getUsername())
                .password(encoder.encode(command.getPassword()))
                .role("ROLE_LOCALSERVER")
                .build();
        entityManager.persist(user);
    }

    public Optional<ServerUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
