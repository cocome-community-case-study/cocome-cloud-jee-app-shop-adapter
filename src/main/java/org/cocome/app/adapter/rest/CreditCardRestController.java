package org.cocome.app.adapter.rest;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.cocome.app.adapter.json.JsonCreditcard;
import org.cocome.app.adapter.json.JsonStore;
import org.cocome.app.adapter.service.CreditCardService;
import org.cocome.cloud.logic.stub.NotInDatabaseException_Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardRestController {
	
	@Autowired
	private CreditCardService creditCardService;

    @RequestMapping("/")
    public List<JsonCreditcard> index(HttpServletResponse response, @RequestParam String user, @RequestParam String password) throws NotInDatabaseException_Exception {
    	response.setHeader("Access-Control-Allow-Origin","*");
        return creditCardService.getCarts(user, password);
    }

    @RequestMapping("/add")
    public List<JsonCreditcard> add(HttpServletResponse response, @RequestParam String card, @RequestParam String user, @RequestParam String password) throws NotInDatabaseException_Exception, JsonParseException, JsonMappingException, IOException {
    	response.setHeader("Access-Control-Allow-Origin","*");
    	JsonCreditcard jsoncard = new ObjectMapper().readValue(card, JsonCreditcard.class);
    	return creditCardService.addCard(user,password, jsoncard);
    }
   
    @RequestMapping("/checkpin")
    public Boolean checkpin(HttpServletResponse response,  @RequestParam String card, @RequestParam String user, @RequestParam Integer pin) throws JsonParseException, JsonMappingException, IOException {
    	response.setHeader("Access-Control-Allow-Origin","*");
    	JsonCreditcard jsoncard = new ObjectMapper().readValue(card, JsonCreditcard.class);
        return creditCardService.checkPin(user,jsoncard,pin);
    }
}