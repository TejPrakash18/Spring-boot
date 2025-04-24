package com.tej.SpringSecurity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/admin")
    public String admin() {
        return "Welcome, Admin!";
    }

    @GetMapping("/user")
    public String user() {
        return "Welcome, User!";
    }

    @GetMapping("/common")
    public String common() {
        return "This is a common endpoint!";
    }
}
