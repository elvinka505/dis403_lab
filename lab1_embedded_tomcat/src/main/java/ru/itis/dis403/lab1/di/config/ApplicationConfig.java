package ru.itis.dis403.lab1.di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.itis.dis403.lab1.di.component.StoreService;
import ru.itis.dis403.lab1.di.model.Basa;

@Configuration
@ComponentScan("ru.itis.dis403.lab1.di")
public class ApplicationConfig {

    // new StoreService(new Basa())
    @Bean
    public StoreService storeService() {
        return new StoreService(new Basa());
    }
}
