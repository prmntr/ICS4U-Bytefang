import java.util.ArrayList;

/**
 * @author Sherlok Chan
 *         2025-05-29
 */
public class Board {
    private Character selectedCharacter;
    private ArrayList<Character> characterList;

    /**
     * Constructor
     * 
     * @param selectedCharacter
     */
    public Board(Character selectedCharacter) {
        characterList = new ArrayList<Character>();
        initializeBoard(); // helper function, instantiate all of the Characters and add them to the
                           // characterList
        this.selectedCharacter = selectedCharacter;
    }

    private void initializeBoard() {
        Character Chantal = new Character("Chantal", false, true, false, false, false, false, false, true, false, false,
                false, true, false, true, true);
        characterList.add(Chantal);
        Character Eric = new Character("Eric", true, false, true, true, true, false, false, false, false, false, false,
                false, false, false, false);
        characterList.add(Eric);
        Character Alex = new Character("Alex", true, false, false, false, false, true, false, false, false, false,
                false, false, true, false, true);
        characterList.add(Alex);
        Character Bob = new Character("Bob", true, false, false, true, false, true, false, false, true, false, false,
                true, false, false, false);
        characterList.add(Bob);
        Character Paul = new Character("Paul", true, false, true, false, false, false, false, false, false, true, false,
                false, false, false, false);
        characterList.add(Paul);
        Character Frank = new Character("Frank", true, false, true, false, false, false, true, false, false, false,
                true, true, false, false, true);
        characterList.add(Frank);
        Character Zoe = new Character("Zoe", false, false, false, false, false, false, false, true, false, false, true,
                true, false, true, true);
        characterList.add(Zoe);
        Character Joe = new Character("Joe", true, true, true, false, true, false, false, false, false, false, false,
                false, false, false, false);
        characterList.add(Joe);
        Character Buba = new Character("Buba", false, false, false, false, false, false, true, false, false, false,
                true, true, false, false, false);
        characterList.add(Buba);
        Character Rita = new Character("Rita", false, true, false, false, false, false, false, false, false, false,
                true, true, true, false, false);
        characterList.add(Rita);
        Character Rick = new Character("Rick", true, true, true, false, false, true, false, false, false, false, false,
                false, true, false, false);
        characterList.add(Rick);
        Character Antoine = new Character("Antoine", true, false, true, false, false, false, false, true, false, false,
                false, false, false, false, false);
        characterList.add(Antoine);
        Character John = new Character("John", true, true, false, false, false, false, true, false, false, false, false,
                false, false, false, false);
        characterList.add(John);
        Character Chap = new Character("Chap", true, true, false, false, true, false, false, false, true, false, false,
                true, false, false, false);
        characterList.add(Chap);
        Character Evelyn = new Character("Evelyn", false, false, false, false, false, false, false, false, false, false,
                false, true, false, true, false);
        characterList.add(Evelyn);
        Character Lady = new Character("Lady", false, true, false, false, false, false, false, true, false, false,
                false, true, false, false, false);
        characterList.add(Lady);
        Character Samantha = new Character("Samantha", false, false, false, false, false, true, false, false, false,
                true, false, true, false, true, false);
        characterList.add(Samantha);
        Character Jenny = new Character("Jenny", false, true, false, false, false, false, false, false, false, false,
                false, true, false, false, false);
        characterList.add(Jenny);
        Character Javier = new Character("Javier", true, false, true, false, false, false, false, false, false, false,
                true, false, false, false, true);
        characterList.add(Javier);
        Character Evan = new Character("Evan", true, false, true, false, false, true, false, false, false, false, false,
                false, false, false, false);
        characterList.add(Evan);
        Character Mathias = new Character("Mathias", true, true, false, false, false, false, false, false, false, false,
                false, false, false, false, false);
        characterList.add(Mathias);
        Character Michael = new Character("Michael", true, false, false, false, false, true, false, false, false, false,
                false, true, true, false, false);
        characterList.add(Michael);
        Character Hank = new Character("Hank", true, false, false, true, false, false, false, false, false, false,
                false, false, true, false, true);
        characterList.add(Hank);
        Character Vito = new Character("Vito", true, false, false, false, true, false, false, false, true, false, false,
                false, false, false, false);
        characterList.add(Vito);
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
