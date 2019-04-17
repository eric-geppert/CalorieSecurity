package com.genrs.webdataservice.service;

import com.genrs.webdataservice.model.server.entity.PlayerCredentials;
import com.genrs.webdataservice.repository.server.PlayerCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    //must impl UserDetailsService because it's required by "the service" library were building off of

    @Autowired //for dependency injection of dependent types (playcred.)
    private PlayerCredentialsRepository playerCredentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PlayerCredentials> credentials = Optional.ofNullable(playerCredentialsRepository.findByUsername(username));
        //use Optional.of()? safer code because it throws nullPointerException rather than
        //if playCred. is null and it keeps running may break things later?
        if (!credentials.isPresent()) {
            throw new UsernameNotFoundException(username);
        } else {
            User returned_user = new User(credentials.get().getUsername(), credentials.get().getPassword(), new ArrayList<>());
            System.out.println(returned_user);
            return returned_user;   //why returning a new user????????????????????
        }
    }
}
