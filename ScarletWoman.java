public class ScarletWoman extends PlayerCharacter {
    
    public ScarletWoman(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        picksPlayer = false;
    }
}
