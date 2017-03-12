package org.cocome.app.adapter.service;

import java.util.ArrayList;
import java.util.List;

import org.cocome.app.adapter.json.JsonItem;
import org.cocome.app.adapter.json.JsonStore;
import org.springframework.stereotype.Component;

@Component
public class ItemService {

	private List<JsonItem> items = new ArrayList<>();

	public ItemService()
	{
        this.items.add(new JsonItem(1, "JsonItem ABC", "http://placekitten.com/g/140/140", this.getDescrption(), 25.23, 8));
        this.items.add(new JsonItem(2, "JsonItem CDE", "http://placekitten.com/g/140/140", this.getDescrption(), 34.34, 34));
        this.items.add(new JsonItem(3, "JsonItem EFG", "http://placekitten.com/g/140/140", this.getDescrption(), 22.11, 45));
        this.items.add(new JsonItem(4, "JsonItem HIJ", "http://placekitten.com/g/140/140", this.getDescrption(), 11.15, 33));
        this.items.add(new JsonItem(5, "JsonItem KLM", "http://placekitten.com/g/140/140", this.getDescrption(), 67.54, 34));

        this.items.add(new JsonItem(6, "JsonItem ABC", "http://placekitten.com/g/140/140", this.getDescrption(), 15.13, 123));
        this.items.add(new JsonItem(7, "JsonItem ABCD", "http://placekitten.com/g/140/140", this.getDescrption(), 25.23, 13));
        this.items.add(new JsonItem(8, "JsonItem ABCDE", "http://placekitten.com/g/140/140", this.getDescrption(), 4.54, 12));
        this.items.add(new JsonItem(9, "JsonItem ABCDEF", "http://placekitten.com/g/140/140", this.getDescrption(), 34.23, 23));
        this.items.add(new JsonItem(10, "JsonItem ABCDEFG", "http://placekitten.com/g/140/140", this.getDescrption(), 25.66, 123));

        this.items.add(new JsonItem(11, "JsonItem ABC1", "http://placekitten.com/g/140/140", this.getDescrption(), 25.23, 123));
        this.items.add(new JsonItem(12, "JsonItem ABC2", "http://placekitten.com/g/140/140", this.getDescrption(), 25.23, 123));
        this.items.add(new JsonItem(13, "JsonItem ABC3", "http://placekitten.com/g/140/140", this.getDescrption(), 25.23, 123));
        this.items.add(new JsonItem(14, "JsonItem ABC4", "http://placekitten.com/g/140/140", this.getDescrption(), 25.23, 123));
        this.items.add(new JsonItem(15, "JsonItem ABC5", "http://placekitten.com/g/140/140", this.getDescrption(), 25.23, 123));

        this.items.add(new JsonItem(16, "JsonItem ABCBBBBB", "http://placekitten.com/g/140/140", this.getDescrption(), 25.23, 123));
        this.items.add(new JsonItem(17, "JsonItem ABCBBBBBB", "http://placekitten.com/g/140/140", this.getDescrption(), 25.23, 123));	}

	private String getDescrption() {
		return "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
	}

	public List<JsonItem> getAllItems(JsonStore store) {
		return this.items;
	}

	public List<JsonItem> getAllItemsByQuery( JsonStore store,  String query) {
		List<JsonItem> result = new ArrayList<JsonItem>();
		
		for (JsonItem jsonItem : this.items) {
			if(jsonItem.getName().contains(query))
				result.add(jsonItem);
		}
        return result;
	}

	public JsonItem getItemById( JsonStore store,  Integer id) {
		for (JsonItem jsonItem : this.items) {
			if(jsonItem.getId() == id)
				return jsonItem;
		}
        return null;
	}

}
