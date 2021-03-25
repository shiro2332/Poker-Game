//Optimized
package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class gameController {

	@FXML
	private Label p1Name, p2Name, p3Name;
	@FXML
	private Label p1Score, p2Score, p3Score;
	@FXML
	private Label p1Win, p2Win, p3Win;

	@FXML
	private ImageView player1card1, player1card2, player1card3, player1card4, player1card5;
	@FXML
	private ImageView player2card1, player2card2, player2card3, player2card4, player2card5;
	@FXML
	private ImageView player3card1, player3card2, player3card3, player3card4, player3card5;

	@FXML
	private Label roundNum;

	@FXML
	private Label p3TextLabel;
	
	@FXML
    private Label p1TotalScore, p2TotalScore, p3TotalScore;

	int currentRound = 1;
	ImageView[][] CardImageSlots = new ImageView[3][5];
	gameSession currentSession;

	private Label[] winLabels = new Label[3];
	private Label[] scoreLabels = new Label[3];
	private Label[] playerNames = new Label[3];
	private Label[] playerTotalScore = new Label[3];

	public void fetchWinner(gameSession currentSession, int playerNum) {
		winLabels[0] = p1Win;
		winLabels[1] = p2Win;
		winLabels[2] = p3Win;

		int arrayScore[] = new int[playerNum];
		arrayScore[0] = currentSession.fetchPlayer()[0].currentGameScore;
		arrayScore[1] = currentSession.fetchPlayer()[1].currentGameScore;

		if (playerNum == 3) {
			arrayScore[2] = currentSession.fetchPlayer()[2].currentGameScore;
		}

		int winScore = arrayScore[0];

		for (int score : arrayScore) {
			if (score > winScore) {
				winScore = score;
			}
		}

		for (int i = 0; i < playerNum; i++) {
			winLabels[i].setText(arrayScore[i] == winScore ? "Win" : null);
			currentSession.fetchPlayer()[i].winGame(arrayScore[i] == winScore ? currentSession.fetchPlayer()[i].currentGameScore : 0);
			currentSession.fetchPlayer()[i].currentGameScore = 0;
		}

		cmdLogger.log("All score for current round resetted to 0.");

	}

	@FXML
	public void initialize() {
		cmdLogger.log("===============================Game Screen initialized===============================");
		currentSession = gameSession.getSession();
		ImageView[] player0 = { player1card1, player1card2, player1card3, player1card4, player1card5 };
		ImageView[] player1 = { player2card1, player2card2, player2card3, player2card4, player2card5 };
		ImageView[] player2 = { player3card1, player3card2, player3card3, player3card4, player3card5 };

		playerNames[0] = p1Name;
		playerNames[1] = p2Name;
		playerNames[2] = p3Name;

		scoreLabels[0] = p1Score;
		scoreLabels[1] = p2Score;
		scoreLabels[2] = p3Score;

		CardImageSlots[0] = player0;
		CardImageSlots[1] = player1;
		CardImageSlots[2] = player2;
		
		playerTotalScore[0] = p1TotalScore;
		playerTotalScore[1] = p2TotalScore;
		playerTotalScore[2] = p3TotalScore;

		UpdateDisplay(currentRound);

	}

	void UpdateDisplay(int currentRound) {
		int playerCount = currentSession.fetchPlayer().length;
		roundNum.setText(Integer.toString(currentRound));

		for (int i = 0; i < playerCount; i++) {
			playerNames[i].setText(currentSession.fetchPlayer()[i].fetchUserName());
			cmdLogger.log("p" + (i + 1) + "Name is set to " + currentSession.fetchPlayer()[i].fetchUserName());
		}
		if (playerCount < 3) {
			p3Name.setVisible(false);
			p3TextLabel.setVisible(false);
			p3Score.setVisible(false);
			p3TotalScore.setVisible(false);
		}

		cmdLogger.log("===============================Match Started===============================");

		gameHandler.gameEvent(currentSession, currentRound, currentSession.fetchPlayer().length);

		// Load images into card
		for (int playerIndex = 0; playerIndex < playerCount; playerIndex++) {
			for (int cardDisplayIndex = 0; cardDisplayIndex < CardImageSlots[playerIndex].length; cardDisplayIndex++) {
				String cardName = currentSession.fetchPlayer()[playerIndex].fetchActualDeck().get(0).get(cardDisplayIndex).fetchCard();
				CardImageSlots[playerIndex][cardDisplayIndex].setImage(new Image("file:Images/" + imageMap.fetchMap().get(cardName)));
			}
		}

		for (int i = 0; i < playerCount; i++)
			scoreLabels[i].setText(Integer.toString(currentSession.fetchPlayer()[i].currentGameScore));

		fetchWinner(currentSession, currentSession.fetchPlayer().length);
		
		for (int i = 0; i < playerCount; i++) {
			playerTotalScore[i].setText(Integer.toString(currentSession.fetchPlayer()[i].fetchTotalGameScore()));
			System.out.println("Player " + (i + 1) + " score = " + currentSession.fetchPlayer()[i].fetchTotalGameScore());
		}
			

		for (int i = 0; i < playerCount; i++) {
			currentSession.fetchPlayer()[i].fetchActualDeck().remove(0); //Remove played cards
			cmdLogger.log("Player " + (i + 1) + " deck available deck size = " + Integer.toString(currentSession.fetchPlayer()[i].fetchActualDeck().size() * 5));
		}
	}

	@FXML
	void onNextButtonClick(ActionEvent event) {
		cmdLogger.logActionByUser("next button clicked", "gameController");
		cmdLogger.log("===============================Event updated===============================");
		int playerCount = currentSession.fetchPlayer().length;
		int RoundLimit = playerCount < 3 ? 4 : 3;

		if (currentRound < RoundLimit) {
			currentRound++;
			currentSession = gameSession.getSession();
			UpdateDisplay(currentRound);

		} else {
			SceneHandler sceneHandler = new SceneHandler("winnerScreen.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
			try {
				cmdLogger.log("New scene winnerScreen created");
				sceneHandler.showWindow();
				cmdLogger.log("Scene showed");
			} catch (IOException e) {
				cmdLogger.logError(e);
				e.printStackTrace();
			}
		}
	}
}
