package view;

import model.simpletrivia;
import mvc.Controller;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Dan Crosby  CIT360
 * 6/10/19
 * A View class for demonstrating MVC
 */
public class TriviaView implements mvc.View {
    private simpletrivia triviaModel;
    private Controller controller;

    /**
     * Method TriviaView
     * <p>Not implementing at this time</p>
     */
    public TriviaView() {
        //no custom logic for instantiating the view
    }

    /**
     * Method modelChanged
     * <p>Not implementing at this time</p>
     */
    public void modelChanged() {
        //Not implementing this since this is intended as a single user app where changes to an existing loaded record would not be important.
    }

    /**
     * Method getMainMenuCommand
     * <p>Display prompts and return input to the controller</p>
     *
     * @return  String - the selected key stroke
     */
    public String getMainMenuCommand() {
        return myUtil.Util.getMenuInput("Main Menu: (p)lay, (a)dd Question, (q)uit -->","((p|a|q))");
    }

    /**
     * Method addNewQuestionPrompts
     * <p>Display prompts and return input to the controller</p>
     *
     * @returns  ArrayList<String>
     *     (0) - The question prompt
     *     (1) - The true false answer to the question
     */
    public ArrayList<String> addNewQuestionPrompts(){
        ArrayList<String> newQuestion = new ArrayList<>();
        String input =myUtil.Util.getInput("Enter your true-false trivia question (or enter to quit)");
        if (!input.isEmpty()) newQuestion.add(input); else return newQuestion;

        input=myUtil.Util.getMenuInput("What is the correct answer? (True or False)","((True|False))");
        if (input.equalsIgnoreCase("True") || input.equalsIgnoreCase("False")) newQuestion.add(input); else return newQuestion;

        System.out.println("The following question will be added:");
        newQuestion.forEach((s) -> System.out.println(s));
        return newQuestion;
    }

    /**
     * Method getQuizResponse
     * <p>Display prompts and return a String response to the controller</p>
     *
     * @param  question - the text of the question to display
     * @returns  String - the true, false, quit response going back to the controller
     */
    public String getQuizResponse(String question) {
        System.out.format("**************************\n** True or false:\n**     %s",cleanString(question));
        return myUtil.Util.getMenuInput("\n**     Your response: (t)rue, (f)alse, (q)uit -->","((t|f|q))");
    }

    /**
     * Method getQuizResponse
     * <p>Display a random prompt showing that the answer was correct</p>
     */
    public void showCorrect(){
        int random= ThreadLocalRandom.current().nextInt(1, 4);
        switch(random) {
            case 1:
                System.out.println("Rocket scientist, I see.");
                break;
            case 2:
                System.out.println("correct-o-mundo.");
                break;
            case 3:
                System.out.println("Correct, your knight or lady ship.");
        }
    }

    /**
     * Method showIncorrect
     * <p>Display a random prompt showing that the answer was incorrect</p>
     */
    public void showIncorrect(){
        {
            int random= ThreadLocalRandom.current().nextInt(1, 4);
            switch(random) {
                case 1:
                    System.out.println("Well shucks, that was embarrassing.  Try again.");
                    break;
                case 2:
                    System.out.println("Nope, I hate when that happens!");
                    break;
                case 3:
                    System.out.println("nuh uh, better luck next time!");
            }
        }
    }

    /**
     * Method showFailed
     * <p>Display a prompt showing that the attempt to add a question has failed</p>
     */
    public void showFailed(String s){
        System.out.format("Your attempt to add a question has failed: %s\n",s);
    }

    /**
     * Method showAdded
     * <p>Display a prompt showing that the question was added</p>
     */
    public void showAdded(String s){
        System.out.format("%s\n",s);
    }

    /**
     * Method cleanString
     * <p>A Utility function to clean up any JSON style text markers that would mess up the console output.</p>
     * @param s - the uncleaned string
     * @return String - the cleaned string
     */
    public static String cleanString(String s) {
        return s.replace("&quot;","'").replace("&#039;","'");
    }

}
