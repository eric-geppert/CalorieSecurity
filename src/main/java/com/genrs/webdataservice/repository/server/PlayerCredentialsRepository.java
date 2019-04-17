package com.genrs.webdataservice.repository.server;

import com.genrs.webdataservice.model.server.entity.PlayerCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerCredentialsRepository extends MongoRepository<PlayerCredentials, String> {

    PlayerCredentials findByUsername(String username);

}
