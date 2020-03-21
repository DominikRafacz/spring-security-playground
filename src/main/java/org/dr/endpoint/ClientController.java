package org.dr.endpoint;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("server")
@RestController
public class ClientController {

    @RequestMapping("/inside")
    public String inside(){
        return null;
    }
}
