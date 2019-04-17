package com.genrs.webdataservice.repository.server;

import com.genrs.webdataservice.model.server.entity.GoalThisWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class WeeklyGoalRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public WeeklyGoalRepository(MongoTemplate mongoTemplate) {this.mongoTemplate = mongoTemplate;}

    public GoalThisWeek AdminAddGoal(GoalThisWeek goalThisWeek){
        return mongoTemplate.save(goalThisWeek);
    }

//    public GoalThisWeek UserAddGoal(GoalThisWeek goalThisWeek){
//        return mongoTemplate.save(goalThisWeek);
//    }

    /*breaks on null value why? designed not to??????????????????????????????????????????*/
    public Optional<GoalThisWeek> findGoalThisWeek(GoalThisWeek goalThisWeek) {
//    public List<Optional<GoalThisWeek>> findGoalThisWeek(GoalThisWeek goalThisWeek) {

        LocalDate today = goalThisWeek.getDate();
        System.out.println("day of week: "+today.getDayOfWeek());
        switch (goalThisWeek.getDate().getDayOfWeek().getValue()) {
            case (1):
                return Optional.ofNullable(findGoalsBetween(goalThisWeek.getEmail(), today, today.plusDays(6)));
                //ofNullable returns an Optional describing the specified value, if non-null, otherwise returns an empty Optional.
            case (2):
                return Optional.ofNullable(findGoalsBetween(goalThisWeek.getEmail(), today.minusDays(1), today.plusDays(5)));
            case (3):
                return Optional.ofNullable(findGoalsBetween(goalThisWeek.getEmail(), today.minusDays(2), today.plusDays(4)));
            case (4):
                return Optional.ofNullable(findGoalsBetween(goalThisWeek.getEmail(), today.minusDays(3), today.plusDays(3)));
            case (5):
                System.out.println(findGoalsBetween(goalThisWeek.getEmail(), today.minusDays(4), today.plusDays(2)));
                return Optional.ofNullable(findGoalsBetween(goalThisWeek.getEmail(), today.minusDays(4), today.plusDays(2)));
            case (6):
                return Optional.ofNullable(findGoalsBetween(goalThisWeek.getEmail(), today.minusDays(5), today.plusDays(1)));
            case (7):
                return Optional.ofNullable(findGoalsBetween(goalThisWeek.getEmail(), today.minusDays(6), today));
            default: {
                System.out.println("executing default");
                return Optional.empty();//"day of week could not be found";
            }
        }
    }

//    public boolean addGoalThisMonth(GoalThisMonth goalThisMonth){
//        return mongoTemplate.save(goalThisMonth)== goalThisMonth;
//    }

    /**helper function only (not in controller)*/
    public GoalThisWeek findGoalsBetween(String email, LocalDate start, LocalDate end){
//    public List<GoalThisWeek> findGoalsBetween(String email, LocalDate start, LocalDate end){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        query.addCriteria(Criteria.where("date").lte(end).gte(start));
        System.out.println("start: "+start);
        System.out.println("end: "+end);
        System.out.println("email: "+email);
        System.out.println("list with find: "+mongoTemplate.find(query,GoalThisWeek.class));
        System.out.println("list with findOne: "+mongoTemplate.findOne(query,GoalThisWeek.class));
        return mongoTemplate.findOne(query,GoalThisWeek.class);
//        return mongoTemplate.find(query,GoalThisWeek.class);

    }
}


//        Optional<Object> optional = Optional.ofNullable(null);
//        optional.orElseThrow(() -> new Exception("sdlkf"));