package Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Kaams_small {
 /* @Test
  public void f() {
	  WebDriver driver=new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("https://bali.kaams.com/Topic/Detail/781#");
	  driver.findElement(By.xpath("//div[@class='editor-label']/descendant::label[contains(text(),'User name')]/parent::div/following-sibling::div/input[@id='UserName']")).sendKeys("Manish");
	  driver.findElement(By.xpath("//div[@class='editor-label']/descendant::label[contains(text(),'Password')]/parent::div/following-sibling::div/input[@id='Password']")).sendKeys("123456");
	  driver.findElement(By.xpath("//input[@value='Log On']")).click();
	  ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id='libraries']/li[7]/a/div[2]/img")));
	  driver.findElement(By.xpath("//*[@id='libraries']/li[7]/a/div[2]/img")).click();
	  driver.switchTo().frame("iframePlayer");
	  driver.findElement(By.id("LibraryTitle")).sendKeys("sabya");
	  if (driver.findElement(By.xpath("//ul[@class='ul-top-options']/descendant::a[contains(text(),'Library Info')]")).isDisplayed()) {
		  System.out.println("Library Info Present");
		
	}
	  else {
		  System.out.println("Library Info Not Present");
		
	}
	  if (driver.findElement(By.xpath("//ul[@class='ul-top-options']/descendant::a[contains(text(),'Create New Topic')]")).isDisplayed()) {
		  System.out.println("Create New Topic Present");
		
	}
	  else {
		  System.out.println("Create New Topic Not Present");
		
	}
	  if (driver.findElement(By.xpath("//ul[@class='ul-top-options']/descendant::a[contains(text(),'Associate Topic')]")).isDisplayed()) {
		  System.out.println("Associate Topic Present");
		
	}
	  else {
		  System.out.println("Associate Topic Not Present");
		
	}
	  if (driver.findElements(By.id("imgSave")).size()!=0) {
		  System.out.println("Save Button is present");
		
	} else {
		System.out.println("Save button is not present");

	}
	  if (driver.findElements(By.id("imgCancel")).size()!=0) {
		System.out.println("Cancel Button is Present");
	} else {
	System.out.println("Cancel Button is not present");

	}
	 
  }*/
	
	public static void main(String args[]){
		String method=TC_01.class.getName();
		System.out.println(method);
	}
}
