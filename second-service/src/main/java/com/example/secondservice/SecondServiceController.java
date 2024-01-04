package com.example.secondservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/second-service")
public class SecondServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Second Service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-service-request-header") String header) {
        return String.format("Welcome to the Second Service %s", header).toString();
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        return String.format("Hi, there. This is a message from Second Service. server.port = %s", request.getServerPort());
    }
}
