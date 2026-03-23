public class Imp extends PlayerCharacter {
    private boolean canAct;
    private boolean picksPlayer;
    private char charType;
    private char baseAlignment;
    private String name;

    public Imp(char charType, String name) {
        super(charType, name);
        baseAlignment = 'e';
        canAct = false;
        picksPlayer = true;
    }
    
    public void useAbility(Player guy){
        if (guy.getProtected() == false && guy.getIsAlive() == true){
            guy.kill();
        }
    }
    
    public boolean getCanAct() { return canAct; } 
}