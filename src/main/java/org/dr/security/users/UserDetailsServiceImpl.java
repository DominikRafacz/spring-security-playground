package org.dr.security.users;

import org.dr.security.users.control.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Profile("server")
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<ServerUser> optUser = userRepository.findByUsername(username);
        if (optUser.isPresent()) {
            ServerUser serverUser = userRepository.findByUsername(username).get();

            Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(serverUser.getRole()));

            return new org.springframework.security.core.userdetails.User(serverUser.getUsername(),
                    serverUser.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("User with username " + username + " is not present in the database");
        }
    }
}