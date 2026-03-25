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
        Script script = null;

        System.out.println("Welcome to Blood on the Clocktower!");

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
                    script = scriptTroubleBrewing();
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
        game = new Game(numPlayers, script);

        System.out.println("It's now time to determine what roles are in the game.");
        System.out.println("Would you like to: ");
        System.out.println("1. Create a list of roles in play");
        System.out.println("2. Use the pre-built list of roles");
        System.out.println("0. Cancel game");
        // chat we are not randomly generating this idc its too complex bc there are characters that change what's needed
        ArrayList<PlayerCharacter> charactersInPlay = new ArrayList<>(numPlayers);

        // determining number of character types in game
        int reqDemons = 1;
        int reqMinions;
        int reqOutsiders;
        if(numPlayers >= 7) {
            reqMinions = Math.floorDiv(numPlayers-1, 3)-1;
            reqOutsiders = numPlayers-1 % 3;
        } else {
            reqMinions = 1;
            reqOutsiders = numPlayers+1 % 3;
        }
        int reqTownsfolk = numPlayers-reqDemons-reqMinions-reqOutsiders;

        int roleListChoice = sc.nextInt();
        while(roleListChoice < 0 || roleListChoice > 2) {
            System.out.println("Invalid selection.");
            System.out.println("Would you like to: ");
            System.out.println("1. Create a list of roles in play");
            System.out.println("2. Use the pre-built list of roles");
            System.out.println("0. Cancel game");

            roleListChoice = sc.nextInt();
        }
        switch (roleListChoice) {
            case 1:
                int townsfolk = 0;
                int outsiders = 0;
                int minions = 0;
                int demons = 0;
                do {
                    System.out.println("What would you like to do?");
                    System.out.println("(" + townsfolk + "/" + reqTownsfolk + " Townsfolk)");
                    System.out.println("(" + outsiders + "/" + reqOutsiders + " Outsiders)");
                    System.out.println("(" + minions + "/" + reqMinions + " Minions)");
                    System.out.println("(" + demons + "/" + reqDemons + " Demons)");
                    System.out.println("1. Add a character");
                    System.out.println("2. Remove a character");
                    System.out.println("0. Cancel game");
                    int choice = sc.nextInt();
                    
                    switch(choice) {
                        case 1:
                            PlayerCharacter characterToAdd = choosePlayerCharacter(script.getCharacters(), sc);
                            switch(characterToAdd.getCharacterType()) {
                                case 't':
                                    townsfolk++;
                                    break;
                                case 'o':
                                    outsiders++;
                                    break;
                                case 'm':
                                    minions++;
                                    break;
                                case 'd':
                                    demons++;
                                    break;
                            }
                            charactersInPlay.add(characterToAdd);
                            break;
                        case 2:
                            PlayerCharacter characterToRemove = choosePlayerCharacter(charactersInPlay, sc);
                            switch(characterToRemove.getCharacterType()) {
                                case 't':
                                    townsfolk--;
                                    break;
                                case 'o':
                                    outsiders--;
                                    break;
                                case 'm':
                                    minions--;
                                    break;
                                case 'd':
                                    demons--;
                                    break;
                            }
                            charactersInPlay.remove(characterToRemove);
                            break;
                        case 0:
                            return;
                        default:
                            System.out.println("Invalid choice. Enter a number to make a decision.");
                    }
                } while (
                        charactersInPlay.size() != numPlayers &&
                        townsfolk == reqTownsfolk &&
                        outsiders == reqOutsiders &&
                        minions == reqMinions &&
                        demons == reqDemons
                        );  
                break;
            case 2:
                PlayerCharacter[] townsfolkArray = new PlayerCharacter[9];
                PlayerCharacter[] outsiderArray = new PlayerCharacter[2];
                PlayerCharacter[] minionArray = new PlayerCharacter[3];
                PlayerCharacter[] demonArray = new PlayerCharacter[1];

                ArrayList<PlayerCharacter> townsfolkList = script.getTownsfolk();
                townsfolkArray[0] = townsfolkList.get(1);   // Washerwoman
                townsfolkArray[1] = townsfolkList.get(0);   // Monk
                townsfolkArray[2] = townsfolkList.get(7);   // Empath
                townsfolkArray[3] = townsfolkList.get(3);   // Investigator
                townsfolkArray[4] = townsfolkList.get(10);  // Slayer
                townsfolkArray[5] = townsfolkList.get(5);   // Ravenkeeper
                townsfolkArray[6] = townsfolkList.get(12);  // Mayor
                townsfolkArray[7] = townsfolkList.get(6);   // Undertaker
                townsfolkArray[8] = townsfolkList.get(4);   // Chef
                for (int i = 0; i < reqTownsfolk; i++) {                
                    charactersInPlay.add(townsfolkArray[i]);
                }

                ArrayList<PlayerCharacter> outsiderList = script.getOutsiders();
                outsiderArray[0] = outsiderList.get(3); // Saint
                outsiderArray[1] = outsiderList.get(2); // Recluse
                for (int i = 0; i < reqOutsiders; i++) {                
                    charactersInPlay.add(outsiderArray[i]);
                }

                ArrayList<PlayerCharacter> minionList = script.getMinions();
                minionArray[0] = minionList.get(0); // Poisoner
                minionArray[1] = minionList.get(1); // Spy
                minionArray[2] = minionList.get(2); // Scarlet Woman
                for (int i = 0; i < reqMinions; i++) {                
                    charactersInPlay.add(minionArray[i]);
                }

                demonArray[0] = script.getDemons().get(0);  // Imp
                charactersInPlay.add(demonArray[0]);
                break;
            case 0:
                return;
            default:
                break;
        }

        // TODO filling in the player information (requires PlayerCharacter, Player)
        // choice between rigging the bag and randomly giving characters?
        
        game.runGame(sc, charactersInPlay);
    }

    public static PlayerCharacter choosePlayerCharacter(ArrayList<PlayerCharacter> choices, Scanner sc) {
        PlayerCharacter chosenCharacter = null;
        sc.nextLine(); // making sure to clear the buffer
        do {
            for(int i = 0; i < choices.size(); i++) {
                char type = choices.get(i).getCharacterType();
                String charType = "";
                if(type == 't') charType = "Townsfolk";
                else if(type == 'o') charType = "Outsider";
                else if(type == 'm') charType = "Minion";
                else if(type == 'd') charType = "Demon";
                System.out.println(i+1 + ". " + choices.get(i) + " (" + charType + ")");
            }
            System.out.println("Make sure you scroll up to view all options!");
            System.out.print("Character chosen: ");
            int choice = sc.nextInt();
            try {
                chosenCharacter = choices.get(choice-1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid option. Press enter to continue.");
                sc.nextInt();
            }
        } while(chosenCharacter == null);
        return chosenCharacter;
    }

    /**
     * This method returns an array of the characters on the script Trouble Brewing.
     * 
     * @return  scriptCharacters    array of characters on the script
     */
    public static Script scriptTroubleBrewing() {
        Script troubleBrewing = new Script();

        // Preventative roles
        troubleBrewing.addNewCharacter(new Poisoner('m', "Poisoner"), true, true);
        troubleBrewing.addNewCharacter(new Monk('t', "Monk"), false, true);

        // Misc. 1
        troubleBrewing.addNewCharacter(new Spy('m', "Spy"), true, true);
        troubleBrewing.addNewCharacter(new Passive('m', "Scarlet Woman"), false, false);
        troubleBrewing.addNewCharacter(new Butler('o', "Butler"), true, true);

        // Killing roles
        troubleBrewing.addNewCharacter(new Imp('d', "Imp"), false, true);

        // Informational roles
        troubleBrewing.addNewCharacter(new Washerwoman('t', "Washerwoman"), true, false);
        troubleBrewing.addNewCharacter(new Librarian('t', "Librarian"), true, false);
        troubleBrewing.addNewCharacter(new Investigator('t', "Investigator"), true, false);
        troubleBrewing.addNewCharacter(new Chef('t', "Chef"), true, false);
        troubleBrewing.addNewCharacter(new Ravenkeeper('t', "Ravenkeeper"), false, true);
        troubleBrewing.addNewCharacter(new Passive('t', "Undertaker"), false, true);
        troubleBrewing.addNewCharacter(new Empath('t', "Empath"), true, true);
        troubleBrewing.addNewCharacter(new FortuneTeller('t', "Fortune Teller"), true, true);

        // Misc. 2, Day roles, and passives
        troubleBrewing.addNewCharacter(new Passive('t', "Virgin"), false, false);
        troubleBrewing.addNewCharacter(new Slayer('t', "Slayer"), false, false);
        troubleBrewing.addNewCharacter(new Soldier('t', "Soldier"), false, false);
        troubleBrewing.addNewCharacter(new Passive('t', "Mayor"), false, false);
        troubleBrewing.addNewCharacter(new Passive('o', "Drunk"), false, false);
        troubleBrewing.addNewCharacter(new Recluse('o', "Recluse"), false, false);
        troubleBrewing.addNewCharacter(new Saint('o', "Saint"), false, false);
        troubleBrewing.addNewCharacter(new Baron('m', "Baron"), false, false);

        return troubleBrewing;
    }
}
