package com.genrs.webdataservice.controller;

import com.genrs.webdataservice.model.server.entity.GoalThisWeek;

import com.genrs.webdataservice.repository.server.WeeklyGoalRepository;
import com.genrs.webdataservice.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@CrossOrigin(origins = "http://localHost:8080")
@RestController
@RequestMapping(value="/goal")
public class GoalThisWeekController {

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private WeeklyGoalRepository weeklyGoalRepository; //remove later

    //put following func in service laterrrrr to make pretty
    //npm installed cors may not need croosOrigin????????????????
    @CrossOrigin  //only because im recieving and sending from same machine (remove later)))))))
    @PostMapping(value="adminAddGoal")
    public GoalThisWeek AdminAddGoal(@RequestBody GoalThisWeek goalThisWeek){
        return weeklyGoalRepository.AdminAddGoal(goalThisWeek);
    }

//        @PostMapping(value = "/find")
//    public Optional<GoalThisWeek> findGoalThisWeek(@RequestBody GoalThisWeek goalThisWeek){
//        return statisticsService.findGoalThisWeek(goalThisWeek);
//    }
}
