package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadpropertiesFile {

	public static void main(String[] args) throws IOException {
		
		FileReader fr = new FileReader("D:\\Automation\\BQAutomationfrmaework\\src\\test\\resources\\configurations\\config.properties");
		Properties p = new Properties();
		p.load(fr);
		System.out.println(p.getProperty("browser"));
		System.out.println(p.getProperty("baseurl"));

	}

}
