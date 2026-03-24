import java.util.Random;
import java.util.Scanner;

public class Washerwoman extends PlayerCharacter {

    public Washerwoman(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        super.picksPlayer = false;
    }
    
    @Override
    public void useAbility(Scanner sc, Player[] players, Random rand){
        if (canAct == true){
            boolean spyChance = false;
            for (int i = 0; i < players.length; i++){
                if (players[i].getCharacter().getName() == "Spy"){spyChance = true;}
            }
            System.out.println("Point to 2 players, then show a Townsfolk token of the Townsfolk amongst them");
            if (spyChance = true){
                System.out.println("You may lie and say the Spy is a Townsfolk");
            }
            canAct = false;
        }
    }
}
