package Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import javax.validation.constraints.AssertTrue;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_05 {
  @Test()
  public void TC_05() throws InterruptedException, IOException, JSONException {
	/* // Driver.driver=new FirefoxDriver();
	  
	  WebDriver driver = new FirefoxDriver();
	 driver.get("http://google.com");
	 Assert.assertTrue(driver.getTitle().equals("Google"), "expected Google ");
	 //Assert.assertTrue(driver.getTitle().equals("oogle"));
	  
	  driver.navigate().to("javascript:document.getElementById('overridelink').click()");*/
	  
	  final String BASE_URL = "http://192.168.11.41:8080";
	    final String ZAPI_URL = BASE_URL + "/rest/api/2/project";
	 final String CREDENTIALS = "sabyasachi.rout:S@1234";
	 final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
	          
      String cycleUrl=BASE_URL+"/rest/zapi/latest/cycle?projectId=10201";
      HttpURLConnection cyConn=(HttpURLConnection) new URL(cycleUrl).openConnection();
      cyConn.setDoInput(true);
      cyConn.setDoOutput(true);
      cyConn.setRequestMethod("GET");
      cyConn.setRequestProperty("Authorization", "Basic " +encoding);
      cyConn.setRequestProperty("Content-type", "application/json");
      
      /*OutputStreamWriter out=new OutputStreamWriter(cyConn.getOutputStream());
      out.write(cycleRetrive);
      out.flush();
      out.close();*/
      
      BufferedReader br=new BufferedReader(new InputStreamReader(cyConn.getInputStream()));
      String response1=br.readLine();
      JSONObject object=new JSONObject(response1);
      JSONArray array1=object.getJSONArray("-1");
      for (int i = 0; i < array1.length(); i++) {
      	System.out.println(array1.length());
			
		}
      
		
      System.out.println("Hello sabya    "+response1);
      
      
      
      System.out.println("Cycle resource...............");
      String retriveTestCycle=BASE_URL+"/rest/zapi/latest/execution?cycleId=91";
      HttpURLConnection retriveCycle=(HttpURLConnection) new URL(retriveTestCycle).openConnection();
      retriveCycle.setDoInput(true);
      retriveCycle.setDoInput(true);
      retriveCycle.setRequestMethod("GET");
      retriveCycle.setRequestProperty("Authorization", "Basic "+encoding);
      retriveCycle.setRequestProperty("Content-type", "application/json");
      BufferedReader br1=new BufferedReader(new InputStreamReader(retriveCycle.getInputStream()));
      System.out.println("Cycle Resource  :"+br1.readLine());
      
      
      
      
      /*
       * For project details
       */
      
      
      
  }
}
