package ru.itis.dis403.lab1.di.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// import ru.itis.dis403.lab_01.di.annotation.Controller;
import org.springframework.stereotype.Controller;
import ru.itis.dis403.lab1.di.annotation.GetMapping;
import ru.itis.dis403.lab1.di.component.StoreService;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/stores")
    public void stores(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><meta charset='utf-8'/></head><body>");
        writer.println("<h1>Магазины</h1>");
        writer.println("<ul>");
        storeService.getAll().forEach(store -> {
            writer.println("<li>" + store.toString() + "</li>");
        });
        writer.println("</ul>");
        writer.println("<a href='/home'>← Главная</a>");
        writer.println("</body></html>");
    }
}
