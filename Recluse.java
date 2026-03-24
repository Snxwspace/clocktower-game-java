public class Recluse extends PlayerCharacter {
    
    public Recluse(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        picksPlayer = false;
    }
}
