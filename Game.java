import java.util.Scanner;

public class Game {
    private Player[] players;

    public Game(int numPlayers) {
        players = new Player[numPlayers];
    }

    public void fillPlayerArray(Scanner sc) {
        sc.nextLine();  // clear buffer
        System.out.println("Please answer the questions about each player going in clockwise order. It doesn't matter which player you start with.");
        for(int i = 0; i < players.length; i++) {
            System.out.println("Player " + i+1 + " Name: ");
            String name = sc.nextLine();
            // probably more things to instantiate Player objects
            players[i] = new Player(name);
        }
    }
}
