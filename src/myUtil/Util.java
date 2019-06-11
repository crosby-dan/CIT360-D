package myUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dan Crosby
 * This class contains global utilities that do not pertain to one specific class or activity.
 */

public class Util {
    /**
     * The promptEnterKey method
     * Purpose: A simple function to prompt and catch an enter key to manage the flow of user input.
     *
     * @return void
     */
    public static void promptEnterKey() {
        System.out.format("Press ENTER to continue.");
        try {
            int readKey = System.in.read(new byte[2]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The promptEnterKey method with an overload to change the prompt message.
     * Purpose: A simple function to prompt and catch an enter key to manage the flow of user input.
     *
     * @return void
     */
    public static void promptEnterKey(String message) {
        System.out.println(message);
        try {
            int readKey = System.in.read(new byte[2]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The textToConsole method
     * A simple function to output a file of text to the console.
     *
     * @param fn (String) - the path and file name of the text file to output to console, i.e. "img/tractor.txt"
     * @return void
     */
    public static void textToConsole(String fn) {

        try {
            BufferedReader in = new BufferedReader(new FileReader(fn));
            String line = in.readLine();
            while (line != null) {
                System.out.println(line);
                line = in.readLine();
                in.close();
            }
        } catch (IOException ex) {
            System.out.format("Error in textToConsole: %s", ex);
        }
    }

/**
     * The getMenuInput method
     * A simple function to display a menu and capture a one character response.
     *
     * @param sMenu (String) - the prompt for the user
     * @param sPattern (String) - regular expression for the options surrounded by an extra set of parenthesis for the grouping.
     * @return string
 */
public static String getMenuInput(String sMenu, String sPattern) {
    //Sample menu string for sMenu:  Choose menu item:\nc) Collections Demo\nt) Threads Demo\na) Application Controller Demo\n\nq) Quit:
    //Sample pattern:  ((a|c|t|q))
    try {
        Pattern pattern = Pattern.compile(sPattern);
        Matcher matcher;
        boolean matchResult;
        do {
            System.out.println(sMenu);
            @SuppressWarnings("resource")
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            matcher = pattern.matcher(input);
            matchResult = matcher.find();
            if (!matchResult)
                System.out.println("Invalid value provided.");
            else
                return matcher.group(1);
        }
        while (true);
    } catch (Exception ex) {
        System.out.format("Error in textToConsole: %s", ex);
    }
    return "q";
    }

    /**
     * The getMenuInput method
     * A simple function to display a menu and capture a one character response.
     *
     * @param sPrompt (String) - the instructions to display to the user.
     * @return string
     */
    public static String getInput(String sPrompt) {
        System.out.print (sPrompt + "-->");
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }
}