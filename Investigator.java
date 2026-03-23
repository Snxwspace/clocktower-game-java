public class Investigator extends PlayerCharacter {
    private boolean canAct;
    private boolean picksPlayer;
    private char charType;
    private char baseAlignment;
    private String name;
    
    public Investigator(char charType, String name) {
        super(charType, name);
        baseAlignment = 'g';
        canAct = true;
        picksPlayer = false;
    }
    
    public void useAbility(Player guy, Player guy2){
        
    }
}
