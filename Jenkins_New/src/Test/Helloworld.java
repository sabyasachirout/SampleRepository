package Test;

import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class Helloworld {
	public static void main(String args[]){
		//TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add("C:\\Users\\Sabyasachi.Rout\\Desktop\\MyWorkspace\\Jenkins_New\\Testng.xml");//path to xml..
		//suites.add("c:/tests/testng2.xml");
		testng.setTestSuites(suites);
		testng.run();
	}

	
}
