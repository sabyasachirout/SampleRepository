package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import org.testng.TestNG;
import org.testng.collections.Lists;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class JiraTestng {
public static void main(String[] args) {
		  int tcId=0;
		   int rowincrementer=3;
		   
		   
		try {
			System.out.println("Connecting To Jira server.....");
			final String BASE_URL = "http://192.168.11.41:8080";
		   // final String ZAPI_URL = BASE_URL + "/rest/api/2/project";
		    final String CREDENTIALS = JiraListner.getProperties("JiraCredentials");
		    final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
		        String summary;
			    String retexecution=BASE_URL+"/rest/zapi/latest/execution?cycleId="+JiraListner.getProperties("TestCycleId");
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
		      
		      //creation of xml
		      String suiteName="SabyaTest";
				String verbose="2";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.newDocument();
				
				Element suitename=doc.createElement("suite");
				doc.appendChild(suitename);
				
				suitename.setAttribute("name", suiteName);
				suitename.setAttribute("verbose", verbose);
		      
		      for (int i = 0; i < sportsArray.length(); i++) {
		    	  JSONObject firstSport = sportsArray.getJSONObject(i);
		    	  String name = firstSport.getString("cycleName"); // basketball
			      int id = firstSport.getInt("cycleId"); // 40
			     // JSONArray leaguesArray = firstSport.getJSONArray("leagues");
		         int executionId=firstSport.getInt("id");
		         int issueId=firstSport.getInt("issueId");
		         summary=firstSport.getString("summary");
			      //System.out.println("Cycle name :"+name +"  "+"CycleId:"+id+" "+"Execution Id  :"+executionId+"  "+" Issue Id  :"+issueId+" "+"summary  :"+summary);
			      
			      tcId++;
			      rowincrementer++;
			      
			      String tests="Test"+i;
					String autoclass="Test."+summary;
					
					Element testName=doc.createElement("test");
					
					Attr test=doc.createAttribute("name");
					test.setValue(tests);
					testName.setAttributeNodeNS(test);
					
					Attr preserveorder=doc.createAttribute("preserve-order");
					preserveorder.setValue("true");
					testName.setAttributeNodeNS(preserveorder);
					suitename.appendChild(testName);
					
					Element classes=doc.createElement("classes");
					testName.appendChild(classes);
					
					Element classname=doc.createElement("class");
					Attr test2=doc.createAttribute("name");
					test2.setValue(autoclass);
					classname.setAttributeNodeNS(test2);
					classes.appendChild(classname);
					
				}
				
				/*Element listners=doc.createElement("listeners");
				suitename.appendChild(listners);
				Element listner=doc.createElement("listener");
				Attr list=doc.createAttribute("class-name");
				list.setValue("Test.JiraTestng");
				listner.setAttributeNodeNS(list);
				listners.appendChild(listner);
				*/
				
				
				
				
				
				
		 
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("C:\\Users\\Sabyasachi.Rout\\Desktop\\MyWorkspace\\Jenkins_New\\testng.xml"));
		 
				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);
		 
				transformer.transform(source, result);
		        System.out.println("Sucessfully retrived data from Jira Server....");
				System.out.println("File saved!");
				Thread.sleep(1000);
				JiraListner jira=new JiraListner();
				TestNG testng = new TestNG();
				List<String> suites = Lists.newArrayList();
				suites.add("C:\\Users\\Sabyasachi.Rout\\Desktop\\MyWorkspace\\Jenkins_New\\testng.xml");//path to xml..
				//suites.add("c:/tests/testng2.xml");
				testng.setTestSuites(suites);
				testng.addListener(jira);
				testng.run();
			      
			       
					
			      
		      
		      
		     
		      


		} catch (Exception e) {
			System.out.println(e.getMessage());
           e.printStackTrace();
		}

	}

}
