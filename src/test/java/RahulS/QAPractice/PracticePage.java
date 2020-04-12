package RahulS.QAPractice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Utility.BaseTest;
import Utility.BrowserFactory;
import Utility.ExcelDriver;

public class PracticePage {
	
	
 WebDriver driver;
 
 public PracticePage(WebDriver driver) {
	 
	 this.driver=driver;
	 
 }
	// How to get the WebElement = using @FindBy for locating WebElement
	
	
	@FindBy
	(how = How.XPATH, using = "//*[@id=\"homepage\"]/div[4]/div[2]/div/div/div/span/div/div[7]/div/div/div[2]" )
	WebElement Newsletter;
			
	//driver.findElement(By.xpath("//*[text()='NO THANKS']"));
	@FindBy
	(how=How.XPATH, using = "//*[text()='Practice']") 
	WebElement Practice;
	
	
	public void Newsletter() {
	
	Newsletter.click();
	}
	
	public void HomePractice(){
		
		Practice.click();
		
	
		
	}



	
	
	
		//Close Join Our Newsletter Window
		
	
}

