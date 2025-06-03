public class ComputerPlayer extends Players {

    private String playerName = "Computer";
    private Board board; // Computer will choose new out 

    public ComputerPlayer() {
        super("Computer"); // Call the appropriate Players constructor with required arguments
    }
    
    public String askQuestion(String question) {
        // Placeholder implementation
        return "Computer is making a guess...";
    }

    public boolean makeGuess(String guess) {
        // Placeholder implementation
        return false;
    }
}