package com.example.spring_initializr3.ControllerHello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HelloController {

    @RequestMapping("/")
    public String initial() {
        return "Welcome to the application, \n" +
                "this is just a test...";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/hola")
    public String hola() {
        return "Hola Mundo!";
    }
}
