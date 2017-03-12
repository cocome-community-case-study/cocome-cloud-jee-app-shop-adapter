package org.cocome.app.adapter.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ApiRestController {

    @RequestMapping("/")
    public String carts() {
        return "Greetings from Cocome-App-Shop API!";
    }
    


}