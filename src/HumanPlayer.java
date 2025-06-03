import java.util.*;
/**
 * @author: Navin Vallipuram (Team ByteFang)
 * date: 2025.05.26
 * Humanplayer class will handle all the actions that the human will make throughout the game
 */

public class HumanPlayer extends Players{

	public HumanPlayer(String playerName, Board board, List<String> questionList) {
		 super(playerName, board, questionList);
	}
	
	@Override
    /**
     * @param - String guessedName - name of the charecter that the user would like to guess as ComputerCharecters
     * @return - boolean (true/false if name that human guessed and computers character match)
     * Method will evaluate and return wether the charecter that the user guesses matches with the charecter 
     */
	public boolean makeGuess(String guessedName) {
		
		// Retrieve the character that the computer selected and assign it into new Character object
		Character computerCharecter = getBoard().getSelectedCharecter();
		
		return computerCharacter.getName().equalsIgnoreCase(guessedName);
		
	}

    @Override
    /**
     * @param question - question taken from GuessWho.java that will be asked to ComputerPlayer
     * 
     */
    public String askQuestion(String question) {
        getBoard().removeCharacter(question);

        getQuestionList().removeQuestion(question);

        return question;



        
    }

}
