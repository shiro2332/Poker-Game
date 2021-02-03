package application;

import java.util.Collections;

public class gameHandler {
	public static void gameEvent(gameSession currentSession, int currentRound, int playerNum) {
		for (int i = 0; i < playerNum; i++) {
			Collections.sort(currentSession.fetchPlayer()[i].fetchActualDeck().get(currentRound - 1));
		}
		
		for (int i = 0; i < currentSession.fetchPlayer()[0].fetchActualDeck().get(0).size(); i++) {
			System.out.print(" " + currentSession.fetchPlayer()[0].fetchActualDeck().get(0).get(i).fetchCard());
		}
		
	}
}
