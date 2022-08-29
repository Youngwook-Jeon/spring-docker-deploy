package com.project.young.candidateservice;

import com.project.young.candidateservice.repository.CandidateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

// Unit test case
@SpringBootTest
@EnableAutoConfiguration(exclude = MongoReactiveAutoConfiguration.class)
@MockBean({CandidateRepository.class})
class CandidateServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
