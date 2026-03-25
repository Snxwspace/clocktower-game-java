import java.util.Random;
import java.util.Scanner;

public class Librarian extends PlayerCharacter {

    public Librarian(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        super.picksPlayer = false;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand){
        if (canAct == true){
            boolean spyChance = false;
            for (Player player : game.getPlayers()) {
                if (player.getCharacter().getName().equals("Spy")) spyChance = true;
            }
            System.out.println("Point to 2 Players, then show an Outsider token of the Outsider amongst them");
            if (spyChance) System.out.println("You may lie and say the Spy is any Outsider.");
            canAct = false;
        }
    }
}
