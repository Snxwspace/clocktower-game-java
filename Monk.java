public class Monk extends PlayerCharacter {
    private boolean canAct;
    private boolean picksPlayer;
    private char charType;
    private char baseAlignment;
    private String name;

    public Monk(char charType, String name) {
        super(charType, name);
        baseAlignment = 'g';
        canAct = false;
        picksPlayer = true;
    }
    
    public void useAbility(Player guy){
        guy.setProtected(true);
    }
    
    public boolean getCanAct() { return canAct; } 
}