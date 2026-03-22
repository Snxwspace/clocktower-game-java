/**
 * An instance of this class represents one character, or role, that a player can be.
 * It holds the type of character they are in a char, 't' meaning Townsfolk, 
 * 'o' meaning Outsider, 'm' meaning Minion, and 'd' meaning Demon.
 * It also holds the name of the character, and booleans for if the character would wake 
 * up on the first night and if they would wake up on other night.
 */
public class PlayerCharacter {
    private final char characterType; // 't' for townsfolk, 'o' for outsider, 'm' for minion, 'd' for demon
    private final char baseAlignment; // 'g' for good, 'e' for evil
    private final String name;
    
    private final boolean actsFirstNight;
    private final boolean actsOtherNights;

    /**
     * Constructor for the PlayerCharacter class. This will be used if the character will wake up at 
     * some point throughout the night due to their ability.
     * 
     * @param   charType    The type of character. 't' for Townsfolk, 'o' for Outsiders, 'm' for Minions, and 'd' for Demons.
     * @param   name    The name of the character.
     * @param   actsFirstNight  A boolean value holding whether or not they may wake up due to their ability on the first night.
     * @param   actsOtherNights A boolean value holding whether or not they may wake up due to their ability on other nights.
     */
    public PlayerCharacter(char charType, String name, boolean actsFirstNight, boolean actsOtherNights) {
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
                throw new IllegalArgumentException("Invalid character type."); // yeah ik travellers would throw this but they don't exist they're fake characters actually
        }
        this.name = name;

        this.actsFirstNight = actsFirstNight;
        this.actsOtherNights = actsOtherNights;
    }

    /**
     * Constructor for the PlayerCharacter class. This will be used if the character will not wake up
     * due to their ability at any point throughout the game.
     * 
     * @param   charType    The type of character. 't' for Townsfolk, 'o' for Outsiders, 'm' for Minions, and 'd' for Demons.
     * @param   name    The name of the character.
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
                throw new IllegalArgumentException("Invalid character type."); // yeah ik travellers would throw this but they don't exist they're fake characters actually
        }
        this.name = name;

        this.actsFirstNight = false;
        this.actsOtherNights = false;
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
    public char getBaseAlignemnt() { return baseAlignment; }

    /**
     * Gets the name of the character.
     * @return  name    The name of the character.
     */
    public String getName() { return name; } 
}
