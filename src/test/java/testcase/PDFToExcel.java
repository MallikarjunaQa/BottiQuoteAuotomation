package testcase;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PDFToExcel {

	public static Properties prop = new Properties();
	public static FileReader fr;
	
	

	public static void main(String[] args) throws InterruptedException, IOException  {

		FileReader fr = new FileReader("D:\\Automation\\BQAutomationfrmaework\\src\\test\\resources\\configurations\\config.properties");
		prop.load(fr);
		
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        

		driver.get(prop.getProperty("baseurl"));//	driver.findElement(By.id("inputEmail")).sendKeys(prop.getProperty("username"));//
		driver.findElement(By.id("inputEmail")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("inputPassword")).sendKeys(prop.getProperty("passwrd"));
		
		driver.findElement(By.xpath("//button")).click();

		Thread.sleep(5000);
		
		
		// clear all botton 
		driver.findElement(By.xpath("//*[@id=\"customerQuotationsTable_filter\"]/label/input")).sendKeys((prop.getProperty("jobid")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div/div[1]/div/div[2]/div[1]/div[3]/button[3]")).click();
	
		Thread.sleep(2000);
		
		// check final print taken or not to naviagte Po page
		driver.findElement(By.xpath("//*[@id=\"customerQuotationsTable_filter\"]/label/input")).sendKeys("7072087_1");
		Thread.sleep(1000);
		 WebElement statusElement = driver.findElement(By.xpath("//*[@id=\"customerQuotationsTable\"]/tbody/tr/td[8]"));
		 String status = statusElement.getText();
		 System.out.println(status);
		 if (status.equalsIgnoreCase("Closed")) {
	     WebElement plusIcon = driver.findElement(By.xpath("\r\n" + "\r\n" +"//*[@id=\"customerQuotationsTable\"]/tbody/tr/td[12]/div[2]/div[2]/span"));
	     plusIcon.click();
		   }
		 else {
			 System.out.println("close the order by clicking the 'Final Print' button on the calculation page");
		 }
		 
		 //expand side nav bar 
		
		 try {
	            // Replace these with the appropriate locators for your elements
			 WebElement plusIcon1 = driver.findElement(By.xpath("//*[@id=\\\"sidebar-container\\\"]/ul/a[1]/div"));
			 WebElement plusIcon2 = driver.findElement(By.xpath("//*[@id=\\\"actionListDiv\\\"]/a[6]/div/span[2]/span[1]"));
			 plusIcon1.click();
			 plusIcon2.click();
	            System.out.println("Both elements are clickable and have been clicked.");
	        } catch (Exception e) {
	            System.out.println("One or both elements are not clickable or an error occurred.");
	        }

		 
		 
		 
		/*driver .quit();
		*/
	}

}

		