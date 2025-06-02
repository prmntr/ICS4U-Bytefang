
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

    // constructor for class, that will accept a name for the human player and a
    // board for each
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

    public ArrayList<String> getQuestionList() {
        return questionList;
    }
	
	// askQuestion() and makeGuess() will be inherited by ComputerPlayer and HumanPlayer to be overridden
	public abstract String askQuestion();
	
	public abstract boolean makeGuess();





}
