import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class holds a static method that is the method that gets run
 * every time the entire program runs.
 * 
 * @author Snow Shiosaki, Will Lachance
 * @version 2026-22-03
 */
public class Controller {
    /**
     * This method is the method that gets run when the program runs.
     */
    public static void main(String[] args) {
        // Filling basic variables
        Scanner sc = new Scanner(System.in);
        Game game;
        ArrayList<PlayerCharacter> script = null;

        // TODO fill out these messages
        System.out.println(""); // Welcome statement

        // This do-while loop will keep looping until the user chooses a script to play
        do {
            System.out.println("Which script would you like to play?");
            System.out.println("1. Trouble Brewing");
            // System.out.println("2. Bad Moon Rising");
            // System.out.println("3. Sects and Violets");
            // System.out.println("4. Custom Script");
            System.out.println("Type 0 to cancel.");
            int choice = sc.nextInt();
            switch(choice) {
                case 1:
                    // TODO trouble brewing
                    break;
                case 0:
                    System.out.println("Cancelling game...");
                    return;
                default:
                    System.out.println("Please enter a valid choice.");
                    break;
            }
        } while(script == null);
        
        // This while loop will keep looping until the user selects a valid number of players
        System.out.println("How many players are playing? The minimum player count is 5, and the maximum is 15.");
        System.out.println("Type 0 to cancel.");
        int numPlayers = sc.nextInt();
        while(numPlayers > 15 || numPlayers < 5) {
            if(numPlayers == 0) {
                System.out.println("Cancelling game...");
                return;
            } else if(numPlayers > 15) {
                System.out.println("Too many players are playing!"); // add "Please consider adding Travellers." when implemented (future scalability)
            } else {
                System.out.println("You don't have enough players for a game!");
            }
            System.out.println("How many players are playing? The minimum player count is 5, and the maximum is 15.");
            System.out.println("Type 0 to cancel.");
            numPlayers = sc.nextInt();
        }
        game = new Game(numPlayers);

        // TODO choosing characters that are in play (requires PlayerCharacter)
            // choice between randomly generating and choosing characters

        // TODO filling in the player information (requires PlayerCharacter, Player)
            // choice between rigging the bag and randomly giving characters?
    }

    /**
     * This method returns an array of the characters on the script Trouble Brewing.
     * 
     * @return  scriptCharacters    array of characters on the script
     */
    public static ArrayList<PlayerCharacter> scriptTroubleBrewing() {
        // TODO scripts
        return null; // to satisfy the computer while I wait for PlayerCharacter to finish
    }
}
