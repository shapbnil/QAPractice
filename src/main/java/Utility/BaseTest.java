package Utility;

import java.io.File;

import java.io.IOException;

import java.time.Duration;

import java.util.ArrayList;

import java.util.List;

import java.util.concurrent.TimeUnit;

 

/*import javax.imageio.ImageIO;*/

 

import org.apache.commons.io.FileDeleteStrategy;

import org.apache.commons.io.FileUtils;

import org.apache.commons.lang.RandomStringUtils;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.core.Logger;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.Keys;

import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.FluentWait;

import org.openqa.selenium.support.ui.Wait;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.ITestContext;

import org.testng.ITestResult;

import org.testng.annotations.AfterSuite;

 

/*import ru.yandex.qatools.ashot.AShot;

import ru.yandex.qatools.ashot.Screenshot;

import ru.yandex.qatools.ashot.shooting.ShootingStrategies;*/

 

 

public class BaseTest {

               

                protected Logger log = (Logger) LogManager.getLogger(this.getClass().getName());

                protected WebDriver driver;

              

               

                @AfterSuite()

                public void endingTestSuite(ITestContext iTestContext) {

                               

                                log.info("Completed execution of Test Suite: " + iTestContext.getSuite().getName());

                               

                }

                               

                @SuppressWarnings("unused")

                private void deleteScreenshots(ITestContext iTestContext) {

 

                                File dir = new File("./screenshots/");

                                String suiteName = iTestContext.getCurrentXmlTest().getSuite().getName();

 

                                if (dir.exists()) {

                                                for (File file : dir.listFiles()) {

                                                                if (file.getName().startsWith(suiteName)) {

                                                                                try {

                                                                                                FileDeleteStrategy.FORCE.delete(file);

                                                                                } catch (IOException e) {

                                                                                                e.printStackTrace();

                                                                                }

                                                                }

                                                }

                                }

                               

                }

               

                public String getTestName(ITestResult iTestResult) {

                               

                                String suiteName = iTestResult.getTestContext().getSuite().getName();

                                String testName = null;

                               

                                try {

                                                 

                                                testName = iTestResult.getParameters()[0].toString();

                                 

                                }

                                catch(Exception e) {

                               

                                                // Do nothing

                                }

 

                                if (testName == null || testName.contains("TestRunner"))                                           

                                                testName = iTestResult.getMethod().getMethodName();

                               

                                return suiteName + "_" + testName + "_" + iTestResult.getTestClass().getName();

                               

                }

               

                public void checkStatus(ITestResult testResult) {

                               

                                @SuppressWarnings("unused")

                                String fileName = null;

                                String result = null;

                               

                                if (testResult.getStatus() == ITestResult.FAILURE || testResult.getStatus() == ITestResult.SUCCESS) {

                                               

                                                result = testResult.getStatus() == ITestResult.SUCCESS ? "passed" : "failed";          

                                                fileName = getTestName(testResult) + "_" + result;                                                                             

                                                //takeScreenshot(fileName);

 

                                }

                                 

                }

               

    public static void takeScreenshot(WebDriver driver, String path, WebElement Element) throws InterruptedException, IOException

    {

                               

                JavascriptExecutor js = (JavascriptExecutor) driver;

                                js.executeScript("arguments[0].scrollIntoView();", Element);

                                Thread.sleep(3000);

                               

                                try {

                                                File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

                                                FileUtils.copyFile(src,new File("src/main/resources/passed-screenshots/" + path + ".png"));

                                }

                                catch (IOException e) {

                                                e.printStackTrace();

                                }

    }

   

    

    public void waitSecond(int sec) {

               

        try {

              

             TimeUnit.SECONDS.sleep(sec);

             

        }

        catch (InterruptedException e) {

              

               log.error("Exception occured. ", e);

              

        }

       

     }

 

