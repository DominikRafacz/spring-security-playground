package org.dr.endpoint;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("server")
@RestController
public class MainController {

    @RequestMapping("/user/shelter")
    public String secured_form(){
        System.out.println("Someone came inside 'user'.");
        return "You're safe by here.";
    }

    @RequestMapping("/guest/shelter")
    public String unsecured(){
        System.out.println("Someone came inside.");
        return "You're not safe at all here.";
    }
}
