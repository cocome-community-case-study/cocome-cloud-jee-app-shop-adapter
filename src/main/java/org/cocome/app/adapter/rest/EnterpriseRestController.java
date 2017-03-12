package org.cocome.app.adapter.rest;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.cocome.app.adapter.json.JsonEnterprise;
import org.cocome.app.adapter.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseRestController {

	@Autowired
	private EnterpriseService enterpriseService;

	@RequestMapping("/")
	public List<JsonEnterprise> index(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin","*");
		return enterpriseService.getEnterprises();
	}

}