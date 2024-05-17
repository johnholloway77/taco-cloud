package ca.johnholloway.tacocloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Slf4j
public class profileConfig {

    String message;

    @Bean(name="testMessage")
    @Profile("test")
    public String printTest(){
        message = "\n********\n\tIf you see this output it is because the application is running with test profile\n********\n";
        log.info(message);
        return message;
    }

    @Bean
    @Profile("!test")
    public String printNotTest(){
        message = "\n----------\n\tIf you see this output it is because the application is not using test profile\n----------\n";
        log.info(message);
        return message;
    }
}
