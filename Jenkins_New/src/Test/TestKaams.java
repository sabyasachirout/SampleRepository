package Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
public class TestKaams {
	@Test
	public void kaams(){
		 WebDriver driver=new FirefoxDriver();
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.get("https://bali.kaams.com/Topic/Detail/781#");
		  driver.findElement(By.xpath("//div[@class='editor-label']/descendant::label[contains(text(),'User name')]/parent::div/following-sibling::div/input[@id='UserName']")).sendKeys("Manish");
		  driver.findElement(By.xpath("//div[@class='editor-label']/descendant::label[contains(text(),'Password')]/parent::div/following-sibling::div/input[@id='Password']")).sendKeys("123456");
		  driver.findElement(By.xpath("//input[@value='Log On']")).click();
		 /* System.out.println(driver.findElements(By.xpath("//ul[@id='libraries']/descendant::li")).size());
		  for (int i = 1; i < driver.findElements(By.xpath("//ul[@id='libraries']/descendant::li")).size(); i++) {
			  if (driver.findElements(By.xpath("//ul[@id='libraries']/descendant::li["+i+"]/a/div[2]")).size()!=0) {
				 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//ul[@id='libraries']/descendant::li["+i+"]/a/div[2]")));
				  driver.findElement(By.xpath("//ul[@id='libraries']/descendant::li["+i+"]/a/div[2]")).click();
				
			}
			
		}*/
		  ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//ul[@id='libraries']/descendant::li[3]/a/div[2]")));
		  driver.findElement(By.xpath("//ul[@id='libraries']/descendant::li[3]/a/div[2]")).click();
		  ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//ul[@id='libraries']/descendant::li[7]/a/div[2]")));
		  driver.findElement(By.xpath("//ul[@id='libraries']/descendant::li[7]/a/div[2]")).click();
		  System.out.println("hello");
	}

}
