import java.util.ArrayList;

public class ComputerPlayer extends Players {

    public ComputerPlayer(String playerName, Board board, ArrayList<String> questionList)  {
        super(playerName, board, questionList);
    }

    public boolean makeGuess() {

    }

    public String askQuestion() {

    }

    /**
     * 
     * @param remainingCharacters
     * @return question - Question that computer will decide to ask after narrowing it down using its decision making
     * Method will give logic to the computer's decision making when choosing a question. 
     * The computer will determine what the safest question is (Question that is closest to 50% elimination, 50% remaining) and what the riskest is 
     * (Question that may eliminate one charecter and keep the rest or vice versa)
     * The computer has a 60% 
     */
    public String computerDecisionMaking(ArrayList<Character> remainingCharacters){
        
        // Use the getter from Players.java to get the list of question that the computer can choose to ask from
        ArrayList<String> questionList = getQuestionList(); 

         int total = remainingCharacters.size();
    }

}
