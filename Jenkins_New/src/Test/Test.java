package Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;

import javax.naming.AuthenticationException;

import mx4j.tools.remote.http.HTTPConnection;
import net.sf.saxon.functions.BaseURI;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializer;

import Test.ZapiApi.Status;

public class Test   {

	public static void main(String[] args) throws ClientProtocolException, IOException, AuthenticationException, JSONException {
		
		//Get all project
		 final String BASE_URL = "http://192.168.11.41:8080";
		    final String ZAPI_URL = BASE_URL + "/rest/api/2/project";
		 final String CREDENTIALS = "sabyasachi.rout:S@1234";
		
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
	        	System.out.println(httpCon.getResponseMessage());
	        	BufferedReader br=new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
	        	//System.out.println(br.readLine());
	        	//StringBuffer resultString=new StringBuffer();
	        	
	        	String jsonResponse=br.readLine();
	        	
	        	//System.out.println("Json line  [{"+ jsonResponse);
	        	
	        	System.out.println(jsonResponse);
	        	JSONArray projectArray;
				try {
					projectArray = new JSONArray(jsonResponse);
					for (int i = 0; i < projectArray.length(); i++) {
						JSONObject proj = projectArray.getJSONObject(i);
						System.out.println("Key: "+proj.getString("key")+", Name: "+proj.getString("name")+" , projectId :"+proj.getString("id"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	        	
	        	
	        }
	        
	        /*//create issue
	        String createIssueData = "{\"fields\":{\"project\":{\"key\":\"SAB\"},\"summary\":\"REST Test issue new by Sabya\",\"issuetype\":{\"name\":\"Bug\"}}}";
	        
	        String issueUrl=BASE_URL+"/rest/api/2/issue";
	        HttpURLConnection issueCon=(HttpURLConnection) new URL(issueUrl).openConnection();
	        issueCon.setDoInput(true);
	        issueCon.setRequestMethod("POST");
	        issueCon.setDoOutput(true);
	        final String encoding1 = new Base64().encodeToString(CREDENTIALS.getBytes());
	        issueCon.setRequestProperty("Authorization", "Basic " + encoding1);
	 
	        issueCon.setRequestProperty("Content-type", "application/json");
	        
	        
	        int responseCode=issueCon.getResponseCode();
	        System.out.println(issueCon.getResponseCode());
	        if (responseCode==401) {
	        	System.out.println("Invalid UserName or Password");
				
			}
	       
				//System.out.println(issueCon.getResponseMessage());
				OutputStreamWriter osw=new OutputStreamWriter(issueCon.getOutputStream());
				osw.write(createIssueData);
				osw.close();
				System.out.println("sucessfully created issue");
				 final BufferedReader rd =
			                new BufferedReader(new InputStreamReader(issueCon.getInputStream()));
			        final StringBuffer result = new StringBuffer();
			        String line = "";
			        while (null != (line = rd.readLine())) {
			           System.out.println( result.append(line));
			        }
	        */
	        
			        
			String cycleRetrive="{\n    \"projectId\": 10103,\n    \"versionId\": -1,\n    \"offset\": 0,\n    \"expand\": \"executionSummaries\"\n}";        
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
	        


	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        String retriveTestCycle=BASE_URL+"/rest/zapi/latest/cycle/"+"87";
	        HttpURLConnection retriveCycle=(HttpURLConnection) new URL(retriveTestCycle).openConnection();
	        retriveCycle.setDoInput(true);
	        retriveCycle.setDoInput(true);
	        retriveCycle.setRequestMethod("GET");
	        retriveCycle.setRequestProperty("Authorization", "Basic "+encoding);
	        retriveCycle.setRequestProperty("Content-type", "application/json");
	        BufferedReader br1=new BufferedReader(new InputStreamReader(retriveCycle.getInputStream()));
	        System.out.println("Cycle Resource  :"+br1.readLine());
	         
	        
	        /*String deletecycle=BASE_URL+"/rest/zapi/latest/cycle/57";
	        HttpURLConnection delconn=(HttpURLConnection) new URL(deletecycle).openConnection();
	        delconn.setDoOutput(true);
	        delconn.setDoInput(true);
	        delconn.setRequestMethod("DELETE");
	        delconn.setRequestProperty("Authorization", "Basic "+encoding);
	        delconn.setRequestProperty("Content-type", "application/json");
	        BufferedReader br2=new BufferedReader(new InputStreamReader(delconn.getInputStream()));
	        System.out.println(br2.readLine());*/
	        
	       /* //for creating cycle
	        String createCycle=BASE_URL+"/rest/zapi/latest/cycle";
	        HttpURLConnection cloneCycle1=(HttpURLConnection) new URL(createCycle).openConnection();
	        cloneCycle1.setDoInput(true);
	        cloneCycle1.setDoOutput(true);
	        cloneCycle1.setRequestMethod("POST");
	        cloneCycle1.setRequestProperty("Authorization", "Basic "+encoding);
	        cloneCycle1.setRequestProperty("Content-type", "application/json");
	        
	        JSONObject obj=new JSONObject();
	        obj.put("name", "sabya_created");
	        obj.put("projectId", "10103");
	        obj.put("versionId", "-1");
	        OutputStreamWriter osw1=new OutputStreamWriter(cloneCycle1.getOutputStream());
	        osw1.write(obj.toString());
	        osw1.flush();
	        osw1.close();
	        
	        BufferedReader br3=new BufferedReader(new InputStreamReader(cloneCycle1.getInputStream()));
	        System.out.println("Cycle creation Sucessful"+br3.readLine());*/
	        
	        
	      /*  //for cloning
	        String addTestCase=BASE_URL+"/rest/zapi/latest/cycle";
	        HttpURLConnection tconn=(HttpURLConnection) new URL(addTestCase).openConnection();
	        tconn.setDoInput(true);
	        tconn.setDoOutput(true);
	        tconn.setRequestMethod("POST");
	        tconn.setRequestProperty("Authorization", "Basic "+encoding);
	        tconn.setRequestProperty("Content-type", "application/json");
	        JSONObject obj1=new JSONObject();
	        obj1.put("clonedCycleId", "53");
	        obj1.put("description", "sabya");
	        obj1.put("name", "Cloning Sucessful");
	        obj1.put("versionId", "-1");
	        obj1.put("projectId", "10103");
	        obj1.put("build", "sabya");*/
	       
	       
	       /* OutputStreamWriter outtest=new OutputStreamWriter(tconn.getOutputStream());
	        outtest.write(obj1.toString());
	        outtest.flush();
	        outtest.close();
	        
	        BufferedReader br4=new BufferedReader(new InputStreamReader(tconn.getInputStream()));
	        System.out.println(br4.readLine());
	        
	        String retriveIssues=BASE_URL+"/rest/zapi/latest/issues";
	        HttpURLConnection iconn=(HttpURLConnection) new URL(retriveIssues).openConnection();
	        iconn.setDoInput(true);
	        iconn.setDoOutput(true);
	        iconn.setRequestMethod("GET");
	        iconn.setRequestProperty("Authorization", "Basic "+encoding);
	        iconn.setRequestProperty("Content-type", "application/json");
	        BufferedReader br5=new BufferedReader(new InputStreamReader(iconn.getInputStream()));
	        System.out.println(br5.readLine());*/
	        
	        
	       /* //Index all Execution to get Token
	        String latestExecu=BASE_URL+"/rest/zapi/latest/execution/updateBulkStatus";
	        HttpURLConnection lconn=(HttpURLConnection) new URL(latestExecu).openConnection();
	        lconn.setDoInput(true);
	        lconn.setDoOutput(true);
	        lconn.setRequestMethod("PUT");
	        lconn.setRequestProperty("Authorization", "Basic "+encoding);
	        lconn.setRequestProperty("Content-type", "application/json");
	       
	        BufferedReader br6=new BufferedReader(new InputStreamReader(lconn.getInputStream()));
	        System.out.println(br6.readLine());*/
	        
	        /*//Retrive status of indexing using Token
	        String indexstat=BASE_URL+"/rest/zapi/latest/execution/indexStatus/1404472616774";
	        HttpURLConnection stConn=(HttpURLConnection) new URL(indexstat).openConnection();
	        stConn.setDoInput(true);
	        stConn.setDoOutput(true);
	        stConn.setRequestMethod("GET");
	        stConn.setRequestProperty("Authorization", "Basic "+encoding);
	        stConn.setRequestProperty("Content-type", "application/json");
	        BufferedReader br7=new BufferedReader(new InputStreamReader(stConn.getInputStream()));
	        System.out.println(br7.readLine());*/
	        
	        //retrive all executions based on cycle
	        String retexecution=BASE_URL+"/rest/zapi/latest/execution?cycleId=91";
	        HttpURLConnection rconn=(HttpURLConnection) new URL(retexecution).openConnection();
	        rconn.setDoInput(true);
	        rconn.setDoOutput(true);
	        rconn.setRequestMethod("GET");
	        rconn.setRequestProperty("Authorization", "Basic "+encoding);
	        rconn.setRequestProperty("Content-type", "application/json");
	        JSONObject obj8=new JSONObject();
	       
	        BufferedReader br8=new BufferedReader(new InputStreamReader(rconn.getInputStream()));
	       
	      String esec=br8.readLine();
	      System.out.println(esec);
	      
	      JSONObject root = new JSONObject(esec);
	      JSONArray sportsArray = root.getJSONArray("executions");
	     
	      System.out.println(sportsArray.length());
	      
	      for (int i = 0; i < sportsArray.length(); i++) {
	    	  JSONObject firstSport = sportsArray.getJSONObject(i);
	    	  String name = firstSport.getString("cycleName"); // basketball
		      int id = firstSport.getInt("cycleId"); // 40
		     // JSONArray leaguesArray = firstSport.getJSONArray("leagues");
	         int executionId=firstSport.getInt("id");
	         int issueId=firstSport.getInt("issueId");
	         String summary=firstSport.getString("summary");
		      System.out.println("Cycle name :"+name +"  "+"CycleId:"+id+" "+"Execution Id  :"+executionId+"  "+" Issue Id  :"+issueId+" "+"summary  :"+summary);
		}
	      
	      
	      //update Execution
	      HttpURLConnection failconn=(HttpURLConnection) new URL(BASE_URL+"/rest/zapi/latest/"+"execution/" + "192" + "/execute").openConnection();
    	  failconn.setDoInput(true);
    	  failconn.setDoOutput(true);
    	  failconn.setRequestMethod("PUT");
    	  failconn.setRequestProperty("Authorization", "Basic "+encoding);
    	  failconn.setRequestProperty("Content-type", "application/json");
    	  
    	  int status=2;
    	  JSONObject failObj=new JSONObject();
    	  failObj.put("status", String.valueOf(status));
          failObj.put("comment", "Test fail");
          OutputStreamWriter failOut=new OutputStreamWriter(failconn.getOutputStream());
          failOut.write(failObj.toString());
          failOut.flush();
          failOut.close();
          BufferedReader brFail=new BufferedReader(new InputStreamReader(failconn.getInputStream()));
          System.out.println(brFail.readLine());
          
	      
          //add Atatchment
          File file1=new File("C:\\Users\\Sabyasachi.Rout\\Desktop\\JiraSnapshot\\"+"TC_0109-07-2014_174952"+".png");
         
   
	      
	      
	}
}
