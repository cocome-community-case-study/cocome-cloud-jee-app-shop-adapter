package org.cocome.app.adapter.rest;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.cocome.app.adapter.json.JsonItem;
import org.cocome.app.adapter.json.JsonStore;
import org.cocome.app.adapter.service.ItemService;
import org.cocome.cloud.logic.stub.NotInDatabaseException_Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/items")
public class ItemsRestController {

	@Autowired
	private ItemService itemsService;

	@RequestMapping("/")
	public List<JsonItem> index(HttpServletResponse response, @RequestParam String store)
			throws NotInDatabaseException_Exception, JsonParseException, JsonMappingException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JsonStore jsonstore = new ObjectMapper().readValue(store, JsonStore.class);
		return itemsService.getAllItems(jsonstore);
	}

	@RequestMapping("/query")
	public List<JsonItem> query(HttpServletResponse response, @RequestParam String store, @RequestParam String query)
			throws NotInDatabaseException_Exception, JsonParseException, JsonMappingException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JsonStore jsonstore = new ObjectMapper().readValue(store, JsonStore.class);
		return itemsService.getAllItemsByQuery(jsonstore, query);
	}

	@RequestMapping("/id")
	public JsonItem id(HttpServletResponse response, @RequestParam String store, @RequestParam Integer id)
			throws NotInDatabaseException_Exception, JsonParseException, JsonMappingException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JsonStore jsonstore = new ObjectMapper().readValue(store, JsonStore.class);
		return itemsService.getItemById(jsonstore, Long.valueOf(id));
	}

}