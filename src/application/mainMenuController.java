package application;
import java.io.IOException;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class mainMenuController {

    @FXML
    private JFXTextField player1Name;

    @FXML
    private JFXTextField player2Name;

    @FXML
    private JFXTextField player3Name;

    @FXML
    void onStartButtonClick(ActionEvent event) {
    	cmdLogger.logActionByUser("start button clicked", "mainMenuController");
    	if(player1Name.getText().equals("") || player2Name.getText().equals("") || player3Name.getText().equals("")) {
    		cmdLogger.log("Entry field missing from user at mainMenuController");
    	} else {
    		//Save username to current game session
    		gameSession currentSession = gameSession.getSession();
    		currentSession.setPlayer(new Player(player1Name.getText()), new Player(player2Name.getText()), new Player(player3Name.getText()));
    		SceneHandler sceneHandler = new SceneHandler("lobby.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    		try {
    			
    			cmdLogger.log("New scene gameTable created");
    			sceneHandler.showWindow();
    			cmdLogger.log("Scene showed");
    		} catch (IOException e) {
    			cmdLogger.logError(e);
    			e.printStackTrace();
    		}
    	}
    	
    }

}
