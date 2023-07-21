package testcase;

import java.awt.AWTException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.Wait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateRfq {
//SSM
	public static Properties prop = new Properties();
	public static FileReader fr;
	public static String UmgRfqnum =null;
	
	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		
		FileReader fr = new FileReader("D:\\Automation\\BQAutomationfrmaework\\src\\test\\resources\\configurations\\config.properties");
		prop.load(fr);
	
		
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        options.addArguments("disable-cache"); // Disable cache
        options.addArguments("disable-application-cache"); // Disable application cache
        options.addArguments("disable-offline-load-stale-cache"); // Disable offline load stale cache
        options.addArguments("disk-cache-size=0"); // Set disk cache size to 0
        options.addArguments("media-cache-size=0"); // Set media cache size to 0

        driver.manage().deleteAllCookies(); // Delete all cookies
        driver.navigate().refresh(); 
       

     /*
        driver.get(prop.getProperty("umgbaseurl"));//comment umg
		driver.findElement(By.xpath("//*[@id=\"details-button\"]")).click();//comment umg
		driver.findElement(By.xpath("//*[@id=\"proceed-link\"]")).click();//comment umg
		driver.findElement(By.id("inputEmail")).sendKeys(prop.getProperty("umgusername"));//comment umg
		driver.findElement(By.id("inputPassword")).sendKeys(prop.getProperty("umgpasswrd"));//comment umg
*/

        driver.get(prop.getProperty("baseurl"));//
        driver.findElement(By.id("inputEmail")).sendKeys(prop.getProperty("username"));//
		driver.findElement(By.id("inputPassword")).sendKeys(prop.getProperty("passwrd"));//
		
		
		driver.findElement(By.xpath("//button")).click();
		
	
		Thread.sleep(8000);
		WebElement element = driver.findElement(By.xpath("//button"));
		Actions actions = new Actions(driver);
		actions.click(element).build().perform();
		
		
		/*driver.findElement(By.xpath("//button")).click();*/
		
		
		
		Thread.sleep(4000);

		

		// Create R.F.Q
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div/div[1]/div/div[2]/div[1]/div[3]/button[2]/span")).click();

		driver.navigate().refresh(); // Perform a hard refresh
		
		 WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"newOrderSave\"]/i"));
	     saveButton.click();
	     Thread.sleep(3000);
		 CreateRfq.captureScreenshot(driver, UmgRfqnum +" MandatoryfeildsCreatePage");
		
		//select random Customer Account Number
		
		WebElement AccNum = driver.findElement(By.id("customerAccountNo")); 
		try {
            Select select = new Select(AccNum);
            select.selectByValue(getRandomOption(60, 75));
        } catch (UnexpectedTagNameException e) {
            // If the element is not a <select> element, assume it's an <input> element and set its value
        	AccNum.sendKeys(getRandomOption(60, 75));
        }
		WebElement ulElement = driver.findElement(By.xpath("(//ul)[5]"));
		List<WebElement> liElements = ulElement.findElements(By.tagName("li"));
		Random random = new Random();
        int index = random.nextInt(liElements.size());
        driver.findElement(By.xpath("//li[contains(@id,'option-"+index+"')]")).click();
        
        Thread.sleep(4000);
       // SelctRandom sap or Siemens
        WebElement Calculationtype = driver.findElement(By.xpath("//*[@id=\"calculationType\"]"));
        Select select = new Select(Calculationtype);
        List<WebElement> options1 = select.getOptions();
        int numberOfOptionsToSelect = 2;
        
        Random random1 = new Random();
        for (int i = 1; i < numberOfOptionsToSelect; i++) {
            int randomIndex = random1.nextInt(options1.size());
            select.selectByIndex(randomIndex);
          
        }

        //enter alpha numeric charcater in InquiryNumber
        driver.findElement(By.xpath("//*[@id=\"enquiryNumber\"]")).sendKeys("Aoutomation test comment @12345");
        
        //Inquiry Date verify with created date
		driver.findElement(By.xpath("//*[@id=\"custRFQDocumentationType\"]")).sendKeys("Data (Gerber,ODB,etc.)");
 
		String date1=driver.findElement(By.xpath("//*[@id=\"enquiryDate\"]")).getAttribute("value");
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String str = formatter.format(date);
	
		if (date1.compareTo(str) == 0) {
		System.out.println("Inquiry Date:correct");
		}
		else 
			System.out.println("Inquiry date suppose to be a job created date");
	
        //enter alpha numeric charcater in drawingNumber
        driver.findElement(By.xpath("//*[@id=\"drawingNumber\"]")).sendKeys("Aoutomation test comment @12345");
        
        //select random Customer RFQ Documentation

        Thread.sleep(2000);
		WebElement CustomerRFQDocumentation =driver.findElement(By.xpath("//*[@id=\"custRFQDocumentationType\"]"));
		Select select1 = new Select(CustomerRFQDocumentation);
	    List<WebElement> options11 = select1.getOptions();
        int numberOfOptionsToSelect1 = 2;
        
        Random random11 = new Random();
        for (int i = 1; i < numberOfOptionsToSelect1; i++) {
            int randomIndex1 = random11.nextInt(options11.size());
            select1.selectByIndex(randomIndex1);
        }
        
	     
	    //Send random number grater than 500 to 2000 to Quantity / Year
		 driver.findElement(By.xpath("//*[@id=\"quantity\"]")).sendKeys(getRandomOption(500, 2000));
		 
	     //selct random priority 
	    
		 WebElement priority =driver.findElement(By.xpath("//*[@id=\"priority\"]"));
	     Select select2 = new Select(priority);
	     List<WebElement> options15 = select2.getOptions();
	     int numberOfOptionsToSelect11 = 1;
	     
	        for (int i = 0; i < numberOfOptionsToSelect11; i++) {
	            int randomIndex = random11.nextInt(options15.size());
	            select2.selectByIndex(randomIndex);
	        }
	        
	      //Due Date Unimicron to Customer, 4 days adhed from date create
	        
			String date2=driver.findElement(By.xpath("//*[@id=\"dueDateUnimToCustm\"]")).getAttribute("value");//fetch due date umg tp cust
		
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
		    c.add(Calendar.DATE, 4); // Adding 4 days
			String output = sdf.format(c.getTime());
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			/*Calendar c2 = Calendar.getInstance();*/
		    c.add(Calendar.DATE, -3); // Adding 1 days
			String output2 = sdf2.format(c.getTime());
		
			String priorit = driver.findElement(By.xpath("//*[@id=\"priority\"]")).getAttribute("value");
			if (priorit.equals("Normal")) {
				
			if (date2.equalsIgnoreCase(output)) {
				System.out.println("Due Date Unimicron to Customer:correct");
			}else
			{
				System.out.println("Due Date Unimicron to Customer:Incorrect in normal condiation");
			} 
			
			}
		

			if (priorit.equals("QTA")) {
				
				if (date2.equalsIgnoreCase(output2)) {
					System.out.println("Due Date Unimicron to Customer:correct");
				}else
				{
					System.out.println("Due date Umg to costomer:Incorrect QTA condiation");
				} 
				
				}
		
			//Due Date NCS to Unimicron
			
			String date4=driver.findElement(By.xpath("//*[@id=\"dueDateNcsToUnim\"]")).getAttribute("value");
			if (date4.compareTo(output2) == 0) {
				System.out.println("Due Date NCS to Unimicron :correct");
			}else
			{
				System.out.println("Due Date NCS to Unimicron :incorrect");
			}
			
			
		//to check read only feilds
			
			// List of field locators
			List<By> fieldLocators = new ArrayList<>();
			fieldLocators.add(By.xpath("//*[@id=\"customarName\"]"));//Customer Name feild
			fieldLocators.add(By.xpath("//*[@id=\"customarCity\"]"));//Customer City feild
			fieldLocators.add(By.xpath("//*[@id=\"closureDateUnimToCustm\"]"));//Closure Date(Unimicron to Customer)
			fieldLocators.add(By.xpath("//*[@id=\"predecessor\"]"));//Predecessor / Job Name
			fieldLocators.add(By.xpath("//*[@id=\"unimtocustStatus\"]"));//Status(Unimicron to Customer)
			fieldLocators.add(By.xpath("//*[@id=\"status\"]"));//Status (NCS to UMG)
			fieldLocators.add(By.xpath("//*[@id=\"dueDateNcsToUnim\"]"));//	Due Date NCS to Unimicron

			// Iterate over the field locators
			for (By locator : fieldLocators) {
			    WebElement field = driver.findElement(locator);
			    /*String readonlyAttribute = field.getAttribute("readonly");*/

			    if (!field.isEnabled()) {
			        System.out.println("read-only feilds correct");
			    } else {
			        System.out.println("Field with locator " + locator + " should be read-only; hereit is incorrect");
			    }
			}

			
			// check defult value Open Status(Unimicron to Customer) and Closure Date (NCS to UMG)
			
			 WebElement field = driver.findElement(By.xpath("//*[@id=\"unimtocustStatus\"]"));
			 String fieldValue = field.getAttribute("value");
			 String expectedValue = "Open";
			 /*WebElement field2 = driver.findElement(By.xpath("//*[@id=\"status\"]"));*/
			 String fieldValue2 = field.getAttribute("value");
			 
		        if (fieldValue.equals(expectedValue) && fieldValue2.equals(expectedValue)) {
		            System.out.println("DueDate NCS to Unimicron has default value:"+expectedValue+":Correct");
		            System.out.println("Closure Date (NCS to UMG) default value: " +expectedValue +":Correct");
		        } else {
		            System.out.println("Due Date NCS to Unimicron does not have the default value.");
		            System.out.println("Closure Date (NCS to UMG)does not have the default value : Open ; incorrect");
		        }
		        
		        
		     // check sales and ncs engineer/sales reamrks    
		     WebElement textArea1 = driver.findElement(By.xpath("//*[@id=\"salesInformation\"]")); // Replace "textAreaId" with the actual ID of the text area element
		     WebElement textArea2 = driver.findElement(By.xpath("//*[@id=\"createComment\"]")); // Replace "textAreaId" with the actual ID of the text area element

		        
		     
		     Random randomt = new Random();
		     StringBuilder remarks = new StringBuilder();

		     for (int i = 0; i < 100; i++) {
		         char randomChar = (char) (randomt.nextInt(50) + 'a');
		         remarks.append(randomChar);
		     }

		     String remarksText = remarks.toString();
		     textArea1.sendKeys(remarksText);
		     textArea2.sendKeys(remarksText);

		 
		      // seklect random Umg RFQ number 
		     
		 	WebElement UmgRFQNumber = driver.findElement(By.xpath("//*[@id=\"materialNo\"]")); 
			try {
	            Select selectu = new Select(AccNum);
	            selectu.selectByValue(getRandomOption(60, 79));
	        } catch (UnexpectedTagNameException e) {
	            // If the element is not a <select> element, assume it's an <input> element and set its value
	        	UmgRFQNumber.sendKeys(getRandomOption(60, 75));
	        }
			WebElement ulElementz = driver.findElement(By.xpath("(//ul)[6]"));
			List<WebElement> liElementsz = ulElementz.findElements(By.tagName("li"));
			Random random12 = new Random();
			
		
			if (liElementsz.size() > 0) {
			
	        int indexz = random12.nextInt(liElementsz.size());
	        driver.findElement(By.xpath("//li[contains(@id,'option-"+indexz+"')]")).click();
			}
	        Thread.sleep(1000);
	        String UmgRfqnum=driver.findElement(By.xpath("//*[@id=\"materialNo\"]")).getAttribute("value");
	      
	        
	        Thread.sleep(3000);
	        
	        //take screen shot 
	        CreateRfq.captureScreenshot(driver, UmgRfqnum +"CreatOrderPage");
	        
	        
	   // upoad files      
	       
	     // Set the system property for the ChromeDriver
	        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
	        
	        try {
	          

	            // Upload PDF file
	            uploadFile(driver, "D:\\Automation\\Screen captured\\UploadToBrowse\\DraftQuote.pdf");

	            // Upload txt file
	            uploadFile(driver, "D:\\Automation\\Screen captured\\UploadToBrowse\\undefined (1).txt");

	            // Upload ZIP file
	            uploadFile(driver, "D:\\Automation\\Screen captured\\UploadToBrowse\\OrderAttachments.zip");

	            // Upload Excel file
	            uploadFile(driver, "D:\\Automation\\Screen captured\\UploadToBrowse\\3000125_1.xlsx");

	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        	//out of block 
	        }
	        
	        
              //save button 
	        
		     WebElement saveButton1 = driver.findElement(By.xpath("//*[@id=\"newOrderSave\"]/i"));
		     saveButton1.click();
		     System.out.println("order saved");
	  	
	        
		
	}

	
	 private static String getRandomOption(double min, double max) {
	        int randomOption = (int) (Math.random() * (max - min + 1) + min);
	        return Integer.toString(randomOption);
	    }
	 
	 
	 // method to take screen shot and store in crated sub folder wrt date
	 public static void captureScreenshot(WebDriver driver, String screenshotName) {
	        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	        String subfolderName = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	        String subfolderPath = "D:\\Automation\\Screen captured\\" + subfolderName;

	        File subfolder = new File(subfolderPath);
	        if (!subfolder.exists()) {
	            subfolder.mkdir();
	        }

	        String fullPath = subfolderPath + File.separator + screenshotName;

	        try {
	            FileUtils.copyFile(screenshotFile, new File(fullPath));
	            System.out.println("Screenshot saved successfully to: " + fullPath);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	 
	 // to upload files 
	 
	 private static void uploadFile(WebDriver driver, String filePath) throws AWTException, InterruptedException {
		WebElement browse = driver.findElement(By.xpath("//*[@id=\"createOrderFilepUpload\"]"));
		browse.sendKeys(filePath);
		driver.findElement(By.xpath("//*[@id=\"newOrderSave\"]")).click();
		Thread.sleep(5000); 
	    }
	 
	 
}
