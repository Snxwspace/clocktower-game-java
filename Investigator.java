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
            for (int i = 0; i < game.getPlayers().length; i++){
                if (game.getPlayers()[i].getCharacter().getName() == "Recluse"){recChance = true;}
            }
            System.out.println("Point to 2 game.getPlayers(), then show a Minion token of the Minion amongst them");
            if (recChance = true){
                System.out.println("You may lie and say the Spy is a Townsfolk");
            }
            canAct = false;
        }
    }
}
