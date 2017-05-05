package _Game;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Game Of Life program created for HIOA final project
 * The ReadGameBoard class opens RLE file and decoding it to pattern on board.
 *
 * @version 1.0
 * @since   2017-01-14
 * @author  S315325 & S315285
 */


public class ReadGameBoard {
    private File file;
    private Stage stage;
    private String patterName;
    public int[][] pattern;
    private int cell;

    /**
     * Constructs  board with Height and Width and Initialize a pattern array, and openfile, readfile methods
     *
     * @param boardHeight canvas height
     * @param boardWidth  canvas width
     */


    public ReadGameBoard(int boardHeight, int boardWidth)  {

        pattern = new int[boardHeight][boardWidth];

        openFile();
        readFile();
    }

    /**
     * This method opens the file with FileChooser.
     */

    private void openFile() {

        //Opens file
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open GOL Shape");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Run Length Encoded File", "*.RLE"),
                new FileChooser.ExtensionFilter("Text File", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*")


        );
        file = fileChooser.showOpenDialog(stage);

    }




    /**
     * This method decodes each line that has string 'b' , 'o' , '$'.
     * The it is putting it to the pattern array.
     *
     */

    public void readFile() {
        int rowNumber = 5;
        int columnNumber = 0;

        if(file != null){

            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    // Checking if line is empty or commented or with rule line
                    if (line.isEmpty() || Pattern.matches(".*rule.*", line) || Pattern.matches(".*#.*", line)) {
                        continue;
                    }

                    // Split the line with $
                    Pattern p = Pattern.compile("(?<=\\$)");

                    String[] items = p.split(line);

                    for (String item : items) {

                        String itemTmp = item;

                        // While itemTmp is a valid form
                        while ((!itemTmp.isEmpty()) && Pattern.matches(".*b.*|.*o.*", itemTmp)) {


                            // b pattern
                            Pattern bnumber = Pattern.compile("^(?<cnumber>\\d*?)b");
                            Matcher bmatcher = bnumber.matcher(itemTmp);

                            // o pattern
                            Pattern onumber = Pattern.compile("^(?<onumber>\\d*?)o");
                            Matcher omatcher = onumber.matcher(itemTmp);

                            if (bmatcher.find()) {

                                String bNumString = bmatcher.group("cnumber");
                                int bNumInt = 1;

                                if (!bNumString.isEmpty()) {
                                    bNumInt = Integer.parseInt(bNumString);
                                }

                                columnNumber = columnNumber + bNumInt;
                                itemTmp = itemTmp.replaceFirst("^\\d*?b", "");

                            } else if (omatcher.find()) {
                                String oNumString = omatcher.group("onumber");

                                int oNumInt = 1;
                                if (!oNumString.isEmpty()) {

                                    oNumInt = Integer.parseInt(oNumString);
                                }


                                for (int cnum = 1; cnum <= oNumInt; cnum++) {
                                    try {
                                        pattern[rowNumber + 10][columnNumber + cnum + 10] = 1;
                                    } catch (ArrayIndexOutOfBoundsException e) {
                                        if (rowNumber > 1000 && columnNumber > 1000) {
                                            setCell((rowNumber + columnNumber) / pattern.length);
                                        }
                                    }

                                }

                                columnNumber = columnNumber + oNumInt;
                                itemTmp = itemTmp.replaceFirst("^\\d*?o", "");
                            }

                        }

                        //if $ ONLY move to next row
                        if (Pattern.matches(".*\\$", item)) {
                            columnNumber = 0;
                            rowNumber = rowNumber + 1;
                        }

                    }

                }
            }
            catch(Exception e) {

                System.out.println("File not found");

            }
        }

    }

    /**
     * Gets MetaData from file
     *
     * @param file
     *
     */

    public String getCreationDetails(File file) {
        if(file == null)
            try {
                Path p = Paths.get(file.getAbsolutePath());
                BasicFileAttributes view
                        = Files.getFileAttributeView(p, BasicFileAttributeView.class)
                        .readAttributes();
                FileTime fileTime = view.creationTime();

                return ("" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((fileTime.toMillis())));

            } catch (IOException e) {
                e.printStackTrace();
            }
        return "";
    }

    /**
     * Returns PatterName
     * @return
     */

    public String getPatterName() {
        return patterName;
    }

    /**
     * Returns Cell
     * @return
     */

    public int getCell() {
        return cell;
    }

    /**
     * Sets Cell
     * @param cell
     */

    public void setCell(int cell) {
        this.cell = cell;
    }

    /**
     * Gets File
     * @return
     */

    public File getFile() {
        return file;
    }

}
