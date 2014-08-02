package Test;

public class TestNew {

	public static void main(String[] args) {
		String[] str={"a","b","c","a","a","b"};
		String dup="";
		for (int i = 0; i < str.length; i++) {
			int temp=0;
			for (int j = 0; j < str.length; j++) {
				dup=str[i];
				if (dup.equalsIgnoreCase(str[j])) {
					temp++;
					
				}
			}
			System.out.println("Found  "+dup+"  "+temp+"  Times");
		}

	}

}
