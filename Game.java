import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * An instance of this class represents one game of Blood on the Clocktower.
 * An array of type Player is held, where each Player is a player in the game, sat in a clockwise order.
 * An instance also holds an object to help with randomly generating things.
 * 
 * @author Snow Shiosaki, Will Lachance
 * @version 2026-22-03
 */
public class Game {
    private Player[] players;
    private Random rand = new Random();
    private boolean isNight;
    private boolean isFirstNight;
    private Script currentScript;
    private HashMap<Player,Integer> votesPerNomination;

    /**
     * Constructor for Game objects.
     * 
     * @param   numPlayers  number of players that will be playing
     */
    public Game(int numPlayers, Script script) {
        players = new Player[numPlayers];
        currentScript = script;
        isNight = true;
        isFirstNight = true;
        votesPerNomination = new HashMap<>(numPlayers);
    }

    /**
     * This method loops through the (likely empty) array of Players and fills it with Player objects.
     * 
     * @param   sc  Scanner object to read inputs from the console
     * @param   availableCharacters ArrayList of characters that are available to be chosen for players
     * @param   isFullRandom    Boolean representing if assigning characters is fully random
     */
    public void fillPlayerArray(Scanner sc, ArrayList<PlayerCharacter> availableCharacters, boolean isFullRandom) {
        sc.nextLine();  // clear buffer
        System.out.println(
            "Please answer the questions about each player going in clockwise order. " +
            "It does not matter which player you start with."
        );
        for(int i = 0; i < players.length; i++) {
            System.out.print("Press enter to continue.");
            sc.nextLine();
            System.out.print("Player " + (i+1) + " Name: ");
            String name = sc.nextLine();
            // probably more things to set up Player objects
            if(!isFullRandom) {
                players[i] = rigBag(sc, availableCharacters, name);
            } else {
                players[i] = new Player(name, availableCharacters, rand);
            }
            availableCharacters.remove(players[i].getCharacter());
        }
    }

