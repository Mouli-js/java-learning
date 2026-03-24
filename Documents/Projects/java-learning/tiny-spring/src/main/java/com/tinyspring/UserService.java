package com.tinyspring;

@tinyspring.Service
public class UserService {
    public String getUser() {
        return "John Doe";
    }

    public int getUserAge() {
        return 30;
    }
}
