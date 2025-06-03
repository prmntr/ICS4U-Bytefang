
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

    private ArrayList<String> questionList;

    /* constructor for class, that will accept a name for the humman player and a
    board for each */
    // question list has been initialized within the constructor as opposed to being constant for use by the AI for character elimination
    public Players(String playerName, Board board, ArrayList<String> questionList) {
        this.playerName = playerName;
        this.board = board;
        this.questionList = new ArrayList<String>();

        questionList.add("Is your character a male?");
        questionList.add("Is your character wearing glasses?");
        questionList.add("Does your character have a moustache?");
        questionList.add("Is your character wearing bunny ears?");
        questionList.add("Is your character bald?");
        questionList.add("Does you character have blond hair?");
        questionList.add("Does you character have black hair?");
        questionList.add("Does you character have brown hair?");
        questionList.add("Is your character wearing a bandana?");
        questionList.add("Is your character wearing a band in the hair?");
        questionList.add("Is your character wearing earrings?");
        questionList.add("Is your character wearing a tie?");
        questionList.add("Is your character wearing a necklace?");
        questionList.add("Does your chracter have mouth open?");
    }
	
	public String getPlayerName() {
		return playerName;
	}
	
	public Board getBoard() {
		return board;
	}
	
	// askQuestion() and makeGuess() will be inherited by ComputerPlayer and HumanPlayer to be overridden
    
    // for example: in the human player object, this method will be called by the human through the gui, which will then accept the question asked through the parameter and recieve a response from the AI
    // the ai can also ask a question to the user and get a response
	public abstract String askQuestion(String question);
    
	public abstract boolean makeGuess(String guessedName);





}
