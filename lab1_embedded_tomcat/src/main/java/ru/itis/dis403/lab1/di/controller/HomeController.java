package ru.itis.dis403.lab1.di.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// import ru.itis.dis403.lab1.di.annotation.Controller;
import org.springframework.stereotype.Controller;
import ru.itis.dis403.lab1.di.annotation.GetMapping;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class HomeController {

    @GetMapping("/home")
    public void home(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><meta charset='utf-8'/></head><body>");
        writer.println("<h1>Главная страница (/home)</h1>");
        writer.println("<a href='/index'>Перейти на /index</a>");
        writer.println("</body></html>");
    }

    @GetMapping("/index")
    public void index(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><meta charset='utf-8'/></head><body>");
        writer.println("<h1> Страница индекса (/index)</h1>");
        writer.println("<a href='/home'>Вернуться на /home</a>");
        writer.println("</body></html>");
    }
}

