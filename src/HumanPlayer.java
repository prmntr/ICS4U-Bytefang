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
     * @param - 2 Characters: c1 is the Character human asked, c2 is computer's secret character
     * @return - boolean (true/false if name that human guessed and computers character match)
     * Method will evaluate and return wether the charecter that the user guesses matches with the charecter 
     */
	public boolean makeGuess(Character c1, Character c2) {
		return c1.equals(c2);
	}

}
