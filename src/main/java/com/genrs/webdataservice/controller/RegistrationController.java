package com.genrs.webdataservice.controller;

import com.genrs.webdataservice.model.server.entity.PlayerCredentials;
import com.genrs.webdataservice.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/v0/register")
public class RegistrationController {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    private RegistrationService registrationService;

    @PostMapping(value = "/")
    public boolean registerPlayer(@RequestBody PlayerCredentials playerCredentials) {
        System.out.println("playerCredentials: " + playerCredentials);
        return registrationService.registerPlayer(playerCredentials);
    }
}
