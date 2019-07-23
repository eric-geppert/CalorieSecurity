package com.genrs.webdataservice.controller;

import com.genrs.webdataservice.model.server.entity.PlayerCredentials;
import com.genrs.webdataservice.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

//@CrossOrigin(origins = "https://localhost:8443")

@RestController
@RequestMapping(value = "/api/v0/register")
public class RegistrationController {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    private RegistrationService registrationService;

//    @CrossOrigin(origins = "https://localhost:8443")
    @PostMapping(value = "/")
    public boolean registerPlayer(@RequestBody PlayerCredentials playerCredentials) {
        System.out.println("playerCredentials: " + playerCredentials);
        return registrationService.registerPlayer(playerCredentials);
    }
}
