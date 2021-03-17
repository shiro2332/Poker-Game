//Optimized
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//JavaFX Main Class
public class Main extends Application {

    private Parent root;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        try {
            this.root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            this.scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Poker");
            stage.setResizable(false);
            
            stage.show();
            cmdLogger.log("Game successfully started");
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}	