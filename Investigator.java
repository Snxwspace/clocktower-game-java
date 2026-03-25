import java.util.Random;
import java.util.Scanner;

public class Investigator extends PlayerCharacter {

    public Investigator(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        super.picksPlayer = false;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand){
        if (canAct == true){
            boolean recChance = false;
            for (Player player : game.getPlayers()) {
                if (player.getCharacter().getName().equals("Recluse")) recChance = true;
            }
            System.out.println("Point to 2 players, then show a Minion token of the Minion amongst them");
            if (recChance) System.out.println("You may lie and say the Recluse is any Minion.");
            canAct = false;
        }
    }
}
