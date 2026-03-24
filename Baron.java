public class Baron extends PlayerCharacter {
    
    public Baron(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        picksPlayer = false;
    }
}
