import java.util.ArrayList;
import java.util.Random;

/**
 * An instance of this class represents one player in a game of Blood on the Clocktower.
 * It holds whether or not the player is alive, has their ghost vote, is able to nominate this day, and is able to be nominated this day.
 * It also holds the name of the player, their current alignment, and what character they currently are.
 */
public class Player {
    private boolean isAlive;
    private boolean hasGhostVote;
    private char alignment; // 'g' for good, 'e' for evil
    private PlayerCharacter character;
    private String name;

    private boolean canNominate;
    private boolean canBeNominated;

    /**
     * Constructor for Player objects that are given a specific character.
     * 
     * @param   name    Name of the player
     * @param   character   The character the player has been assigned
     */
    public Player(String name, PlayerCharacter character) {
        isAlive = true;
        hasGhostVote = true;
        canNominate = true;
        canBeNominated = true;
        this.name = name;
        this.character = character;
        this.alignment = character.getBaseAlignemnt();
    }

    /**
     * Constructor for Player objects that are not given a specific character.
     * 
     * @param   name    Name of the player
     * @param   characterOptions    ArrayList of characters that could be assigned to the player
     * @param   rand    Random object to determine which character is given to the player
     */
    public Player(String name, ArrayList<PlayerCharacter> characterOptions, Random rand) {
        isAlive = true;
        hasGhostVote = true;
        canNominate = true;
        canBeNominated = true;
        this.name = name;
        
        int i = rand.nextInt(0, characterOptions.size());
        this.character = characterOptions.get(i);
        
        this.alignment = character.getBaseAlignemnt();
    }

    /**
     * Gets whether the player is currently alive.
     * @return  isAlive Boolean value for whether the character is alive
     */
    public boolean getIsAlive() { return isAlive; }
    
    /**
     * Gets the current alignment of the player
     * @return  alignment   char primitive object representing the current alignment of the player. 'g' means good, 'e' means evil.
     */
    public char getAlignment() { return alignment; }

    /**
     * Gets the name of the player
     * @return  name    Name of the player
     */
    public String getName() { return name; }
    
    /**
     * Gets whether the player can nominate another player today
     * @return  canNominate Boolean value for whether the player can nominate today
     */
    public boolean getCanNominate() { return canNominate; }

    /**
     * Gets whether the player can be nominated by another player today
     * @return  canBeNominated  Boolean value for whether the player can be nominated today
     */
    public boolean getCanBeNominated() { return canBeNominated; }


    /**
     * Gets whether the player can vote on the current nomination.
     * @return  canVote Boolean value for whether the player can vote
     */
    public boolean canVote() {
        // if the player is alive, they can vote
        // if the player has a ghost vote, they can vote
        // otherwise, they cannot vote
        return (isAlive || hasGhostVote);
    }
    
    /**
     * Kills the player. If the player is already dead, nothing happens.
     */
    public void kill() {
        if(isAlive) {
            isAlive = false;
            hasGhostVote = true;
            canNominate = false;
        }
    }
    
    /**
     * Revives the player. If the player is already alive, nothing happens.
     */
    public void revive() {
        if(!isAlive) {
            isAlive = true;
            // restore character abilities
        }
    }

    /**
     * 
     */
    public void afterNominated() { // TODO better method name BEFORE WE START INTEGRATING THINGS
        canBeNominated = false;
    }

    /**
     * 
     */
    public void afterNomination() { // TODO better method name BEFORE WE START INTEGRATING THINGS
        canNominate = false;
    }

    /**
     * Resets everything that should be reset when the new day starts.
     */
    public void daybreak() {
        canBeNominated = true;
        if(isAlive) {
            canNominate = true;
        }
    }
}
