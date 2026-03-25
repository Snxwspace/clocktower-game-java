import java.util.Random;
import java.util.Scanner;

public class Virgin extends PlayerCharacter {
    public Virgin(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        picksPlayer = false;
    }

    @Override
    public void useAbility(Scanner sc, Game game, Random rand, boolean badAbility) {
        
    }
}
