package _Game;

import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.FileNameMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * The Game Of Life program created for HIOA final project
 * The ReadGameBoard class opens RLE file and decoding it to pattern on board.
 *
 * @author  Sivert Allergodt Borgeteien & Gajaen Chandrasegaram
 * Studentnr : S315325 & S315285
 * @version 1.0
 * @since   2017-01-14
 */


public class ReadGameBoard {
    private Stage stage;
    File file;
    public int[][] pattern;
    String line;

    /**
     * Constructs  board with Height and Width and Initialize a pattern array, and openfile, readfile methods
     *
     * @param boardHeight canvas height
     * @param boardWidth  canvas width
     */


    public ReadGameBoard(int boardHeight, int boardWidth) {

        pattern = new int[boardWidth][boardHeight];


        try {
            openFile();
            readFile(line);
           // setText();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

 /*  public void setText(){


        Pattern hash = Pattern.compile(".*#.*");

        Matcher m = hash.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
        }else {
            System.out.println("NO MATCH");

        }


    }*/


    /**
     * Constructs and initializes the stage.
     *
     * @param primaryStage unused.
     */

    public void init(Stage primaryStage) {

        this.stage = stage;

    }

    /**
     * This method opens the file with FileChooser.
     *
     * @throws IOException On input error.
     * @see IOException
     */

    private void openFile() throws IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open GOL Shape");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Run Length Encoded File", "*.RLE"),
                new FileChooser.ExtensionFilter("Text File", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*")

        );

        file = fileChooser.showOpenDialog(stage);


        if (file != null) {
            System.out.println("Choosen file " + file);
        }


        System.out.println(getCreationDetails(file));

        Scanner scanner1 = new Scanner(file);
        scanner1.nextLine();
        System.out.printf(scanner1.nextLine());
        String line2 = scanner1.nextLine();

        if (Pattern.matches(".*#.*", line2)) {

            Pattern hash = Pattern.compile(".*#.*");

            Matcher m = hash.matcher(line2);
            if (m.find( )) {
                System.out.println("Found value: " + m.group(0) );
                System.out.println("Found value: " + m.group(1) );
                System.out.println("Found value: " + m.group(2) );
            }else {
                System.out.println("NO MATCH");
            }
        }


    }


    /**
     * This method decodes each line that has string 'b' , 'o' , '$'.
     * The it is putting it to the pattern array.
     *
     * @throws IOException On input error.
     * @see FileNotFoundException
     */
    public void readFile(String line1) throws IOException {

        int rownumber = 5;
        int columnnumber = 0;
        this.line = line1;
        if (file == null) {
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            BufferedReader reader = new BufferedReader(new FileReader(file));


            while (scanner.hasNextLine()) {

                 line = scanner.nextLine();

                // checkin g line is empty or commented or with rule line
                if (line.isEmpty() || Pattern.matches(".*rule.*" , line)|| Pattern.matches(".*#.*", line)) {

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
                                pattern[rownumber + 10][columnnumber + cnum + 10] = 1;
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






            }}



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getCreationDetails(File file) {
        try {
            Path p = Paths.get(file.getAbsolutePath());
            BasicFileAttributes view
                    = Files.getFileAttributeView(p, BasicFileAttributeView.class)
                    .readAttributes();
            FileTime fileTime = view.creationTime();
            System.out.println(file.getName());

            return ("" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((fileTime.toMillis())));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }







}
