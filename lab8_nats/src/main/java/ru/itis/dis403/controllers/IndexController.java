package ru.itis.dis403.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.dis403.model.Weather;
import ru.itis.dis403.service.WeatherService;

@Controller
public class IndexController {

    private final WeatherService weatherService;

    public IndexController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String index(Model model) {

        Weather weather = weatherService.getWeather();
        model.addAttribute("weather", weather);

        return "index";
    }

}