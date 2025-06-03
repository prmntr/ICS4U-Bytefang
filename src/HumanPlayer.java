import java.util.*;
/**
 * @author: Navin Vallipuram (Team ByteFang)
 * date: 2025.05.26
 * Humanplayer class will handle all the actions that the human will make throughout the game
 */

public class HumanPlayer extends Players {

    //arraylist not list navin
    public HumanPlayer(String playerName, Board board, ArrayList<String> questionList) {
        super(playerName, board, questionList);
    }

    // method will be called from the gameGUI
    @Override
    public boolean makeGuess(String guessedName) {
        return true;
    }

    @Override
    /**
     * @param question - question taken from GuessWho.java that will be asked to ComputerPlayer
     * 
     */
<<<<<<< HEAD
    public String askQuestion(String question) {
        getBoard().removeCharacter(question);

        getQuestionList().removeQuestion(question);

        return question;



        
=======
    public String askQuestion() {
        return "hello";
>>>>>>> 7a83aab53f50e8de6e7022d0648cb1787a4df108
    }

}
