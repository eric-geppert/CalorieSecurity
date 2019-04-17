package com.genrs.webdataservice.repository.server;

import com.genrs.webdataservice.model.server.entity.TodaysCalories;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class CalorieRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CalorieRepository(MongoTemplate mongoTemplate) {this.mongoTemplate = mongoTemplate;}

    public boolean addTodaysCalories(TodaysCalories todaysCalories){
        return mongoTemplate.save(todaysCalories)==todaysCalories;
    }

    public List<TodaysCalories> findEmail(String email){
        System.out.println("(repo findAthelete) searching email: "+ email);
        Query query = new Query(
                Criteria.where("email").is(email)
        );
        return mongoTemplate.find(query,TodaysCalories.class);
    }

    public List<TodaysCalories> getEmailsCalorieRowsBetween(String email, LocalDate start, LocalDate end){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        query.addCriteria(Criteria.where("date").gte(start).lte(end));
        return mongoTemplate.find(query,TodaysCalories.class);
    }

    public List<TodaysCalories> getEmailsCalorieRowsForLastXDays(String email,int days){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        System.out.println("query: "+ query);
        List<TodaysCalories> tempList=mongoTemplate.find(query,TodaysCalories.class);
//        query.addCriteria(Criteria.where("days").gte(LocalDate.now().minusDays(days)));
        System.out.println("tempList: "+ tempList);
        return tempList;
    }

    public int getCalsFromLastXDays(String email, int days) {
        List<TodaysCalories> aList=getEmailsCalorieRowsBetween(email,LocalDate.now().minusDays(days) , LocalDate.now());
        int totalCals= aList.stream().mapToInt(a->a.getCalories()).sum();
        return totalCals;
    }

    public int getAvgCalsFromLastXDays(String email, int days){
        //doesn't look at days that you haven't entered
        int numDays =getEmailsCalorieRowsBetween(
                email,
                LocalDate.now().minusDays(days),
                LocalDate.now()
        ).size();
        int cals=getCalsFromLastXDays(email,days);
        return cals/numDays;
    }
//can prepend: @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) before @Date to change date format
    public DeleteResult deleteDate(String email, LocalDate date){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        query.addCriteria(Criteria.where("date").is(date));
        return mongoTemplate.remove(query,TodaysCalories.class);
    }
    //.remove (removes multiple elements if multiple exist
    //.findAllandRemove (only removes one but cant get it to work.
    //happens to be how I want to implement my date function (only one entry for a particular day



}
