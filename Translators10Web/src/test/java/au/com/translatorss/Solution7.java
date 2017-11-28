package au.com.translatorss;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Solution7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "abacabad";
		System.out.println(firstNotRepeatingCharacter(s));
	}

	
	static char firstNotRepeatingCharacter(String s) {
		Map<Character, Integer> repeatedChar = new HashMap<>();
		int position=s.length();
		for(int i = 0; i<s.length();i++) {
			if(!repeatedChar.containsKey(s.charAt(i)) && position<i) {
				repeatedChar.put(s.charAt(i), 0);
			}else {
				Integer cant=repeatedChar.get(s.charAt(i));
				cant = cant +1;
				repeatedChar.replace(s.charAt(i), cant);
			}
		}
		return 0;

	}
}
