package Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TC_03 {
	
	@Test
	public void TC_03(){
		Driver.driver.get(Driver.getLocators("ApplicationUrl"));
		Driver.driver.manage().window().maximize();
		Driver.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Driver.driver.findElement(By.id(Driver.getLocators("Username"))).sendKeys(Driver.getLocators("UserId"));
		//System.out.println(Driver.getLocators("Password"));
		Driver.driver.findElement(By.id(Driver.getLocators("PasswordTextBox"))).sendKeys(Driver.getLocators("Password"));
		Driver.driver.findElement(By.id(Driver.getLocators("LoginButton"))).click();
		Driver.driver.findElement(By.linkText(Driver.getLocators("SourceLink"))).click();
		Driver.driver.findElement(By.id(Driver.getLocators("AddNewSource"))).click();
		Driver.driver.findElement(By.id(Driver.getLocators("SourceCode"))).sendKeys(Driver.getLocators("SourceCodeData"));
		Driver.driver.findElement(By.id(Driver.getLocators("SourceName"))).sendKeys(Driver.getLocators("SourceNameData"));
		Assert.assertEquals("sabya", "sabya");
		
	}
	@AfterTest
	public void after(){
		
	}

}
