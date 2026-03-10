package ru.itis.dis403.lab1.di.config;
/*
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.itis.dis403.lab1.di.annotation.Controller;
import ru.itis.dis403.lab1.di.annotation.GetMapping;
import ru.itis.dis403.lab1.di.config.MethodHandler;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Context {

    private String scanPath = "ru.itis.dis403.lab1.di";

    private Map<Class<?>, Object> components = new HashMap<>();

    private Map<String, MethodHandler> mappings = new HashMap<>();

    public Context() {
        scanComponent();
        // добавила
        scanControllers();
    }

    public Object getComponent(Class clazz) {
        return components.get(clazz);
    }

    private void scanComponent() {
        List<Class<?>> classes = PathScan.find(scanPath);

        // Создание экземпляров компонент
        // перебираем список классов
        // находим конструктор с аргументами, если такого нет - создаем экземпляр
        // размещаем в components, удаляем из списка
        // если есть конструктор с аргументами (только компоненты)
        // пытаемся получить из components объекты - аргументы
        // если полный набор - создаем экземпляр, иначе пропускаем итерацию
        int countClasses = classes.size();
        while (countClasses > 0) {
            // Перебираем классы компоненты
            objectNotFound:
            for (Class c : classes) {
                if (components.get(c) != null) continue;
                // берем первый попавшийся контруктор
                Constructor constructor = c.getConstructors()[0];
                // извлекаем типы аргументов конструктора
                Class[] types = constructor.getParameterTypes();
                // Пытаемся найти готовые компоненты по аргументу
                Object[] args = new Object[types.length];
                for (int i = 0; i < types.length; ++i) {
                    args[i] = components.get(types[i]);
                    if (args[i] == null) {
                        continue objectNotFound;
                    }
                }

                try {
                    Object o = constructor.newInstance(args);
                    components.put(c, o);
                    countClasses--;
                    System.out.println(c + " добавлен");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public MethodHandler getHandler(String path) {
        return mappings.get(path);
        // "/home" → MethodHandler{HomeController, home()}
    }

    private void scanControllers() {
        List<Class<?>> controllerClasses = PathScan.findControllers("ru.itis.dis403.lab_01.di");

        for (Class<?> clazz : controllerClasses) {
            try {
                Constructor<?> constructor = clazz.getConstructors()[0];
                Object[] args = new Object[constructor.getParameterTypes().length];
                for (int i = 0; i < args.length; i++) {
                    args[i] = components.get(constructor.getParameterTypes()[i]);
                }
                // StoreService.class → components.get() → StoreService{...}
                Object controller = constructor.newInstance(args);
                components.put(clazz, controller);

                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(GetMapping.class)) {
                        GetMapping mapping = method.getAnnotation(GetMapping.class);
                        String path = mapping.value();
                        mappings.put(path, new MethodHandler(controller, method));
                        // "/home" → MethodHandler{HomeController{}, home()}
                        System.out.println("Mapped: " + path + " -> " + method.getName());
                    }
                }
            } catch (Exception e) {
                System.out.println("Ошибка создания контроллера " + clazz + ": " + e.getMessage());
            }
        }
    }

}
 */