package model;

import mvc.Model;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

import javax.persistence.*;

/**
 * @author Dan Crosby  CIT360
 * 6/10/19
 * A class for demonstrating MVC Model and Hibernate with a MySQL backend database
 */

@Entity
@Table
public class simpletrivia extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String category;
    private String type;
    private String question;
    private String difficulty;
    private String correct_answer;

    /**
     * Method getId
     * <p>Getter for the ID property</p>
     *
     * @return  int - the ID from the database for the current question
     */
    public int getId() {
        return id;
    }

    /**
     * Method getQuestion
     * <p>Getter for the question property</p>
     *
     * @return  String - the question from the database for the current question
     */
    public String getQuestion() { return this.question; }

    /**
     * Method getAnswer
     * <p>Getter for the correct_answer property</p>
     *
     * @return  boolean - the correct_answer from the database for the current question
     */
    public boolean getAnswer() {
        //System.out.format("Debug 2 question %s,answer %b",question,correct_answer);
        return Boolean.parseBoolean(correct_answer);
    }

    /**
     * Method simpletrivia
     * <p>Default class constructor</p></p>
     */
    public simpletrivia() {
        super();
    }

    /**
     * Method simpletrivia
     * <p>Class constructor for creating a new trivia item to be saved in the database</p></p>
     *
     * @param  question - the Text of the question
     * @param  type - the type of the question, which shoudl be 'boolean' for true/false questions
     * @param  difficulty - The usual value is "easy"
     * @param  correct_answer - The value can be True or False for a true false question
     * @param  category - the category of question, which can be any text general description like "80's".
     */
    private simpletrivia(String question, String type, String difficulty, String correct_answer, String category) {
        super( );
        this.question=question;
        this.category=category;
        this.type=type;
        this.difficulty=difficulty;
        this.correct_answer=correct_answer;
    }

    /**
     * Method getRandomQuestion
     * <p>Locate a random question and persist it using Entity Manager</p><
     * <p>Settings for the database connection can be found in META-INF - persistence</p>
     *
     * @return simpletrivia - A reference to this java class containing the properties of the retrieved question
     */
    public simpletrivia getRandomQuestion() {
        int random=ThreadLocalRandom.current().nextInt(1, getMaxID());

        try {
            SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(simpletrivia.class)
                    .buildSessionFactory();

            EntityManager entityManager = factory.createEntityManager();

            String qlQuery = "from simpletrivia where id=" + random;
            Query query = entityManager.createQuery(qlQuery);
            List<?> list = query.getResultList();
            return (simpletrivia)list.get(0);
            //System.out.format("DEBUG from model: Question %d : %s (%s)",random, triviaModel.question,triviaModel.correct_answer);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method toString
     * <p>Provide a text based equivalent of the selected trivia object</p>
     *
     * @return String - A formatted description of the trivia question.
     */
    @Override
    public String toString() {
        return "Question [id=" + id + ", question=" + question + ", type=" + type + ", answer=" + correct_answer + ", category=" + category + ", difficult=" + difficulty + "]";
    }

    /**
     * Method getMaxID
     * <p>Locate the highest ID number in the database</p><
     * <p>Settings for the database connection can be found in META-INF - persistence</p>
     *
     * @return int - The highest ID property in the database.   Note:  Current code assumes that all ID numbers will be sequential.
     */
    private static int getMaxID() {
        int i=0;
        try {
            SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(simpletrivia.class)
                    .buildSessionFactory();

            EntityManager entityManager = factory.createEntityManager();

            String qlQuery = "select id from simpletrivia ";
            Query query = entityManager.createQuery(qlQuery);
            List i2 = query.getResultList();
            i=i2.size();
            //System.out.println("Max ID : " + i);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return i;
    }

    /**
     * Method createQuestion
     * <p>Create a new question in the database based on passed in user input</p><
     * <p>Settings for the database connection can be found in META-INF - persistence</p>
     *
     * @param newQuestion - newQuestion(0) is the text of the question, and newQuestion(1) is the True/False answer.
     *
     * @return boolean - True = question successfully added.
     */
        public static boolean createQuestion ( ArrayList<String> newQuestion) {

        if (newQuestion.size()!=2) {
            //This command can only work with 2 inputs
            return false;
        }
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(simpletrivia.class)
                .buildSessionFactory();

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction( ).begin( );

        //String question, String type, String difficulty, String correct_answer, String category
        simpletrivia st = new simpletrivia(newQuestion.get(0),"boolean","easy",newQuestion.get(1),"user added");
        entityManager.persist( st );

        entityManager.getTransaction( ).commit( );

        entityManager.close( );
        factory.close( );
        return true;

    }
}
