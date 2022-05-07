package day13_14;

import java.util.*;
import java.io.*;

public class PropertiesEx3 {
	public static void main(String[] args) {
		Properties prop = new Properties();
		
		prop.setProperty("timeout", "30");
		prop.setProperty("language", "kr");
		prop.setProperty("size", "10");
		prop.setProperty("capacity", "10");
		
		try {
			prop.store(new FileOutputStream("output.txt"), "Properties Excample");
			
			prop.storeToXML(new FileOutputStream("output.xml"), "Properties Example");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