    /**
     * This method goes through the bag of available characters and lets the user choose how to determine the role
     * of the specific player.
     * 
     * @param   sc  Scanner object to read inputs from the console
     * @param   availableCharacters ArrayList of characters that are available to be chosen for players
     * @param   playerName  String that holds the name of the player whose role is currently being assigned
     * @return  player  Initialized Player object with the chosen role
     */
    private Player rigBag(Scanner sc, ArrayList<PlayerCharacter> availableCharacters, String playerName) {
        int choice;
        Player player = null;
        while(player == null) {
            System.out.println("How would you like to modify what character " + playerName + " gets?");
            System.out.println("1. Randomly choose an available character");
            System.out.println("2. Manually select an available character");
            System.out.println("3. Remove available characters, then randomly choose from what's left");
            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    player = new Player(playerName, availableCharacters, rand);
                    break;
                case 2:
                    int characterChoice;
                    do {
                        System.out.println("Which character would you like " + playerName + " to get?");
                        for(int i = 0; i < availableCharacters.size(); i++) {
                            System.out.println(i+1 + ". " + availableCharacters.get(i).getName());
                        }
                        System.out.println("Type 0 to cancel.");
                        characterChoice = sc.nextInt();
                    } while(characterChoice < 0 || characterChoice >= availableCharacters.size());
                    if(characterChoice != 0) {
                        player = new Player(playerName, availableCharacters.get(characterChoice-1));
                    }
                    break;
                case 3:
                    // i dont wanna see availableCharacters being messed with LOL
                    ArrayList<PlayerCharacter> newBag = new ArrayList<>(availableCharacters);
                    // maybe sort the newBag alphabetically?
                    Iterator<PlayerCharacter> it = newBag.iterator(); //makes cycling through the list easier

                    sc.nextLine(); // clear buffer
                    boolean isFinished = false;
                    boolean cancelled = false;
                    do {
                        System.out.println("We are about to loop through every single available character, and you will decide whether that character should be removed from the list.");
                        System.out.println("Type \'y\' to remove the option, \'n\' to keep the option, \'e\' to keep the option and end, and \'c\' to cancel.");
                        System.out.println("This will only affect the random character generation for " + playerName + ".");
                        
                        printCharacters(newBag);

                        while(it.hasNext()) {
                            boolean isValid = false;
                            PlayerCharacter character = it.next();

                            
                            do {
                                System.out.print("Would you like to take " + character.getName() + " out of the bag? (y/n/e/c)");
                                String response = sc.nextLine();

                                switch(response.toLowerCase()) {
                                    case "y":
                                        System.out.println("Removing " + character.getName() + "...");
                                        it.remove();
                                        // no break, the code follows through to do run what's in case 'n'
                                    case "n":
                                        isValid = true;
                                        break;
                                    
                                    case "c":
                                        System.out.println("Cancelling...");
                                        cancelled = true;
                                        isFinished = true;
                                        isValid = true;
                                        break;
                                    
                                    case "e":
                                        System.out.println("Exiting...");
                                        isFinished = true;
                                        isValid = true;
                                        break;
                                    
                                    default:
                                        System.out.println("Invalid response. Please try again.");
                                        isValid = false;
                                        break;
                                }
                            } while(!isValid);
                        }

                        if(!newBag.isEmpty()) {
                            printCharacters(newBag);
                            String response;
                            boolean isValid;
                            do {
                                System.out.print("Would you like to go through the list again to remove more characters, or would you like to [r]estart the process? (y/n/r) ");
                                response = sc.nextLine();
                                switch (response.toLowerCase()) {
                                    case "n":
                                        System.out.println("Finalizing settings for " + playerName + "...");
                                        isFinished = true;
                                        // No break, the code immediately goes through and runs what it would've in case 'y'  
                                    case "y":
                                        isValid = true;
                                        break;
                                    case "r":
                                        isValid = true;
                                        System.out.println("Restarting bag modifications...");
                                        // reestablishing old variables
                                        newBag = new ArrayList<>(availableCharacters);
                                        it = newBag.iterator();
                                        break;
                                    default:
                                        System.out.println("Invalid response. Use \'y\', \'n\', or \'r\' to indicate what you would like.");
                                        isValid = false;
                                }
                            } while (!isValid);
                        } else {
                            String response;
                            System.out.println("The bag is currently empty. Would you like to [r]estart creating the bag or [c]ancel the process?");
                            response = sc.nextLine();
                            while (!response.equalsIgnoreCase("r") && !response.equalsIgnoreCase("c")) {
                                System.out.println("Invalid response. Type \'r\' or \'c\' to make a decision.");
                                System.out.println("The bag is currently empty. Would you like to [r]estart creating the bag or [c]ancel the process?");
                                response = sc.nextLine();
                            }

                            switch(response.toLowerCase()) {
                                case "r":
                                    System.out.println("Restarting bag modifications...");
                                    // resetting the bag and iterator to the inital state
                                    newBag = new ArrayList<>(availableCharacters);
                                    it = newBag.iterator();
                                    break;
                                case "c":
                                    System.out.println("Cancelling bag modifications...");
                                    cancelled = true;
                                    isFinished = true;
                                    break;
                                default:
                                    throw new AssertionError("How did you even get here this wasn't supposed to happen");
                            }
                        }
                    } while(!isFinished);
                    
                    if(!cancelled) {
                        player = new Player(playerName, newBag, rand);
                    }
                    break;

                default:
                    System.out.println("Please choose a valid option.");
                    break;
            }
        }
        return player;
    }
    
    public void runGame(Scanner sc, ArrayList<PlayerCharacter> charactersInPlay) {
        boolean gameWon = false;
        int randomization = 0;
        
        do{
            System.out.println("Would you like the bag to be fully randomized, or choose things yourself (1: Randomized, 2: Choose)");
            randomization = sc.nextInt();
            switch(randomization){
                case 1:
                    fillPlayerArray(sc, charactersInPlay, true);
                    break;
                case 2:
                    fillPlayerArray(sc, charactersInPlay, false);
                    break;
            }
        }while(randomization != 1 && randomization != 2);
        
        do{
            night(sc);
            dawn(sc);
            day(sc);
            dusk(sc);
        }while(gameWon == false);
    }
    
    /**
     * Prints the current list of available characters to be chosen on one line.
     * 
     * @param   availableCharacters ArrayList of characters that are available to be chosen for players.
     */
    private void printCharacters(ArrayList<PlayerCharacter> availableCharacters) {
        System.out.print("The current available characters are: ");
        for(PlayerCharacter p : availableCharacters) {
            System.out.print(p.getName());
            if(!p.equals(availableCharacters.getLast())) {   // checking if p isn't the last element in the list
                System.out.print(", ");       // add a comma and a space if p isn't the last element
            } else {                            // otherwise...
                System.out.println(".");        // add a period and end the line
            }
        }
    }

    public Player[] getPlayers() { return players; }
    public Script getScript(){ return currentScript; }

    private void night(Scanner sc) {
        if (isFirstNight){
            if(players.length >= 7) {
                System.out.println("Awaken the Minion(s), show them who the Demon is, and put them to sleep");    
                System.out.println("Awaken the Demon, show them who the Minion(s) are, and put them to sleep");
                //TODO: Demon Bluffs
            }
            
            for(PlayerCharacter character : currentScript.getFirstNightOrder()) {
                for(Player player : players) {
                    if (player.getCharacter().getName().equals(character.getName())){
                        System.out.println("\n" + player.getName() + "'s turn as the " + player.getCharacter().getName() + ":");
                        boolean badAbility = false;
                        if (player.getPoisoned() == true){
                            System.out.println("They are poisoned, and therefore, you may lie to them, or their ability may not work.");
                            badAbility = true;
                        } 
                        player.getCharacter().useAbility(sc, this, rand, badAbility);
                        System.out.print("Press enter to continue.");
                        sc.nextLine();
                    }
                }
            }
            isFirstNight = false;
        } else {
            for(PlayerCharacter character : currentScript.getOtherNightOrder()) {
                for(Player player : players) {
                    if (player.getCharacter().getName().equals(character.getName())){
                        System.out.println("\n" + player.getName() + "'s turn as the " + player.getCharacter().getName() + ":");
                        boolean badAbility = false;
                        if (player.getPoisoned() == true){
                            System.out.println("They are poisoned, and therefore, you may lie to them, or their ability may not work.");
                            badAbility = true;
                        }
                        player.getCharacter().useAbility(sc, this, rand, badAbility);
                    }
                }
            }
        }
    }

    private void dawn(Scanner sc) {
        for(Player player : players) {
            player.daybreak(sc, this, rand);
        }
        votesPerNomination.clear();
    }

    private void dusk(Scanner sc) {
        for(Player player : players) {
            player.nightfall(sc, this, rand);
        }
    }

    private void day(Scanner sc) {
        System.out.print("It is now day. ");
        int choice = 0;
        while(choice != 3) {
            System.out.println("What would you like to do?");
            System.out.println("1. Make a nomination");
            System.out.println("2. A slayer shot is declared");
            System.out.println("3. End the day");
            choice = sc.nextInt();
            switch(choice) {
                case 1:
                    boolean isExecuted = makeNomination(sc);
                    if(isExecuted) return;
                    break;
                
                case 2:
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Please choose an available option.");
            }
        }
    }

    private boolean makeNomination(Scanner sc) {
        int choice;
        boolean isFinal = false;
        Player nominator = null;
        Player nominee = null;
        while(!isFinal) {
            do { 
                System.out.println("Which player is making a nomination?");
                for (int i = 0; i < players.length; i++) {
                    System.out.println((i+1) + ". " + players[i]);
                }
                choice = sc.nextInt();

                try {
                    nominator = players[choice-1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid option. Please choose a number from the list.");
                }
            } while (nominator != null);

            do { 
                System.out.println("Which player is " + nominator + " nominating?");
                for (int i = 0; i < players.length; i++) {
                    System.out.println((i+1) + ". " + players[i]);
                }
                choice = sc.nextInt();

                try {
                    nominator = players[choice-1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid option. Please choose a number from the list.");
                }
            } while (nominee != null);

            sc.nextLine();
            String confirmation;
            do { 
                System.out.print(nominator + " is nominating " + nominee + ". Is that correct? (y/n) ");
                confirmation = sc.nextLine();
            } while (!confirmation.toLowerCase().equals("y") && !confirmation.toLowerCase().equals("n"));

            if(confirmation.toLowerCase().equals("y")) isFinal = true;
        }

        if(nominee.getCharacter().getName().equals("Virgin")) {
            // TODO virgin execution
            return true;
        }


        nominator.afterNomination();
        nominee.afterNominated();
        return false;
    }

    private int tallyVotes(Scanner sc) {

    }
}