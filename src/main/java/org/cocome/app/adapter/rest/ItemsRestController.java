package org.cocome.app.adapter.rest;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.cocome.app.adapter.json.JsonItem;
import org.cocome.app.adapter.json.JsonStore;
import org.cocome.app.adapter.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/items")
public class ItemsRestController {

	@Autowired
	private ItemService itemsService;

	@RequestMapping("/")
	public List<JsonItem> index(HttpServletResponse response, @RequestParam JsonStore store) {
		response.setHeader("Access-Control-Allow-Origin","*");
		return itemsService.getAllItems(store);
	}
	
	@RequestMapping("/query")
	public List<JsonItem> query(HttpServletResponse response, @RequestParam JsonStore store, @RequestParam String query) {
		response.setHeader("Access-Control-Allow-Origin","*");
		return itemsService.getAllItemsByQuery(store, query);
	}
	
	@RequestMapping("/id")
	public JsonItem id(HttpServletResponse response, @RequestParam JsonStore store, @RequestParam int id) {
		response.setHeader("Access-Control-Allow-Origin","*");
		return itemsService.getItemById(store, id);
	}

}