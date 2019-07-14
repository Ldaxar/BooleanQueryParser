package stargarth.core;

import java.util.ArrayList;
import java.util.List;

public class PlayField {

	public static void main(String[] args) {
		String s = "1234" ;
		String s2 = "123.45" ;
		String s3 = "false" ; 
		String s4 = "elo";
		List<String> arr = new ArrayList<>();
		arr.add(s);
		arr.add(s2);
		arr.add(s3);
		arr.add(s4);
		Integer i = 1234;
		Object o = i;
	
		System.out.println(i instanceof Number);
	}

}
