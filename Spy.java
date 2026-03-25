import java.util.Random;
import java.util.Scanner;

public class Spy extends PlayerCharacter {

    public Spy(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        picksPlayer = false;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand, boolean badAbility){
        if (canAct == true){
            System.out.println("Show the Spy the Grimoire.");
        }
    }
}
