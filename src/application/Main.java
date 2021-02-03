package application;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
        	bgm();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    MediaPlayer mediaPlayer;
    Media media = null;
    public void bgm() {
    	try {
            media = new Media(Paths.get("instrumental.mp3").toUri().toString());
            mediaPlayer = new MediaPlayer(media);
            Runnable onEnd = new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.dispose();
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                    mediaPlayer.setOnEndOfMedia(this);
                }
            };
            mediaPlayer.setOnEndOfMedia(onEnd);
            mediaPlayer.play();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}	