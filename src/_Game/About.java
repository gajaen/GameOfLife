package _Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.stage.Window;
import javafx.util.Duration;
import java.io.*;
import java.net.URL;

/**
 * Created by Sivert on 05.05.2017.
 */
public class About {

    public static void display(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("About");
        window.setMinHeight(400);
        window.setMinWidth(200);

        Label label = new Label();
        label.setText("About us");

        VBox layout = new VBox(10);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


    }
}
