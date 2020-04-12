package Utility;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

	 ///Method to invoke webdriver and open browser of your choice

    public static WebDriver StartBrowser(String browserName, String URL)

    {

                    if(browserName.equals("IE"))

                    {

                                    EdgeOptions EdgeOptions = new EdgeOptions();

                                    System.setProperty("webdriver.IE.driver", "\\drivers\\IEDriverServer.exe");

                                    WebDriver driver = new EdgeDriver(EdgeOptions);

                                    String currentWindow = driver.getWindowHandle();

                                    driver.switchTo().window(currentWindow);

                                    driver.manage().window().maximize();

                                    driver.get(URL);

                                    return driver;

                    }

                    else if(browserName.equals("Chrome"))

                    {

                                    ChromeOptions chromeOptions = new ChromeOptions();

                                    chromeOptions.setExperimentalOption("useAutomationExtension", false);

                                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

                                    WebDriver driver = new ChromeDriver(chromeOptions);

                                    String currentWindow = driver.getWindowHandle();

                                    driver.switchTo().window(currentWindow);

                                    driver.manage().window().maximize();

                                    driver.get(URL);

                                    return driver;

                    }

                    else if(browserName.equals("Firefox"))

                    {

                                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                                    System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");

                                    WebDriver driver = new FirefoxDriver(firefoxOptions);

                                    String currentWindow = driver.getWindowHandle();

                                    driver.switchTo().window(currentWindow);

                                    driver.manage().window().maximize();

                                    driver.get(URL);

                                    return driver;

                    }

                    return null;

                   

   

    }
    
}
