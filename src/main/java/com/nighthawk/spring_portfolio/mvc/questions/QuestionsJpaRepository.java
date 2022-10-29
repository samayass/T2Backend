package com.nighthawk.spring_portfolio.mvc.questions;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA is an object-relational mapping (ORM) to persistent data, originally relational databases (SQL). Today JPA implementations has been extended for NoSQL.
public interface QuestionsJpaRepository extends JpaRepository<Questions, Long> {
    // JPA has many built in methods, these few have been prototyped for this application
    void save(String Question);

    // A
    List<Questions> findAllByOrderByQuestionAsc();  // returns a List of Jokes in Ascending order
    List<Questions> findByQuestionIgnoreCase(String question);  // look to see if Joke(s) exist
}