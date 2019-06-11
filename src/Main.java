import controller.TriviaController;
import java.util.logging.Level;

/**
 * @author Dan Crosby  CIT360
 * 6/10/19
 * A class for demonstrating MVC Model and Hibernate with a MySQL backend database
 */
public class Main {

    /**
     * Method main
     * <p>The main method turns off the Hibernate logging</p>
     * <p>Initializes Model, View, and then Controller (which is linked to the view and the model)</p>
     *
     * @param args   - Args are not used in this application
     */
    public static void main (String[] args){
        System.out.println("Creating model, view and controller for Trivia.");

        //Turn off excessive logging
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF); //or whatever level you need

        //Instantiate Model and View, and then pass them to the instantiated controller.
        model.simpletrivia triviaModel = new model.simpletrivia();
        view.TriviaView triviaView= new view.TriviaView();
        TriviaController controller = new TriviaController(triviaModel,triviaView);
  }
}
