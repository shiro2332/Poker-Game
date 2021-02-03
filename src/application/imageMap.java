package application;

import java.util.HashMap;

public class imageMap {
	private static HashMap<String, String> cardToUrl;
	
	public static void loadURL() {
		cardToUrl = new HashMap<String, String>();
		cardToUrl.put("DiamondsA", "DiamondsA.png");
		cardToUrl.put("Diamonds2", "Diamonds2.png");
		cardToUrl.put("Diamonds3", "Diamonds3.png");
		cardToUrl.put("Diamonds4", "Diamonds4.png");
		cardToUrl.put("Diamonds5", "Diamonds5.png");
		cardToUrl.put("Diamonds6", "Diamonds6.png");
		cardToUrl.put("Diamonds7", "Diamonds7.png");
		cardToUrl.put("Diamonds8", "Diamonds8.png");
		cardToUrl.put("Diamonds9", "Diamonds9.png");
		cardToUrl.put("DiamondsX", "DiamondsX.png");
		cardToUrl.put("DiamondsJ", "DiamondsJ.png");
		cardToUrl.put("DiamondsQ", "DiamondsQ.png");
		cardToUrl.put("DiamondsK", "DiamondsK.png");
		
		cardToUrl.put("ClubsA", "ClubsA.png");
		cardToUrl.put("Clubs2", "Clubs2.png");
		cardToUrl.put("Clubs3", "Clubs3.png");
		cardToUrl.put("Clubs4", "Clubs4.png");
		cardToUrl.put("Clubs5", "Clubs5.png");
		cardToUrl.put("Clubs6", "Clubs6.png");
		cardToUrl.put("Clubs7", "Clubs7.png");
		cardToUrl.put("Clubs8", "Clubs8.png");
		cardToUrl.put("Clubs9", "Clubs9.png");
		cardToUrl.put("ClubsX", "ClubsX.png");
		cardToUrl.put("ClubsJ", "ClubsJ.png");
		cardToUrl.put("ClubsQ", "ClubsQ.png");
		cardToUrl.put("ClubsK", "ClubsK.png");
		
		cardToUrl.put("HeartsA", "HeartsA.png");
		cardToUrl.put("Hearts2", "Hearts2.png");
		cardToUrl.put("Hearts3", "Hearts3.png");
		cardToUrl.put("Hearts4", "Hearts4.png");
		cardToUrl.put("Hearts5", "Hearts5.png");
		cardToUrl.put("Hearts6", "Hearts6.png");
		cardToUrl.put("Hearts7", "Hearts7.png");
		cardToUrl.put("Hearts8", "Hearts8.png");
		cardToUrl.put("Hearts9", "Hearts9.png");
		cardToUrl.put("HeartsX", "HeartsX.png");
		cardToUrl.put("HeartsJ", "HeartsJ.png");
		cardToUrl.put("HeartsQ", "HeartsQ.png");
		cardToUrl.put("HeartsK", "HeartsK.png");
		
		cardToUrl.put("SpadesA", "SpadesA.png");
		cardToUrl.put("Spades2", "Spades2.png");
		cardToUrl.put("Spades3", "Spades3.png");
		cardToUrl.put("Spades4", "Spades4.png");
		cardToUrl.put("Spades5", "Spades5.png");
		cardToUrl.put("Spades6", "Spades6.png");
		cardToUrl.put("Spades7", "Spades7.png");
		cardToUrl.put("Spades8", "Spades8.png");
		cardToUrl.put("Spades9", "Spades9.png");
		cardToUrl.put("SpadesX", "SpadesX.png");
		cardToUrl.put("SpadesJ", "SpadesJ.png");
		cardToUrl.put("SpadesQ", "SpadesQ.png");
		cardToUrl.put("SpadesK", "SpadesK.png");
		
		cardToUrl.put("BlankBlank", "back1.png");
	}
	
	public static HashMap<String, String> fetchMap(){
		loadURL();
		return cardToUrl;
	}
}
