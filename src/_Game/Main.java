package _Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Frame.fxml"));
        Parent root = loader.load();
        Controller controller = (Controller)loader.getController();
        controller.init(primaryStage);

        primaryStage.setTitle("GameOfLife");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();

    }

}