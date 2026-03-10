package ru.itis.dis403.lab1.di.config;
/*
import ru.itis.dis403.lab1.di.annotation.Component;
import ru.itis.dis403.lab1.di.annotation.Controller;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PathScan {

    public static List<Class<?>> find(String scannedPackage) {
        String scannedPath = scannedPackage.replace(".", "/");
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
        if (scannedUrl == null) {
            throw new IllegalArgumentException("Bad package " + scannedPackage);
        }
        File scannedDir = new File(scannedUrl.getFile());
        List<Class<?>> classes = new ArrayList<>();
        for (File file : scannedDir.listFiles()) {
            classes.addAll(find(file, scannedPackage));
        }
        return classes;
    }

    private static List<Class<?>> find(File file, String scannedPackage) {
        List<Class<?>> classes = new ArrayList<>();
        String resource = scannedPackage + "." + file.getName();
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(find(child, resource));
            }
        } else if (resource.endsWith(".class")) {
            String className = resource.substring(0, resource.length() - 6);
            try {
                Class clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Component.class)) {
                    classes.add(clazz);
                }
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
    }

    public static List<Class<?>> findControllers(String scannedPackage) {
        String scannedPath = scannedPackage.replace(".", "/");
        URL scannedUrl = Thread.currentThread()
                .getContextClassLoader()
                .getResource(scannedPath);
        // ищет: target/classes/ru/itis/lab_01/di/
        if (scannedUrl == null) {
            throw new IllegalArgumentException("Bad package controllers " + scannedPackage);
        }
        File scannedDir = new File(scannedUrl.getFile());
        List<Class<?>> classes = new ArrayList<>();
        for (File file : scannedDir.listFiles()) {
            classes.addAll(findControllers(file, scannedPackage));
        }
        return classes;
    }

    private static List<Class<?>> findControllers(File file, String scannedPackage) {
        List<Class<?>> classes = new ArrayList<>();
        String resource = scannedPackage + "." + file.getName();
        // "ru.itis.lab_01.di" + "." + "controller" = "ru.itis.lab_01.di.controller"
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(findControllers(child, resource));
            }
        } else if (resource.endsWith(".class")) {
            String className = resource.substring(0, resource.length() - 6);
            // "HomeController.class" → "HomeController"
            try {
                Class clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Controller.class)) {
                    classes.add(clazz);
                }
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
    }

}
 */