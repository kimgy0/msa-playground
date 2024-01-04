package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/first-service")
public class FirstServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First Service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-service-request-header") String header) {
        return String.format("Welcome to the First Service %s", header).toString();
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        return String.format("Hi, there. This is a message from First Service. server.port = %s", request.getServerPort());
    }
}
