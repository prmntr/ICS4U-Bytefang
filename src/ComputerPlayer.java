import java.util.ArrayList;
import java.util.random.*;

public class ComputerPlayer extends Players {

    /**
     * @author Brian Xue
     * 2025-06-23
     */

    // computerplayer does not need player or board fields it iss inherriter
    // however it does need to chppse a character
    private Character aiChar;

    private Board aiBoard;

    public ComputerPlayer() {
        super(
                "Computer",
                new Board(selectAICharacter()), // create a board and pass in the ai character selected
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
        return "Computer is making a guess...";
    }

    @Override
    public boolean makeGuess() {
        // Implement AI logic for asking a question if needed
        return true;
    }


    // made my navin
    /**
     * 
     * @param remainingCharacters
     * @return question - Question that computer will decide to ask after narrowing
     *         it down using its decision making
     *         Method will give logic to the computer's decision making when
     *         choosing a question.
     *         The computer will determine what the safest question is (Question
     *         that is closest to 50% elimination, 50% remaining) and what the
     *         riskest is
     *         (Question that may eliminate one charecter and keep the rest or vice
     *         versa)
     *         The computer has a 60% of going with the safest question and a 40% of
     *         going with the risker question
     */
    public String computerDecisionMaking(ArrayList<Character> remainingCharacters) {

        // Use the getter from Players.java to get the list of question that the
        // computer can choose to ask from
        ArrayList<String> questionList = getQuestionList();

        int total = remainingCharacters.size();

        String safestQuestion = "";
        String riskiestQuestion = "";

        // track the smallest difference between yes and no answers to question (Safest
        // question)
        int minDiff = Integer.MAX_VALUE;

        // track the biggest difference between yes and no answers to question to
        // question (Riskest question)
        int maxDiff = Integer.MIN_VALUE;

        // outer loop for every question within the questionList
        for (String question : questionList) {
            int yesCount = 0;
            int noCount;

            // for loop runs for all charecters that are remaining in the
            // remainingCharacters array list
            for (Character c : remainingCharacters) {

                if (question.equals("Is your character a male?") && c.isMale == true) {
                    yesCount++;
                }

                else if (question.equals("Is your character wearing glasses?") && c.wearingGlasses == true) {
                    yesCount++;
                }
            }
        }
        return "yes";

    }
}
