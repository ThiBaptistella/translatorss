package au.com.translatorss;

import java.util.ArrayList;
import java.util.List;

public class Solution4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		newNumeralSystem('G');
	}

	private static String[] newNumeralSystem(char number) {

	    List<String> result = new ArrayList<String>();
	    if(48<=number&&number<=57) {
	        int n = Integer.parseInt(""+number);
	        for(int i=0;i<=n/2;i++) {
	            result.add(i+" + "+(n-i));
	        }
	        return (String[])result.toArray();
	        
	    }else if(65<=number&&number<=91){
	        int n = Integer.parseInt(""+(number-65));
	        for(int i=0;i<=n/2;i++) {
	            result.add((char)(65+i)+" + "+(char)(65+n-i));
	        }
	        return (String[])result.toArray();
	    }
	    else
	        return null;
	}
	
}
