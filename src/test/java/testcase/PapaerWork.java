package testcase;


import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class PapaerWork {

	public static Properties prop = new Properties();
	public static FileReader fr;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		FileReader fr = new FileReader("D:\\Automation\\BQAutomationfrmaework\\src\\test\\resources\\configurations\\config.properties");
		prop.load(fr);
		
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
		driver.get(prop.getProperty("umgbaseurl"));//comment umg
		driver.findElement(By.xpath("//*[@id=\"details-button\"]")).click();//comment umg
		driver.findElement(By.xpath("//*[@id=\"proceed-link\"]")).click();//comment umg
		driver.findElement(By.id("inputEmail")).sendKeys(prop.getProperty("umgusername"));//comment umg
		driver.findElement(By.id("inputPassword")).sendKeys(prop.getProperty("umgpasswrd"));//comment umg
		
		
		/*driver.get(prop.getProperty("baseurl"));
	    driver.findElement(By.id("inputEmail")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("inputPassword")).sendKeys(prop.getProperty("passwrd"));*/
		driver.findElement(By.xpath("//button")).click();
		Thread.sleep(4000);
		
		   Wait<WebDriver> wait = new FluentWait<>(driver)
	                .withTimeout(Duration.ofSeconds(10))
	                .pollingEvery(Duration.ofMillis(500))
	                .ignoring(org.openqa.selenium.NoSuchElementException.class);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='customerQuotationsTable_filter']/label/input")));
        
		driver.findElement(By.xpath("//*[@id=\"customerQuotationsTable_filter\"]/label/input")).sendKeys(prop.getProperty("jobid"));
		
		driver.navigate().refresh(); 
	       
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[@title='View/Edit User Datasheet']")).click();
		
		//clear all inputfrom feilds beofre updating
		
	/*	((JavascriptExecutor) driver).executeScript(document.getElementById('my-form').reset());*/
	
		/*driver.findElement(By.xpath("(//input[@type='number'])")).getSize();*/
		Thread.sleep(2000);
		for(int n=1; n<=20; n++) {
			
		driver.findElement(By.xpath("(//input[@type='number'])["+n+"]")).clear();
		
		}
		driver.findElement(By.xpath("(//input[@type='text'])[10]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[11]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[12]")).clear();
		
		
		Thread.sleep(4000);
		//select drop down 
		Select se = new Select(driver.findElement(By.xpath("//*[@id=\"collapseBasic\"]/div/div[1]/div[1]/div/select")));
		se.selectByValue(prop.getProperty("layer"));
		
		//pcb x direction
		driver.findElement(By.xpath("//*[@id=\"pcbDimensionX\"]")).sendKeys((prop.getProperty("PCBDimensionX(mm)")));
	
		//pcb y direction
		driver.findElement(By.xpath("//*[@id=\"pcbDimensionY\"]")).sendKeys((prop.getProperty("PCBDimensionY(mm)")));
		
		//Board Type 
		WebElement AccNum = driver.findElement(By.xpath("//*[@id=\"boardType\"]"));
		AccNum.sendKeys((prop.getProperty("BoardType")));
		
		//arrayDimensionX
		driver.findElement(By.xpath("//*[@id=\"arrayDimensionX\"]")).sendKeys((prop.getProperty("ArrayDimensionX(mm)")));;
		
		//arrayDimensionX
		driver.findElement(By.xpath("//*[@id=\"arrayDimensionY\"]")).sendKeys((prop.getProperty("ArrayDimensionY(mm)")));
		
		//PCBsperArray
		driver.findElement(By.xpath("//*[@id=\"pcbPerArray\"]")).sendKeys((prop.getProperty("PCBsperArray")));
		
		//NumberOfPCBsInArrayXDirection
		driver.findElement(By.xpath("//*[@id=\"pcbArrayDirectionX\"]")).sendKeys((prop.getProperty("NumberOfPCBsInArrayXDirection")));
		
		//NumberOfPCBsInArrayYDirection
		driver.findElement(By.xpath("//*[@id=\"pcbArrayDirectionY\"]")).sendKeys((prop.getProperty("NumberOfPCBsInArrayYDirection")));
		
		//GapNBetweenArrayPCBsInXDirection(mm)
		driver.findElement(By.xpath("//*[@id=\"pcbGapArrayDirectionX\"]")).sendKeys((prop.getProperty("GapNBetweenArrayPCBsInXDirection(mm)")));
				
		//NumberOfPCBsinArrayYDirection
		driver.findElement(By.xpath("//*[@id=\"pcbGapArrayDirectionY\"]")).sendKeys((prop.getProperty("NumberOfPCBsinArrayYDirection")));
		
		//PCBThickness(mm)
	    driver.findElement(By.xpath("//*[@id=\"pcbThickness\"]")).sendKeys((prop.getProperty("PCBThickness(mm)")));
	    
	    //ThicknessTolerance+ve(%)
	    driver.findElement(By.xpath("//*[@id=\"thicknessTolerancePlus\"]")).sendKeys((prop.getProperty("ThicknessTolerance+ve(%)")));
		
		
	    //Thickness Tolerance-ve(%)
	    driver.findElement(By.xpath("//*[@id=\"thicknessToleranceMinus\"]")).sendKeys((prop.getProperty("ThicknessTolerance-ve(%)")));

	    
	  //NumberofDifferentDrillLevels
	    driver.findElement(By.xpath("//*[@id=\"collapseBasic\"]/div/div[4]/div[4]/div/input")).sendKeys((prop.getProperty("NumberofDifferentDrillLevels")));
	    
	
	    Actions act = new Actions(driver);
		act.sendKeys(Keys.PAGE_DOWN).build().perform();
	   
	    
		//Surface of the PCB
		
		
	//SurfaceFinish
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[1]/div[1]/div/select")).sendKeys((prop.getProperty("SurfaceFinish")));
		
		
	//SolderMask
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[1]/div[3]/div/select")).sendKeys((prop.getProperty("SolderMask")));
	
	//LegendPrint
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[1]/div[3]/div/select")).sendKeys((prop.getProperty("LegendPrint")));

	//HeatSinkPaste
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[1]/div[4]/div/select")).sendKeys((prop.getProperty("HeatSinkPaste")));
	
	
	//HardGoldPlating
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[2]/div[1]/div/select")).sendKeys((prop.getProperty("HardGoldPlating")));
	
	//SolderMaskColor
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[2]/div[2]/div/select")).sendKeys((prop.getProperty("SolderMaskColor")));
		
	//LegendColor
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[2]/div[3]/div/select")).sendKeys((prop.getProperty("LegendColor")));
		
	//HeatSinkPasteSurface/PCB
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[2]/div[4]/div/input")).sendKeys((prop.getProperty("HeatSinkPasteSurface/PCB")));
	
	
	//HardGoldPlatingThickness(nm)
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[3]/div[1]/div/input")).sendKeys((prop.getProperty("HardGoldPlatingThickness(nm)")));
	
	//SolderMaskDoubleCoat
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[3]/div[2]/div/select")).sendKeys((prop.getProperty("SolderMaskDoubleCoat")));
	
	//Carbonprint
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[3]/div[3]/div/select")).sendKeys((prop.getProperty("Carbonprint")));
	
	
	//Photoviafilling
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[3]/div[4]/div/select")).sendKeys((prop.getProperty("Photoviafilling")));
	
	
	//HardGoldPlatingSurface/Panel
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[4]/div[1]/div/input")).sendKeys((prop.getProperty("HardGoldPlatingSurface/Panel")));
	
	//UsedSolderMask
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[4]/div[2]/div/select")).sendKeys((prop.getProperty("UsedSolderMask")));
	
	//PeelableMask
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[4]/div[3]/div/select")).sendKeys((prop.getProperty("PeelableMask")));
	
	//ViaPlugging 
	driver.findElement(By.xpath("//*[@id=\"collapsePcb\"]/div/div[4]/div[4]/div/select")).sendKeys((prop.getProperty("ViaPlugging")));
	
	
	
	//Material and Stackup View
	
	
	Thread.sleep(500);
	//Tg Value(°C)
	
	WebElement TgValue = driver.findElement(By.xpath("//*[@id=\"tgVal1\"]"));
	Select selectTgValue = new Select(TgValue);
	List<WebElement> options1 = selectTgValue.getOptions();
	int randomIndex = new Random().nextInt(options1.size());
	selectTgValue.selectByIndex(randomIndex);

	Thread.sleep(500);
	//BaseMaterialVendor
	WebElement BaseMaterialVendor = driver.findElement(By.xpath("//*[@id=\"baseMaterialVend1\"]"));
	Select selectBaseMaterialVendor = new Select(BaseMaterialVendor);
	List<WebElement> options2 = selectBaseMaterialVendor.getOptions();
	int randomIndex1 = new Random().nextInt(options2.size());
	selectBaseMaterialVendor.selectByIndex(randomIndex1);
	Thread.sleep(500);
	//Base Material Family
	
	WebElement BaseMaterialFam =driver.findElement(By.xpath("//*[@id=\"baseMaterialFamily1\"]"));
	Select selectBaseMaterial = new Select(BaseMaterialFam);
	List<WebElement> options3 = selectBaseMaterial.getOptions();
	int randomIndex2 = new Random().nextInt(options3.size());
	selectBaseMaterial.selectByIndex(randomIndex2);
	
	
	int desclairow = driver.findElements(By.xpath("//*[@id=\"stackupDetailsTable\"]/tbody/tr")).size();
	
	
	// entering tolerance
	
	for(int i=1; i<=desclairow; i++) {
	driver.findElement(By.xpath("//*[@id=\"tolerancePlus"+i+"\"]")).sendKeys((prop.getProperty("ThicknessTolerance+ve(%)")));
	driver.findElement(By.xpath("//*[@id=\"toleranceMinus"+i+"\"]")).sendKeys((prop.getProperty("ThicknessTolerance+ve(%)")));
	}
	
	
	//Design
	
	//OuterLayerLineWidth(µm)
	driver.findElement(By.xpath("//*[@id=\"collapseDesign\"]/div/div[1]/div[1]/div/input")).sendKeys((prop.getProperty("OuterLayerLineWidth(µm)")));
	
	
	//InnerLayerLineWidth(µm)
	driver.findElement(By.xpath("//*[@id=\"collapseDesign\"]/div/div[1]/div[3]/div/input")).sendKeys((prop.getProperty("InnerLayerLineWidth(µm)")));
	
	//OuterLayerSpacing(µm)
	driver.findElement(By.xpath("//*[@id=\"collapseDesign\"]/div/div[1]/div[2]/div/input")).sendKeys((prop.getProperty("OuterLayerSpacing(µm)")));
	
	//InnerLayerSpacing(µm)
	driver.findElement(By.xpath("//*[@id=\"collapseDesign\"]/div/div[1]/div[4]/div/input")).sendKeys((prop.getProperty("InnerLayerSpacing(µm)")));
	
	  Actions act1 = new Actions(driver);
	  act1.sendKeys(Keys.PAGE_DOWN).build().perform();
	//routing
	driver.findElement(By.xpath("//*[@id=\"collapseDesign\"]/div/div[2]/div[1]/div/select")).sendKeys((prop.getProperty("routing")));
	
	//Scoring 
	driver.findElement(By.xpath("//*[@id=\"collapseDesign\"]/div/div[2]/div[2]/div/select")).sendKeys((prop.getProperty("Scoring")));
	
	
	//Drill Details enter
	String numdrill1 = prop.getProperty("numdrill");
	int numdrill=Integer.parseInt(numdrill1);
	for(int j=1;j<=(numdrill-1); j++ ) {
	
	driver.findElement(By.xpath("//*[@id=\"drill_row"+j+"\"]/td[12]/span[1]/i")).click();
	driver.findElement(By.xpath("//*[@id=\"direction"+j+"\"]")).sendKeys((prop.getProperty("Direction")));
	driver.findElement(By.xpath("//*[@id=\"from"+j+"\"]")).sendKeys((prop.getProperty("drillfrom")));
	driver.findElement(By.xpath("//*[@id=\"to"+j+"\"]")).sendKeys((prop.getProperty("drillTo")));
	driver.findElement(By.xpath("//*[@id=\"technology"+j+"\"]")).sendKeys((prop.getProperty("technolgy")));
	driver.findElement(By.xpath("//*[@id=\"plating_type"+j+"\"]")).sendKeys((prop.getProperty("platingtype")));
	driver.findElement(By.xpath("//*[@id=\"drill_hole_type"+j+"\"]")).sendKeys((prop.getProperty("drillholetype")));
	driver.findElement(By.xpath("//*[@id=\"filling"+j+"\"]")).sendKeys((prop.getProperty("filling")));
	
	}
	
	
	//Special Work Process
	
	driver.findElement(By.xpath("//*[@id=\"collapseSpecialWork\"]/div/div[2]/div[4]/div/select")).sendKeys((prop.getProperty("impedencecontl")));//Impedence
	driver.findElement(By.xpath("//*[@id=\"collapseSpecialWork\"]/div/div[1]/div[1]/div/select")).sendKeys((prop.getProperty("busbar")));//Busbar
	driver.findElement(By.xpath("//*[@id=\"collapseSpecialWork\"]/div/div[3]/div[4]/div/select")).sendKeys((prop.getProperty("sbu")));//sbu
	driver.findElement(By.xpath("//*[@id=\"collapseSpecialWork\"]/div/div[3]/div[4]/div/select")).sendKeys((prop.getProperty("thickcoppertechnolgy")));//thickcoppertechnolgy
	driver.findElement(By.xpath("//*[@id=\"collapseSpecialWork\"]/div/div[4]/div[2]/div/select")).sendKeys((prop.getProperty("ResinFilling(IPC4761typeVll)")));//ResinFilling(IPC4761typeVll)
	driver.findElement(By.xpath("//*[@id=\"collapseSpecialWork\"]/div/div[2]/div[2]/div/select")).sendKeys((prop.getProperty("HDi")));//HDi
	driver.findElement(By.xpath("//*[@id=\"collapseSpecialWork\"]/div/div[4]/div[4]/div/select")).sendKeys((prop.getProperty("wirelaid")));//wirelaid
	
        
//        driver.quit();
        Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"saveUserDatasheet\"]/i")).click();
//	driver.quit();
	
	driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div/div/div[1]/div/div/h5/a/i")).click();//back to datasheet
	Thread.sleep(2000);
	
	/*driver.findElement(By.xpath("//input[@type='search']")).sendKeys(prop.getProperty("jobid"));
	Thread.sleep(2000);*/
	driver.findElement(By.xpath("//*[@id=\"customerQuotationsTable\"]/tbody/tr/td[12]/div[2]/div[3]")).click();
	
	
	int scroll = 1;

	do {
		Actions acti = new Actions(driver);
		acti.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(2000);
		scroll++;

	} while (scroll <= 3);
	}	

}
