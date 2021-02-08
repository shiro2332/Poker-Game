package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class gameController {

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

    @FXML
    private ImageView player1card5;

    @FXML
    private Label p1Score;

    @FXML
    private Label p2Score;

    @FXML
    private Label p3Score;
    
    @FXML
    private Label roundNum;
    
    @FXML
    private Label p1Win;

    @FXML
    private Label p2Win;

    @FXML
    private Label p3Win;
    
    int currentRound = 1;
    int currentCardRound = 0; //Current card in current round
    ImageView[][] imageViewID = new ImageView[3][5];
    gameSession currentSession;
    
    public void fetchWinner(gameSession currentSession, int playerNum) {
		if (currentSession.fetchPlayer()[0].fetchCurrentGameScore() > currentSession.fetchPlayer()[1].fetchCurrentGameScore() &&
				currentSession.fetchPlayer()[0].fetchCurrentGameScore() > currentSession.fetchPlayer()[2].fetchCurrentGameScore()) {
			p1Win.setText("Win");
			p2Win.setText(null);
			p3Win.setText(null);
			cmdLogger.log("Player 1 win this round.");
			currentSession.fetchPlayer()[0].winGame(currentSession.fetchPlayer()[0].fetchCurrentGameScore());
			
		} else if (currentSession.fetchPlayer()[1].fetchCurrentGameScore() > currentSession.fetchPlayer()[0].fetchCurrentGameScore() &&
				currentSession.fetchPlayer()[1].fetchCurrentGameScore() > currentSession.fetchPlayer()[2].fetchCurrentGameScore()) {
			p2Win.setText("Win");
			p1Win.setText(null);
			p3Win.setText(null);
			cmdLogger.log("Player 2 win this round.");
			currentSession.fetchPlayer()[1].winGame(currentSession.fetchPlayer()[1].fetchCurrentGameScore());
			
		} else if (currentSession.fetchPlayer()[2].fetchCurrentGameScore() > currentSession.fetchPlayer()[0].fetchCurrentGameScore() &&
				currentSession.fetchPlayer()[2].fetchCurrentGameScore() > currentSession.fetchPlayer()[1].fetchCurrentGameScore()) {
			p3Win.setText("Win");
			p1Win.setText(null);
			p2Win.setText(null);
			cmdLogger.log("Player 3 win this round.");
			currentSession.fetchPlayer()[2].winGame(currentSession.fetchPlayer()[2].fetchCurrentGameScore());
		
		} else {
			cmdLogger.log("Draw.");
		}
		
		currentSession.fetchPlayer()[0].setCurrentGameScore(0);
		currentSession.fetchPlayer()[1].setCurrentGameScore(0);
		currentSession.fetchPlayer()[2].setCurrentGameScore(0);
		cmdLogger.log("All score for current round resetted to 0.");

	}
    
    @FXML
	public void initialize() {
    	cmdLogger.log("===============================Game Screen initialized===============================");
		currentSession = gameSession.getSession();

		ImageView[] player0 = { player1card1, player1card2, player1card3, player1card4, player1card5 };
		ImageView[] player1 = { player2card1, player2card2, player2card3, player2card4, player2card5 };
		ImageView[] player2 = { player3card1, player3card2, player3card3, player3card4, player3card5 };
		imageViewID[0] = player0;
		imageViewID[1] = player1;
		imageViewID[2] = player2;
		
		roundNum.setText(Integer.toString(currentRound));

		p1Name.setText(currentSession.fetchPlayer()[0].fetchUserName());
		p2Name.setText(currentSession.fetchPlayer()[1].fetchUserName());
		p3Name.setText(currentSession.fetchPlayer()[2].fetchUserName());

		cmdLogger.log("p1Name is set to " + currentSession.fetchPlayer()[0].fetchUserName());
		cmdLogger.log("p2Name is set to " + currentSession.fetchPlayer()[1].fetchUserName());
		cmdLogger.log("p3Name is set to " + currentSession.fetchPlayer()[2].fetchUserName());
		
		cmdLogger.log("===============================Match Started===============================");
		
		gameHandler.gameEvent(currentSession, currentRound, currentSession.fetchPlayer().length);
		
		for (int playerIndex = 0; playerIndex < imageViewID.length; playerIndex++) {
			for (int cardDisplayIndex = 0; cardDisplayIndex < imageViewID[playerIndex].length; cardDisplayIndex++) {
				String cardName = currentSession.fetchPlayer()[playerIndex].fetchActualDeck().get(0).get(cardDisplayIndex + currentCardRound).fetchCard();
				imageViewID[playerIndex][cardDisplayIndex].setImage(
						new Image("file:../../../../Documents/application/" + imageMap.fetchMap().get(cardName)));
			}
		}
		
		p1Score.setText(Integer.toString(currentSession.fetchPlayer()[0].fetchCurrentGameScore()));
		p2Score.setText(Integer.toString(currentSession.fetchPlayer()[1].fetchCurrentGameScore()));
		p3Score.setText(Integer.toString(currentSession.fetchPlayer()[2].fetchCurrentGameScore()));
		
		fetchWinner(currentSession, currentSession.fetchPlayer().length);
		
		System.out.println("Player 1 score = " + currentSession.fetchPlayer()[0].fetchTotalGameScore());
		System.out.println("Player 2 score = " + currentSession.fetchPlayer()[1].fetchTotalGameScore());
		System.out.println("Player 3 score = " + currentSession.fetchPlayer()[2].fetchTotalGameScore());
		
		for (int i = 0; i < currentSession.fetchPlayer().length; i++) {
    		currentSession.fetchPlayer()[i].fetchActualDeck().remove(0);
    	}
		cmdLogger.log("Player 1 deck available deck size = " + Integer.toString(currentSession.fetchPlayer()[0].fetchActualDeck().size() * 5));
		cmdLogger.log("Player 2 deck available deck size = " + Integer.toString(currentSession.fetchPlayer()[1].fetchActualDeck().size() * 5));
		cmdLogger.log("Player 3 deck available deck size = " + Integer.toString(currentSession.fetchPlayer()[2].fetchActualDeck().size() * 5));
	}
    
    @FXML
    void onNextButtonClick(ActionEvent event) {
    	cmdLogger.logActionByUser("next button clicked", "gameController");
    	cmdLogger.log("===============================Event updated===============================");
    	if (currentRound < 3){
    		currentCardRound += 5;
        	currentRound ++;
        	
        	currentSession = gameSession.getSession();
        	
        	roundNum.setText(Integer.toString(currentRound));

        	gameHandler.gameEvent(currentSession, currentRound, currentSession.fetchPlayer().length);
        	for (int playerIndex = 0; playerIndex < imageViewID.length; playerIndex++) {
    			for (int cardDisplayIndex = 0; cardDisplayIndex < imageViewID[playerIndex].length; cardDisplayIndex++) {
    				String cardName = currentSession.fetchPlayer()[playerIndex].fetchActualDeck().get(0).get(cardDisplayIndex).fetchCard();
    				imageViewID[playerIndex][cardDisplayIndex].setImage(
    						new Image("file:../../../../Documents/application/" + imageMap.fetchMap().get(cardName)));
    			}
    		}
    		
    		p1Score.setText(Integer.toString(currentSession.fetchPlayer()[0].fetchCurrentGameScore()));
    		p2Score.setText(Integer.toString(currentSession.fetchPlayer()[1].fetchCurrentGameScore()));
    		p3Score.setText(Integer.toString(currentSession.fetchPlayer()[2].fetchCurrentGameScore()));
    		
    		fetchWinner(currentSession, currentSession.fetchPlayer().length);
    		
    		System.out.println("Player 1 score = " + currentSession.fetchPlayer()[0].fetchTotalGameScore());
    		System.out.println("Player 2 score = " + currentSession.fetchPlayer()[1].fetchTotalGameScore());
    		System.out.println("Player 3 score = " + currentSession.fetchPlayer()[2].fetchTotalGameScore());
    		
    		for (int i = 0; i < currentSession.fetchPlayer().length; i++) {
        		currentSession.fetchPlayer()[i].fetchActualDeck().remove(0);
        	}
    		cmdLogger.log("Player 1 deck available deck size = " + Integer.toString(currentSession.fetchPlayer()[0].fetchActualDeck().size() * 5));
    		cmdLogger.log("Player 2 deck available deck size = " + Integer.toString(currentSession.fetchPlayer()[1].fetchActualDeck().size() * 5));
    		cmdLogger.log("Player 3 deck available deck size = " + Integer.toString(currentSession.fetchPlayer()[2].fetchActualDeck().size() * 5));
        	
    	} else {
    		cmdLogger.log("===============================Match Ended===============================");
    		int p1 = currentSession.fetchPlayer()[0].fetchTotalGameScore();
    		int p2 = currentSession.fetchPlayer()[1].fetchTotalGameScore();
    		int p3 = currentSession.fetchPlayer()[2].fetchTotalGameScore();
    		
    		System.out.println("Player 1 score = " + currentSession.fetchPlayer()[0].fetchTotalGameScore());
    		System.out.println("Player 2 score = " + currentSession.fetchPlayer()[1].fetchTotalGameScore());
    		System.out.println("Player 3 score = " + currentSession.fetchPlayer()[2].fetchTotalGameScore());
    		
    		//Checking if game continue or end
    		
    		//Normal condition
			if (p1 < p3 && p1 < p2){
				cmdLogger.log("Player 1 eliminated");
			} else if(p2 < p3 && p2 < p3){
				cmdLogger.log("Player 2 eliminated");
			} else if(p3 < p1 && p3 < p2){
				cmdLogger.log("Player 3 eliminated");
			} 
			
			//Rare condition
			//Game end in draw where either all player receive same score or draw in 3 round
			else if(p1 == p2 && p1 == p3 && p2 ==p3){
				cmdLogger.log("Game ended with draw.");
			} 
			
			//Game end in 1 player where the other 2 player does not win any round
			else if(p2 == 0 && p3 == 0){
				cmdLogger.log("Game ended with player 1 win.");
			} else if(p1 == 0 && p3 == 0){
				cmdLogger.log("Game ended with player 2 win.");
			} else if(p1 == 0 && p2 == 0){
				cmdLogger.log("Game ended with player 3 win.");
			}
    	}
    	
    }
}
