package com.jsonxml.demo;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class dataUtilities {
	//method to convert Xml response string to json response string
		public static String Convert(String xml) {
			JSONObject json1 = XML.toJSONObject(xml);
			String json = json1.toString(4);

			return json;
		}

		//method to compare the content of xml and json responses
		public static boolean compareJson(JSONArray obj1,JSONArray obj2) throws IOException {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode tree1 = mapper.readTree(obj1.toString());
			JsonNode tree2 = mapper.readTree(obj2.toString());
			return tree1.equals(tree2);
		}
		public static boolean compareJson(JSONObject obj1,JSONObject obj2) throws IOException {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode tree1 = mapper.readTree(obj1.toString());
			JsonNode tree2 = mapper.readTree(obj2.toString());
			return tree1.equals(tree2);
		}

}
