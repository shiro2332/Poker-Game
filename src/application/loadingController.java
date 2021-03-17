//Optimized
package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class loadingController {
	gameSession currentSession;
	int p1CardRound = 0;
	int p2CardRound = 0;
	int p3CardRound = 0;

	@FXML
	private Label p1Name, p2Name, p3Name;

	@FXML
	private ImageView player1card1, player1card2, player1card3, player1card4, player1card5;

	@FXML
	private ImageView player2card1, player2card2, player2card3, player2card4, player2card5;

	@FXML
	private ImageView player3card1, player3card2, player3card3, player3card4, player3card5;
	
	@FXML
    private JFXButton p3NextButton, p3BackButton;
	
	ImageView[][] CardImageSlots = new ImageView[3][5];
	
	private int playerNum;
	private Label[] playerNames = new Label[3];
	private int[] playerCardRounds = new int[3];
	
	void loadFunction() {
		ImageView[] player0 = { player1card1, player1card2, player1card3, player1card4, player1card5 };
		ImageView[] player1 = { player2card1, player2card2, player2card3, player2card4, player2card5 };
		ImageView[] player2 = { player3card1, player3card2, player3card3, player3card4, player3card5 };
		
		CardImageSlots[0] = player0;
		CardImageSlots[1] = player1;
		CardImageSlots[2] = player2;
		
		playerNames[0] = p1Name;
		playerNames[1] = p2Name;
		playerNames[2] = p3Name;
		
		playerCardRounds[0] = 0; 
		playerCardRounds[1] = 0;
		playerCardRounds[2] = 0;
		
		cmdLogger.log("Game session loaded from mainMenuController");
		cmdLogger.log("Game session is loaded with " + currentSession.fetchPlayer().length + " player");
		
		for (int i = 0; i < playerNum; i++) {
			playerNames[i].setText(currentSession.fetchPlayer()[i].fetchUserName());
			cmdLogger.log("p" + (i + 1) + "Name is set to " + currentSession.fetchPlayer()[i].fetchUserName());
		}
		
		if (playerNum < 3) {
			p3NextButton.setVisible(false);
			p3BackButton.setVisible(false);
			playerNames[2].setVisible(false);
		}

		CardHandler cardHandler = new CardHandler();
		cardHandler.distributeDeckToPlayers(playerNum, currentSession.fetchPlayer());
		
		for (int i = 0; i < playerNum; i++) {
			currentSession.fetchPlayer()[i].updateDeck();
		}
		
		cmdLogger.log("Deck has been assigned as below: ");
		for(int i = 0; i < playerNum; i++) {
			cmdLogger.noTimeLog("Player " + (i + 1) + ": " + cmdLogger.cardListToString(currentSession.fetchPlayer()[i].fetchDeck()));
			cmdLogger.noTimeLog("Player " + (i + 1) + ": Deck size = " + currentSession.fetchPlayer()[i].fetchDeck().size());
		}
		

		// Load images into card
		for (int i = 0; i < playerNum; i++) {
			displayCards(i);
		}
	}
	
	@FXML
	public void initialize() {
		cmdLogger.log("===============================Loading Screen initialized===============================");
		currentSession = gameSession.getSession();
		playerNum = currentSession.fetchPlayer().length;
		
		loadFunction();
			
	}

	void displayCards(int playerIndex) {
		try {
			for (int cardDisplayIndex = 0; cardDisplayIndex < CardImageSlots[playerIndex].length; cardDisplayIndex++) {
				String cardName = currentSession.fetchPlayer()[playerIndex].fetchDeck().get(cardDisplayIndex + playerCardRounds[playerIndex]).fetchCard();
				CardImageSlots[playerIndex][cardDisplayIndex].setImage(new Image("file:Images/" + imageMap.fetchMap().get(cardName)));
			}
		} catch (IndexOutOfBoundsException e) {
			cmdLogger.logErrorShownToUser("attempted to access deck value more than deck size", "loadingController");
		}
	}
	
	@FXML
	void onBackDeckShowPlayer1(ActionEvent event) {
		cmdLogger.logActionByUser("player 1 back button clicked", "loadingController");
		cmdLogger.log("===============================Event updated===============================");
		if (playerCardRounds[0] > 0) {
			playerCardRounds[0] -= 5;
			displayCards(0);
		}else {
			cmdLogger.logErrorShownToUser("attempted to access deck value more than deck size", "loadingController");
		}
		
	}

	@FXML
	void onBackDeckShowPlayer2(ActionEvent event) {
		cmdLogger.logActionByUser("player 2 back button clicked", "loadingController");
		cmdLogger.log("===============================Event updated===============================");
		if (playerCardRounds[1] > 0) {
			playerCardRounds[1] -= 5;
			displayCards(1);
		}else {
			cmdLogger.logErrorShownToUser("attempted to access deck value more than deck size", "loadingController");
		}
	}

	@FXML
	void onBackDeckShowPlayer3(ActionEvent event) {
		cmdLogger.logActionByUser("player 3 back button clicked", "loadingController");
		cmdLogger.log("===============================Event updated===============================");
		if (playerCardRounds[2] > 0) {
			playerCardRounds[2] -= 5;
			displayCards(2);
		}else {
			cmdLogger.logErrorShownToUser("attempted to access deck value more than deck size", "loadingController");
		}
	}

	@FXML
	void onNextDeckShowPlayer1(ActionEvent event) {
		cmdLogger.logActionByUser("player 1 next button clicked", "loadingController");
		cmdLogger.log("===============================Event updated===============================");
		int rounds = (playerNum == 3) ? 4 : 6;
		if (playerCardRounds[0] >= 0 && playerCardRounds[0] < rounds * 5) {
			playerCardRounds[0] += 5;
			displayCards(0);
		}else {
			cmdLogger.logErrorShownToUser("attempted to access deck value more than deck size", "loadingController");
		}
	}

	@FXML
	void onNextDeckShowPlayer2(ActionEvent event) {
		cmdLogger.logActionByUser("player 2 next button clicked", "loadingController");
		cmdLogger.log("===============================Event updated===============================");
		int rounds = (playerNum == 3) ? 4 : 6;
		if (playerCardRounds[1] >= 0 && playerCardRounds[1] < rounds * 5) {
			playerCardRounds[1] += 5;
			displayCards(1);
		}else {
			cmdLogger.logErrorShownToUser("attempted to access deck value more than deck size", "loadingController");
		}
	}

	@FXML
	void onNextDeckShowPlayer3(ActionEvent event) {
		cmdLogger.logActionByUser("player 3 next button clicked", "loadingController");
		cmdLogger.log("===============================Event updated===============================");
		int rounds = (playerNum == 3) ? 4 : 6;
		if (playerCardRounds[2] >= 0 && playerCardRounds[2] < rounds * 5) {
			playerCardRounds[2] += 5;
			displayCards(2);
		}else {
			cmdLogger.logErrorShownToUser("attempted to access deck value more than deck size", "loadingController");
		}
	}

	@FXML
	void onStartButtonClick(ActionEvent event) {
		cmdLogger.logActionByUser("start button clicked", "loadingController");
		cmdLogger.log("===============================Event updated===============================");
		SceneHandler sceneHandler = new SceneHandler("gameScreen.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
		try {
			sceneHandler.showWindow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onShuffleButtonClick(ActionEvent event) {
		cmdLogger.logActionByUser("shuffle button clicked", "loadingController");
		cmdLogger.log("===============================Event updated===============================");
		
		loadFunction();
	}			
}

