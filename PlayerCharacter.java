import java.util.Random;
import java.util.Scanner;

/**
 * An instance of this class represents one character, or role, that a player can be.
 * It holds the type of character they are in a char, 't' meaning Townsfolk, 
 * 'o' meaning Outsider, 'm' meaning Minion, and 'd' meaning Demon.
 * It also holds the name of the character, and booleans for if the character would wake 
 * up on the first night and if they would wake up on other night.
 */
public class PlayerCharacter {
    protected final char characterType; // 't' for townsfolk, 'o' for outsider, 'm' for minion, 'd' for demon
    protected char baseAlignment; 
    protected boolean canAct; //declares if someone can take an action
    protected boolean picksPlayer; //declares if someone's role requires them to picj a player
    protected boolean guarded;
    protected String name;

    /**
     * Constructor for the PlayerCharacter class. This will be used if the character will wake up at 
     * some point throughout the night due to their ability.
     * 
     * @param   charType    The type of character. 't' for Townsfolk, 'o' for Outsiders, 'm' for Minions, and 'd' for Demons.
     * @param   canAct      Clarifies if a character can act at any given point in the game
     * @param   picksPlayer Clarifies if a character picks a player as part of their action
     */
    public PlayerCharacter(char charType, String name) {
        this.characterType = charType;
        switch (charType) {
            case 't':
            case 'o':
                this.baseAlignment = 'g';
                break;
            case 'm':
            case 'd':
                this.baseAlignment = 'e';
                break;
            default:
                throw new IllegalArgumentException("Invalid character type.");
        }
        this.name = name;
    }

    /**
     * Gets the type of character the PlayerCharacter is.
     * @return  characterType   A char object representing the type of character. 
     *      't' for Townsfolk, 'o' for Outsiders, 'm' for Minions, and 'd' for Demons
     */
    public char getCharacterType() { return characterType; }

    /**
     * Gets the alignment the character is by default.
     * @return  baseAlignment   The alignment of the character by default.
     *      'g' for good-aligned, and 'e' for evil-aligned.
     */
    public char getBaseAlignment() { return baseAlignment; }
    
    /**
     * Gets the name of the character.
     * @return  name    The name of the character.
     */
    public String getName() { return name; } 
    
    /**
     * 
     * @return  
     */
    public boolean getCanAct() { return canAct; } 
    
    public void setCanAct(boolean a) { canAct = a; }

    public void useAbility(Scanner sc, Game game, Random rand) {}
}
