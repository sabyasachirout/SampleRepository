package Test;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

public class Practi {

	public static void main(String[] args) {
		try {
			FileInputStream fi=new FileInputStream("C:\\Users\\Sabyasachi.Rout\\Desktop\\MyWorkspace\\Jenkins_New\\config.properties");
			Properties prop=new Properties();
			prop.load(fi);
			fi.close();
			String url = prop.getProperty("ApplicationUrl");
			
			/*while (enuKeys.hasMoreElements()) {
				//String key=(String) enuKeys.nextElement();
				//String value=prop.getProperty("ApplicationUrl");
				//System.out.println(value);
				
			}*/
			
			System.out.println(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
