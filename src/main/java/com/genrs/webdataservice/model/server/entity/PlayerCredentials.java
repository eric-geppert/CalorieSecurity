package com.genrs.webdataservice.model.server.entity;

import javax.validation.constraints.Email;
import java.util.UUID;

public class PlayerCredentials {

    private UUID id;

    @Email
    private String username;
    private String password;

    @Override
    public String toString() {
        return "PlayerCredentials{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public PlayerCredentials() {
    }

    public PlayerCredentials(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
