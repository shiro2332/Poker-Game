//Optimized
package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class winnerController {

    @FXML
    private Label Middle;

    @FXML
    private Label Left;

    @FXML
    private Label Right;
    
    gameSession currentSession;
    boolean end = false;
    int[] totalScoreArray = new int[3];
    int playerNum;
    
    @FXML
    public void initialize() {
        currentSession = gameSession.getSession();
        playerNum = currentSession.fetchPlayer().length;
    	
    	cmdLogger.log("===============================Match Ended===============================");
    	
    	if(playerNum ==3 ) {
    		totalScoreArray[0] = currentSession.fetchPlayer()[0].fetchTotalGameScore();
    		totalScoreArray[1] = currentSession.fetchPlayer()[1].fetchTotalGameScore();
    		totalScoreArray[2] = currentSession.fetchPlayer()[2].fetchTotalGameScore();
    		
    		for(int i = 0; i < playerNum; i++) {
    			System.out.println("Player " + (i + 1) +  " score = " + totalScoreArray[i]);
    		}
    		
    		boolean sameScoreFlag = false;
    		int winner1Index;
    		int winner2Index;
    		int sameScore1Index = 0;
    		int sameScore2Index = 0;
    		int differentScoreIndex = 0;
    		
    		//Check if there is same score
    		for (int i = 1; i < playerNum; i++) {
    			if(totalScoreArray[i] == totalScoreArray[i-1]) {
    				sameScore1Index = i - 1;
    				sameScore2Index = i;
    				sameScoreFlag = true;
    				differentScoreIndex = playerNum - sameScore1Index - sameScore2Index; //Diff score = index 2, 3 - 0 - 1 = 2
    			}
    		}
    		
    		//Check if same score is larger or smaller
    		if(sameScoreFlag) {
    			if(totalScoreArray[sameScore1Index] > totalScoreArray[differentScoreIndex]){
    				Left.setText(currentSession.fetchPlayer()[sameScore1Index].fetchUserName());
    				Right.setText(currentSession.fetchPlayer()[sameScore2Index].fetchUserName());
    				Middle.setText("VS");
    				cmdLogger.log("Player " + (differentScoreIndex + 1) + " eliminated");
    				gameSession currentSession = gameSession.getSession();
            		currentSession.setPlayer(2, new Player(currentSession.fetchPlayer()[sameScore1Index].fetchUserName()), new Player(currentSession.fetchPlayer()[sameScore2Index].fetchUserName()));
    			} else if (sameScore1Index == 0) {
    				Left.setText("");
        			Right.setText("");
        			Middle.setText(currentSession.fetchPlayer()[differentScoreIndex].fetchUserName());
        			cmdLogger.log("Game ended with player " + (differentScoreIndex + 1) + " win");
        			end = true;
    			} else {
    				Left.setText("");
        			Right.setText("");
        			Middle.setText(currentSession.fetchPlayer()[differentScoreIndex].fetchUserName());
        			cmdLogger.log("Game ended with player " + (differentScoreIndex + 1) + " win");
        			end = true;
    			}
    		} else {
    			int highestScoreIndex = 0;
    			int lowestScoreIndex = 0;
    			
    			//Find highest score Index
    			for(int i = 1; i < playerNum; i++) {
    				if(totalScoreArray[i] > totalScoreArray[highestScoreIndex]) {
    					highestScoreIndex = i;
    				}
    			}
    			
    			//Find lowest score Index
    			for(int i = 1; i < playerNum; i++) {
    				if(totalScoreArray[i] < totalScoreArray[lowestScoreIndex]) {
    					lowestScoreIndex = i;
    				}
    			}
    			
    			winner1Index = highestScoreIndex;
    			winner2Index = playerNum - highestScoreIndex - lowestScoreIndex; //mid range = index 2, 3 - 1 - 0 = 2
    			cmdLogger.log("Player " + (lowestScoreIndex + 1) + " eliminated");
    			
    			for(int i = 0; i < playerNum; i++) {
    				if(i == winner1Index) {
    					Left.setText(currentSession.fetchPlayer()[winner1Index].fetchUserName());
    				} else if (i == winner2Index) {
    					Right.setText(currentSession.fetchPlayer()[winner2Index].fetchUserName());
    				}
    			}
    			
    			gameSession currentSession = gameSession.getSession();
        		currentSession.setPlayer(2, new Player(currentSession.fetchPlayer()[winner1Index].fetchUserName()), new Player(currentSession.fetchPlayer()[winner2Index].fetchUserName()));
    		}
    				
    		
    	}else {
    		totalScoreArray[0] = currentSession.fetchPlayer()[0].fetchTotalGameScore();
    		totalScoreArray[1] = currentSession.fetchPlayer()[1].fetchTotalGameScore();
    		
    		for(int i = 0; i < playerNum; i++) {
    			System.out.println("Player " + (i + 1) +  " score = " + totalScoreArray[i]);
    		}
    		
    		Left.setVisible(false);
    		Right.setVisible(false);
    		
    		if(totalScoreArray[0] > totalScoreArray[1]) {
    			cmdLogger.log("Game ended with player 1 win.");
    			Middle.setText(currentSession.fetchPlayer()[0].fetchUserName());
    		} else if(totalScoreArray[0] < totalScoreArray[1]) {
    			cmdLogger.log("Game ended with player 2 win.");
    			Middle.setText(currentSession.fetchPlayer()[1].fetchUserName());
    		} else {
    			Middle.setText("DRAW");
    		}
//    		
    		end = true;
    	}
		
    }

    @FXML
    void onNextButtonClick(ActionEvent event) {
    	if (end == false) {
    		SceneHandler sceneHandler = new SceneHandler("lobby.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    		try {    			
    			cmdLogger.log("New scene lobby created");
    			sceneHandler.showWindow();
    			cmdLogger.log("Scene showed");
    		} catch (IOException e) {
    			cmdLogger.logError(e);
    			e.printStackTrace();
    		}
    	}else {
    		SceneHandler sceneHandler = new SceneHandler("mainMenu.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    		try {
    			
    			cmdLogger.log("New scene mainMenu created");
    			sceneHandler.showWindow();
    			cmdLogger.log("Scene showed");
    		} catch (IOException e) {
    			cmdLogger.logError(e);
    			e.printStackTrace();
    		}
    	}
    }

}
