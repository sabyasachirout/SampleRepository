package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class Issue {

	public static void main(String[] args) {
		try {
			final String BASE_URL = "http://192.168.11.41:8080";
			final String CREDENTIALS = "sabyasachi.rout:S@1234";
			 final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
			 
			    String retriveTestCycle=BASE_URL+"/rest/zapi/latest/cycle/"+"87";
		        HttpURLConnection retriveCycle=(HttpURLConnection) new URL(retriveTestCycle).openConnection();
		        retriveCycle.setDoInput(true);
		        retriveCycle.setDoInput(true);
		        retriveCycle.setRequestMethod("GET");
		        retriveCycle.setRequestProperty("Authorization", "Basic "+encoding);
		        retriveCycle.setRequestProperty("Content-type", "application/json");
		        BufferedReader br1=new BufferedReader(new InputStreamReader(retriveCycle.getInputStream()));
		        String str=br1.readLine();
		        System.out.println(str);
		        
		        //Using Gson To Parse Json response
		        JsonObject obj=(JsonObject) new JsonParser().parse(str);
		        
		        System.out.println(obj.get("id"));
		        System.out.println(obj.get("name"));
		        System.out.println(obj.get("projectId"));
		        
		        String retriveIssue=BASE_URL+"rest/zapi/latest/issues/10103";
		        HttpURLConnection issue=(HttpURLConnection) new URL(retriveIssue).openConnection();
		        issue.setDoInput(true);
		        issue.setDoInput(true);
		        issue.setRequestMethod("GET");
		        issue.setRequestProperty("Authorization", "Basic "+encoding);
		        issue.setRequestProperty("Content-type", "application/json");
		        BufferedReader br2=new BufferedReader(new InputStreamReader(retriveCycle.getInputStream()));
		        System.out.println(br2.readLine());
		        /*String str1=br2.readLine();
		        System.out.println(str);*/
		       /* JsonObject obj1=(JsonObject) new JsonParser().parse(str);
		        System.out.println(obj.get("id"));
		        System.out.println(obj.get("name"));
		        System.out.println(obj.get("projectId"));*/
		        
		        
		        
		        
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
