package com.github.mkopylec.rpggame;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class RpgGameApplication {

    public static void main(String[] args) throws JsonProcessingException {
        run(RpgGameApplication.class, args);
    }
}
