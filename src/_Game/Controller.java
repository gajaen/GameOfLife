package _Game;

import javafx.scene.control.*;

public class Controller {

    public Button startButton;

    public void clickedStartButton(){
        System.out.println("Du trykket på start");
        startButton.setText("PAUSE");
    }
}
