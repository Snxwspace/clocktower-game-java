public class Mayor extends PlayerCharacter {
    
    public Mayor(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        picksPlayer = false;
    }
}
