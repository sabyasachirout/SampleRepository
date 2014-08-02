package Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.randomsync.testng.excel.ExcelTestNGRunner;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

public class JiraRunner {
	

	public static void main(String args[]){
		  int tcId=0;
		   int rowincrementer=3;
		try {
			final String BASE_URL = "http://192.168.11.41:8080";
		    final String ZAPI_URL = BASE_URL + "/rest/api/2/project";
		    final String CREDENTIALS = "sabyasachi.rout:S@1234";
		    final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
		    
		    String summary;
			    String retexecution=BASE_URL+"/rest/zapi/latest/execution?cycleId=87";
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
		         summary=firstSport.getString("summary");
			      System.out.println("Cycle name :"+name +"  "+"CycleId:"+id+" "+"Execution Id  :"+executionId+"  "+" Issue Id  :"+issueId+" "+"summary  :"+summary);
			      
			      tcId++;
			      rowincrementer++;
			        FileInputStream fi=new FileInputStream("C:\\Users\\Sabyasachi.Rout\\Desktop\\jenkins\\Test_configuration.xls");
					Workbook wb=Workbook.getWorkbook(fi);
					Sheet sh=wb.getSheet(0);
					FileOutputStream fo=new FileOutputStream("C:\\Users\\Sabyasachi.Rout\\Desktop\\jenkins\\Test_configuration.xls");
					WritableWorkbook wwb=Workbook.createWorkbook(fo, wb);
					WritableSheet wsh=wwb.getSheet(0);
					Label l=new Label(0, rowincrementer, String.valueOf(tcId));
					
					Label l1=new Label(1, rowincrementer, "classes=Test."+summary);
					wsh.addCell(l);
					wsh.addCell(l1);
					wwb.write();
					wwb.close();
					
			      
		      }
		      
		      Thread.sleep(20000);
		      

System.out.println("Here-1");

				ExcelTestNGRunner runner=new ExcelTestNGRunner("C:\\Users\\Sabyasachi.Rout\\Desktop\\jenkins\\Test_configuration.xls");
				System.out.println("excel write complete");

System.out.println("Here-2");
				runner.setPreserveOrder(true);
System.out.println("Here-3");
				runner.setVerbose(5);
				runner.addListener(new JiraListner());
				runner.run();
				System.out.println("run completes");
		} catch (Exception e) {
			System.out.println(e.getMessage());
            e.printStackTrace();
		}
	}
}
