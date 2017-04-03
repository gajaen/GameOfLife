package _Game;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ReadGameBoard extends GUI {
    private Stage stage;
    File file;
    private Cell cell;
    private Board board;
    private CanvasFrame canvasFrame;



    public ReadGameBoard() throws IOException {

    }
    public void init(Stage primaryStage) {

        this.stage = stage;

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
    }



    public void openfile() {

        int rownumber = 5;
        int columnnumber = 0;


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


                                board.setOpenBoard(rownumber, columnnumber, cnum, board.getBoard());

                                //      canvasFrame.setBoardRandom(cnum,columnnumber);
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

                cell.setCellColor(colorPicker.getValue());
                canvasFrame.clearCanvas();
                canvasFrame.drawCells();
                canvasFrame.drawLines();


            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    }
