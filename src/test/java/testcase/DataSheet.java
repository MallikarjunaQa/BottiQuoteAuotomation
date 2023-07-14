package testcase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataSheet {
	
	public static Properties prop = new Properties();
	public static FileReader fr;

	public static void main(String[] args) throws IOException, InterruptedException {
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
        
        
        driver.get(prop.getProperty("umgbaseurl"));//comment umg
		driver.findElement(By.xpath("//*[@id=\"details-button\"]")).click();//comment umg
		driver.findElement(By.xpath("//*[@id=\"proceed-link\"]")).click();//comment umg
		driver.findElement(By.id("inputEmail")).sendKeys(prop.getProperty("umgusername"));//comment umg
		driver.findElement(By.id("inputPassword")).sendKeys(prop.getProperty("umgpasswrd"));//comment umg
		
        
       /* 
        driver.get(prop.getProperty("baseurl"));
	    driver.findElement(By.id("inputEmail")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("inputPassword")).sendKeys(prop.getProperty("passwrd"));*/
		
		driver.findElement(By.xpath("//button")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"customerQuotationsTable_filter\"]/label/input")).sendKeys(prop.getProperty("jobid"));
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[@title='Datasheet']")).click();
		driver.findElement(By.xpath("//*[@id=\"expandClass\"]/i")).click();
        Thread.sleep(2000);
		
		
        
        int scroll = 1;

		do {
			Actions act = new Actions(driver);
			act.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(2000);
			scroll++;

		} while (scroll <= 5);
		
		// Array validation

		WebElement pcbperarray = driver.findElement(By.xpath("//*[@id=\"pcbPerArray\"]"));
		String pcbperarray1 = pcbperarray.getAttribute("value");
		int i = Integer.parseInt(pcbperarray1);

		WebElement arrayperpane = driver.findElement(By.xpath("//*[@id=\"arrayPerPanel\"]"));
		String arrayperpane1 = arrayperpane.getAttribute("value");
		int j = Integer.parseInt(arrayperpane1);
		

		WebElement pcbperprodpanel = driver.findElement(By.xpath("//*[@id=\"pcbperproduction\"]"));
		String pcbperprodpanel1 = pcbperprodpanel.getAttribute("value");
		int z = Integer.parseInt(pcbperprodpanel1);

		int Pcbperprod = (i * j);

		if (Pcbperprod == z) {

			System.out.println("PCB / Prod. Panel " + "displaying correct");
		} else {
			System.err.println("No array job  or arrays related feilds incorrect ");
		}

		// layer count matches

		WebElement laycontt = driver.findElement(By.xpath("//*[@id=\"layerCountDisplay\"]"));
		String laycont1 = laycontt.getAttribute("value");

		WebElement laycontb = driver.findElement(By.xpath("//*[@id=\"layerCountDisplayBasic\"]"));
		String laycontb1 = laycontb.getAttribute("value");

		if (laycontb1.equalsIgnoreCase(laycont1)) {

			System.out.println("Layer Count verified & correct");
		} else {
			System.err.println("layer count at technical and items and basic section: incorrect");
		}

		// Panels per batch (optimal batch)
		int defultpanelsperbatch = 90;

		WebElement panelsperbatch = driver.findElement(By.xpath("//*[@id=\"panelPerBatch\"]"));
		String panelsperbatch1 = panelsperbatch.getAttribute("value");
		int a3 = Integer.parseInt(panelsperbatch1);

		if (a3 == defultpanelsperbatch) {

			System.out.println("Panels per batch is verified = 90");

		} else {

			System.err.println("Issue in Panels per batch");
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
			System.err.println("displayed linewidth/spacing is incorrect");
		}

		// Sml colour

		WebElement smlcolour = driver.findElement(By.xpath("//*[@id=\"solderColor\"]"));
		String smlcolour1 = smlcolour.getAttribute("value");

		WebElement smlcolour2 = driver.findElement(By.xpath("//*[@id=\"lsmColor\"]"));
		String smlcolour3 = smlcolour2.getAttribute("value");

		if (smlcolour1.equalsIgnoreCase(smlcolour3)) {

			System.out.println("solder mask colour Correct");
		} else {
			System.err.println("issue in solder mask colour");
		}

		// Copper via filling

		WebElement cufilling = driver.findElement(By.xpath("//*[@id=\"cuFillingId\"]"));
		String cufilling1 = cufilling.getAttribute("value");

		WebElement cufilling2 = driver.findElement(By.xpath("//*[@id=\"displayCopperViaFilling\"]"));
		String cufilling3 = cufilling2.getAttribute("value");

		if (cufilling1.equalsIgnoreCase(cufilling3)) {

			System.out.println("Copper via filling correct");
		} else {
			System.err.println("issue in Copper via filling");
		}

		// HDi validtaion

		WebElement hdi = driver.findElement(By.xpath("//*[@id=\"hdiId\"]"));
		String hdi1 = hdi.getAttribute("value");

		WebElement hdi2 = driver.findElement(By.xpath("//*[@id=\"displayhdi\"]"));
		String hdi3 = hdi2.getAttribute("value");

		if (hdi1.equalsIgnoreCase(hdi3)) {

			System.out.println("hdi correct");
		} else {
			System.err.println("issue in hdi");
		}

		// busbar

		WebElement busbar = driver.findElement(By.xpath("//*[@id=\"busbarId\"]"));
		String busbar1 = busbar.getAttribute("value");

		WebElement busbar2 = driver.findElement(By.xpath("//*[@id=\"displayBusbar\"]"));
		String busbar3 = busbar2.getAttribute("value");

		if (busbar1.equalsIgnoreCase(busbar3)) {

			System.out.println("busbar Correct");
		} else {
			System.err.println("issue in busbar");
		}

		// Photo via filling

		WebElement photoviafill = driver.findElement(By.xpath("//*[@id=\"fotoViaFillingId\"]"));
		String photoviafill1 = photoviafill.getAttribute("value");

		WebElement photoviafill2 = driver.findElement(By.xpath("//*[@id=\"displayPhotoViaFilling\"]"));
		String photoviafill3 = photoviafill2.getAttribute("value");

		if (busbar1.equalsIgnoreCase(busbar3)) {

			System.out.println("Photo via filling Correct");
		} else {
			System.err.println("issue in Photo via filling");

		}

		// Resin Filling

		WebElement resifill = driver.findElement(By.xpath("//*[@id=\"resinFillingMlId\"]"));
		String resifill1 = resifill.getAttribute("value");

		WebElement resifill2 = driver.findElement(By.xpath("//*[@id=\"displayResinfilling\"]"));
		String resifill3 = resifill2.getAttribute("value");

		if (busbar1.equalsIgnoreCase(busbar3)) {

			System.out.println("Resin Filling Correct");
		} else {
			System.err.println("issue in Resin Filling");

		}

		// sbu

		WebElement Semiflex = driver.findElement(By.xpath("(//*[@id=\"displaySbu\"])[1]"));
		String Semiflex1 = Semiflex.getAttribute("value");

		WebElement Semiflex2 = driver.findElement(By.xpath("(//*[@id=\"displaySbu\"])[2]"));
		String Semiflex3 = Semiflex2.getAttribute("value");

		WebElement Semiflex4 = driver.findElement(By.xpath("//*[@id=\"displaySbuText\"]"));
		String Semiflex5 = Semiflex4.getAttribute("value");

		if (Semiflex1.equalsIgnoreCase(Semiflex3)) {
			System.out.println("sbu 1&2 Correct");

		} else {
			System.err.println("issue in sbu");
		}

		if (Semiflex3.equalsIgnoreCase(Semiflex5)) {
			System.out.println("sbu loaction 2&3 Correct");
		} else {
			System.err.println("issue in sbu");
		}

		// Viaplugging

		WebElement Viaplugging = driver.findElement(By.xpath("//*[@id=\"pluggingId\"]"));
		String Viaplugging1 = Viaplugging.getAttribute("value");

		WebElement Viaplugging2 = driver.findElement(By.xpath("//*[@id=\"displayViaPlugging\"]"));
		String Viaplugging3 = Viaplugging2.getAttribute("value");

		if (Viaplugging1.equalsIgnoreCase(Viaplugging3)) {
			System.out.println("Viaplugging Correct");

		} else {
			System.err.println("issue in Viaplugging1");
		}

		// Wirelaid

		WebElement Wirelaid = driver.findElement(By.xpath("//*[@id=\"wirelaidId\"]"));
		String Wirelaid1 = Wirelaid.getAttribute("value");

		WebElement Wirelaid2 = driver.findElement(By.xpath("//*[@id=\"displayWirelaid\"]"));
		String Wirelaid3 = Wirelaid2.getAttribute("value");

		if (Wirelaid3.equalsIgnoreCase(Wirelaid1)) {
			System.out.println("Wirelaid Corrrect");

		} else {
			System.err.println("issue in Wirelaid");
		}
		
		// Number of press cycles

		WebElement numofpresscycl = driver.findElement(By.xpath("//*[@id=\"numberOfPress\"]"));
		String numofpresscycl1 = numofpresscycl.getAttribute("value");

		WebElement numofpresscycl2 = driver.findElement(By.xpath("//*[@id=\"noOfPress\"]"));
		String numofpresscycl3 = numofpresscycl2.getAttribute("value");

		if (numofpresscycl1.equalsIgnoreCase(numofpresscycl3)) {

			System.out.println("Number of press cycles Correct");
		} else {
			System.err.println("issue in Number of press cycles");
		}

		// Buried Vias

		WebElement busrivia = driver.findElement(By.xpath("(//*[@id=\"displayBurriedVias\"])[1]"));
		String busrivia1 = busrivia.getAttribute("value");

		WebElement busrivia2 = driver.findElement(By.xpath("(//*[@id=\"displayBurriedVias\"])[2]"));
		String busrivia3 = busrivia2.getAttribute("value");

		if (busrivia1.equalsIgnoreCase(busrivia3)) {

			System.out.println("Buried Vias Correct");
		} else {
			System.err.println("issue in Buried Vias");
		}

		// drill level

		WebElement drilllevel = driver.findElement(By.xpath("//*[@id=\"drillLevelsId\"]"));
		String drilllevel1 = drilllevel.getAttribute("value");

		WebElement drilllevel2 = driver.findElement(By.xpath("//*[@id=\"numDrillLevels\"]"));
		String drilllevel3 = drilllevel2.getAttribute("value");

		if (drilllevel1.equalsIgnoreCase(drilllevel3)) {

			System.out.println("drill levels Correct");
		} else {
			System.err.println("issue in drill levels");
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
					System.err.println("heat sink paste No but  as surfc value - fialed");
				}
			} else if ((heatsinkpest3.equalsIgnoreCase("Yes")) && (heatsinkpest1.equalsIgnoreCase("Yes"))) {
				if (heatsinkpestsrf2 > 0) {
					System.out.println("heat sink paste yes & as surfc value -verified");
				} else {
					System.err.println("heat sink paste yes but  no surfc value - fialed");
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

			System.out.println("corbonprint Correct");
		} else {
			System.err.println("issue in corbonprint");
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

				System.err.println("gold present but no surface value - failed");
			}
		} else if (gold1.equalsIgnoreCase("No")) {

			if (g == 0) {
				System.out.println("Nogold job- verified");
			} else {

				System.err.println("gold no but why surface value - failed");
			}
		}
		 
	}

}
