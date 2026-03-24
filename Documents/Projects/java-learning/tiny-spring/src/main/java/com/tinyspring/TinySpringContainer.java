package com.tinyspring;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;


// A tiny version of Spring's ApplicationContext
// It manages beans (objects) and their dependencies

public class TinySpringContainer {
    private Map<String, Object> beans = new HashMap<>();

    // Register a bean container.registerBean("userService", new UserService());

    public void registerBean(String name, Object instance) {
        beans.put(name, instance);
        System.out.print("Bean Registered" + name);
    }

    // Get a bean by name
    //Example: UserService service = container.getBean("userService");

    public Object getBean(String name) {
        return beans.get(name);
    }

    /**
     * Step 1: Find all fields with @Autowired annotation
     * Step 2: For each field, look up the matching bean in container
     * Step 3: Inject the bean into the field
     */

    public void autowire(Object instance) throws IllegalAccessException {
        Class<?> clazz = instance.getClass();

        // Get all the fields from the class
        Field[] fields = clazz.getDeclaredFields();

        // check each field

        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                System.out.print("Found Autowired Field" + field.getName());

                // Look up the bean
                // Use field name as bean name

                String beanName = field.getName();
                Object bean = getBean(beanName);


                if (bean == null) {
                    System.out.print("No bean found Named:" + beanName);
                    continue;
                }

                    // Step 4: Inject it
                    // field.setAccessible(true) = "Let me access private fields"

                    field.setAccessible(true);
                    field.set(instance, bean); // DI happens here

                    System.out.print("Injected" + beanName + "to" + field.getName());
                }
            }
        }

        public void listBeans() {
            System.out.println(" Beans in Container ");
            for (String name : beans.keySet()) {
                System.out.println("  - " + name + ": " + beans.get(name).getClass().getSimpleName());
            }
        }
    }


