package com.example.studentdataapi.dao;

import com.example.studentdataapi.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args  -> {
            Student mariam = new Student( /* alt + ctl + v = introduce var */
                    "Mariam",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2003, JANUARY, 5)
            );

            Student alex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, MARCH, 27)
            );

            Student toyin = new Student(
                    "Toyin",
                    "tbalo@hotmail.com",
                    LocalDate.of(2000, JANUARY, 30)
            );

            repository.saveAll( /*takes a list*/
                    List.of(mariam, alex, toyin)
            );
        };
    }
}
