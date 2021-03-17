package application;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class gameHandler {
	public static void gameEvent(gameSession currentSession, int currentRound, int playerNum) {
		sortDeck(currentSession, currentRound, playerNum);
		setScore(currentSession, currentRound, playerNum);
	}
	
	public static void sortDeck(gameSession currentSession, int currentRound, int playerNum) {
		List<String> orderSuit= Arrays.asList("Diamonds", "Clubs", "Hearts", "Spades");
		List<String> orderFace= Arrays.asList( "A", "2", "3", "4", "5", "6", "7", "8", "9", "X", "J", "Q", "K");
		
		Comparator<Card> bySuit = (c1, c2) -> {
		    return Integer.compare(orderSuit.indexOf(c1.fetchSuit()), orderSuit.indexOf(c2.fetchSuit()));
		};
		
		Comparator<Card> byFace = (c1, c2) -> {
		    return Integer.compare(orderFace.indexOf(c1.fetchFace()), orderFace.indexOf(c2.fetchFace()));
		};
	
		for (int i = 0; i < playerNum; i++) {
			currentSession.fetchPlayer()[i].fetchActualDeck().get(0).sort(bySuit.thenComparing(byFace));
		}
		
		cmdLogger.log("All player deck for round " + currentRound + " has been sorted by their suit and face.");
		cmdLogger.noTimeLog("Suit is sorted according to this order : Diamonds, Clubs, Hearts, Spades");
		cmdLogger.noTimeLog("Face is sorted according to this order : A, 2, 3, 4, 5, 6, 7, 8, 9, X, J, Q, K");
	}
	
	public static int max(int a, int b, int c, int d) {
		if(a >= b  && a >= c && a >= d) {
			return a;
		} else if (b >= a && b >= c && b >= d) {
			return b;
		} else if (c >= a && c >= b && c >= d ) {
			return c;
		} else {
			return d;
		}
	}
	
	public static void setScore(gameSession currentSession, int currentRound, int playerNum) {
		int spadeScore = 0;
		int heartScore = 0;
		int clubScore = 0;
		int diamondScore = 0;
		
		for (int i = 0; i < playerNum; i++) {
			cmdLogger.log(currentSession.fetchPlayer()[i].fetchUserName() + " score");
			for (int j = 0; j < currentSession.fetchPlayer()[i].fetchActualDeck().get(0).size(); j ++) {
				String suit = currentSession.fetchPlayer()[i].fetchActualDeck().get(0).get(j).fetchSuit();
				
				switch(suit) {
				case "Diamonds" : diamondScore += currentSession.fetchPlayer()[i].fetchActualDeck().get(0).get(j).fetchPoint(); break;
				case "Clubs" : clubScore += currentSession.fetchPlayer()[i].fetchActualDeck().get(0).get(j).fetchPoint(); break;
				case "Hearts" : heartScore += currentSession.fetchPlayer()[i].fetchActualDeck().get(0).get(j).fetchPoint(); break;
				case "Spades" : spadeScore += currentSession.fetchPlayer()[i].fetchActualDeck().get(0).get(j).fetchPoint(); break;
				}
				
				cmdLogger.log("Diamond = " + diamondScore + " Club = " + clubScore + " Heart = " + heartScore + " Spade =  " + spadeScore);
			}
			
			currentSession.fetchPlayer()[i].currentGameScore = max(spadeScore, heartScore, clubScore, diamondScore);
			cmdLogger.log("Current player highest score = " + currentSession.fetchPlayer()[i].currentGameScore);
			spadeScore = 0;
			heartScore = 0;
			clubScore = 0;
			diamondScore = 0;
		}
	}	
	
}


