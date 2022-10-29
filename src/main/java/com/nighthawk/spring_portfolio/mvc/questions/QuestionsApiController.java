package com.nighthawk.spring_portfolio.mvc.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/questions")  // all requests in file begin with this URI
public class QuestionsApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily for Database CRUD operations
    @Autowired
    private QuestionsJpaRepository repository2;

    /* GET List of Jokes
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Questions>> getQuestions() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>( repository2.findAll(), HttpStatus.OK);
    }

    /* Update Like
     * @PutMapping annotation is used for mapping HTTP PUT requests onto specific handler methods.
     * @PathVariable annotation extracts the templated part {id}, from the URI
     */
    @PutMapping("/like/{id}")
    public ResponseEntity<Questions> setLike(@PathVariable long id) {
        /*
        * Optional (below) is a container object which helps determine if a result is present.
        * If a value is present, isPresent() will return true
        * get() will return the value.
        */
        Optional<Questions> optional = repository2.findById(id);
        if (optional.isPresent()) {  // Good ID
            Questions question = optional.get();  // value from findByID
            question.setLike(question.getLike()+1); // increment value
            repository2.save(question);  // save entity
            return new ResponseEntity<>(question, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Failed HTTP response: status code, headers, and body
    }

    /* Update Jeer
     */
    @PutMapping("/jeer/{id}")
    public ResponseEntity<Questions> setJeer(@PathVariable long id) {
        Optional<Questions> optional = repository2.findById(id);
        if (optional.isPresent()) {  // Good ID
            Questions question = optional.get();
            question.setDislike(question.getDislike()+1);
            repository2.save(question);
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}