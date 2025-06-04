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
    public boolean makeGuess() {
        return true;
    }

    @Override
    /**
     * @param question - question taken from GuessWho.java that will be asked to ComputerPlayer
     * 
     */
    public String askQuestion(String question) {
        getBoard().removeCharacter(question);

        //getQuestionList().removeQuestion(question);

        return question;



        
    }

}
