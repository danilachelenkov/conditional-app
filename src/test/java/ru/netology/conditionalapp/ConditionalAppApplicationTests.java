package ru.netology.conditionalapp;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalAppApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    private final GenericContainer<?> devApp = new GenericContainer<>("devapp:1.0")
            .withExposedPorts(8080);

    @Container
    private final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:1.0")
            .withExposedPorts(8081);

    @BeforeEach
    public void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void contextLoadsDev() {
        String expected = "Current profile is DEV";

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(8080), String.class);
        String actual = forEntity.getBody();


        Assertions.assertEquals(expected, actual);

    }

    @Test
    void contextLoadsProd() {
        String expected = "Current profile is PROD";

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prodApp.getMappedPort(8081), String.class);
        String actual = forEntity.getBody();

        Assertions.assertEquals(expected, actual);
    }


}
