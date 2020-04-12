package testPage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import RahulS.QAPractice.PracticePage;
import Utility.BaseTest;
import Utility.BrowserFactory;
import Utility.ExcelDriver;

public class TestRun extends BaseTest {
WebDriver driver;
//constructor  
	@Test(priority=0, description="This Test Case Open the Browser")
	public void setupApplication() throws InterruptedException, IOException {
		String URL = ExcelDriver.getCelldata(1,1, "URL_AND_BROWSER");
		String Browser = ExcelDriver.getCelldata(1,0, "URL_AND_BROWSER");	
		driver = BrowserFactory.StartBrowser(Browser, URL);
		BaseTest.WaitUntilVisible(driver, By.xpath("//*[@id=\"homepage\"]/div[4]/div[2]/div/div/div/span/div/div[7]/div/div/div[2]"), 30);
	
	}
	
@Test (priority=1, description="This Test Case Open the Browser")
public void TestCase() {
	PracticePage p= new PracticePage(driver);
	p.Newsletter();
	p.HomePractice();
	//20 steps
	
	
	
}

@Test (priority=2, description="This Test Case Open the Browser")
public void AllTest() {
	

	
}



}
