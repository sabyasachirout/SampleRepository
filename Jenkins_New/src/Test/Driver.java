package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Driver {
	
	public static WebDriver driver;
	private static File file=new File("C:\\Users\\Sabyasachi.Rout\\Desktop\\MyWorkspace\\Jenkins_New\\cDiscountLocators.txt");
	//private static File file=new File("C:\\Users\\Sabyasachi.Rout\\Desktop\\MyWorkspace\\Jenkins_New\\Locators");
	
	public static String getLocators(String name) {
		String str=null;
		try {
			
			Scanner sc=new Scanner(file);
			while (sc.hasNext()) {
				String str1=sc.nextLine();
				if (str1.startsWith(name)&&str1.split("=")[0].equals(name)) {
					str=str1.substring(str1.indexOf("=")+1).trim();
					
				}
				
				
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return str;
		
	}
	
		
}
	
	

