import java.util.Random;
import java.util.Scanner;

public class Poisoner extends PlayerCharacter {
    Player poisonedPlayer;

    public Poisoner(char charType, String name) {
        super(charType, name);
        baseAlignment = 'e';
        canAct = true;
        picksPlayer = true;
        poisonedPlayer = null;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand, boolean badAbility){
        sc.nextLine(); // clear buffer
        if (canAct == true){
            poisonedPlayer = null;
            do{
                System.out.println("Who would they like to poison?");
                String poisonee = sc.nextLine();
                for (Player player : game.getPlayers()) {
                    if (player.getName().equals(poisonee)) {
                        if(!badAbility) player.setPoisoned(true);
                        poisonedPlayer = player;
                    }
                }
                if(poisonedPlayer == null) System.out.println("Choose a valid target.");
            } while(poisonedPlayer == null);
        }
    }

    @Override
    public void upkeepDusk(Scanner sc, Game game, Random rand) {
        if(poisonedPlayer != null) poisonedPlayer.setPoisoned(false);
    }
}