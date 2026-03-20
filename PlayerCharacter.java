public class PlayerCharacter {
    private final char characterType; // 't' for townsfolk, 'o' for outsider, 'm' for minion, 'd' for demon
    private final char baseAlignment; // 'g' for good, 'e' for evil
    private final String name;
    
    private final boolean actsFirstNight;
    private final boolean actsOtherNights;

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

    // for the passives
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

    public char getCharacterType() { return characterType; }
    public char getBaseAlignemnt() { return baseAlignment; }
    public String getName() { return name; } 
}
