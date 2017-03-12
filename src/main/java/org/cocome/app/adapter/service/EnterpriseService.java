package org.cocome.app.adapter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.cocome.app.adapter.json.JsonEnterprise;
import org.cocome.app.adapter.json.JsonStore;
import org.springframework.stereotype.Component;

@Component
public class EnterpriseService {

	public List<JsonEnterprise> getEnterprises() {

		List<JsonEnterprise> list = new ArrayList<>();

		list.add(new JsonEnterprise("JsonEnterprise 123", Arrays.asList(new JsonStore("JsonStore 1"),
				new JsonStore("JsonStore 2"), new JsonStore("JsonStore 3"))));
		list.add(new JsonEnterprise("JsonEnterprise 456", Arrays.asList(new JsonStore("JsonStore 4"),
				new JsonStore("JsonStore 5"), new JsonStore("JsonStore 6"))));
		list.add(new JsonEnterprise("JsonEnterprise 789", Arrays.asList(new JsonStore("JsonStore 7"),
				new JsonStore("JsonStore 8"), new JsonStore("JsonStore 9"))));

		return list;
	}

}
