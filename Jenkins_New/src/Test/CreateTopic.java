package Test;




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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class CreateTopic {

	static boolean status = false;
	
	@BeforeTest
	public void start()  {
	
		System.setProperty("webdriver.ie.driver", "D:\\sabya\\IEChrome\\IEDriverServer.exe");
		Driver.driver = new InternetExplorerDriver();
		Driver.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		/*
//For IE Browser
	  	DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	  	capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	  	System.setProperty("webdriver.ie.driver", "..\\KaamsNG\\IEDriverServer.exe");
	  		
	  	Driver.driver = new InternetExplorerDriver(capabilities);
	    */
	    
		//Driver.driver = new FirefoxDriver();
	}
	
	
	@Test
	public void CreateTopic()  {

   try {
	   Logger Log = Logger.getLogger(CreateTopic.class);
		
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
		  
		  int addMoreDetails=Driver.driver.findElements(By.xpath(GetData.getLocator("Add More Details"))).size();
		  if (addMoreDetails!=0) {
			  System.out.println("Add new topic page Navigated sucessfully");
			  Log.info("_______Add new topic page Navigated sucessfully_______");
		} else {
			throw new Exception("Add new topic page Failed to load");

		}
			  
	//Enter text in the Title field
		  Thread.sleep(10000);
		  //Driver.driver.findElement(By.id("TopicTitle")).sendKeys("TestNewTopic");
		  Driver.driver.findElement(By.xpath(GetData.getLocator("Title updated"))).sendKeys(GetData.getTestData("Title updated"));
		  System.out.println("Title updated for New Topic");
		  Log.info("_______Title updated for New Topic_______");
		  
		  
	//Selecting Type dropdown option selection
				Select dropdownType = new Select(Driver.driver.findElement(By.xpath(GetData.getLocator("ManageTopic dropdown"))));
				dropdownType.selectByVisibleText("Generic");
				System.out.println("'Generic' type option selected from dropdown options");
				Log.info("_______'Generic' type option selected from dropdown options_______");
				
	 //Enter text in the Short Description field
		  
		  Thread.sleep(10000);
		  Driver.driver.findElement(By.xpath(GetData.getLocator("Description"))).sendKeys(GetData.getTestData("description"));
		  System.out.println("Short Description updated for New Topic");
		  Log.info("_______Short Description updated for New Topic_______");
		  
		  
		
	//Clicking on Save Info Button
			 Thread.sleep(5000);
			Driver.driver.findElement(By.xpath(GetData.getLocator("Save Info Button"))).click();
			System.out.println("'Save Info' button clicked on successfully");
			Log.info("_______'Save Info' button clicked on successfully_______");
} catch (Exception e) {
	e.printStackTrace();
	e.getCause();
}
	  
	  
	}
	
	@AfterTest
	public void after(){
		Driver.driver.close();
		
	}
}
