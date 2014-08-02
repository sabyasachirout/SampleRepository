package Test;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TC001 {
	private static Logger Log = Logger.getLogger(TC001.class);
	static boolean informatique = false;
	static boolean Addtocart= false;
	//static boolean Viewcart=false;
  @Test
  public void TC001() throws InterruptedException {
	 Driver.driver.get("http://www.cdiscount.com/");
	 Driver.driver.manage().window().maximize();
	/* Driver.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(Driver.driver, 40);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name(Driver.getLocators("cDiscountITName"))));

try {
informatique = Driver.driver.findElement(By.name(Driver.getLocators("cDiscountITName"))).isDisplayed();	
System.out.println("Cdiscount home page navigated successfully");
Log.info("_______Cdiscount home page navigated successfully_______");

} catch (Exception e) {
System.out.println("Cdiscount home page fails to load");
Log.info("_______Cdiscount home page fails to load_______");
}


//Mouseover action on InformationTechnology menu link'
Actions actions = new Actions(Driver.driver);

WebElement menuHoverLink = Driver.driver.findElement(By.name(Driver.getLocators("cDiscountITName")));

actions.moveToElement(menuHoverLink).moveToElement(Driver.driver.findElement(By.name(Driver.getLocators("cDiscountITName")))).click().build().perform();
System.out.println("'Informatique' (Information Technology) menu header hovered successfully");



//Thread.sleep(5000);
Driver.driver.get("http://www.cdiscount.com/informatique/tablettes-tactiles-ebooks/v-10798-10798.html");

System.out.println("'Tablettes tactiles' (Tablet PCs) sub menu link accessed successfully");


//Clicking on "Shop by brand" sub menu link

WebElement Brand = wait.until(ExpectedConditions.elementToBeClickable(By.name(Driver.getLocators("cDiscountShopByBrandName"))));
Driver.driver.findElement(By.name(Driver.getLocators("cDiscountShopByBrandName"))).click();
System.out.println("'Choix par marque' (Shop per brand) option selected successfully present below the Tablet PCs section");



try {
	//Thread.sleep(3000);
	Addtocart = Driver.driver.findElement(By.xpath(Driver.getLocators("cDiscountAddtocartXPATH"))).isDisplayed();	// Add to cart--- > Ajouter au panier	
	System.out.println("Selected handset details page navigated successfully");
	Log.info("_______Selected handset details page navigated successfully_______");

} catch (Exception e) {
	System.out.println("Selected handset details page fail to load");
	Log.info("_______Selected handset details page fail to load_______");
}

Driver.driver.findElement(By.xpath(Driver.getLocators("cDiscountAddtocartXPATH"))).click();
System.out.println("Add To Cart button clicked on successfully");
Log.info("_______Add To Cart button clicked on successfully_______");

try{
	
	if ( Driver.driver.findElement(By.xpath(Driver.getLocators("viewcart"))).isDisplayed()) {
		System.out.println("View cart Button is displayed");
		Log.info("_______View cart Button is displayed_______");	
	}
	else {
		
		System.out.println("User is unable to see the view Cart");
		Log.info("_______User is unable to see the view Cart_______");	
		throw new Exception("element not visible");
	}
			
}catch (Exception e){
	e.printStackTrace();
}
   
{
	
	
}
//Thread.sleep(5000);


//Driver.driver.findElement(By.xpath(Driver.getLocators("viewcart"))).clear();
//System.out.println("Clcik on Viewcart , user can able to see his Products");
//Log.info("_______Clcik on Viewcart , user can able to see his Products_______");
*/

  }
  @AfterSuite
  public void after(){
	  Driver.driver.quit();
  }
}
