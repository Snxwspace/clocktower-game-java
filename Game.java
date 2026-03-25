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
    private Player lastExecuted;

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
        lastExecuted = null;
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
                System.out.println("Awaken the Demon, show them who the Minion(s) are.");
                System.out.println("Show the Demon three characters that aren't in play, and put them to sleep");
            }
            
            for(PlayerCharacter character : currentScript.getFirstNightOrder()) {
                boolean preventDuplicateAbility = false;
                for(Player player : players) {
                    if (player.getCharacter().getName().equals(character.getName()) && player.getCharacter().getCanAct() && !preventDuplicateAbility) {
                        System.out.println("\n" + player.getName() + "'s turn as the " + player.getCharacter().getName() + ":");
                        boolean badAbility = false;
                        if (player.getPoisoned() == true) {
                            System.out.println("They are poisoned, and therefore, you may lie to them, or their ability may not work.");
                            badAbility = true;
                        } 
                        player.getCharacter().useAbility(sc, this, rand, badAbility);
                        System.out.print("Press enter to continue.");
                        sc.nextLine();

                        // i love hardcoding random edge cases asldkjgaotnoer
                        if(character.getName().equals("Imp")) {
                            preventDuplicateAbility = false;
                        }
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
        Player onBlockPlayer = null;
        boolean isVoteTied = false;
        int alivePlayers = 0;
        for(Player player : players) {
            if(player.getIsAlive()) alivePlayers++;
        }
        for(Player nominee : votesPerNomination.keySet()) {
            if(onBlockPlayer == null) onBlockPlayer = nominee;
            else if(votesPerNomination.get(nominee) > votesPerNomination.get(onBlockPlayer)) {
                onBlockPlayer = nominee;
                isVoteTied = false;
            } else if(votesPerNomination.get(nominee).equals(votesPerNomination.get(onBlockPlayer))) {
                isVoteTied = true;
            } else if(votesPerNomination.get(nominee) == -1) {
                onBlockPlayer = nominee;
                break; // instant execution abilities; don't continue looping
            }
        }

        // TODO saint execution ending
            // use lastExecuted probably
        if(onBlockPlayer == null) {
            System.out.println("No one was nominated!");
        } else if(votesPerNomination.get(onBlockPlayer) == -1) {
            System.out.println(onBlockPlayer.getName() + " is immediately executed.");
            System.out.println("The day immediately ends.");
            lastExecuted = onBlockPlayer;
            onBlockPlayer.kill();
        } else if(votesPerNomination.get(onBlockPlayer) < Math.ceilDiv(alivePlayers, 2)) {
            System.out.println("There were not enough votes to execute someone!");
        } else if(isVoteTied) {
            System.out.println("The vote was tied-- no executions tonight.");
        } else {
            System.out.println("With " + votesPerNomination.get(onBlockPlayer) + " votes, " + onBlockPlayer.getName() + " is executed!");
            lastExecuted = onBlockPlayer;
            onBlockPlayer.kill();
        }

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
                    Player slayer = null;
                    do { 
                        System.out.println("Which player is claiming to make a Slayer shot? Type 0 to cancel.");
                        for (int i = 0; i < players.length; i++) {
                            System.out.println((i+1) + ". " + players[i].getName());
                        }
                        int slayerChoice = sc.nextInt();

                        try {
                            slayer = players[slayerChoice-1];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            if(choice == 0) {
                                System.out.println("Cancelling slayer shot...");
                                break;
                            }
                            System.out.println("Invalid option. Please choose a number from the list.");
                        }
                    } while (slayer == null);

                    if(slayer != null) {
                        if(slayer.getCharacter().getName().equals("Slayer")) {
                            slayer.getCharacter().useAbility(sc, this, rand, slayer.getPoisoned());
                        } else {
                            System.out.println("Pretend that this player is actually the slayer. Ask them who they would like to shoot, and announce that nothing happened.");
                        }
                    }
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
                System.out.println("Which player is making a nomination? Type 0 to cancel.");
                for (int i = 0; i < players.length; i++) {
                    System.out.println((i+1) + ". " + players[i].getName());
                }
                choice = sc.nextInt();

                try {
                    if(players[choice-1].getCanNominate()) {
                        nominator = players[choice-1];
                    } else {
                        System.out.println("That player can't nominate!");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    if(choice == 0) {
                        System.out.println("Cancelling nomination...");
                        return false;
                    }
                    System.out.println("Invalid option. Please choose a number from the list.");
                }
            } while (nominator == null);

            do { 
                System.out.println("Which player is " + nominator + " nominating? Type 0 to cancel.");
                for (int i = 0; i < players.length; i++) {
                    System.out.println((i+1) + ". " + players[i].getName());
                }
                choice = sc.nextInt();

                try {
                    if(players[choice-1].getCanBeNominated()) {
                        nominee = players[choice-1];
                    } else {
                        System.out.println("That player can't be nominated!");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    if(choice == 0) {
                        System.out.println("Cancelling nomination...");
                        return false;
                    }
                    System.out.println("Invalid option. Please choose a number from the list.");
                }
            } while (nominee == null);

            sc.nextLine();
            String confirmation;
            do { 
                System.out.print(nominator + " is nominating " + nominee + ". Is that correct? (y/n) ");
                confirmation = sc.nextLine();
            } while (!confirmation.toLowerCase().equals("y") && !confirmation.toLowerCase().equals("n"));

            if(confirmation.toLowerCase().equals("y")) isFinal = true;
        }

        if(nominee.getCharacter().getName().equals("Virgin")) {
            if(nominator.getCharacter().getCharacterType() == 't' && !nominee.getPoisoned() && nominee.getCharacter().getCanAct()) {
                votesPerNomination.put(nominator, -1);
                return true;
            } else if (nominator.getCharacter().getName().equals("Spy") && nominee.getCharacter().getCanAct() && 
                       !nominee.getPoisoned() && !nominator.getPoisoned()) {
                boolean spyProc = false;
                boolean validChoice = false;
                while(validChoice) {
                    System.out.println("Should the Spy be executed by the Empath ability? (y/n) ");
                    String spyProcChoice = sc.nextLine();
                    switch(spyProcChoice.toLowerCase()){
                        case "y":
                            spyProc = true;
                        case "n": 
                            validChoice = true;
                        default:
                            break;
                    }
                }
                if(spyProc) {
                    votesPerNomination.put(nominator, -1);
                    return true;
                }
            }
        }

        System.out.print("There has been a nomination on " + nominee.getName() + ". Allow the prosecution to speak, then allow the defense to speak. Press enter to continue and tally the vote.");
        sc.nextLine();
        votesPerNomination.put(nominee, tallyVotes(sc, nominee));

        nominator.afterNomination();
        nominee.afterNominated();
        return false;
    }

    private int tallyVotes(Scanner sc, Player nominee) {
        // tally required votes
        int alivePlayers = 0;
        for(Player player : players) {
            if(player.getIsAlive()) alivePlayers++;
        }
        int reqVotes = Math.ceilDiv(alivePlayers, 2);
        boolean isExecutionPrimed = false;
        for(int voteCount : votesPerNomination.values()) {
            if(voteCount >= reqVotes) {
                reqVotes = voteCount+1;
                isExecutionPrimed = true;
            }
        }
        System.out.println("Call the vote for " + nominee.getName() + ".");
        if(isExecutionPrimed) {
            System.out.print((reqVotes-1) + " votes are needed to tie the vote, and ");
            System.out.println(reqVotes + " votes are needed to put them on the block.");
        } else {
            System.out.println(reqVotes + " votes are needed to put them on the bock.");
        }

        int totalVotes = 0;
        for (Player player : players) {
            String choice;
            do {
                if(player.canVote()) {
                    System.out.print("Is " + player.getName() + " going to vote for " + nominee.getName() + "?");
                    if(!player.getIsAlive()) System.out.print(" This would be using their ghost vote.");
                    System.out.print(" (y/n) ");
                    choice = sc.nextLine();
                } else {
                    choice = "n";
                }
            } while(!choice.toLowerCase().equals("y") && !choice.toLowerCase().equals("n"));
            if(choice.toLowerCase().equals("y")) {
                totalVotes++;
                if(!player.getIsAlive()) player.useGhostVote();
            }
        }

        System.out.print("There were a total of " + totalVotes + " votes. ");
        if(totalVotes >= reqVotes) System.out.print(nominee.getName() + " is now up for execution. ");
        System.out.print("Press enter to continue. ");
        sc.nextLine();
        return totalVotes;
    }
    
    public Player getLastExecuted() { return lastExecuted; }

    private boolean checkAliveDemon(Scanner sc) {
        // TODO use this to check end game conditions
        Player scarletWoman = null;
        for (Player player : players) {
            if(player.getCharacter().getCharacterType() == 'd' && player.getIsAlive()) {
                return true;
            } else if(player.getCharacter().getName().equals("Scarlet Woman")) {
                scarletWoman = player;
            }
        }
        if(scarletWoman != null) {
            scarletWoman.getCharacter().useAbility(sc, this, rand, scarletWoman.getPoisoned());
            // check again to see if scarlet woman became demon
            if(scarletWoman.getCharacter().getCharacterType() == 'd' && scarletWoman.getIsAlive()) {
                return true;
            }
        }
        
        return false;
    }
}