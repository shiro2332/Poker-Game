package application;

import java.util.ArrayList;

public class Player {
	private String userName;
	private int currentGameScore = 0;
	private int gameWinCount = 0;
	private ArrayList<Card> deck = new ArrayList<Card>(); //For CMD uses
	private ArrayList<ArrayList<Card>> actualDeck = new ArrayList<ArrayList<Card>>();
	
	public Player(String userName) {
		this.userName = userName;
	}

	public String fetchUserName() {
		return userName;
	}
	
	public int fetchCurrentGameScore() {
		return currentGameScore;
	}

	public void setCurrentGameScore(int score) {
		this.currentGameScore = score;
	}
	
	public int fetchGameWinCount() {
		return gameWinCount;
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
		for(Card card : deck) {
			if (counter == 4) {
				temp.add(card);
				actualDeck.add(temp);
				temp = new ArrayList<Card>();
				counter = 0;
			}
			else {
				temp.add(card);
				counter++;
			}
		}
	}
}