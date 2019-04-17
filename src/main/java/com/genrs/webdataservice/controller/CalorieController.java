package com.genrs.webdataservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genrs.webdataservice.model.server.entity.TodaysCalories;
import com.genrs.webdataservice.service.StatisticsService;
import com.mongodb.client.result.DeleteResult;
import com.genrs.webdataservice.repository.server.CalorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value="/calorie")
public class CalorieController {

    @Autowired
    private CalorieRepository calorieRepository;
    @Autowired
    private StatisticsService statisticsService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

//    example input (as JSON)
//        {
//             "calories": 2000,
//              "date": 2019-04-11
//        }
    //email: is null then I set it from the authentication
    @PostMapping(value = "/addCalEntry")
    public boolean addTodaysCalories(@RequestBody TodaysCalories todaysCalories, Authentication authentication) {
        todaysCalories.setEmail(authentication.getName());
        return calorieRepository.addTodaysCalories(todaysCalories);
    }

    @GetMapping(value = "/getAllCalEntriesForMe")  //used authenticatin instead of principle because auth has more stuff (contains principle)
    public List<TodaysCalories> getAllCalEntriesForMe(Authentication authentication) { //dont need @RequestBody on gets, gets username from verified JWT
        return calorieRepository.findEmail(authentication.getName());
    }

//        example input (as plaintext)
//        7
    @GetMapping(value = "/getCalsFromLastXDays")
    public int getCalsFromLastXDays(@RequestBody int input,Authentication authentication) throws IOException {
        return calorieRepository.getCalsFromLastXDays(authentication.getName(), input);
    }

    @GetMapping(value = "/getEntriesBetween")
    public List<TodaysCalories> getEntriesBetween(@RequestBody String input,Authentication authentication) throws IOException {
        String date1 =objectMapper.readTree(input).get("start").asText();
        String date2 =objectMapper.readTree(input).get("end").asText();

        LocalDate start = LocalDate.parse(date1);
        System.out.println("start: "+ start);
        LocalDate end = LocalDate.parse(date2);
        System.out.println("end: "+ end);

        return statisticsService.getEntriesBetween(authentication.getName(),start, end);
    }

//        example input (as plaintext)
//        7
    @GetMapping(value = "/getAvgCalsFromLastXDays")
    public int getAvgCalsFromLastXDays(@RequestBody int input,Authentication authentication) throws IOException {
        return calorieRepository.getAvgCalsFromLastXDays(authentication.getName(), input);
    }

//        example input (as plaintext) (dont add ""s around the date!
//        2019-04-17
    @DeleteMapping(value = "/delete")
    public DeleteResult deleteDate(@RequestBody String input, Authentication authentication) throws IOException {
        LocalDate date = LocalDate.parse(input);
        return calorieRepository.deleteDate(authentication.getName(), date);
    }

}