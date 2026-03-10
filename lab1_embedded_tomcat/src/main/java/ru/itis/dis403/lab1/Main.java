package ru.itis.dis403.lab1;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.dis403.lab1.di.config.ApplicationConfig;
import ru.itis.dis403.lab1.di.servlet.DispatcherServlet;

import java.io.File;

public class Main {
    // ru.itis.dis403.lab_01.di.config.Context diContext = new ru.itis.dis403.lab_01.di.config.Context();
    public static void main(String[] args) {
        ApplicationContext springContext =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        Connector conn = new Connector();
        conn.setPort(8090);
        tomcat.setConnector(conn);

        String contextPath = "";
        String docBase = new File(".").getAbsolutePath();
        Context tomcatContext = tomcat.addContext(contextPath, docBase);

        DispatcherServlet dispatcher = new DispatcherServlet(springContext);
        String servletName = "dispatcherServlet";
        tomcat.addServlet(contextPath, servletName, dispatcher);
        tomcatContext.addServletMappingDecoded("/*", servletName);

        try {
            tomcat.start();
            System.out.println("Сервер: http://localhost:8090/home");
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }
}