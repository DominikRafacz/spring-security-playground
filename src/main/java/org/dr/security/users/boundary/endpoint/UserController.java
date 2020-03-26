package org.dr.security.users.boundary.endpoint;

import org.dr.security.users.boundary.UserService;
import org.dr.security.users.boundary.command.CreateNewLocalServerUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Profile("server")
@Controller
@RequestMapping("/administration")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerNewLocalServerUser(@RequestBody CreateNewLocalServerUserCommand command) {
        userService.registerNewLocalServerUser(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
