package com.genrs.webdataservice.model.server.entity;

import javax.validation.constraints.Email;
import java.time.LocalDate;

public class GoalThisWeek {

    @Email
    private String email;

    private int weekGoal;

    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getWeekGoal() {
        return weekGoal;
    }

    public void setWeekGoal(int weekGoal) {
        this.weekGoal = weekGoal;
    }

    @Override
    public String toString() {
        return "GoalThisWeek{" +
                "email='" + email + '\'' +
                ", weekGoal=" + weekGoal +
                ", date=" + date +
                '}';
    }
}
