package Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TC_02 {
	
	@Test
	public void TC_02() throws Exception{
		Driver.driver.get(Driver.getLocators("ApplicationUrl"));
		Driver.driver.manage().window().maximize();
		Driver.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		/*
		 * Verification point 
		 */
		String window= Driver.driver.getTitle();
		
		try {
			Assert.assertEquals(window, "Recruitment System");
			System.out.println("Sucessfully navigated to Recruitment System Page");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		Driver.driver.findElement(By.id(Driver.getLocators("Username"))).sendKeys(Driver.getLocators("UserId"));
		//System.out.println(Driver.getLocators("Password"));
		Driver.driver.findElement(By.id(Driver.getLocators("PasswordTextBox"))).sendKeys(Driver.getLocators("Password"));
		Driver.driver.findElement(By.id(Driver.getLocators("LoginButton"))).click();
		/*
		 * Verification For Home Page
		 
		 */
        String home= Driver.driver.getTitle();
		
		try {
			Assert.assertEquals(home, "Home");
			System.out.println("Sucessfully navigated to Home Page");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		Driver.driver.findElement(By.linkText(Driver.getLocators("SourceLink"))).click();
		/*
		 * Verification For Source Master
		 *
		 */
		 String sourceMaster= Driver.driver.getTitle();
			
			try {
				Assert.assertEquals(sourceMaster, "Source Master");
				System.out.println("Sucessfully navigated to Source Master Page");
			} catch (Exception e) {
				System.out.println(e);
			}
			
		
		Driver.driver.findElement(By.id(Driver.getLocators("AddNewSource"))).click();
		/*
		 * Verification For Add Source
		 */
		String addSource= Driver.driver.getTitle();
		
		try {
			Assert.assertEquals(addSource, "Add Source");
			System.out.println("Sucessfully navigated to Source Master Page");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		Driver.driver.findElement(By.id(Driver.getLocators("SourceCode"))).sendKeys(Driver.getLocators("SourceCodeData"));
		Driver.driver.findElement(By.id(Driver.getLocators("SourceName"))).sendKeys(Driver.getLocators("SourceNameData"));
		Driver.driver.findElement(By.id(Driver.getLocators("SubmitButton"))).click();
		/*
		 * Verification For New Add Source
		 */
		Thread.sleep(1000);
		String sucessMsg=Driver.driver.findElement(By.cssSelector("body")).getText();
		try {
			if (sucessMsg.contains("Source added successfully")) {
				
				System.out.println("Sucessfully Added Source");
				
				
			}
			else {
				throw new Exception("Sucessfully Added Source  Text Not Present");
			}
			
		} catch (Exception e) {
			throw new Exception("Sucessfully Added Source  Text Not Present");
		}
		
	}
	@AfterTest
	public void after(){
		
	}

}
