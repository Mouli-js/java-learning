package com.tinyspring;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("=== TinySpring Demo ===\n");

        // Step 1: Create the container
        System.out.println("Step 1: Create container");
        TinySpringContainer container = new TinySpringContainer();

        // Step 2: Create instances of services
        System.out.println("\nStep 2: Create instances");
        UserService userService = new UserService();
        OrderService orderService = new OrderService();

        // Step 3: Register them in the container
        System.out.println("\nStep 3: Register in container");
        container.registerBean("userService", userService);
        container.registerBean("productService", new ProductService());
        container.registerBean("orderService", orderService);

        // Step 4: Show what's in the container
        container.listBeans();

        // Step 5: THE MAGIC - Autowire dependencies
        System.out.println("\nStep 5: Autowire dependencies");
        container.autowire(orderService);  // This injects userService into orderService

        // Step 6: Use the service
        System.out.println("\nStep 6: Use the service");
        orderService.placeOrder(123);

        System.out.println("\n✓ Demo complete!");
    }
}
