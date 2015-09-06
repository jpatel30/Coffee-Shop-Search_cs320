package cs320.onlinetest;

import java.util.HashMap;

public class HashMapDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		HashMap<String, String> hm = new HashMap<>();
		
		hm.put("Name 1", "Palak");
		hm.put("Name 2", "Ankit");
		hm.put("Name 3", "Rushab");
		hm.put("Name 4", "Pranav");
		hm.put("Name 5", "Mariana");
		
		System.out.println(hm.get("Name 1"));
		System.out.println(hm.get("Name 2"));
		System.out.println(hm.get("Name 3"));
		System.out.println(hm.get("Name 4"));
		System.out.println(hm.get("Name 5"));
		
		if(hm.containsKey("Name 6")){
			System.out.println("Same key already exist in the map");
			
		}else{ 
			System.out.println("You can add this key in map");
	}hm.put("Name 6", "WTF");
		System.out.println(hm.get("Name 6"));

	}

}
