import java.util.Random;
import java.util.Scanner;

public class Imp extends PlayerCharacter {

    public Imp(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        super.picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Player[] players, Random rand){
        
        
        
        // if (guy.getProtected() == false && guy.getIsAlive() == true){
        //     guy.kill();
        // }
    }
    
    public boolean getCanAct() { return canAct; } 
}