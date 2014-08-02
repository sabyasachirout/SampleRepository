package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.naming.AuthenticationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class JiraListner implements ITestListener {
	
	
	final String BASE_URL = "http://192.168.11.41:8080";
    final String ZAPI_URL = BASE_URL + "/rest/api/2/project";
    final String CREDENTIALS = "sabyasachi.rout:S@1234";
    final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
	private static String projectName="";
	public static void getProjectName(String currentproject){
		 projectName=currentproject;
		
	}
	public enum Status {
        PASS(1), FAIL(2), WIP(3), BLOCKED(4);
        private final int value;
 
        private Status(final int value) {
            this.value = value;
        }
 
        public int getValue() {
            return value;
        }
    }
	
	static String CREDENTIALS1="";
	public static String getProperties(String property){
    try {
	FileInputStream fi=new FileInputStream("C:\\Users\\Sabyasachi.Rout\\Desktop\\MyWorkspace\\Jenkins_New\\config.properties");
	  Properties prop=new Properties();
	  prop.load(fi);
	  fi.close();
	  CREDENTIALS1=prop.getProperty(property);
	  
    } catch (Exception e) {
	e.printStackTrace();
    }
    return CREDENTIALS1;
		
	}
	
	

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		System.out.println("Test starts.....");
		String projectId="";
		try {
			 final String BASE_URL = "http://192.168.11.41:8080";
			    final String ZAPI_URL = BASE_URL + "/rest/api/2/project";
			 final String CREDENTIALS = getProperties("JiraCredentials");
			
			 final HttpURLConnection httpCon = (HttpURLConnection) new URL(ZAPI_URL).openConnection();
			 
		        httpCon.setDoOutput(true);
		        httpCon.setRequestMethod("GET");
		 
		        final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
		        httpCon.setRequestProperty("Authorization", "Basic " + encoding);
		 
		        httpCon.setRequestProperty("Content-type", "application/json");
		        
		        int statusCode = httpCon.getResponseCode();
		        if (statusCode == 401) {
		            throw new AuthenticationException("Invalid Username or Password");
		        }
		        else{
		        	System.out.println("Sucessfully Loged in to Jira server");
		        	//System.out.println(httpCon.getResponseMessage());
		        	BufferedReader br=new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
		        	//System.out.println(br.readLine());
		        	//StringBuffer resultString=new StringBuffer();
		        	
		        	String jsonResponse=br.readLine();
		        	
		        	//System.out.println("Json line  [{"+ jsonResponse);
		        	
		        	//System.out.println(jsonResponse);
		        	JSONArray projectArray = new JSONArray(jsonResponse);
					for (int i = 0; i < projectArray.length(); i++) {
						JSONObject proj = projectArray.getJSONObject(i);
						//System.out.println("Key: "+proj.getString("key")+", Name: "+proj.getString("name")+" , projectId :"+proj.getString("id"));
						if (proj.getString("name").equalsIgnoreCase(projectName)) {
							projectId=proj.getString("id");
							
						}
					}
		        }
		        
		        
		        
		        
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult fail) {
		
		File scrFile = ((TakesScreenshot)Driver.driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh-mm-ss");
		String destDir ="C:\\Users\\Sabyasachi.Rout\\Desktop\\JiraSnapshot";
		new File(destDir).mkdirs();
	    Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
		
		
		String destFile=fail.getMethod().getMethodName()+format1.format(cal.getTime())+".png";
		
        try {
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	    //System.out.println(fail.getThrowable().getStackTrace());
	    StringWriter failSw=new StringWriter();
	    fail.getThrowable().printStackTrace(new PrintWriter(failSw));
	    String failStacktrace=failSw.toString();
	   // System.out.println(failStacktrace);
		//System.out.println("Fail Method Name  :"+fail.getName());
		try {
			
			 String retexecution=BASE_URL+"/rest/zapi/latest/execution?cycleId="+getProperties("TestCycleId");
		        HttpURLConnection rconn=(HttpURLConnection) new URL(retexecution).openConnection();
		        rconn.setDoInput(true);
		        rconn.setDoOutput(true);
		        rconn.setRequestMethod("GET");
		        rconn.setRequestProperty("Authorization", "Basic "+encoding);
		        rconn.setRequestProperty("Content-type", "application/json");
		        JSONObject obj8=new JSONObject();
		       
		        BufferedReader br8=new BufferedReader(new InputStreamReader(rconn.getInputStream()));
		       
		      String esec=br8.readLine();
		     // System.out.println(esec);
		      
		      JSONObject root = new JSONObject(esec);
		      JSONArray failArray = root.getJSONArray("executions");
		     
		      //System.out.println(failArray.length());
		      
		      for (int i = 0; i < failArray.length(); i++) {
		    	  JSONObject firstSport = failArray.getJSONObject(i);
		    	  String name = firstSport.getString("cycleName"); 
			      int id = firstSport.getInt("cycleId"); 
			   
			      int executionId=firstSport.getInt("id");
			      int issueId=firstSport.getInt("issueId");
		         String summary=firstSport.getString("summary");
			      //System.out.println("Cycle name :"+name +"  "+"CycleId:"+id+" "+"Execution Id  :"+executionId+"  "+" Issue Id  :"+issueId+" "+"summary  :"+summary);
			      if (summary.equalsIgnoreCase(fail.getName())) {
			    	  HttpURLConnection failconn=(HttpURLConnection) new URL(BASE_URL+"/rest/zapi/latest/"+"execution/" + executionId + "/execute").openConnection();
			    	  failconn.setDoInput(true);
			    	  failconn.setDoOutput(true);
			    	  failconn.setRequestMethod("PUT");
			    	  failconn.setRequestProperty("Authorization", "Basic "+encoding);
			    	  failconn.setRequestProperty("Content-type", "application/json");
			    	  
			    	  int status=2;
			    	  JSONObject failObj=new JSONObject();
			    	  failObj.put("status", String.valueOf(status));
			          failObj.put("comment", failStacktrace);
			          OutputStreamWriter failOut=new OutputStreamWriter(failconn.getOutputStream());
			          failOut.write(failObj.toString());
			          failOut.flush();
			          failOut.close();
			          BufferedReader brFail=new BufferedReader(new InputStreamReader(failconn.getInputStream()));
			          //System.out.println(brFail.readLine());
			          File fileFail=new File("C:\\Users\\Sabyasachi.Rout\\Desktop\\JiraSnapshot\\"+destFile);
			         // System.out.println(destFile);
			         // ZapiApi.addAttachment(fileFail, executionId);
			          
			          final HttpPost httpPost =
			                  new HttpPost(BASE_URL+"/rest/zapi/latest/"+ "attachment?entityId=" + executionId
			                          + "&entityType=EXECUTION");
			          final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
			          httpPost.setHeader("X-Atlassian-Token", "nocheck");
			          httpPost.setHeader("Authorization", "Basic " + encoding);
			   
			          final MultipartEntity mpEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			          final ContentBody cbFile = new FileBody(fileFail);
			          mpEntity.addPart("file", cbFile);
			          httpPost.setEntity(mpEntity);
			          final HttpResponse response = new DefaultHttpClient().execute(httpPost);
			          final HttpEntity resEntity = response.getEntity();
			   
			          if (null != resEntity) {
			              EntityUtils.consume(resEntity);
			          }
			          
			         
			   
					  
				}
			      
			}
		      
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult pass) {
		try {
			System.out.println("Passed......1");
			/*StringWriter passWriter=new StringWriter();
			pass.getThrowable().printStackTrace(new PrintWriter(passWriter));
			String passStackTrace=passWriter.toString();
			System.out.println(passStackTrace);*/
			File scrFile = ((TakesScreenshot)Driver.driver).getScreenshotAs(OutputType.FILE);
			DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh-mm-ss");
			String destDir ="C:\\Users\\Sabyasachi.Rout\\Desktop\\JiraSnapshot";
			new File(destDir).mkdirs();
		    Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 1);
			SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
			
			
			String destFile=pass.getMethod().getMethodName()+format1.format(cal.getTime())+".png";
			
	        try {
				FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
			 String retexecution=BASE_URL+"/rest/zapi/latest/execution?cycleId="+getProperties("TestCycleId");
		        HttpURLConnection rconn=(HttpURLConnection) new URL(retexecution).openConnection();
		        rconn.setDoInput(true);
		        rconn.setDoOutput(true);
		        rconn.setRequestMethod("GET");
		        rconn.setRequestProperty("Authorization", "Basic "+encoding);
		        rconn.setRequestProperty("Content-type", "application/json");
		        JSONObject obj8=new JSONObject();
		       
		        BufferedReader br8=new BufferedReader(new InputStreamReader(rconn.getInputStream()));
		       
		      String esec=br8.readLine();
		     // System.out.println(esec);
		      
		      JSONObject root = new JSONObject(esec);
		      JSONArray passArray = root.getJSONArray("executions");
		      System.out.println(passArray);
		      System.out.println(passArray.length());
		      
		      for (int i = 0; i < passArray.length(); i++) {
		    	  JSONObject firstSport = passArray.getJSONObject(i);
		    	  String name = firstSport.getString("cycleName"); 
			      int id = firstSport.getInt("cycleId"); 
			   
			      int executionId=firstSport.getInt("id");
			      int issueId=firstSport.getInt("issueId");
		         String summary=firstSport.getString("summary");
		         System.out.println(summary);
			     // System.out.println("Cycle name :"+name +"  "+"CycleId:"+id+" "+"Execution Id  :"+executionId+"  "+" Issue Id  :"+issueId+" "+"summary  :"+summary);
			      if (summary.equalsIgnoreCase(pass.getName())) {
			    	  System.out.println("inside...pass.getname");
			    	  HttpURLConnection passConn=(HttpURLConnection) new URL(BASE_URL+"/rest/zapi/latest/"+"execution/" + executionId + "/execute").openConnection();
			    	  passConn.setDoInput(true);
			    	  passConn.setDoOutput(true);
			    	  passConn.setRequestMethod("PUT");
			    	  passConn.setRequestProperty("Authorization", "Basic "+encoding);
			    	  passConn.setRequestProperty("Content-type", "application/json");
			    	  
			    	  int status=1;
			    	  JSONObject passObj=new JSONObject();
			    	  passObj.put("status", String.valueOf(status));
			    	  passObj.put("comment", "Passed Sucessfully");
			          OutputStreamWriter passOut=new OutputStreamWriter(passConn.getOutputStream());
			          passOut.write(passObj.toString());
			          passOut.flush();
			          passOut.close();
			          BufferedReader brPass=new BufferedReader(new InputStreamReader(passConn.getInputStream()));
			         // System.out.println(brPass.readLine());
			          File filePass=new File("C:\\Users\\Sabyasachi.Rout\\Desktop\\JiraSnapshot\\"+destFile);
			          final HttpPost httpPost =
			                  new HttpPost(BASE_URL+"/rest/zapi/latest/"+ "attachment?entityId=" + executionId
			                          + "&entityType=EXECUTION");
			          final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
			          httpPost.setHeader("X-Atlassian-Token", "nocheck");
			          httpPost.setHeader("Authorization", "Basic " + encoding);
			   
			          final MultipartEntity mpEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			          final ContentBody cbFile = new FileBody(filePass);
			          mpEntity.addPart("file", cbFile);
			          httpPost.setEntity(mpEntity);
			          final HttpResponse response = new DefaultHttpClient().execute(httpPost);
			          final HttpEntity resEntity = response.getEntity();
			   
			          if (null != resEntity) {
			              EntityUtils.consume(resEntity);
			          }
			          System.out.println("Passed......");
					
				}
			      
			}
		      
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
