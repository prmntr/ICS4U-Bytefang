import java.util.ArrayList;
import java.util.random.*;
public class ComputerPlayer extends Players {

    // computerplayer does not need player or board fields it iss inherriter
    //however it does need to chppse a character
    private Character aiChar;

    private Board aiBoard;

    public ComputerPlayer() {
        super(
                "Computer",
                new Board(selectAICharacter()), //create a board and pass in the ai character selected
                new ArrayList<String>() // questionList will be initialized in players contruct
        );
        this.aiBoard = getBoard();
        this.aiChar = aiBoard.getSelectedCharacter();
    }

    // PLACEHOLDER TO BE CHANGED BY NAVIN
    private static Character selectAICharacter() {
        int randomIndex = (int) (Math.random() * 24); // gen random number // Assumes this static method exists
        Board tempBoard = new Board();
        return tempBoard.getCharacterList().get(randomIndex);
       
    }

    @Override
    public String askQuestion(String question) {
        // Implement AI logic for asking a question if needed
        return "Computer is making a guess...";
    }

    @Override
    public boolean makeGuess(String guessedName) {
        // Implement AI logic for making a guess
        // For now, just compare guessedName to aiChar's name
        return aiChar != null && aiChar.getName().equalsIgnoreCase(guessedName);
    }
}