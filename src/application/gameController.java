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
    
    int currentRound = 1;
    int currentCardRound = 0; //Current card in current round
    ImageView[][] imageViewID = new ImageView[3][5];
    gameSession currentSession;
    
    @FXML
	public void initialize() {
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

		for (int playerIndex = 0; playerIndex < imageViewID.length; playerIndex++) {
			for (int cardDisplayIndex = 0; cardDisplayIndex < imageViewID[playerIndex].length; cardDisplayIndex++) {
				String cardName = currentSession.fetchPlayer()[playerIndex].fetchDeck().get(cardDisplayIndex + currentCardRound).fetchCard();
				imageViewID[playerIndex][cardDisplayIndex].setImage(
						new Image("file:../../../../Documents/application/" + imageMap.fetchMap().get(cardName)));
			}
		}
		
		gameHandler.gameEvent(currentSession, currentRound, currentSession.fetchPlayer().length);
	}
    
    @FXML
    void onNextButtonClick(ActionEvent event) {
    	if (currentRound < 4){
    		currentCardRound += 5;
        	currentRound ++;
        	
        	currentSession = gameSession.getSession();
        	
        	roundNum.setText(Integer.toString(currentRound));
    		for (int playerIndex = 0; playerIndex < imageViewID.length; playerIndex++) {
    			for (int cardDisplayIndex = 0; cardDisplayIndex < imageViewID[playerIndex].length; cardDisplayIndex++) {
    				String cardName = currentSession.fetchPlayer()[playerIndex].fetchDeck().get(cardDisplayIndex + currentCardRound).fetchCard();
    				imageViewID[playerIndex][cardDisplayIndex].setImage(
    						new Image("file:../../../../Documents/application/" + imageMap.fetchMap().get(cardName)));
    			}
    		}
    	} else {
    		cmdLogger.logErrorShownToUser("attempted to access deck value more than deck size", "gameController");
    	}
    	
    }

}
