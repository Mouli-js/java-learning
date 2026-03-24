package com.tinyspring;

@tinyspring.Service
public class OrderService {
    @Autowired

    private UserService userService;

    @Autowired
    private ProductService productService;

    public void placeOrder(int productId) {
        String user = userService.getUser();
        String product = productService.getProduct(productId);
        System.out.println(user + " ordered " + product);
    }
}
