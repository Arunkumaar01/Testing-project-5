package org.deserilisation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Deserilisation {
	
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		FileReader reader = new FileReader("C:\\Users\\DELL\\eclipse-workspace\\Api\\src\\test\\resources\\Sample.json");
		
		ObjectMapper map = new ObjectMapper();
		
		PojoRead readValue = map.readValue(reader, PojoRead.class);
		
		System.out.println(readValue.getName());
		System.out.println(readValue.getAge());
		System.out.println(readValue.isStatus());
		System.out.println(readValue.getEmployeeAddress().getCity());
		System.out.println(readValue.getEmployeeAddress().getState());
		System.out.println(readValue.getEmployeeAddress().getCountry());
		System.out.println(readValue.getCourses());
		
	}


}
