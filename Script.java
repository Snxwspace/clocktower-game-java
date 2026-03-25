import java.util.ArrayList;

public class Script {
    private ArrayList<PlayerCharacter> characters;
    private ArrayList<PlayerCharacter> townsfolk;
    private ArrayList<PlayerCharacter> outsiders;
    private ArrayList<PlayerCharacter> minions;
    private ArrayList<PlayerCharacter> demons;
    private ArrayList<PlayerCharacter> firstNightOrder;
    private ArrayList<PlayerCharacter> otherNightOrder;

    public Script() {
        characters = new ArrayList<>();
        firstNightOrder = new ArrayList<>();
        otherNightOrder = new ArrayList<>();
        
        townsfolk = new ArrayList<>();
        outsiders  = new ArrayList<>();
        minions = new ArrayList<>();
        demons = new ArrayList<>();
    }

    public void addNewCharacter(PlayerCharacter character, boolean canActFirstNight, boolean canActOtherNights) {
        characters.add(character);
        
        switch(character.getCharacterType()) {
            case 't':
                townsfolk.add(character);
                break;
            
            case 'o':
                outsiders.add(character);
                break;

            case 'm':
                minions.add(character);
                break;
            
            case 'd':
                demons.add(character);
                break;
        }

        if(canActFirstNight) firstNightOrder.add(character);
        if(canActOtherNights) otherNightOrder.add(character);
    }

    // getters bc idc
    public ArrayList<PlayerCharacter> getCharacters() { return characters; }
    public ArrayList<PlayerCharacter> getTownsfolk() { return townsfolk; }
    public ArrayList<PlayerCharacter> getOutsiders() { return outsiders; }
    public ArrayList<PlayerCharacter> getMinions() { return minions; }
    public ArrayList<PlayerCharacter> getDemons() { return demons; }
    public ArrayList<PlayerCharacter> getFirstNightOrder() { return firstNightOrder; }
    public ArrayList<PlayerCharacter> getOtherNightOrder() { return otherNightOrder; }
}
