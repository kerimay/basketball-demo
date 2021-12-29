package com.kerimay;

import com.kerimay.user.config.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class BasketballApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasketballApplication.class, args);
    }

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategyLocal() {
        // with flyway 7.5 the method flyway.migrate has a different return value. This seams not to be compatible with spring-boot 2.3.7.FINAL
        // in future spring boot versions, this bean can probably be removed again.
        return flyway -> {
            flyway.clean(); // you can disable this, if you do not want to reset the database
            flyway.migrate();
        };
    }
}
