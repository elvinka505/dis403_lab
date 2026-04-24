package ru.itis.dis403.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Subscription;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.itis.dis403.model.Weather;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class WeatherService {

    private Weather lastWeather;

    @PostConstruct
    public void publishAndSubscribe() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        Weather weather = Weather.builder()
                .city("Kazan")
                .temp(18.5)
                .pressure(1013.0)
                .windSpeed(4.2)
                .windDirection("North")
                .dateTime(LocalDateTime.now())
                .build();

        try (Connection nc = Nats.connect("nats://localhost:4222")) {
            // Публикуем
            nc.publish("Weather", mapper.writeValueAsBytes(weather));

            // Сразу подписываемся и читаем
            Subscription sub = nc.subscribe("Weather");
            Message msg = sub.nextMessage(Duration.ofSeconds(3));
            if (msg != null) {
                lastWeather = mapper.readValue(msg.getData(), Weather.class);
                System.out.println("Получено: " + lastWeather.getCity());
            }
        }
    }

    public Weather getWeather() {
        return lastWeather;
    }
}