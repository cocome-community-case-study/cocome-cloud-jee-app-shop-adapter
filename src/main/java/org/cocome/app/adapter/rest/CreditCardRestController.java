package org.cocome.app.adapter.rest;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.cocome.app.adapter.json.JsonCreditcard;
import org.cocome.app.adapter.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardRestController {
	
	@Autowired
	private CreditCardService creditCardService;

    @RequestMapping("/")
    public List<JsonCreditcard> index(HttpServletResponse response, @RequestParam String user) {
    	response.setHeader("Access-Control-Allow-Origin","*");
        return creditCardService.getCarts(user);
    }

    @RequestMapping("/add")
    public void add(HttpServletResponse response, @RequestParam JsonCreditcard card, @RequestParam String user) {
    	response.setHeader("Access-Control-Allow-Origin","*");
    	creditCardService.addCard(user,card);
    }
   
    @RequestMapping("/checkpin")
    public Boolean checkpin(HttpServletResponse response,  @RequestParam JsonCreditcard card, @RequestParam String user, @RequestParam Integer pin) {
    	response.setHeader("Access-Control-Allow-Origin","*");
        return creditCardService.checkPin(user,card,pin);
    }
}