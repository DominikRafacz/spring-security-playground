package org.dr.endpoint;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("server")
@RestController
public class MainController {

    @RequestMapping("/shelter")
    public String secured(){
        System.out.println("Someone came inside.");
        return "You're safe here.";
    }
}
