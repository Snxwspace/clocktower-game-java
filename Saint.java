public class Saint extends PlayerCharacter {
    
    public Saint(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        picksPlayer = false;
    }
}
