package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.naming.AuthenticationException;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JiraFramework {
	private static String projectId="";
	private static int cycleId=0;
	/*
	 * To get project id from Jira
	 */
	public static String getProjectId(String projectName){
    try {
	
    	final String BASE_URL = "http://192.168.11.41:8080";
	    final String ZAPI_URL = BASE_URL + "/rest/api/2/project";
	    final String CREDENTIALS = JiraListner.getProperties("JiraCredentials");
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
        	
        	//System.out.println(jsonResponse);
        	JSONArray projectArray;
			try {
				projectArray = new JSONArray(jsonResponse);
				for (int i = 0; i < projectArray.length(); i++) {
					JSONObject proj = projectArray.getJSONObject(i);
					//System.out.println("Key: "+proj.getString("key")+", Name: "+proj.getString("name")+" , projectId :"+proj.getString("id"));
					if (proj.getString("name").equalsIgnoreCase(JiraListner.getProperties(projectName))) {
						projectId=proj.getString("id");
						//System.out.println(projectId);
						
						/*
						 * to get cycle Id
						 */
						System.out.println("Cycle resource...............");
					      String retriveTestCycle=BASE_URL+"/rest/zapi/latest/cycle?projectId="+projectId;
					      HttpURLConnection retriveCycle=(HttpURLConnection) new URL(retriveTestCycle).openConnection();
					      retriveCycle.setDoInput(true);
					      retriveCycle.setDoInput(true);
					      retriveCycle.setRequestMethod("GET");
					      retriveCycle.setRequestProperty("Authorization", "Basic "+encoding);
					      retriveCycle.setRequestProperty("Content-type", "application/json");
					      BufferedReader br1=new BufferedReader(new InputStreamReader(retriveCycle.getInputStream()));
					      //System.out.println("Cycle Resource  :"+br1.readLine());
					      String jsonResponse1=br1.readLine();
					      JSONObject root = new JSONObject(jsonResponse1);
					      
					      System.out.println(jsonResponse1);
					      JSONArray cycleArray=new JSONArray(jsonResponse1);
					      for (int i1 = 0; i1 < cycleArray.length(); i1++) {
								JSONObject cycleProj = projectArray.getJSONObject(i1);
								System.out.println("Key: "+cycleProj.getString("key")+", Name: "+cycleProj.getString("name")+" , projectId :"+cycleProj.getString("id"));
							}
					     /* JSONArray failArray = root.getJSONArray("executions");
					      System.out.println(failArray);
					      
					      for (int i1 = 0; i1 < failArray.length(); i1++) {
					    	  JSONObject firstSport = failArray.getJSONObject(i1);
					    	  String name = firstSport.getString("cycleName"); 
						      cycleId = firstSport.getInt("cycleId"); 
						   
						      int executionId=firstSport.getInt("id");
						      int issueId=firstSport.getInt("issueId");
						      System.out.println("Cycle Id  ...."+cycleId);
						      
					      }*/

					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        	
        	
        }
} catch (Exception e) {
	e.printStackTrace();
}
	return projectId;
		
	}

}