                public boolean waitUntilVisible(WebDriver driver, By by) {

                               

                                boolean webElementPresence = false;

 

                                try {

                                               

                                                Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)

                                                                                .withTimeout(Duration.ofSeconds(90))

                                                                                .pollingEvery(Duration.ofMillis(600))

                                                                                .ignoring(NoSuchElementException.class);

                                               

                                                fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));

                                                if (driver.findElement(by).isDisplayed()) {

                                                               

                                                                webElementPresence= true;

                                                               

                                                }

                                }

                                catch (TimeoutException toe) {

                                               

                                                log.error("Wait for element has timed out.", toe);

                                                webElementPresence = false;

                                               

                                }

                                catch (Exception e) {

                                               

                                                log.error("Error occured.", e);

                                                webElementPresence = false;

                                               

                                }

                                return webElementPresence;

                               

                }

               

                public static void WaitUntilVisible(WebDriver driver, By by, int iTimeout)

                {

                                WebDriverWait wait = new WebDriverWait(driver, iTimeout);

                                wait.until(ExpectedConditions.visibilityOfElementLocated(by));

                }

               

                public boolean verifyText(WebDriver driver, By by, String expectedText)

                {

                               

                                boolean result;

                                                                               

                                List<WebElement> elements = driver.findElements(by);

                                if (!elements.isEmpty()) {

                                               

                                                String text = elements.get(0).getText();

                                               

                                                if (text.contains(expectedText)) {

                                                               

                                                                log.info("'" + expectedText + "' is found.");

                                                                result = true;

                                                               

                                                }

                                                else {

                                                               

                                                                log.error("'" + expectedText + "' is not found.");

                                                                log.error("Found this text instead: '" + text + "'");

                                                                result = false;

                                                               

                                                }

                                }

                                else {

                                               

                                                log.error("cannot locate '" + expectedText + "'");

                                                result = false;

                                               

                                }

                                return result;

                }

               

                public void clickIfPresent(WebDriver driver, By by)

                {

                               

                                List<WebElement> elements = driver.findElements(by);

                                if (!elements.isEmpty()) {

                                               

                                                elements.get(0).click();

                                    waitSecond(1);

                                   

                                }

                               

                }

               

                public void mouseOverElement(WebDriver driver, WebElement ele)

                {

                               

                                Actions action = new Actions(driver);

                                action.moveToElement(ele);

                               

                }

               

                public void scrollDown(WebDriver driver, int times) {

                               

                                Actions action = new Actions(driver);

                                for (int i = 1; i <= times; i++)

                                                action.sendKeys(Keys.PAGE_DOWN).build().perform();                 

                               

                }

               

                public void scrollUp(WebDriver driver, int times) {

                               

                                Actions action = new Actions(driver);

                                for (int i = 1; i <= times; i++)

                                                action.sendKeys(Keys.PAGE_UP).build().perform();

                               

                }

               

                public static void scrollIntoView(WebDriver driver, By by) {

                               

                                WebElement element = driver.findElement(by);

                                Actions actions = new Actions(driver);

                                actions.moveToElement(element);

                                actions.perform();

                               

                }

               

                public static void SwitchWindow(WebDriver driver, int tabNumber) {

                                ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());

                                driver.switchTo().window(tabs2.get(tabNumber));

                }

 

                public void jsClick(WebDriver driver, By by) {

                               

                                WebElement element = driver.findElement(by);

                                JavascriptExecutor executor = (JavascriptExecutor)driver;

                                executor.executeScript("arguments[0].click();", element);

                               

                }

 

                public static void UploadFile(WebDriver driver, String filename) {

                               

                                String inputText = System.getProperty("user.dir") + filename;

                                WebElement uploadElement = driver.findElement(By.id("fileUploadInitial"));

                                ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('class')", uploadElement);

                                uploadElement.sendKeys(inputText);

                               

                }

                public void closeModalIfPresent(WebDriver driver) {

                               

                                //check if any modal dialog is present and click on 'default' designated button

               

                waitSecond(1);

                List<WebElement> dlgs = driver.findElements(By.cssSelector("div.modal-dialog"));

                boolean dlgDisplayed = false;

                WebElement dlgWanted = null;

                for (WebElement dlg: dlgs) {

                               

                                if (dlg.isDisplayed()) {

                                               

                                                dlgDisplayed = true;

                                                dlgWanted = dlg;

                                                break;

                                               

                                }

                }

                if (dlgDisplayed && dlgWanted != null) {

                               

                                log.info("Modal dialog displayed with following text: " + dlgWanted.getText());

                                List<WebElement> btns = dlgWanted.findElements(By.tagName("button"));

                                for (WebElement btn: btns) {

                                               

                                                if (btn.getAttribute("class").contains("default")) {

                                                               

                                                                System.out.println("Clicking on the button: " + btn.getText());

                                                                btn.click();

                                                                waitSecond(1);

                                                               break;

                                                               

                                                }

                                }

                }

                               

                }

 

    public int getRandomNumberBetween(int start, int end) {

               

        return start + (int)Math.round(Math.random() * (end - start));

       

    }

 

    public String getRandomAlphaString() {

               

                return RandomStringUtils.randomAlphabetic(getRandomNumberBetween(3, 9)).toUpperCase();

               

    }

   

    public String getRandomAlphaNumericString() {

               

                return RandomStringUtils.randomAlphanumeric(getRandomNumberBetween(5, 9)).toUpperCase();

               

    }

   

    public String getRandomPassportNumber() {

               

                return RandomStringUtils.randomAlphabetic(1).toUpperCase() +

                                                RandomStringUtils.random(8, false, true);

                                               

    }

   

    public String getRandomVisaNumber() {

               

                String str = getRandomPassportNumber();

                return str.substring(0, str.length() - 1);

               

    }

 

}
