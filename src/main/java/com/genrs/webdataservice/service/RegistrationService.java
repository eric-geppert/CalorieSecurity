package com.genrs.webdataservice.service;

import com.genrs.webdataservice.model.server.entity.PlayerCredentials;
import com.genrs.webdataservice.repository.server.PlayerCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrationService {

    @Autowired private PlayerCredentialsRepository playerCredentialsRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean registerPlayer(PlayerCredentials playerCredentials) {
        if (playerCredentials.getId() == null) playerCredentials.setId(UUID.randomUUID());

        playerCredentials.setPassword(bCryptPasswordEncoder.encode(playerCredentials.getPassword()));

        return playerCredentialsRepository.save(playerCredentials) == playerCredentials; //doesn't matter that passwords are different??????
        //save returns null if it doesn't save
        //returns playerCredentials object if it save correctly
        //there statement will be true if it works
    }
}
