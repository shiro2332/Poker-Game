//Optimized
package application;

import java.util.HashMap;

public class imageMap {
	private static HashMap<String, String> cardToUrl;
	
	public static void loadURL() {
		cardToUrl = new HashMap<String, String>();
		
		String[] cardTypes = { "Diamonds", "Clubs", "Hearts", "Spades" };
		String[] cardValues = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "X", "J", "Q", "K" };
		
		for(String cardType : cardTypes) {
			for(String cardValue : cardValues) {
				cardToUrl.put(cardType + cardValue, cardType + cardValue + ".png");
			}
		}
		
		cardToUrl.put("BlankBlank", "back1.png");
	}
	
	public static HashMap<String, String> fetchMap(){
		loadURL();
		return cardToUrl;
	}
}
