package _Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The Game Of Life program created for HIOA final project
 * The Main class starts the project from the main method and loading fxml.
 *
 * @version 1.0
 * @since   2017-01-14
 */


public class Main extends Application {
    /**
     * This is the main method which makes us start the program.
     * @param args lunching.
     */

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This is the start method which makes use creates the stage itself.
     * @param primaryStage parameter is used to create the stage.
     * @return Nothing.
     * @exception Exception On input error.
     * @see Exception
     *
     */

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/Frame.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("GameOfLife");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 1280, 780));

        primaryStage.show();
    }


    }

