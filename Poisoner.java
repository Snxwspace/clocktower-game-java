public class Poisoner extends PlayerCharacter {
    private boolean canAct;
    private boolean picksPlayer;
    private char charType;
    private char baseAlignment;
    private String name;

    public Poisoner(char charType, String name) {
        super(charType, name);
        baseAlignment = 'e';
        canAct = true;
        picksPlayer = true;
    }
    
    public void useAbility(Player guy){
        guy.setPoisoned(true);
    }
    
    public boolean getCanAct() { return canAct; } 
}