package com.genrs.webdataservice.service;

import com.genrs.webdataservice.model.server.entity.GoalThisWeek;
import com.genrs.webdataservice.model.server.entity.TodaysCalories;
import com.genrs.webdataservice.repository.server.CalorieRepository;
import com.genrs.webdataservice.repository.server.WeeklyGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService {

    @Autowired
    private WeeklyGoalRepository weeklyGoalRepository;
    @Autowired
    private CalorieRepository calorieRepository;

//    public Optional<GoalThisWeek> findGoalThisWeek(GoalThisWeek goalThisWeek){
//        return  weeklyGoalRepository.findGoalThisWeek(goalThisWeek);
//    }

    public List<TodaysCalories> getEntriesBetween(String email, LocalDate start, LocalDate end){
        return calorieRepository.getEmailsCalorieRowsBetween(email, start, end);
    }

}
