/**
 * @author: Navin Vallipuram (Team ByteFang)
 * date: 2025.05.26
 * Humanplayer class will handle all the actions that the human will make throughout the game
 */

public class HumanPlayer extends Players{

	public HumanPlayer(String playerName) {
		 super(playerName);
	}
	
    @Override
    /**
     * @param - String guessedName - name of the charecter that the user would like to guess as ComputerCharecters
     * @return - boolean (true/false if name that human guessed and computers character match)
     * Method will evaluate and return wether the charecter that the user guesses matches with the charecter 
     */
	public boolean makeGuess(Character guessedChar, Character aiChar) {
		return guessedChar.equals(aiChar);
		
	}

    
    /**
     * @param question - question taken from GuessWho.java that will be asked to ComputerPlayer
     * 
     */
    public void askQuestion(String question) {
        getBoard().removeCharacter(question);
        getQuestionList().remove(question);
    }
}
