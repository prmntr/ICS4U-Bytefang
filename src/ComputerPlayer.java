<<<<<<< HEAD
import java.util.*;
/**
 * @name: Navin Vallipuram (Team ByteFang)
 * @date: 2025/06/01
 * ComputerPlayer
 */

public class ComputerPlayer extends Players {

   

    public ComputerPlayer(String playerName, Board board)  {
        super(playerName, board);
    }

    @ Override
    public boolean makeGuess() {
        // once there is one charecter left on the board, than the computer is ready to make a guess 
        // The computer will only guess when it knows for certain what charecter is the HumanPlayers charecter
        return (getBoard().getCharacterList().size() == 1);
    }

    @ Override
    // quesiton is returned in computerDecisionMaking, so there is no need for the askQuestion() method for ComputerPlayer
    public String askQuestion() {
        return null; 

    }

=======
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
>>>>>>> 01c373ef38f400b1cc90a77d9f6be227505a52be
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
<<<<<<< HEAD
    public String computerDecisionMaking() {
        
        // Use the getter from Players.java to get the list of question that the computer can choose to ask from
        List<String> questionList = getQuestionList(); 

        List<Character> remainingCharacters = getBoard().getCharacterList();

        if (questionList == null || questionList.isEmpty()){
            // no question in list 
            return "There are no questions available";
        }
=======
    public String computerDecisionMaking(ArrayList<Character> remainingCharacters) {
>>>>>>> 01c373ef38f400b1cc90a77d9f6be227505a52be

        // Use the getter from Players.java to get the list of question that the
        // computer can choose to ask from
        ArrayList<String> questionList = getQuestionList();

<<<<<<< HEAD
         // assume that the safest question and the most riskiest question are the first question in the charecter list (for now)
         String safestQuestion = questionList.get(0);
         String riskiestQuestion = questionList.get(0);
=======
        int total = remainingCharacters.size();
>>>>>>> 01c373ef38f400b1cc90a77d9f6be227505a52be

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
<<<<<<< HEAD
         // for loop runs for all charecters that are remaining in the remainingCharacters array list
         for (Character c : remainingCharacters) {
              
            if (c.isMatch(question) == true) {
               yesCount++;
            }
         }
=======
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
>>>>>>> 01c373ef38f400b1cc90a77d9f6be227505a52be

         int noCount = total - yesCount;

         // absolute value of yesCount - noCount
         int difference = Math.abs(yesCount - noCount);

        // situation in which current question's yes/no is closer to 50/50 than other questions
        // update the safest question + its difference value
        if (difference < minDiff) {
            minDiff = difference;
            safestQuestion = question;
        }

        // sutation in which the current questions's yes/no split is more unbalanced (riskest question)
        // update the riskiest question + its difference values
        if (difference > maxDiff) {
            maxDiff = difference;
            riskiestQuestion = question;
        }

    }
<<<<<<< HEAD

    Random rand = new Random();

    if (rand.nextInt(5) < 3) { // 0, 1 and 2 = safest
        return safestQuestion;
    } 
    else{ // otherwise pick riskest question
        return riskiestQuestion;
    }
    
    
    



}
=======
>>>>>>> 01c373ef38f400b1cc90a77d9f6be227505a52be
}
