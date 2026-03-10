package ru.itis.dis403.lab1.di.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import ru.itis.dis403.lab1.di.annotation.GetMapping;
import ru.itis.dis403.lab1.di.config.MethodHandler;

import java.io.IOException;
import java.lang.reflect.Method;

public class DispatcherServlet extends HttpServlet {

    /* private Context context;

    public DispatcherServlet(Context context) {
        this.context = context;
    }
     */

    private final Map<String, MethodHandler> mappings = new HashMap<>();

    public DispatcherServlet(ApplicationContext springContext) {
        // спринг сам нашёл все @Controller бины = мы просто их берём
        Map<String, Object> controllers =
                springContext.getBeansWithAnnotation(Controller.class);

        for (Object controller : controllers.values()) {
            for (Method method : controller.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(GetMapping.class)) {
                    String path = method.getAnnotation(GetMapping.class).value();
                    mappings.put(path, new MethodHandler(controller, method));
                    System.out.println("Mapped: " + path + " -> " + method.getName());
                }
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        String path = (pathInfo != null) ? pathInfo : "/home";

        MethodHandler handler = mappings.get(path);
        if (handler == null) {
            resp.setStatus(404);
            resp.getWriter().println("<h1>404 - Страница не найдена: '" + path + "'</h1>");
            resp.getWriter().println("<p>Доступные: /home, /index, /stores</p>");
            return;
        }

        try {
            Method method = handler.getMethod();
            method.setAccessible(true);
            method.invoke(handler.getController(), req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getWriter().println("<h1>500 - Ошибка: " + e.getMessage() + "</h1>");
        }
    }
}
