//Optimized
package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneHandler {

    private Stage stage;
    private FXMLLoader loader;
    private Scene scene;

    // change existing window
    public SceneHandler(String file, Stage stage) {
        this.stage = stage;
        this.loader = new FXMLLoader(getClass().getResource(file));
    }
    
    public void showWindow() throws IOException {
        scene = new Scene(this.loader.load());
        stage.setScene(this.scene);
        stage.setTitle("Poker");
        stage.setResizable(false);
        stage.show();
    }

    public FXMLLoader getLoader() {
        return loader;
    }

}