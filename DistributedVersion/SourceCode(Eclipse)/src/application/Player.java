//Optimized

package application;

import java.util.ArrayList;

public class Player {
	private String userName;
	public int currentGameScore = 0;
	private int totalGameScore = 0;
	private ArrayList<Card> deck = new ArrayList<Card>(); //For CMD uses
	private ArrayList<ArrayList<Card>> actualDeck = new ArrayList<ArrayList<Card>>();
	
	public Player(String userName) {
		this.userName = userName;
	}

	public String fetchUserName() {
		return userName;
	}
	
	public void winGame(int currentGameScore) {
		totalGameScore += currentGameScore;
	}
	
	public int fetchTotalGameScore() {
		return totalGameScore;
	}
	
	public void setGameWinCount(int score) {
		this.currentGameScore = score;
	}
	
	public ArrayList<Card> fetchDeck() {
		return deck;
	}
	
	public ArrayList<ArrayList<Card>> fetchActualDeck() {
		return actualDeck;
	}
	
	public void updateDeck() {
		ArrayList<Card> temp = new ArrayList<Card>();
		int counter = 0;
		for(int i = 0; i < deck.size(); i++) {
			if (counter == 4) {
				temp.add(deck.get(i));
				actualDeck.add(temp);
				temp = new ArrayList<Card>();
				counter = 0;
			}
			else {
				temp.add(deck.get(i));
				counter++;
			}
		}
	}
}