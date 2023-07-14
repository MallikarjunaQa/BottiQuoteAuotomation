package testcase;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IncentivePoint {

	public static void main(String[] args) throws InterruptedException {
	
		Thread.sleep(2000);
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
		driver.get("http://192.168.150.75/incentivePoints");
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("anand@ncs-india.com");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Anand#2020");
		driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/form/div[4]/div/button")).click();
		Thread.sleep(4000);
		
	
		driver.findElement(By.xpath("//*[@id=\"fixedBox\"]/ul[2]/li[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"adminTab\"]/li[5]/a")).click();
		
		int n=0;// manual entry, as when u see alredy exisiting rows need to delete all,
		
		if(n>0) {
			
		for(int i=1; i<=n;i++) {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"incentiveTable\"]/tbody/tr[1]/td[4]/a[2]")).click();
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
		
		}else if(n==0){
			
		driver.findElement(By.linkText("Add Incentive Points")).click();
		
		int q= 33;// manual entry,start point
		String q1=String.valueOf(q); 
		
		int m=105;// manual entry,last point
		
		int N= 750;// manual entry,starting increement
		String N1=String.valueOf(N);
		
		int o=17300;// manual entry,last increement
		
			// Precam text add
			int diffpoin = 0;
			do {
				driver.findElement(By.xpath("//*[@id=\"newPointForm\"]/div[2]/button[1]")).click();
				WebElement type = driver.findElement(By.xpath("//*[@id=\"points_type \"]"));
				type.sendKeys("p");
				diffpoin++;
			} while (diffpoin <= m - q);

			int diffpoin1 = 1;
			do {
				WebElement type1 = driver.findElement(By.xpath("//*[@id=\"points_type_" + diffpoin1 + "\"]"));
				type1.sendKeys("p");
				diffpoin1++;
			} while (diffpoin1 <= (m - q+1));
			
			
			//Point add
			
			WebElement points = driver.findElement(By.xpath("//*[@id=\"points\"]"));
			points.clear();
			points.sendKeys(q1);
			int q2=q++;
			for(int h=1; h <= 73; h++) {
				
				int q3=q++;
				String q4=String.valueOf(q3); 
				
				WebElement points1 = driver.findElement(By.xpath("//*[@id=\"points_"+h+"\"]"));
				points1.clear();
				points1.sendKeys(q4);
				
			}
			
			
			
			//amount add 
			
			int addi=100;// ,manual input increment of amont 
			int addi1=150;
			int addi2=200;
			int addi3=250;
			
			int k = N;

			WebElement amount = driver.findElement(By.xpath("//*[@id=\"amount\"]"));
			amount.clear();
			amount.sendKeys(N1);
				
	
				for(int h=1; h <= 73; h++) {
			
					if(N==1250) {
						addi=addi1;
					}
					
					if(N==2150) {
						addi=addi2;
					}
					
					if(N==2550) {
						addi=addi3;
					}
					
					N= N+addi;
					String N5=String.valueOf(N);
					WebElement amount1 = driver.findElement(By.xpath("//*[@id=\"amount_"+h+"\"]"));
					amount1.clear();
					amount1.sendKeys(N5);
					
				}
				
			//Cam engineer
				
				int camq= 33;// manual entry,start point
				String camq1=String.valueOf(camq); 
				
				int camm=127;// manual entry,last point
				
				int camN= 900;// manual entry,starting increement
				String camN1=String.valueOf(camN);
				
				int camo=24250;// manual entry,last increement
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				Actions act1 = new Actions(driver);
				act1.sendKeys(Keys.PAGE_DOWN).build().perform();

				
				
			    for (int f1 = 74; f1 <= 74 + (camm - camq); f1++) {

			    driver.findElement(By.xpath("//*[@id=\"newPointForm\"]/div[2]/button[1]")).click();
				WebElement pointsj = driver.findElement(By.xpath("//*[@id=\"points_type_" + f1 + "\"]"));
				pointsj.click();
				pointsj.sendKeys("cam");
				}
				
				int camqu;
			    for (int f2 = 74; f2 <= 74 +94 ; f2++) {

			    	camqu=camq++;
			    	String camqu1=String.valueOf(camqu); 
			    	
					WebElement pointsjs = driver.findElement(By.xpath("//*[@id=\"points_"+f2+"\"]"));
					pointsjs.clear();
					pointsjs.sendKeys(camqu1);
					
					}

				
			    int addia=150;// ,manual input increment of amont 
				int addi1a=200;
				int addi2a=250;
				int addi3a=300;
				int addi4a=350;
				
			    
			    for(int h12=74; h12 <= 74 +94; h12++) {
					
					if(camN==1800) {
						addia=addi1a;
					}
					
					if(camN==2800) {
						addia=addi2a;
					}
					
					if(camN==3800) {
						addia=addi3a;
					}
					if(camN==6800) {
						addia=addi4a;
					}
					
					if(camN==7500) {
						addia=addi2a;
					}
					
					String camN5=String.valueOf(camN);
					WebElement amount1 = driver.findElement(By.xpath("//*[@id=\"amount_"+h12+"\"]"));
					amount1.clear();
					amount1.sendKeys(camN5);
					camN= camN+addia;
					
				}
					
				

		}
		
		
		
		
	}

}
