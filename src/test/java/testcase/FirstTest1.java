package testcase;

import java.awt.geom.Arc2D.Float;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstTest1 {

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
        
        driver.get(prop.getProperty("baseurl"));//
		/*driver.findElement(By.xpath("//*[@id=\"details-button\"]")).click();//comment umg
		driver.findElement(By.xpath("//*[@id=\"proceed-link\"]")).click();//comment umg
*/		driver.findElement(By.id("inputEmail")).sendKeys(prop.getProperty("username"));//
		driver.findElement(By.id("inputPassword")).sendKeys(prop.getProperty("passwrd"));//
		
		driver.findElement(By.xpath("//button")).click();
		
	
		Thread.sleep(8000);
		WebElement element = driver.findElement(By.xpath("//button"));
		Actions actions = new Actions(driver);
		actions.click(element).build().perform();
		
		Thread.sleep(4000);

	/*	driver.findElement(By.xpath("//*[@id=\"details-button\"]")).click();//comment umg
		driver.findElement(By.xpath("//*[@id=\"proceed-link\"]")).click();//comment umg
*/		
		Thread.sleep(2000);
	
	/*	driver.findElement(By.id("inputEmail")).sendKeys((prop.getProperty("username")));
		driver.findElement(By.id("inputPassword")).sendKeys((prop.getProperty("passwrd")));
		driver.findElement(By.xpath("//button")).click();// locators file
		Thread.sleep(5000);*/

		// Create R.F.Q
		
		driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div/div[1]/div/div[2]/div[1]/div[3]/button[2]/span")).click();
		WebElement AccNum = driver.findElement(By.id("customerAccountNo"));
		AccNum.sendKeys("7539");
		AccNum.sendKeys(Keys.ARROW_DOWN);
		AccNum.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"calculationType\"]")).sendKeys("SAP");
		driver.findElement(By.xpath("//*[@id=\"enquiryNumber\"]")).sendKeys("test123");
		
		driver.findElement(By.xpath("//*[@id=\"drawingNumber\"]")).sendKeys("test123");
		driver.findElement(By.xpath("//*[@id=\"custRFQDocumentationType\"]")).sendKeys("Data (Gerber,ODB,etc.)");
		driver.findElement(By.xpath("//*[@id=\"quantity\"]")).sendKeys("120");
		driver.findElement(By.xpath("//*[@id=\"materialNo\"]")).sendKeys((prop.getProperty("jobid")));

		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@id=\"salesInformation\"]")).sendKeys((prop.getProperty("comment")));
		driver.findElement(By.xpath("//*[@id=\"createComment\"]")).sendKeys((prop.getProperty("comment")));
		

	
		
		//////////////////////////////////////////////
		
		
		String date1=driver.findElement(By.xpath("//*[@id=\"enquiryDate\"]")).getAttribute("value");
		System.out.println("rfq Inquiry Date" + date1); 
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String str = formatter.format(date);
		/*System.out.println("system fetch date; "+str);*/
	
		
		
		if (date1.compareTo(str) == 0) {
			System.out.println("job created today: hence today date: Inquiry Date correct");
		}
		

		//Due Date Unimicron to Customer, 4 days adhed from date create
		
		String date2=driver.findElement(By.xpath("//*[@id=\"dueDateUnimToCustm\"]")).getAttribute("value");
		/*System.out.println("Rfq; Due Date Unimicron to Customer "+ date2);*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
	    c.add(Calendar.DATE, 4); // Adding 4 days
		String output = sdf.format(c.getTime());
		/*System.out.println("stystem deduct 4 days Due Date Unimicron to Customer" + output);*/
		
		if (date2.compareTo(output) == 0) {
			System.out.println("Due date Umg to costomer :correct in normal condiation");
		}else
		{
			System.out.println("Due date Umg to costomer :Incorrect in normal condiation");
		}
		
		
		//in qta status date should diff of 1 day
		
		driver.findElement(By.xpath("//*[@id=\"priority\"]")).sendKeys("qta");
		String date3=driver.findElement(By.xpath("//*[@id=\"dueDateUnimToCustm\"]")).getAttribute("value");
		/*System.out.println("rfQ qta status " +date3);*/
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c2 = Calendar.getInstance();
	    c.add(Calendar.DATE, -3); // Adding 1 days
		String output2 = sdf2.format(c.getTime());
		/*System.out.println("stystem deduct 1 days at qta Due Date Unimicron to Customer"+ output2);*/
		
		if (date3.compareTo(output2) == 0) {
			System.out.println("Due date Umg to costomer :correct in Qta condiation");
		}else
		{
			System.out.println("Due date Umg to costomer :incorrect in Qta condiation");
		}
		
		//	Due Date NCS to Unimicron
		String date4=driver.findElement(By.xpath("//*[@id=\"dueDateNcsToUnim\"]")).getAttribute("value");
		if (date4.compareTo(output2) == 0) {
			System.out.println("Due Date NCS to Unimicron :correct");
		}else
		{
			System.out.println("Due Date NCS to Unimicron :incorrect");
		}
		
		///////////////////////////////////////

		// Edit R.F.Q ( Search and Edit)

		driver.get(prop.getProperty("editturl"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@id=\"customerQuotationsTable_filter\"]/label/input")).sendKeys((prop.getProperty("jobid")));
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[@title='Edit R.F.Q.']")).click();

		/*// To upload files in edit RfQ
		WebElement browse1 = driver.findElement(By.xpath("//*[@id=\"editOrderFilepUpload\"]"));
		browse1.sendKeys("C:\\Users\\mallikarjuna.ss\\Desktop\\Umg test\\6002066_1\\6002066_1.pdf");
		driver.findElement(By.xpath("//*[@id=\"saveModifiedOrder\"]/i")).click();
		*/

	
		Thread.sleep(2000);
		
		WebElement acc = driver.findElement(By.xpath("//input[@id='customerAccountNo']"));
		String acce=acc.getAttribute("value");
		WebElement custdwg = driver.findElement(By.xpath("//*[@id=\"drawingNumber\"]"));
		WebElement rfqdoc = driver.findElement(By.xpath("//*[@id=\"custRFQDocumentationType\"]"));
		WebElement rfqdate = driver.findElement(By.xpath("//*[@id=\"enquiryDate\"]"));
		WebElement rfqnum = driver.findElement(By.xpath("//*[@id=\"materialNumber\"]"));
		WebElement umgtocust = driver.findElement(By.xpath("//*[@id=\"dueDateUnimToCustm\"]"));
		
		WebElement innum = driver.findElement(By.xpath("//*[@id=\"enquiryNumber\"]"));
		String inqnum=innum.getAttribute("value"); 
		WebElement dgorpartnum = driver.findElement(By.xpath("//*[@id=\"drawingNumber\"]"));
		String dwgorpartnum=dgorpartnum.getAttribute("value");
		
		WebElement custcity1 = driver.findElement(By.xpath("//*[@id=\"customarCity\"]"));
		String custcity=custcity1.getAttribute("value");
		
		
		
		// select visible text of Account mnger from static option dropdown
		WebElement accmng = driver.findElement(By.xpath("//*[@id=\"customerAccountManger\"]"));
		Select select = new Select(accmng);
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();
		
		// select visible text of sales from static option dropdown
		WebElement salspers = driver.findElement(By.xpath("//*[@id=\"salesPerson\"]"));
		Select select1 = new Select(salspers);
		WebElement option1 = select1.getFirstSelectedOption();
		String defaultItem1 = option1.getText();
	
		/*//printing customer info
		System.out.println(acc.getAttribute("value"));
	    System.out.println(custdwg.getAttribute("value"));
	    System.out.println(rfqdoc.getAttribute("value"));
	    System.out.println(rfqdate.getAttribute("value"));
	    System.out.println(rfqnum.getAttribute("value"));
	    System.out.println(umgtocust.getAttribute("value"));
	    System.out.println(defaultItem); 
	    System.out.println(defaultItem1);*/
		
		
		String ac = acc.getAttribute("value");
		/*globalvariable.setAccountnum(ac);*/
		
		String custdw=custdwg.getAttribute("value");
		String rfqdo =rfqdoc.getAttribute("value");
		String rfqdat =rfqdate.getAttribute("value");
	    String rfqnu= rfqnum.getAttribute("value");
		String umgtocus =umgtocust.getAttribute("value");
		
		
		int scroll4 = 0;

		do {
			Actions act = new Actions(driver);
			act.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(2000);
			scroll4++;

		} while (scroll4 <= 1);
		Thread.sleep(2000);

		// datasheet validation

		driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div/div/div[1]/div[1]/h5/a")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		/*driver.findElement(By.xpath("//*[@id=\"customerQuotationsTable_filter\"]/label/input")).sendKeys((prop.getProperty("jobid")));*/
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@title='Datasheet']")).click();
		driver.findElement(By.xpath("//*[@id=\"expandClass\"]/i")).click();
        Thread.sleep(3000);

		WebElement acc1 = driver.findElement(By.xpath("//*[@id=\"customer\"]"));
		WebElement custdwg1 = driver.findElement(By.xpath("//*[@id=\"collapseCommercial\"]/div[1]/div[1]/div[2]/input"));
		WebElement rfqdoc1 = driver.findElement(By.xpath("//*[@id=\"collapseCommercial\"]/div[1]/div[1]/div[3]/input"));
		WebElement rfqdate1 = driver.findElement(By.xpath("//*[@id=\"collapseCommercial\"]/div[1]/div[1]/div[4]/input"));
		WebElement rfqnum1 = driver.findElement(By.xpath("//*[@id=\"collapseCommercial\"]/div[1]/div[2]/div[1]/input"));
		WebElement umgtocust1 = driver.findElement(By.xpath("//*[@id=\"collapseCommercial\"]/div[1]/div[2]/div[2]/input"));
		WebElement accmng3 = driver.findElement(By.xpath("//*[@id=\"collapseCommercial\"]/div[1]/div[2]/div[3]/input"));
		WebElement salspers2 = driver.findElement(By.xpath("//*[@id=\"collapseCommercial\"]/div[1]/div[2]/div[4]/input"));

		String ac1 = acc1.getAttribute("value");
		String custdw1 = custdwg1.getAttribute("value");
		String rfqdo1 = rfqdoc1.getAttribute("value");
		String rfqdat1 = rfqdate1.getAttribute("value");
		String rfqnu1 = rfqnum1.getAttribute("value");
		String umgtocus1 = umgtocust1.getAttribute("value");
		String accmn3 = accmng3.getAttribute("value");

		/*System.out.println(acc1.getAttribute("value"));
		System.out.println(custdwg1.getAttribute("value"));
		System.out.println(rfqdoc1.getAttribute("value"));
		System.out.println(rfqdate1.getAttribute("value"));
		System.out.println(rfqnum1.getAttribute("value"));
		System.out.println(umgtocust1.getAttribute("value"));
		System.out.println(accmn3);
		System.out.println(salspers2.getAttribute("value"));*/

	Thread.sleep(5000);
				  
				  
		if (custdw.equalsIgnoreCase(custdw1)) {
			System.out.println("custdwg  matches");
		}
		if (rfqdo.equalsIgnoreCase(rfqdo1)) {
			System.out.println("rfqdoc matches");
		}
		if (rfqdat.equalsIgnoreCase(rfqdat1)) {
			System.out.println("rfqdate matches");
		}
		if (rfqnu.equalsIgnoreCase(rfqnu1)) {
			System.out.println("rfqnumber matches");
		}
		if (umgtocus.equalsIgnoreCase(umgtocus1)) {
			System.out.println("umgtocust due date matches");
		}
		if (defaultItem.equalsIgnoreCase(accmn3.trim())) {
			System.out.println("acc manger matches");
		}

		int scroll = 1;

		do {
			Actions act = new Actions(driver);
			act.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(2000);
			scroll++;

		} while (scroll <= 5);
		
		System.out.println("Scroll down perfomed");

		// Array validation

		WebElement pcbperarray = driver.findElement(By.xpath("//*[@id=\"pcbPerArray\"]"));
		String pcbperarray1 = pcbperarray.getAttribute("value");
		int i = Integer.parseInt(pcbperarray1);

		/* System.out.println(pcbperarray1); */

		WebElement arrayperpane = driver.findElement(By.xpath("//*[@id=\"arrayPerPanel\"]"));
		String arrayperpane1 = arrayperpane.getAttribute("value");
		int j = Integer.parseInt(arrayperpane1);
		/* System.out.println(arrayperpane1); */

		WebElement pcbperprodpanel = driver.findElement(By.xpath("//*[@id=\"pcbperproduction\"]"));
		String pcbperprodpanel1 = pcbperprodpanel.getAttribute("value");
		int z = Integer.parseInt(pcbperprodpanel1);

		int Pcbperprod = (i * j);

		if (Pcbperprod == z) {

			System.out.println("PCB / Prod. Panel" + "displaying corect valuue");
		} else {
			System.out.println("No array job  or arrays related feilds incorrect ");
		}

		// layer count matches

		WebElement laycontt = driver.findElement(By.xpath("//*[@id=\"layerCountDisplay\"]"));
		String laycont1 = laycontt.getAttribute("value");

		WebElement laycontb = driver.findElement(By.xpath("//*[@id=\"layerCountDisplayBasic\"]"));
		String laycontb1 = laycontb.getAttribute("value");

		if (laycontb1.equalsIgnoreCase(laycont1)) {

			System.out.println("Layer Count verified & correct");
		} else {
			System.out.println("issue in layer count");
		}

		// Panels per batch (optimal batch)
		int defultpanelsperbatch = 90;

		WebElement panelsperbatch = driver.findElement(By.xpath("//*[@id=\"panelPerBatch\"]"));
		String panelsperbatch1 = panelsperbatch.getAttribute("value");
		int a3 = Integer.parseInt(panelsperbatch1);

		if (a3 == defultpanelsperbatch) {

			System.out.println("Panels per batch is verified = 90");

		} else {

			System.out.println("Issue in Panels per batch");
		}

         //linewidth validation

		// Min Line width / Spacing (µm)

		WebElement otrlinwidth = driver.findElement(By.xpath("//*[@id=\"outerLayerLineWidth\"]"));
		String otrlinwidth1 = otrlinwidth.getAttribute("value");
		int a = Integer.parseInt(otrlinwidth1);

		// Outer Layer Spacing (µm)

		WebElement otrlinspac = driver.findElement(By.xpath("//*[@id=\"outerLayerSpacing\"]"));
		String otrlinspac1 = otrlinspac.getAttribute("value");
		int b = Integer.parseInt(otrlinspac1);

		// Inner Layer Line Width (µm)

		WebElement itrlinwidth = driver.findElement(By.xpath("//*[@id=\"innerLayerLineWidth\"]"));
		String itrlinwidth1 = itrlinwidth.getAttribute("value");
		int c1 = Integer.parseInt(itrlinwidth1);

		// Inner Layer Spacing (µm)

		WebElement itrlinspac = driver.findElement(By.xpath("//*[@id=\"innerLayerLineWidth\"]"));
		String itrlinspac1 = itrlinspac.getAttribute("value");
		int d = Integer.parseInt(itrlinspac1);

		// Min Line width / Spacing (µm)

		WebElement MinLinewidthBySpacing = driver.findElement(By.xpath("//*[@id=\"displayLineWidth\"]"));
		String MinLinewidthBySpacing1 = MinLinewidthBySpacing.getAttribute("value");
		String s = MinLinewidthBySpacing1;

		int minilinewidth;

		if (a > c1) {
			minilinewidth = a;
		} else {
			minilinewidth = c1;
		}

		int minilinespce;

		if (b > d) {
			minilinespce = b;
		} else {
			minilinespce = d;
		}

		String MinLinewidthBySpacing2 = minilinewidth + " / " + minilinespce;
		

		if (s.equalsIgnoreCase(MinLinewidthBySpacing2)) {

			System.out.println("displayed linewidth/spacing is correct");
		} else {
			System.out.println("displayed linewidth/spacing is incorrect");
		}

		// Sml colour

		WebElement smlcolour = driver.findElement(By.xpath("//*[@id=\"solderColor\"]"));
		String smlcolour1 = smlcolour.getAttribute("value");

		WebElement smlcolour2 = driver.findElement(By.xpath("//*[@id=\"lsmColor\"]"));
		String smlcolour3 = smlcolour2.getAttribute("value");

		if (smlcolour1.equalsIgnoreCase(smlcolour3)) {

			System.out.println("solder mask colour verified");
		} else {
			System.out.println("issue in solder mask colour");
		}

		// Copper via filling

		WebElement cufilling = driver.findElement(By.xpath("//*[@id=\"cuFillingId\"]"));
		String cufilling1 = cufilling.getAttribute("value");

		WebElement cufilling2 = driver.findElement(By.xpath("//*[@id=\"displayCopperViaFilling\"]"));
		String cufilling3 = cufilling2.getAttribute("value");

		if (cufilling1.equalsIgnoreCase(cufilling3)) {

			System.out.println("Copper via filling verified");
		} else {
			System.out.println("issue in Copper via filling");
		}

		// HDi validtaion

		WebElement hdi = driver.findElement(By.xpath("//*[@id=\"hdiId\"]"));
		String hdi1 = hdi.getAttribute("value");

		WebElement hdi2 = driver.findElement(By.xpath("//*[@id=\"displayhdi\"]"));
		String hdi3 = hdi2.getAttribute("value");

		if (hdi1.equalsIgnoreCase(hdi3)) {

			System.out.println("hdi verified");
		} else {
			System.out.println("issue in hdi");
		}

		// busbar

		WebElement busbar = driver.findElement(By.xpath("//*[@id=\"busbarId\"]"));
		String busbar1 = busbar.getAttribute("value");

		WebElement busbar2 = driver.findElement(By.xpath("//*[@id=\"displayBusbar\"]"));
		String busbar3 = busbar2.getAttribute("value");

		if (busbar1.equalsIgnoreCase(busbar3)) {

			System.out.println("busbar verified");
		} else {
			System.out.println("issue in busbar");
		}

		// Photo via filling

		WebElement photoviafill = driver.findElement(By.xpath("//*[@id=\"fotoViaFillingId\"]"));
		String photoviafill1 = photoviafill.getAttribute("value");

		WebElement photoviafill2 = driver.findElement(By.xpath("//*[@id=\"displayPhotoViaFilling\"]"));
		String photoviafill3 = photoviafill2.getAttribute("value");

		if (busbar1.equalsIgnoreCase(busbar3)) {

			System.out.println("Photo via filling verified");
		} else {
			System.out.println("issue in Photo via filling");

		}

		// Resin Filling

		WebElement resifill = driver.findElement(By.xpath("//*[@id=\"resinFillingMlId\"]"));
		String resifill1 = resifill.getAttribute("value");

		WebElement resifill2 = driver.findElement(By.xpath("//*[@id=\"displayResinfilling\"]"));
		String resifill3 = resifill2.getAttribute("value");

		if (busbar1.equalsIgnoreCase(busbar3)) {

			System.out.println("Resin Filling verified");
		} else {
			System.out.println("issue in Resin Filling");

		}

		// sbu

		WebElement Semiflex = driver.findElement(By.xpath("(//*[@id=\"displaySbu\"])[1]"));
		String Semiflex1 = Semiflex.getAttribute("value");

		WebElement Semiflex2 = driver.findElement(By.xpath("(//*[@id=\"displaySbu\"])[2]"));
		String Semiflex3 = Semiflex2.getAttribute("value");

		WebElement Semiflex4 = driver.findElement(By.xpath("//*[@id=\"displaySbuText\"]"));
		String Semiflex5 = Semiflex4.getAttribute("value");

		if (Semiflex1.equalsIgnoreCase(Semiflex3)) {
			System.out.println("sbu 1&2 verified");

		} else {
			System.out.println("issue in sbu");
		}

		if (Semiflex3.equalsIgnoreCase(Semiflex5)) {
			System.out.println("sbu loaction 2&3 verified");
		} else {
			System.out.println("issue in sbu");
		}

		// Viaplugging

		WebElement Viaplugging = driver.findElement(By.xpath("//*[@id=\"pluggingId\"]"));
		String Viaplugging1 = Viaplugging.getAttribute("value");

		WebElement Viaplugging2 = driver.findElement(By.xpath("//*[@id=\"displayViaPlugging\"]"));
		String Viaplugging3 = Viaplugging2.getAttribute("value");

		if (Viaplugging1.equalsIgnoreCase(Viaplugging3)) {
			System.out.println("Viaplugging verified");

		} else {
			System.out.println("issue in Viaplugging1");
		}

		// Wirelaid

		WebElement Wirelaid = driver.findElement(By.xpath("//*[@id=\"wirelaidId\"]"));
		String Wirelaid1 = Wirelaid.getAttribute("value");

		WebElement Wirelaid2 = driver.findElement(By.xpath("//*[@id=\"displayWirelaid\"]"));
		String Wirelaid3 = Wirelaid2.getAttribute("value");

		if (Wirelaid3.equalsIgnoreCase(Wirelaid1)) {
			System.out.println("Wirelaid verified");

		} else {
			System.out.println("issue in Wirelaid");
		}
		
		// Number of press cycles

		WebElement numofpresscycl = driver.findElement(By.xpath("//*[@id=\"numberOfPress\"]"));
		String numofpresscycl1 = numofpresscycl.getAttribute("value");

		WebElement numofpresscycl2 = driver.findElement(By.xpath("//*[@id=\"noOfPress\"]"));
		String numofpresscycl3 = numofpresscycl2.getAttribute("value");

		if (numofpresscycl1.equalsIgnoreCase(numofpresscycl3)) {

			System.out.println("Number of press cycles verified");
		} else {
			System.out.println("issue in Number of press cycles");
		}

		// Buried Vias

		WebElement busrivia = driver.findElement(By.xpath("(//*[@id=\"displayBurriedVias\"])[1]"));
		String busrivia1 = busrivia.getAttribute("value");

		WebElement busrivia2 = driver.findElement(By.xpath("(//*[@id=\"displayBurriedVias\"])[2]"));
		String busrivia3 = busrivia2.getAttribute("value");

		if (busrivia1.equalsIgnoreCase(busrivia3)) {

			System.out.println("Buried Vias verified");
		} else {
			System.out.println("issue in Buried Vias");
		}

		// drill level

		WebElement drilllevel = driver.findElement(By.xpath("//*[@id=\"drillLevelsId\"]"));
		String drilllevel1 = drilllevel.getAttribute("value");

		WebElement drilllevel2 = driver.findElement(By.xpath("//*[@id=\"numDrillLevels\"]"));
		String drilllevel3 = drilllevel2.getAttribute("value");

		if (drilllevel1.equalsIgnoreCase(drilllevel3)) {

			System.out.println("drill levels verified");
		} else {
			System.out.println("issue in drill levels");
		}
		
		
		// Heat Sink Paste

		WebElement heatsinkpest = driver.findElement(By.xpath("(//*[@id=\"heatSinkPastesId\"])[1]"));
		String heatsinkpest1 = heatsinkpest.getAttribute("value");

		WebElement heatsinkpest2 = driver.findElement(By.xpath("(//*[@id=\"heatSinkPastesId\"])[2]"));
		String heatsinkpest3 = heatsinkpest2.getAttribute("value");

		WebElement heatsinkpestsrf = driver.findElement(By.xpath("//*[@id=\"heatSinkPasteSurface\"]"));
		String heatsinkpestsrf1 = heatsinkpestsrf.getAttribute("value");
		int heatsinkpestsrf2 = Integer.parseInt(heatsinkpestsrf1);

		if ((heatsinkpest1.equalsIgnoreCase(heatsinkpest3))) {

			if ((heatsinkpest3.equalsIgnoreCase("No")) && (heatsinkpest1.equalsIgnoreCase("No"))) {

				if (heatsinkpestsrf2 == 0) {

					System.out.println("heat sink paste No &  surfc value = 0 -verified");
				} else {
					System.out.println("heat sink paste No but  as surfc value - fialed");
				}
			} else if ((heatsinkpest3.equalsIgnoreCase("Yes")) && (heatsinkpest1.equalsIgnoreCase("Yes"))) {
				if (heatsinkpestsrf2 > 0) {
					System.out.println("heat sink paste yes & as surfc value -verifie");
				} else {
					System.out.println("heat sink paste yes but  no surfc value - fialed");
				}
			}

		} else {
			System.out.println("heat sink paste diffrent value at 2 location which is incorect");
		}
		
		// corbonprint

		WebElement corbonprint = driver.findElement(By.xpath("//input[@placeholder='No']"));// Corbonprint taking here is placeholder which is wrong
		String corbonprint1 = corbonprint.getAttribute("value");
		

		WebElement corbonprint2 = driver.findElement(By.xpath("//*[@id=\"displayCarbonPrint\"]"));
		String corbonprint3 = corbonprint2.getAttribute("value");
		

		if (corbonprint1.equalsIgnoreCase(corbonprint3)) {

			System.out.println("corbonprint verified");
		} else {
			System.out.println("issue in corbonprint");
		}
		
		
		// gold validation

		WebElement gold = driver.findElement(By.xpath("//*[@id=\"hardGoldPlating\"]"));
		String gold1 = gold.getAttribute("value");

		WebElement goldsurf = driver.findElement(By.xpath("//*[@id=\"hardGoldPlattingArea\"]"));
		String goldsurf1 = goldsurf.getAttribute("value");
		double  g = Double.parseDouble(goldsurf1);
		

		if (gold1.equalsIgnoreCase("Yes")) {

			if (g > 0) {

				System.out.println("gold present and as value- verified");
			} else {

				System.out.println("gold present but no surface value - failed");
			}
		} else if (gold1.equalsIgnoreCase("No")) {

			if (g == 0) {
				System.out.println("Nogold job- verified");
			} else {

				System.out.println("gold no but why surface value - failed");
			}
		}

		
		
		// Base calculation
		driver.findElement(By.xpath("//*[@id=\"calculatePageId\"]/i")).click();// click on base claculayion icon in data
																				// sheet
		driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div[1]/div[2]/div[4]/div[1]/div/div/div/div[2]/span/i")).click();
		Thread.sleep(3000);

		WebElement bcustcode = driver.findElement(By.xpath("//*[@id=\"customerAccountNo\"]"));
		String bcustcode1 = bcustcode.getAttribute("value");
		WebElement bcustomer = driver.findElement(By.xpath("//*[@id=\"customer\"]"));
		String bcustomer1 = bcustomer.getAttribute("value");
		WebElement bcustomercity = driver.findElement(By.xpath("//*[@id=\"customarCity\"]"));
		String bcustomercity1 = bcustomercity.getAttribute("value");
		WebElement bjobid = driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div[1]/div[2]/div[3]/div/div[2]/div[2]/div/div[6]/input"));
		String bjobid1 = bjobid.getAttribute("value");
		WebElement bcustomerdwg = driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div[1]/div[2]/div[3]/div/div[2]/div[2]/div/div[7]/input"));
		String bcustomerdwg1 = bcustomerdwg.getAttribute("value");
		WebElement binqnumb = driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div[1]/div[2]/div[3]/div/div[2]/div[2]/div/div[8]/input"));
		String binqnumb1 = binqnumb.getAttribute("value");
		WebElement brfqdate = driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div[1]/div[2]/div[3]/div/div[2]/div[2]/div/div[9]/input"));
		String brfqdate1 = brfqdate.getAttribute("value");

		/*System.out.println(bcustcode1);
		System.out.println(bcustomer1);
		System.out.println(bcustomercity1);
		System.out.println(bjobid1);// comp1
		System.out.println(bcustomerdwg1);
		System.out.println(binqnumb1);
		System.out.println(brfqdate1);// comp1
*/		
	
		if (acce.equalsIgnoreCase(bcustcode1)) {
			System.out.println("Customer Code  matches");// Comp1
		}else{
			System.out.println("Customer Code Not matches");
		}
		
		if (custcity.equalsIgnoreCase(bcustomercity1)) {
			System.out.println("customercity matches");// from edit order
		}else{
			System.out.println("customercity not matches");
		}
		
		if (brfqdate1.equalsIgnoreCase(rfqdat1)) {
			System.out.println("rfqdate matches");// Comp1
		}else{
			System.out.println("rfqdate not matches");
		}
		
		if (bjobid1.equalsIgnoreCase(rfqnu1)) {
			System.out.println("rfq job number matches");// Comp1
		}else{
			System.out.println("rfq job number  not matches");
		}
		
		if (dwgorpartnum.equalsIgnoreCase(bcustomerdwg1)) {
			System.out.println("cust Dwg or Part num  matches");// Comp1
		}else{
			System.out.println("cust Dwg or Part num not matches");
		}
		
		if (inqnum.equalsIgnoreCase(binqnumb1.trim())) {
			System.out.println("Inq num matches");// Comp1
		}else{
			System.out.println("Inq num not matches");
		}
		
		
		
		//Job General Parameters 
		
		WebElement SAProdC = driver.findElement(By.xpath("//*[@id=\"sapProuctionRatePerBatchId\"]"));// ctach Sap value
		String SAProdC1 = SAProdC.getAttribute("value");
		double SAProdCo = Double.parseDouble(SAProdC1);
		
		/*BigDecimal SAProdC12 = SAProdC1;*/
		
		WebElement OptBatch = driver.findElement(By.xpath("//*[@id=\"sapProuctionRatePerBatchId\"]"));// catch Opt. Batch value
		String  OptBatch1 = OptBatch.getAttribute("value");
		double OptBatchc = Double.parseDouble(OptBatch1);
		
		
		if(SAProdCo != 0 && OptBatchc != 0 ) {
			
		WebElement ArraybyWP1 = driver.findElement(By.xpath("//*[@id=\"arrayPerWorkingPanel\"]"));//catch arrayPerWorkingPanel
		String ArraybyWP = ArraybyWP1.getAttribute("value");
		int ArraybyWP2 = Integer.parseInt(ArraybyWP);
		
		WebElement PCBbyArray1 = driver.findElement(By.xpath("//*[@id=\"pcbPerArray\"]"));//catch pcbPerArray
		String PCBbyArray = PCBbyArray1.getAttribute("value");
		int PCBbyArray2 = Integer.parseInt(PCBbyArray);
		
		WebElement PCBbyWP1 = driver.findElement(By.xpath("//*[@id=\"baseCalculationId\"]/div/div[1]/div[2]/div/div[2]/div/div[3]/div/input"));//catch PCB / WP 
		String PCBbyWP2 = PCBbyWP1.getAttribute("value");
		int PCBbyWP23 = Integer.parseInt(PCBbyWP2);
		int PCBbyWP = ((ArraybyWP2) * (PCBbyArray2));

		if (PCBbyWP == PCBbyWP23) {
			System.out.println("PCB / WP  matches");
		} else {
			System.out.println("PCB / WP  not matches");
		}
		
		}else {
			System.out.println(" no sap value, plese provide sap data");
		}
		
		int scroll1 = 1;

		do {
			Actions act = new Actions(driver);
			act.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(2000);
			scroll1++;

		} while (scroll1 <= 3);
		
		
		// add extrenal cost
		driver.findElement(By.xpath("//*[@id=\"otherCost\"]/i")).click();// click on extrenal dropdown
		Thread.sleep(2000);

		double Sum = 0;
		double Sum1 = 0;
		
		
		for (int r = 1; r <= 6; r++) {
			driver.findElement(By.xpath("//*[@id=\"addMoreButtonExtraCost\"]/span/i")).click();
			driver.findElement(By.xpath("//*[@id=\"description" + r + "\"]")).clear();// clear descriptin
			driver.findElement(By.xpath("//*[@id=\"otherExtCost" + r + "\"]")).clear();// clear cost val
			String R2 = RandomStringUtils.randomAlphanumeric(5);
			driver.findElement(By.xpath("//*[@id=\"description" + r + "\"]")).sendKeys(R2);
			double R1 = Math.random();
			driver.findElement(By.xpath("//*[@id=\"otherExtCost" + r + "\"]")).sendKeys(Double.toString(R1));
			WebElement perchange = driver.findElement(By.xpath("//*[@id=\"otherExtCostUnit" + r + "\"]"));
			perchange.sendKeys(Keys.ARROW_DOWN);
			perchange.sendKeys(Keys.ENTER);
			perchange.sendKeys(Keys.ARROW_UP);
			perchange.sendKeys(Keys.ENTER);
			 
			WebElement CostbyWp1 = driver.findElement(By.xpath("//*[@id=\"otherExtCostWorkingPanel" + r + "\"]")); // Cost/Wp
			String CostbyWp2 = CostbyWp1.getAttribute("value");
			double CostbyWp = Double.parseDouble(CostbyWp2);
			/*System.out.println(CostbyWp);*/
			
			Thread.sleep(20000);
			Sum = Sum + CostbyWp;
			
			// Cost/batch
			WebElement Costbybatch = driver.findElement(By.xpath("//*[@id=\"otherExtCostPerBatch" + r + "\"]")); // Cost/Wp
			String Costbybatch1 = Costbybatch.getAttribute("value");
			double Costbybatch2 = Double.parseDouble(Costbybatch1);
			Sum1 = Sum1 + Costbybatch2;
			System.out.println(Costbybatch2);
			
			
			
		}

		WebElement CostbyWp3 = driver.findElement(By.xpath("//*[@id=\"totalOtherExtCost\"]")); // Cost/Wp
		String CostbyWp4 = CostbyWp3.getAttribute("value");
		double TCostbyWp = Double.parseDouble(CostbyWp4);

		BigDecimal bd = new BigDecimal(TCostbyWp).setScale(1, RoundingMode.HALF_UP);
		double newNum = bd.doubleValue();

		System.out.println(Sum);
		
		if (newNum == Sum) {
			System.out.println("Sum of tottal external cost per Wp verified - verified and is :" + Sum);
		} else {
			System.out.println("Sum of tottal external cost per Wp not correct");
		}

		// verify Base calc Summery

		WebElement textecost1 = driver.findElement(By.xpath("//*[@id=\"totalOtherExtCostSumm\"]")); // catch Textrenl
																									
		String textecost2 = textecost1.getAttribute("value");
		double textecost = Double.parseDouble(textecost2);

		WebElement optbatch = driver.findElement(
				By.xpath("//*[@id=\"baseCalculationId\"]/div/div[1]/div[2]/div/div[2]/div/div[4]/div/input")); // catch
																											
		String optbatch1 = optbatch.getAttribute("value");
		double optbatch2 = Double.parseDouble(optbatch1);

		WebElement wpperbatch = driver.findElement(
				By.xpath("//*[@id=\"baseCalculationId\"]/div/div[1]/div[2]/div/div[2]/div/div[5]/div/input")); // catch
																												
		String wpperbatch1 = wpperbatch.getAttribute("value");
		double wpperbatch2 = Double.parseDouble(wpperbatch1);

		double rest = ((Sum) * ((optbatch2) / (wpperbatch2)));

		if (rest == textecost) {
			System.out.println("tottal external cost per Wp in basesummery verified - verified");
		} else {
			System.out.println("total external cost per Wp in basesummery not correct ");
		}
			
		
		
		// veridy Total Var. Supp. Cost / WP

		WebElement totsupcost = driver.findElement(By.xpath("//*[@id=\"totalVarSupplementCost\"]"));
		String totsupcost1 = totsupcost.getAttribute("value");
		WebElement totsupcost3 = driver.findElement(By.xpath("//*[@id=\"totalVariousCostSumm\"]"));
		String totsupcost11 = totsupcost3.getAttribute("value");
		double veriucost = Double.parseDouble(totsupcost11);
		compare(totsupcost1, totsupcost11);
		
		//  verify total cost in base summery
		
		
		System.out.println("various cost"+ veriucost);
		System.out.println("sapcost"+ SAProdCo);
		double K18= ((OptBatchc)/(i)*(j));
		System.out.println(OptBatchc);
		System.out.println(i);
		System.out.println(j);
		System.out.println(Sum1);
		double tottprobcost = (((SAProdCo)+(Sum1)+(veriucost))/(K18));
		System.out.println(tottprobcost);
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[@id=\"body-row\"]/div[2]/div[1]/div[2]/div[5]/div/div[1]/div/div/div[2]/span/i")).click();//click on bulk calculation drop down
		 
		
		/*driver.quit();*/
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");

		// add rowsto calcable

		for (int r = 1; r <= 7; r++) {

			driver.findElement(By.xpath("//*[@id=\"sample\"]/thead/tr[1]/th[1]/i[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"massProdTableId\"]/thead/tr[1]/th[1]/i[1]")).click();
			Thread.sleep(1000);
		}

		// delet rows
		for (int r = 1; r <= 2; r++) {

			driver.findElement(By.xpath("//*[@id=\"removeSample\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"removeMassProd\"]")).click();
			Thread.sleep(1000);
		}

		// count num of rowin samples
		int samplerow = driver.findElements(By.xpath("//*[@id='sample_tbody']//descendant::tr")).size();
		int massrow = driver.findElements(By.xpath("//*[@id=\"massProdTableId\"]/tbody//descendant::tr")).size();

		// clear all rows and enter
		for (int r = 1; r <= samplerow; r++) {

			driver.findElement(By.xpath("//*[@id=\"quantityB" + r + "\"]")).clear();
			Thread.sleep(2000);
			Random randomNum1 = new Random();
			int showMe1 = randomNum1.nextInt(3000);
			WebElement rows = driver.findElement(By.xpath("//*[@id=\"quantityB" + r + "\"]"));
			rows.click();
			rows.sendKeys(String.valueOf(showMe1));
			Thread.sleep(1000);
			rows.click();
			rows.sendKeys(Keys.ENTER);
		}
			
			for (int rn = 1; rn <= massrow; rn++) {			
			driver.findElement(By.xpath("//*[@id=\"mass_quantityB" + rn + "\"]")).clear();
			Thread.sleep(2000);
			Random randomNum2 = new Random();
			int showMe11 = randomNum2.nextInt(3000);
			WebElement rows2 = driver.findElement(By.xpath("//*[@id=\"mass_quantityB" + rn + "\"]"));
			rows2.click();
			rows2.sendKeys(String.valueOf(showMe11));
			Thread.sleep(1000);
			rows2.click();
			rows2.sendKeys(Keys.ENTER);
			}
		

		// select random qta /normal

		int desclairow = driver.findElements(By.xpath("//*[@id=\"quoteDisclaimerTable\"]/tbody/tr")).size();
		System.out.println(desclairow);
		Random randomNumk = new Random();
		int randk1 = randomNumk.nextInt(desclairow);
		Thread.sleep(2000);
		for (int n = 1; n < randk1; n++) {
			
			Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"leadTimeType" + n + "\"]")));
			dropdown.selectByVisibleText("QTA");
			Thread.sleep(2000);
			Select dropdownb = new Select(driver.findElement(By.xpath("//*[@id=\"leadTimeType" + n + "\"]")));
			dropdownb.selectByVisibleText("QTA");
			
		}
		
		// check box slect random

		js.executeScript("window.scrollBy(1500,70)");
		
		for (int n = 1; n < 3; n++) {
			Random randomNumk1 = new Random();
			int randk = randomNumk1.nextInt(desclairow);

			if (randk == 0) {
				randk++;
			} else
				Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"disclaimerSln" + randk + "\"]")).click();
		}
		
		Thread.sleep(2000);
		//amortised description and value send

		driver.findElement(By.xpath("//*[@id=\"amortisedDescription\"]")).sendKeys("Amortised Recurrent Costs");

		Random randomNum2 = new Random();
		int num3 = randomNum2.nextInt(3);
		
		driver.findElement(By.xpath("//*[@id=\"bulkAllocatedValue\"]")).sendKeys("num3");
		String Rn2 = RandomStringUtils.randomAlphanumeric(150);
		driver.findElement(By.xpath("//*[@id=\"quoteRemarksId\"]")).sendKeys(Rn2);
		driver.findElement(By.xpath("//*[@id=\"bulkDiv\"]/div[6]/div[2]/div/div[2]/div[1]/div/div/span[2]/i")).click();
		Thread.sleep(2000);
		/*
		 * Random randomNum5 = new Random(); int num4 = randomNum5.nextInt(3);
		 */
		driver.findElement(By.xpath("//*[@id=\"qtaValueInput\"]")).sendKeys("num3");
		WebElement qtasurc = driver.findElement(By.xpath("//*[@id=\"qtaType\"]"));
		qtasurc.sendKeys(Keys.ENTER);
		qtasurc.sendKeys(Keys.ARROW_DOWN);
		qtasurc.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		//further external cost
		
		WebElement qtasurc1 = driver.findElement(By.xpath("//*[@id=\"furtherProcess\"]"));
		qtasurc1.sendKeys(Keys.ENTER);
		qtasurc1.sendKeys(Keys.ARROW_DOWN);
		qtasurc1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	
		
		
		Thread.sleep(100000);
	    System.out.println("test completed");
	}
	
	
	static void compare(String c1, String c2) {

		double totsupcost2 = Double.parseDouble(c1);
		double totsupcost5 = Double.parseDouble(c2);
		
		if (totsupcost2 == totsupcost5) {
			System.out.println("this feild corrrect - verified");
		} else {
			System.out.println("this feild not correct ");
		}
		
	}
	
}
