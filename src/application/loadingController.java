package application;

import java.io.IOException;

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
	private Label p1Name;

	@FXML
	private Label p2Name;

	@FXML
	private Label p3Name;

	@FXML
	private ImageView player1card1;

	@FXML
	private ImageView player1card2;

	@FXML
	private ImageView player1card3;

	@FXML
	private ImageView player1card4;

	@FXML
	private ImageView player1card5;

	@FXML
	private ImageView player2card1;

	@FXML
	private ImageView player2card2;

	@FXML
	private ImageView player2card3;

	@FXML
	private ImageView player2card4;

	@FXML
	private ImageView player2card5;

	@FXML
	private ImageView player3card1;

	@FXML
	private ImageView player3card2;

	@FXML
	private ImageView player3card3;

	@FXML
	private ImageView player3card4;

	@FXML
	private ImageView player3card5;

	ImageView[][] imageViewID = new ImageView[3][5];
	
	
	@FXML
	public void initialize() {	
		currentSession = gameSession.getSession();

		ImageView[] player0 = { player1card1, player1card2, player1card3, player1card4, player1card5 };
		ImageView[] player1 = { player2card1, player2card2, player2card3, player2card4, player2card5 };
		ImageView[] player2 = { player3card1, player3card2, player3card3, player3card4, player3card5 };
		imageViewID[0] = player0;
		imageViewID[1] = player1;
		imageViewID[2] = player2;

		cmdLogger.log("Game session loaded from mainMenuController");
		cmdLogger.log("Game session is loaded with " + currentSession.fetchPlayer().length + " player");

		p1Name.setText(currentSession.fetchPlayer()[0].fetchUserName());
		p2Name.setText(currentSession.fetchPlayer()[1].fetchUserName());
		p3Name.setText(currentSession.fetchPlayer()[2].fetchUserName());

		cmdLogger.log("p1Name is set to " + currentSession.fetchPlayer()[0].fetchUserName());
		cmdLogger.log("p2Name is set to " + currentSession.fetchPlayer()[1].fetchUserName());
		cmdLogger.log("p3Name is set to " + currentSession.fetchPlayer()[2].fetchUserName());

		CardHandler cardHandler = new CardHandler();
		cardHandler.dealCard(3, currentSession.fetchPlayer());
		
		currentSession.fetchPlayer()[0].updateDeck();
		currentSession.fetchPlayer()[1].updateDeck();
		currentSession.fetchPlayer()[2].updateDeck();
		
		cmdLogger.log("Deck has been assigned as below: ");
		cmdLogger.noTimeLog(
				"Player 1: " + cmdLogger.convertCardListToString(currentSession.fetchPlayer()[0].fetchDeck()));
		cmdLogger.noTimeLog("Player 1: Deck size = " + currentSession.fetchPlayer()[0].fetchDeck().size());
		cmdLogger.noTimeLog(
				"Player 2: " + cmdLogger.convertCardListToString(currentSession.fetchPlayer()[1].fetchDeck()));
		cmdLogger.noTimeLog("Player 2: Deck size = " + currentSession.fetchPlayer()[1].fetchDeck().size());
		cmdLogger.noTimeLog(
				"Player 3: " + cmdLogger.convertCardListToString(currentSession.fetchPlayer()[2].fetchDeck()));
		cmdLogger.noTimeLog("Player 3: Deck size = " + currentSession.fetchPlayer()[2].fetchDeck().size());

		int round = 0;
		for (int playerIndex = 0; playerIndex < imageViewID.length; playerIndex++) {
			for (int cardDisplayIndex = 0; cardDisplayIndex < imageViewID[playerIndex].length; cardDisplayIndex++) {
				String cardName = currentSession.fetchPlayer()[playerIndex].fetchDeck().get(cardDisplayIndex + round).fetchCard();
				imageViewID[playerIndex][cardDisplayIndex].setImage(
						new Image("file:../../../../Documents/application/" + imageMap.fetchMap().get(cardName)));
			}
		}
	}

	@FXML
	void onBackDeckShowPlayer1(ActionEvent event) {
		if (p1CardRound > 0) {
			p1CardRound -= 5;
			for (int cardDisplayIndex = 0; cardDisplayIndex < imageViewID[0].length; cardDisplayIndex++) {
				String cardName = currentSession.fetchPlayer()[0].fetchDeck().get(cardDisplayIndex + p1CardRound).fetchCard();
				imageViewID[0][cardDisplayIndex].setImage(
						new Image("file:../../../../Documents/application/" + imageMap.fetchMap().get(cardName)));
			}
		}else {
			cmdLogger.logErrorShownToUser("attempted to access deck value less than 0 ", "loadingController");
		}
	}

	@FXML
	void onBackDeckShowPlayer2(ActionEvent event) {
		if (p2CardRound > 0) {
			p2CardRound -= 5;
			for (int cardDisplayIndex = 0; cardDisplayIndex < imageViewID[0].length; cardDisplayIndex++) {
				String cardName = currentSession.fetchPlayer()[0].fetchDeck().get(cardDisplayIndex + p2CardRound).fetchCard();
				imageViewID[0][cardDisplayIndex].setImage(
						new Image("file:../../../../Documents/application/" + imageMap.fetchMap().get(cardName)));
			}
		}else {
			cmdLogger.logErrorShownToUser("attempted to access deck value less than 0 ", "loadingController");
		}
	}

	@FXML
	void onBackDeckShowPlayer3(ActionEvent event) {
		if (p3CardRound > 0) {
			p3CardRound -= 5;
			for (int cardDisplayIndex = 0; cardDisplayIndex < imageViewID[0].length; cardDisplayIndex++) {
				String cardName = currentSession.fetchPlayer()[0].fetchDeck().get(cardDisplayIndex + p3CardRound).fetchCard();
				imageViewID[0][cardDisplayIndex].setImage(
						new Image("file:../../../../Documents/application/" + imageMap.fetchMap().get(cardName)));
			}
		}else {
			cmdLogger.logErrorShownToUser("attempted to access deck value less than 0 ", "loadingController");
		}
	}

	@FXML
	void onNextDeckShowPlayer1(ActionEvent event) {
		if (p1CardRound / 5 < 3) {
			p1CardRound += 5;
			for (int cardDisplayIndex = 0; cardDisplayIndex < imageViewID[0].length; cardDisplayIndex++) {
				String cardName = currentSession.fetchPlayer()[0].fetchDeck().get(cardDisplayIndex + p1CardRound).fetchCard();
				imageViewID[0][cardDisplayIndex].setImage(
						new Image("file:../../../../Documents/application/" + imageMap.fetchMap().get(cardName)));
			}
		}else {
			cmdLogger.logErrorShownToUser("attempted to access deck value more than deck size ", "loadingController");
		}
		
	}

	@FXML
	void onNextDeckShowPlayer2(ActionEvent event) {
		if (p2CardRound / 5 < 3) {
			p2CardRound += 5;
			for (int cardDisplayIndex = 0; cardDisplayIndex < imageViewID[0].length; cardDisplayIndex++) {
				String cardName = currentSession.fetchPlayer()[1].fetchDeck().get(cardDisplayIndex + p2CardRound).fetchCard();
				imageViewID[1][cardDisplayIndex].setImage(
						new Image("file:../../../../Documents/application/" + imageMap.fetchMap().get(cardName)));
			}
		}else {
			cmdLogger.logErrorShownToUser("attempted to access deck value more than deck size ", "loadingController");
		}
	}

	@FXML
	void onNextDeckShowPlayer3(ActionEvent event) {
		if (p3CardRound / 5 < 3) {
			p3CardRound += 5;
			for (int cardDisplayIndex = 0; cardDisplayIndex < imageViewID[0].length; cardDisplayIndex++) {
				String cardName = currentSession.fetchPlayer()[2].fetchDeck().get(cardDisplayIndex + p3CardRound).fetchCard();
				imageViewID[2][cardDisplayIndex].setImage(
						new Image("file:../../../../Documents/application/" + imageMap.fetchMap().get(cardName)));
			}
		}else {
			cmdLogger.logErrorShownToUser("attempted to access deck value more than deck size ", "loadingController");
		}
	}

	@FXML
	void onStartButtonClick(ActionEvent event) {
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

		ImageView[] player0 = { player1card1, player1card2, player1card3, player1card4, player1card5 };
		ImageView[] player1 = { player2card1, player2card2, player2card3, player2card4, player2card5 };
		ImageView[] player2 = { player3card1, player3card2, player3card3, player3card4, player3card5 };
		imageViewID[0] = player0;
		imageViewID[1] = player1;
		imageViewID[2] = player2;
		
		p1CardRound = 0;
		p2CardRound = 0;
		p3CardRound = 0;

		CardHandler cardHandler = new CardHandler();
		cardHandler.dealCard(3, currentSession.fetchPlayer());
		cmdLogger.log("Deck has been assigned as below: ");
		cmdLogger.noTimeLog(
				"Player 1: " + cmdLogger.convertCardListToString(currentSession.fetchPlayer()[0].fetchDeck()));
		cmdLogger.noTimeLog("Player 1: Deck size = " + currentSession.fetchPlayer()[0].fetchDeck().size());
		cmdLogger.noTimeLog(
				"Player 2: " + cmdLogger.convertCardListToString(currentSession.fetchPlayer()[1].fetchDeck()));
		cmdLogger.noTimeLog("Player 2: Deck size = " + currentSession.fetchPlayer()[1].fetchDeck().size());
		cmdLogger.noTimeLog(
				"Player 3: " + cmdLogger.convertCardListToString(currentSession.fetchPlayer()[2].fetchDeck()));
		cmdLogger.noTimeLog("Player 3: Deck size = " + currentSession.fetchPlayer()[2].fetchDeck().size());

		int round = 0;
		for (int playerIndex = 0; playerIndex < imageViewID.length; playerIndex++) {
			for (int cardDisplayIndex = 0; cardDisplayIndex < imageViewID[playerIndex].length; cardDisplayIndex++) {
				String cardName = currentSession.fetchPlayer()[playerIndex].fetchDeck().get(cardDisplayIndex + round).fetchCard();
				imageViewID[playerIndex][cardDisplayIndex].setImage(
						new Image("file:../../../../Documents/application/" + imageMap.fetchMap().get(cardName)));
			}
		}
	}
}
