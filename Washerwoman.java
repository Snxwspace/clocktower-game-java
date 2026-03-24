import java.util.Random;
import java.util.Scanner;

public class Washerwoman extends PlayerCharacter {

    public Washerwoman(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        super.picksPlayer = false;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand){
        if (canAct == true){
            boolean spyChance = false;
            for (int i = 0; i < game.getPlayers().length; i++){
                if (game.getPlayers()[i].getCharacter().getName().equals("Spy")){spyChance = true;}
            }
            System.out.println("Point to 2 players, then show a Townsfolk token of the Townsfolk amongst them");
            if (spyChance = true){
                System.out.println("You may lie and say the Spy is a Townsfolk");
            }
            canAct = false;
        }
    }
}
