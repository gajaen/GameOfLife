package _Game;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class GUI {

    private Controller controller;
    private Stage stage;


    public void RandomButton() {
        //Lager en ny random array for hver gang start er trykket.
        for (int i = 0; i < controller.HEIGHT; i++) {
            for (int j = 0; j < controller.WIDTH; j++) {
                controller.board[i][j] = (int) (Math.random() * 2);
            }
        }
        controller.drawCells();
        controller.drawLines();
    }

    public void init(Stage primaryStage) {

        this.stage = stage;

    }



    public void ClearButton() {
        controller.gen = 0;
        controller.timeline.stop();
        controller.initialize();

    }

    public void StartButton() {
        controller.timeline.play();

    }

    public void ColorPicker() {
        Color color = controller.colorPicker.getValue();
        if (color != null) {
            controller.cellColor = controller.colorPicker.getValue();
        }
    }

    public void StopButton() {
        controller.timeline.stop();
    }

    public void fpsSlider() {
        controller.FPS = (int) controller.sliderFPS.getValue();
    }

    public void cellSlider{
        controller.cellSize = (int) controller.cellSlider.getValue();
    }

    public void openFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open GOL Shape");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Run Length Encoded File", "*.RLE"),
                new FileChooser.ExtensionFilter("Text File", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*")

        );


        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println("Choosen file " + file);
        }


        String xPattern = ("x = (\\d+)");
        String yPattern = ("y = (\\d+)");

        controller.initialize();
        controller.cleanBoard();
        controller.drawLines();

        int rownumber = 5;
        int columnnumber = 0;
        int right = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                // checkin g line is empty or commented or with rule line
                if (line.isEmpty() || Pattern.matches(".*#.*", line) || Pattern.matches(".*rule.*", line)) {
                    continue;
                }

                System.out.println(line);

                // split the line with $
                Pattern p = Pattern.compile("(?<=\\$)");

                String[] items = p.split(line);

                for (String item : items) {


                    // itemTmp = 2b3o1b2o$
                    String itemTmp = item;

                    // while itemTmp is a valid form
                    while ((!itemTmp.isEmpty()) && Pattern.matches(".*b.*|.*o.*", itemTmp)) {


                        // b pattern - eg. 34b --> cnumber will be 34
                        Pattern bnumber = Pattern.compile("^(?<cnumber>\\d*?)b");
                        Matcher bmatcher = bnumber.matcher(itemTmp);

                        // o pattern eg. 3o -> onumber will be 3
                        Pattern onumber = Pattern.compile("^(?<onumber>\\d*?)o");
                        Matcher omatcher = onumber.matcher(itemTmp);

                        if (bmatcher.find()) {
                            String bNumString = bmatcher.group("cnumber");
                            int bNumInt = 1;
                            if (!bNumString.isEmpty()) {

                                bNumInt = Integer.parseInt(bNumString);
                            }
                            columnnumber = columnnumber + bNumInt;
                            itemTmp = itemTmp.replaceFirst("^\\d*?b", "");
                        } else if (omatcher.find()) {
                            String oNumString = omatcher.group("onumber");

                            int oNumInt = 1;
                            if (!oNumString.isEmpty()) {

                                oNumInt = Integer.parseInt(oNumString);
                            }


                            for (int cnum = 1; cnum <= oNumInt; cnum++) {
                                controller.board[rownumber + 5 + right][columnnumber + cnum + 4] = 1;

                                //columnnumber = columnnumber +1;
                            }
                            columnnumber = columnnumber + oNumInt;
                            itemTmp = itemTmp.replaceFirst("^\\d*?o", "");
                        }

                    }

                    //if $ ONLY move to next row (row = row + 1 and column =0)
                    if (Pattern.matches(".*\\$", item)) {
                        columnnumber = 0;
                        rownumber = rownumber + 1;
                    }


                }
                controller.drawCells();


            }


            controller.drawLines();

        } catch (IOException e) {
            e.printStackTrace();

        }



    }


    public void closeWindow() {
        Platform.exit();
    }



}
