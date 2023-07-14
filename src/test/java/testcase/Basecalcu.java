package testcase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Basecalcu /*extends FirstTest1*/ {
	
	/*static GlobalVariables globalvariable = null;*/
	/*globalvariable = new GlobalVariables();*/
	
	public static void basetest() throws InterruptedException  {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Automation\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://192.168.150.20:8080/bottie_quote/logout.html");
		driver.findElement(By.id("inputEmail")).sendKeys("mallikarjuna.ss");
		driver.findElement(By.id("inputPassword")).sendKeys("Mallik@1234");
		driver.findElement(By.xpath("//button")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// datasheet validation
		System.out.println("eneterd to data sheet");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"customerQuotationsTable_filter\"]/label/input")).sendKeys(("7070992_1"));
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tbody/tr[1]/td[11]/div[2]/div/span/i")).click();
		driver.findElement(By.xpath("//*[@id=\"expandClass\"]/i")).click();
		Thread.sleep(3000);

		// Base calculation
		driver.findElement(By.xpath("//*[@id=\"calculatePageId\"]/i")).click();// click on base claculayion icon in data sheet 
		driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div[1]/div[2]/div[4]/div[1]/div/div/div/div[2]/span/i")).click();
		Thread.sleep(3000);
		
		WebElement bcustcode = driver.findElement(By.xpath("//*[@id=\"customerAccountNo\"]"));
		String bcustcode1=bcustcode.getAttribute("value");
		WebElement bcustomer = driver.findElement(By.xpath("//*[@id=\"customer\"]"));
		String bcustomer1=bcustomer.getAttribute("value");
		WebElement bcustomercity = driver.findElement(By.xpath("//*[@id=\"customarCity\"]"));
		String bcustomercity1=bcustomercity.getAttribute("value");
		WebElement bjobid = driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div[1]/div[2]/div[3]/div/div[2]/div[2]/div/div[6]/input"));
		String bjobid1=bjobid.getAttribute("value");
		WebElement bcustomerdwg = driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div[1]/div[2]/div[3]/div/div[2]/div[2]/div/div[7]/input"));
		String bcustomerdwg1=bcustomerdwg.getAttribute("value");
		WebElement binqnumb = driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div[1]/div[2]/div[3]/div/div[2]/div[2]/div/div[8]/input"));
		String binqnumb1=binqnumb.getAttribute("value");
		WebElement brfqdate= driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div[1]/div[2]/div[3]/div/div[2]/div[2]/div/div[9]/input"));
		String brfqdate1=brfqdate.getAttribute("value");
		
		System.out.println(bcustcode1);
		System.out.println(bcustomer1);
		System.out.println(bcustomercity1);
		System.out.println(bjobid1);
		System.out.println(bcustomerdwg1);
		System.out.println(binqnumb1);
		System.out.println(brfqdate1);
		
		
		/*System.out.println(obj.acc);*/
		
		
		/*System.out.println(globalvariable.getAccountnum());*/
		
		
		/*driver.quit();*/
		
		
	}

}

