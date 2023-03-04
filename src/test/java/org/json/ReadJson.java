package org.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {
	public static void main(String[] args) throws IOException, ParseException {
		
		FileReader reader = new FileReader("C:\\Users\\DELL\\eclipse-workspace\\Api\\src\\test\\resources\\Sample.json");
		
		JSONParser jp = new JSONParser();
		
		Object parse = jp.parse(reader);
		
		//type casting
		
		JSONObject obj = (JSONObject)parse;
		
		Object name = obj.get("name");
		System.out.println(name);
		Object age = obj.get("age");
		System.out.println(age);
		Object status = obj.get("status");
		System.out.println(status);
		Object address = obj.get("employeeAddress");
		System.out.println(address);
		Object courses = obj.get("courses");
		System.out.println(courses);
	}


}
