package ru.itis.dis403.lab1.di.config;

import java.lang.reflect.Method;

public class MethodHandler {
    private final Object controller;
    private final Method method;

    public MethodHandler(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public Method getMethod() {
        return method;
    }
}
