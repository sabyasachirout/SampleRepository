package Test;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 


import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xembly.Directives;
import org.xembly.Xembler;

public class XmlCreation {

	 
public static void main(String args[]){
	try {
		 
		String suiteName="TapanTest";
		String verbose="5";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		
		Element suitename=doc.createElement("Suite");
		doc.appendChild(suitename);
		
		suitename.setAttribute("Name", suiteName);
		suitename.setAttribute("verbose", verbose);
		
		
		
		for (int i = 1; i <6; i++) {
			String tests="Test"+i;
			String autoclass="Package."+i;
			
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
		
		Element listners=doc.createElement("listners");
		suitename.appendChild(listners);
		Element listner=doc.createElement("listner");
		Attr list=doc.createAttribute("class-name");
		list.setValue("Package.listner");
		listner.setAttributeNodeNS(list);
		listners.appendChild(listner);
		
		
		
		
		
		
		
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("C:\\Users\\Sabyasachi.Rout\\Desktop\\jenkins\\Testng.xml"));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		System.out.println("File saved!");
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
}

}
