package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import testcase.FirstTest1;

public class BaseTest {

	public static WebDriver driver = null;
	public static Properties prop = new Properties();
	public static FileReader fr;

	@BeforeTest
	public static void setUp() throws IOException, InterruptedException

	{

		if (driver == null) {
			FileReader fr = new FileReader("D:\\Automation\\BQAutomationfrmaework\\src\\test\\resources\\configurations\\config.properties");
			prop.load(fr);
		}

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			options.addArguments("--remote-allow-origins=*");
	        WebDriver driver = new ChromeDriver(options);
	        driver.manage().window().maximize();
			driver.get(prop.getProperty("baseurl"));

		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get(prop.getProperty("baseurl"));
			Thread.sleep(4000);
		} 

	}
	

	@AfterTest
	public static void teardown() {
		driver.manage().window().minimize();
		driver.close();

	}
	
	
	public static void main(String args[]) throws Throwable {

		
		setUp();
		/*FirstTest1();*/
		teardown();
	}


	

}
