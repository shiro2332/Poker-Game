package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class CardHandler {
	private Stack<Card> deck = new Stack<Card>(); //Stack used here
	ArrayList<ArrayList<Card>> playerHands = new ArrayList<ArrayList<Card>>();

	String[] cardType = { "Diamonds", "Clubs", "Hearts", "Spades" };
	String[] cardValue = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "X", "J", "Q", "K" };
	
	private void CreateDeck() {
		for (int i = 0; i < cardType.length; i++) {
			for (int j = 0; j < cardValue.length; j++) {
				deck.push(new Card(cardType[i], cardValue[j]));
			}
		}
		
		cmdLogger.log("Deck contains " + deck.size() + " cards");
		cmdLogger.noTimeLog("Deck contains card : " + cmdLogger.convertDeckToString(deck));
		
		Collections.shuffle(deck); //Shuffle deck
	}
	
	public void dealCard(int playerCount, Player[] playerList) {
		CreateDeck();
		
		for (int i = 0; i < playerCount; i++) {
			playerList[i].fetchDeck().clear();
			playerList[i].fetchActualDeck().clear();
		}

		while (deck.size() >= playerCount) {
			for (int i = 0; i < playerCount; i++) {
				playerList[i].fetchDeck().add(deck.pop());
			}
		}
		
		for (int i = 0; i < playerCount; i++) {
			if(playerList[i].fetchDeck().size() % 5 != 0) {
				for(int j = 0; j < playerList[i].fetchDeck().size() % 5; j++) {
					playerList[i].fetchDeck().add(new Card("Blank", "Blank"));
				}
			}
		}
		
		cmdLogger.log("Deck remains " + deck.size() + " cards");
		cmdLogger.noTimeLog("Deck remains card : " + cmdLogger.convertDeckToString(deck));
	}
}