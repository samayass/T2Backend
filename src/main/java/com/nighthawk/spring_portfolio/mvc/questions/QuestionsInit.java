package com.nighthawk.spring_portfolio.mvc.questions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class QuestionsInit {

    // Inject repositories
    @Autowired
    QuestionsJpaRepository repository2;

    @Bean
    CommandLineRunner run2() { // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // starting jokes
            final String[] questionsArray = {
                    "Should 17-year olds be able to vote?",
                    "Would you adopt a dog?",
                    "Would you ever consider dying your hair?",
                    "Do you want to live in California?",
                    "Do you prefer curly hair?",
                    "Is a hotdog a sandwich?",
                    "Are you going to the Friday football game?",
                    "How many assignments are missing or late?",
                    "If you were eligble, would you get the Covid Booster shot?",
                    "Is the Del Norte FB team good?",
                    "Would you drink Fairlife milk?",
                    "Do you support efforts that aim to put an end to climate change?",
                    "Have you ever been gender stereotyped at school?",
                    "Does height matter in Relationships?",
                    "Is the SAT worth it?",
        
            };

            // make sure Joke database is populated with starting jokes
            for (String questions : questionsArray) {
                List<Questions> test = repository2.findByQuestionIgnoreCase(questions); // JPA lookup
                if (test.size() == 0)
                    repository2.save(new Questions(null, questions, 0, 0)); // JPA save
            }

        };
    }
}