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
        Character Chantal = new Character(false, true, false, false, false, false, false, true, false, false, true, false, true, true)
        CharacterList.add(Chantal);
        Character Eric = new Character(true, false, true, true, true, false, false, false, false, false, false, false, false, false)
        CharacterList.add(Eric);
        Character Alex = new Character(true, false, false, false, false, true, false, false, false, false, false, true, false, true)
        CharacterList.add(Alex);
        Character Bob = new Character(true, false, true, true, false, true, false, false, false, false, true, false, false, false)
        CharacterList.add(Bob);
        Character Paul = new Character(true, false, true, false, true, false, false, false, true, false, false, false, false, false)
        CharacterList.add(Paul);
        Character Frank = new Character(true, false, true, false, false, false, true, false, false, true, true, false, false, true)
        CharacterList.add(Frank);
        Character Zoe = new Character(true, false, true, false, false, false, true, false, false, true, true, false, false, true)
        CharacterList.add(Zoe);
        
    }
    /*
    boolean isMale,
	boolean wearingGlasses,
	boolean hasMoustache,
	boolean wearingBunnyEars,
	boolean isBald,
	boolean hasBlondHair,
	boolean hasBlackHair,
	boolean hasBrownHair,
	boolean wearingBandana,
	boolean wearingHairBand,
	boolean wearingEarrings,
	boolean wearingTie,
	boolean wearingNecklace,
	boolean mouthOpen

    still working on creating characters dont change anything
     */

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
