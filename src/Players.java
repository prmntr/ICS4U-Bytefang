
package guessWho;
import java.util.*;
/**
 * @author Navin Vallipuram (Team ByteFang)
 * date: 2025.05.26
 * Player class contains the abstract methods that computerPlayer and humanPlayer will inherit from and implement. This
 * class serves as the blueprint for its child classes
 */

public abstract class Players {

	
	private String playerName;
	
	// both computerPlayer and humanPlayer will have their own board object 
	private Board board;
	
	private List<String> questionList;

	// constructor for class, that will accept a name for the human player and a board for each
	public Players(String playerName,Board board) {
		this.playerName = playerName;
		this.board = board;
		this.questionList = new ArrayList<>();
		
		questionList.add("Is your character male");
		questionList.add("Does your character have black hair?");
		questionList.add("Does your character have blonde hair?");
		questionList.add("Does your charecter have brown eyes?");
		questionList.add("Does your charecter have blue eyes?");
		questionList.add("Does your charecter have glasses?");
		questionList.add("Does your charecter wear a hat?");
		questionList.add("Is your charecter black?");
		questionList.add("Is your charecter white?");
		questionList.add("Is your charecter asian?");
		questionList.add("Does you character have facial hair?");
		
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public Board getBoard() {
		return board;
	}
	
	// askQuestion() and makeGuess() will be inherited by ComputerPlayer and HumanPlayer to be overridden
	public abstract String askQuestion();
	
	public abstract boolean makeGuess();





}
