package controller;

import model.simpletrivia;
import view.TriviaView;
import java.util.ArrayList;

import static model.simpletrivia.createQuestion;

/**
 * @author Dan Crosby  CIT360
 * 6/10/19
 * A class for demonstrating MVC - Controller which coordinates the interaction between view and model.
 */
public class TriviaController {
   private simpletrivia model;
   private TriviaView view;

    /**
     * Constructor for TriviaController
     * <p>Assign variables to link the controller to the model and the view.</p><
     * <p>Loop through the main menu until the quit command is received</p>
     *
     * @param  model - A link to the model which will hold the data
     * @param  view - A link to the view which will display and capture user input
     */
   public TriviaController(simpletrivia model, TriviaView view) {
       this.model=model;
       this.view=view;
       String answer;

       do {
            answer=view.getMainMenuCommand();

            //If the main menu command is "a", then go through the add new question process.
           if (answer.equalsIgnoreCase("a"))
           {
               ArrayList<String> newQuestion = view.addNewQuestionPrompts();
               if (!(createQuestion(newQuestion))) {
                   view.showFailed("Invalid prompts");
               }
               else {
                   view.showAdded("Successfully added question.");
               }
           }
           //Play a question.   If the sub menu result comes back as Quit, then break the loop and exit.
           if (answer.equalsIgnoreCase("p")) {
               if (!playQuestion()) break;
           }
       }
       //Repeat until 'q' for quit.
        while (!answer.equalsIgnoreCase("q"));
   }

    /**
     * Method playQuestion
     * <p>Request a random question from the model</p><
     * <p>Call the view to display the question and capture a response</p>
     *
     * @return boolean - True = question successfully added.
     */
    private boolean playQuestion() {

        //randomly locate a question
        simpletrivia myRandomQuestion = model.getRandomQuestion();
        //Determine if the user is choosing true or false
        String answer = view.getQuizResponse(myRandomQuestion.getQuestion());

        //If quit, then abort
        if (answer.equalsIgnoreCase("q")) return false;

        //Parse the text input into a boolean true or false
        boolean bAnswer = answer.equalsIgnoreCase("t");

        //Show correct or false depending on if the answers match.
        if (myRandomQuestion.getAnswer()==bAnswer) {
            view.showCorrect();
        }
        else
        {
            view.showIncorrect();
        }
        return true;
    }
}