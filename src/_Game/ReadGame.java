package _Game;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Gajaen on 03.04.2017.
 */
public class ReadGame {/*
    public void openfile() {

        CanvasFrame canvasFrame = new CanvasFrame(Canvas);
        String xPattern = ("x = (\\d+)");
        String yPattern = ("y = (\\d+)");


        int rownumber = 5;
        int columnnumber = 0;
        //int up = 0;
        // int down  = 0;
        // int left = 0;
        int right = 0;


        try (Scanner scanner = new Scanner(ReadGameBoard.file)) {
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


                                canvasFrame.setOpenBoard(rownumber, columnnumber, cnum, canvasFrame.getBoard());

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

                canvasFrame.setCellColor(Color.WHITE);
                canvasFrame.clearCanvas();
                canvasFrame.drawCells();
                canvasFrame.drawLines();


            }


        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }


    }*/


}
