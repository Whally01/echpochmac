package ru.itis.echpochmac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        EchpochmacApplication.class,
        Jsr310JpaConverters.class
})
public class EchpochmacApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchpochmacApplication.class, args);
    }
}
