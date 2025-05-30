import java.util.ArrayList;

/**
 * @author Sherlok Chan
 * 2025-05-29
 */
public class Board {
    private Character selectedCharacter;
    private ArrayList<Character> characterList;

    /**
     * Constructor
     * @param selectedCharacter
     */
    public Board(Character selectedCharacter) {
        characterList = new ArrayList<Character>();
        initializeBoard();
        this.selectedCharacter = selectedCharacter;
    }

    private void initializeBoard() {

    }

    /**
     * This methods iterate over the characteList and remove characters
     * 
     * @param question
     */
    public void removeCharacter(String question) {
        for (int i = 0; i < characterList.size(); i++) {
            if (!characterList.get(i).isMatch(question)) {
                characterList.remove(i);
            }
        }
    }

    /* getter methods */
    public Character getSelectedCharacter() {
        return selectedCharacter;
    }

    public ArrayList<Character> getCharacterList() {
        return characterList;
    }
}
