
import java.util.Random;
import java.util.Scanner;

public class Soldier extends PlayerCharacter {
    
    public Soldier(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        picksPlayer = false;
    }

    @Override
    public void upkeepDawn(Scanner sc, Game game, Random rand) {
        for(Player player : game.getPlayers()) {
            if(player.getCharacter().getName().equals("Soldier")) {
                player.setProtected(true);
            }
        }
    }
}