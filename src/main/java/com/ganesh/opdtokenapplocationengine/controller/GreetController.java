package com.ganesh.opdtokenapplocationengine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class GreetController {
    @GetMapping("/")
    public String Greet(){
        return """
                <html>
                <body style="background-color:yellow";>
                <p style="color:black;">This project is running</p>
                </body>
                </html>
                """;
    }
}
