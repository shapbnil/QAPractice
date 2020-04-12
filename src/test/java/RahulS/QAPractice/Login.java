package RahulS.QAPractice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Utility.BaseTest;
import Utility.BrowserFactory;
import Utility.ExcelDriver;

public class Login extends BaseTest{

	public WebDriver driver;

	@Test(priority=0, description="This Test Case Open the Browser")
	public void setupApplication() throws InterruptedException, IOException {
		String URL = ExcelDriver.getCelldata(1,1, "URL_AND_BROWSER");
		String Browser = ExcelDriver.getCelldata(1,0, "URL_AND_BROWSER");	
		driver = BrowserFactory.StartBrowser(Browser, URL);
		BaseTest.WaitUntilVisible(driver, By.xpath("//*[@id=\"homepage\"]/div[4]/div[2]/div/div/div/span/div/div[7]/div/div/div[2]"), 60);
	}
	
	@Test(priority=1, description="This Test Case Close 'Join Our Newsletter Window' & Click on login Link")
	public void loginToApplication() throws InterruptedException {
		
		//Close Join Our Newsletter Window
		
		driver.findElement(By.xpath("//*[@id=\"homepage\"]/div[4]/div[2]/div/div/div/span/div/div[7]/div/div/div[2]")).click();
		//driver.findElement(By.xpath("//*[text()='NO THANKS']"));
		
		
		
		// Click on login link
		driver.findElement(By.xpath("//*[text()='Login']")).click();
	}	
	
	@Test(priority=2, description="This Test Case Enter Email and Password Then Click on Login Button ")
	public void logInToApplication() throws IOException {
		driver.findElement(By.id("user_email")).sendKeys(ExcelDriver.getCelldata(1,0,"QA_PRACTICE"));
		driver.findElement(By.id("user_password")).sendKeys(ExcelDriver.getCelldata(1,1,"QA_PRACTICE"));
		
		//Click on Login Button
		driver.findElement(By.cssSelector(".btn.btn-primary.btn-md.login-button")).click();
	}
		
	@Test(priority=3, description="This Test Case Click on Home Link")
	public void homeLink() {
			// Click on Home Link
			driver.findElement(By.xpath("/html/body/header/div[2]/div/div/div[2]/nav/div[2]/ul/li[3]/a")).click();
		
			
	}
		
	@Test(priority=4, description="This Test Case Click on Practice Project")
	public void practiceProject() {
		//Click on Practice Project
		
			driver.findElement(By.xpath("//*[text()='Practice Projects']")).click();
	}
		
	/*@Test(priority=5, description="This Test Case Click on Automation Practise")
	public void automationPractice() {
		
		// Click on Automation Practise
				driver.findElement(By.xpath("//*[text()='Automation Practise - 2']")).click();			
	}
	*/
	
	@Test(priority=6, description="This Test Case perform Close the Browser")
	public void closeApplication() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	//	Reporter.log("=====Browser Session End=====", true);
	
	}
}
	
	
	
