
import java.util.*;
/**
 * @author Navin Vallipuram + Brian Xue 
 * 2025-06-23
 */

public abstract class Players {

    private String playerName;

    // both computerPlayer and humanPlayer will have their own board object
    private Board board;

    private ArrayList<String> questionList;

    // constructor for class, that will accept a name for the human player and a
    // board for each
    public Players(String playerName) {
        this.playerName = playerName;
        board = new Board(null);
        questionList = new ArrayList<>();

        questionList.add("Is your character a male?");
        questionList.add("Is your character wearing glasses?");
        questionList.add("Does your character have a moustache?");
        questionList.add("Is your character wearing bunny ears?");
        questionList.add("Is your character bald?");
        questionList.add("Does you character have blond hair?");
        questionList.add("Does you character have black hair?");
        questionList.add("Does you character have brown hair?");
        questionList.add("Does your character have a beard?");
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

    public void removeQuestion(String question) {
        questionList.remove(question);
    }
	
	public abstract boolean makeGuess(Character c1, Character c2);

}
