import java.util.*;
/**
 * @name: Navin Vallipuram (Team ByteFang)
 * @date: 2025/06/01
 * ComputerPlayer
 */

public class ComputerPlayer extends Players {

    Random rand = new Random();

    
   

    public ComputerPlayer(String playerName)  {
        super(playerName);
     
    }

    @ Override
    /**
     * Methods trigger when Computer's board has 1 character left (handled in GUI)
     */
    public boolean makeGuess(Character c1, Character c2) {
        return c1.equals(c2);
    }


    public void setAiCharecter() {
        getBoard().setSelectedCharacter(getBoard().getCharacterList().get(rand.nextInt(24)));
    }

    /**
     * 
     * @param remainingCharacters
     * @return question - Question that computer will decide to ask after narrowing it down using its decision making
     * Method will give logic to the computer's decision making when choosing a question. 
     * The computer will determine what the safest question is (Question that is closest to 50% elimination, 50% remaining) and what the riskest is 
     * (Question that may eliminate one charecter and keep the rest or vice versa)
     * The computer has a 60% of going with the safest question and a 40% of going with the risker question
     */
    public String computerDecisionMaking() {
        
        // Use the getter from Players.java to get the list of question that the computer can choose to ask from
        List<String> questionList = getQuestionList(); 

        List<Character> remainingCharacters = getBoard().getCharacterList();

        if (questionList == null || questionList.isEmpty()){
            // no question in list 
            return "There are no questions available";
        }

         int total = remainingCharacters.size();

         // assume that the safest question and the most riskiest question are the first question in the charecter list (for now)
         String safestQuestion = questionList.get(0);
         String riskiestQuestion = questionList.get(0);

         // track the smallest difference between yes and no answers to question (Safest question)
         int minDiff = Integer.MAX_VALUE;
        
         // track the biggest difference between yes and no answers to question to question (Riskest question)
         int maxDiff = Integer.MIN_VALUE;

        // outer loop for every question within the questionList
        for (String question : questionList){
            int yesCount = 0;
         // for loop runs for all charecters that are remaining in the remainingCharacters array list
         for (Character c : remainingCharacters) {
              
            if (c.isMatch(question) == true) {
               yesCount++;
            }
         }

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

    

    if (rand.nextInt(5) < 3) { // 0, 1 and 2 = safest
        return safestQuestion;
    } 
    else{ // otherwise pick riskest question
        return riskiestQuestion;
    }

}
}
