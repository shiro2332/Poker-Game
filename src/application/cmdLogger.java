package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Stack;

public class cmdLogger {
	public static void log(String operationPerformed) {
		DateTimeFormatter formatDT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime timeNow = LocalDateTime.now();
		System.out.println(timeNow.format(formatDT) + " | " + operationPerformed);
	}
	
	public static void noTimeLog(String operationPerformed) {
		System.out.println("                    | " + operationPerformed);
	}
	
	public static void logError(Exception e) {
		DateTimeFormatter formatDT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime timeNow = LocalDateTime.now();
		System.out.println(timeNow.format(formatDT) + " | Error caused by " + e);
	}
	
	public static void logActionByUser(String userAction, String currentController) {
		DateTimeFormatter formatDT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime timeNow = LocalDateTime.now();
		System.out.println(timeNow.format(formatDT) + " | User has performed " + userAction + " on " + currentController);
	}
	
	public static void logErrorShownToUser(String userError, String currentPage) {
		DateTimeFormatter formatDT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime timeNow = LocalDateTime.now();
		System.out.println(timeNow.format(formatDT) + " | User has performed " + userError + " on " + currentPage);
	}
	
	public static String convertCardListToString(ArrayList<Card> playerDeck) {
		String cardListInString = "";
		int x = 0;
		for (int i = 0; i < playerDeck.size(); i++) {
			if(x == 4) {
				cardListInString += playerDeck.get(i).fetchCard() + ", ";
				x = 0;
			} else {
				cardListInString += playerDeck.get(i).fetchCard() + " ";
				x++;
			}
			
		}
		return cardListInString;
	}
	
	public static String convertDeckToString(Stack<Card> deck) {
		String deckInStrng = "\n";
		int x = 0;
		for (int i = 0; i < deck.size(); i++) {
			if(x == 12) {
				deckInStrng += deck.get(i).fetchCard() + "\n ";
				x = 0;
			} else {
				deckInStrng += deck.get(i).fetchCard() + " ";
				x++;
			}
			
		}
		return deckInStrng;
	}
}
