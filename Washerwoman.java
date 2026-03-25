import java.util.Random;
import java.util.Scanner;

public class Washerwoman extends PlayerCharacter {

    public Washerwoman(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        super.picksPlayer = false;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand, boolean badAbility){
        if (canAct == true){
            boolean spyChance = false;
            for (Player player : game.getPlayers()) {
                if (player.getCharacter().getName().equals("Spy")) spyChance = true;
            }
            System.out.println("Point to 2 players, then show a Townsfolk token of the Townsfolk amongst them");
            if (spyChance){
                System.out.println("You may lie and say the Spy is any Townsfolk");
            }
            canAct = false;
        }
    }
}
