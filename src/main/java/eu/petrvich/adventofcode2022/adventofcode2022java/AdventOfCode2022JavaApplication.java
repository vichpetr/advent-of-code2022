package eu.petrvich.adventofcode2022.adventofcode2022java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AdventOfCode2022JavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdventOfCode2022JavaApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
