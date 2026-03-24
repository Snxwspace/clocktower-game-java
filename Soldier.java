public class Soldier extends PlayerCharacter {
    
    public Soldier(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        picksPlayer = false;
    }
}