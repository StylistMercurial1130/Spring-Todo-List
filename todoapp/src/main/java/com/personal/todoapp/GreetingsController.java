package com.personal.todoapp;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
    private final AtomicLong counter = new AtomicLong();
    private static final String template = "Hello, %s!";

    @GetMapping("/greet")
    public Greeting greet(@RequestParam(defaultValue = "world") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
