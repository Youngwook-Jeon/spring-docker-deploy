package com.project.young.jobservice;

import com.project.young.jobservice.dto.JobDto;
import com.project.young.jobservice.compose.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

@SpringBootTest
@AutoConfigureWebTestClient
class JobServiceIT extends BaseTest {

    @Autowired
    private WebTestClient client;

    @Test
    public void allJobsTest() {
        this.client.get()
                .uri("/job/all")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    public void searchBySkillsTest() {
        this.client.get()
                .uri("/job/search?skills=java")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(3);
    }

    @Test
    public void postJobTest() {
        JobDto jobDto = JobDto
                .create(null, "k8s engineer", "google", Set.of("k8s"), 200000, true, null);
        this.client.post()
                .uri("/job")
                .bodyValue(jobDto)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.description").isEqualTo("k8s engineer");
    }

}
