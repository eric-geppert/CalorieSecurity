package com.genrs.webdataservice.model.server.entity;


import javax.validation.constraints.Email;
import java.time.LocalDate;

public class TodaysCalories {

    @Email
    private String email;

    private int calories;

    private LocalDate date;

    @Override
    public String toString() {
        return "TodaysCalories{" +
                "email='" + email + '\'' +
                ", calories=" + calories +
                ", date=" + date +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
