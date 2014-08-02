package Test;



import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class ManageTopic {

	static boolean status = false;
	
	@BeforeTest
	public void start()  {
	
		System.setProperty("webdriver.ie.driver", "D:\\sabya\\IEChrome\\IEDriverServer.exe");
		Driver.driver = new InternetExplorerDriver();
		Driver.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	
	@Test
	public void ManageTopic() throws Exception  {

		try {
			Logger Log = Logger.getLogger(ManageTopic.class);
			 Driver.driver.get(GetData.getTestData("url"));
			    WebDriverWait wait = new WebDriverWait(Driver.driver, 20);
			    
			    
			     //Explicit wait, script will wait max 20 sec's, If element visible before that it will be proceed.
			    wait.until(ExpectedConditions.visibilityOf(Driver.driver.findElement(By.id(GetData.getLocator("username")))));
			    Driver.driver.findElement(By.id(GetData.getLocator("username"))).sendKeys(GetData.getTestData("Username"));
			    System.out.println("Username entered successfully");
			  	Log.info("_______Username entered successfully_______");
			  	
			    Driver.driver.findElement(By.id(GetData.getLocator("password"))).sendKeys(GetData.getTestData("Password"));
			    System.out.println("Password entered successfully");
			  	Log.info("_______Password entered successfully_______");


				//Driver.driver.findElement(By.linkText("Log On")).click();
				Driver.driver.findElement(By.className(GetData.getLocator("loginButton"))).click();
				System.out.println("LogOn button clicked on successfully");
				  Log.info("_______LogOn button clicked on successfully_______");
				  
				  Thread.sleep(5000);
				  String loggedUserName=Driver.driver.findElement(By.xpath(GetData.getLocator("Logged username"))).getText();
				  Assert.assertEquals(loggedUserName, GetData.getTestData("Username"));
				  System.out.println("Loggedin successfully");
				  Log.info("_______Loggedin successfully_______");
				  
				  
				  ((JavascriptExecutor)Driver.driver).executeScript("arguments[0].scrollIntoView(true);", Driver.driver.findElement(By.xpath(GetData.getLocator("existingLibrary"))));
				  wait.until(ExpectedConditions.visibilityOf(Driver.driver.findElement(By.xpath(GetData.getLocator("existingLibrary")))));
				  Driver.driver.findElement(By.xpath(GetData.getLocator("existingLibrary"))).click();
				  System.out.println("Clicked on existing Library for updating Topic");
				  Log.info("_______Clicked on existing Library for updating Topic_______");
				  
				 
				  //wait.until(ExpectedConditions.visibilityOf(Driver.driver.findElement(By.xpath(".//*[@id='topicViewDiv']/div[2]/div[2]/div/div[1]/table/tbody/tr/td[2]"))));
				 Thread.sleep(5000);
				  Driver.driver.findElement(By.xpath(GetData.getLocator("editIcon"))).click();
				  System.out.println("Edit icon clicked on successfully");
				  Log.info("_______Edit icon clicked on successfully_______");
				  

				   Thread.sleep(10000);
				  Driver.driver.switchTo().frame(GetData.getLocator("iframeplayer"));
				  //wait.until(ExpectedConditions.visibilityOf(Driver.driver.findElement(By.xpath(GetData.getLocator("Edit Library Page")))));
				  Thread.sleep(2000);
				  int page=Driver.driver.findElements(By.xpath(GetData.getLocator("Manage Topic Icon"))).size();
				  if (page!=0) {
					  System.out.println("Successfully navigated to Edit Library Page");
					  Log.info("_______Successfully navigated to Edit Library Page_______");
				} else {
					throw new Exception("Edit Libray page Failed to load");

				}
				  
				  
				  //Clicking on Manage Topics Icon
				  Driver.driver.findElement(By.xpath(GetData.getLocator("Manage Topic Icon"))).click();
				  System.out.println("'Manage Topics' icon clicked on successfully");
				  Log.info("_______'Manage Topics' icon clicked on successfully_______");
				
				
		//Clicking on Add More Details Button
		  Thread.sleep(6000);
		  Driver.driver.findElement(By.xpath(GetData.getLocator("Add More Details"))).click();
		  System.out.println("'Add More Details' button clicked on successfully");
		  Log.info("_______'Add More Details' button clicked on successfully_______");
		  
		  
		  try {
			    status = Driver.driver.findElement(By.xpath(GetData.getLocator("Page Validate"))).isDisplayed();
				if (status) {
				System.out.println("Edit Topic page navigated successfully");
				Log.info("_______Edit Topic page navigated successfully_______");

				} else {
	           throw new Exception("Edit Topic page fails to navigate");
	            }
				
			    } 
		       catch (Exception e) {
				
				System.out.println("Edit Topic page fails to navigate");
				Log.info("_______Edit Topic page fails to navigate_______");
				throw e;
			}

		} catch (Exception e) {
			throw e;
		}
	  
	  
	}
}
