package org.cocome.app.adapter.rest;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.cocome.app.adapter.json.JsonCreditcard;
import org.cocome.app.adapter.json.JsonEnterprise;
import org.cocome.app.adapter.json.JsonItem;
import org.cocome.app.adapter.json.JsonStore;
import org.cocome.app.adapter.service.EnterpriseService;
import org.cocome.app.adapter.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/sale")
public class SaleRestController {

	@Autowired
	private SaleService saleService;

	@RequestMapping("/")
	public Boolean index(HttpServletResponse response, @RequestParam String user, @RequestParam JsonStore store, @RequestParam List<JsonItem> items, JsonCreditcard card) {
		response.setHeader("Access-Control-Allow-Origin","*");
		return saleService.processSale(user,store,items,card);
	}

}