import java.util.Random;
import java.util.Scanner;

public class Monk extends PlayerCharacter {
    Player protectedPlayer;

    public Monk(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        picksPlayer = true;
        protectedPlayer = null;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand, boolean badAbility){
        if (canAct == true){
            protectedPlayer = null;
            do{
                System.out.println("Who would they like to protect?");
                String protectee = sc.nextLine();
                for (Player player : game.getPlayers()) {
                    if (player.getName().equals(protectee)) {
                        if(!(player.getCharacter().getName().equals("Monk"))) {
                            if(!badAbility) player.setProtected(true);
                            protectedPlayer = player;
                        } else {
                            System.out.print("They can't protect themself!");
                        }
                    }
                }
                if(protectedPlayer == null) System.out.println("Choose a valid target.");
            } while(protectedPlayer == null);
        }
    }

    @Override
    public void upkeepDawn(Scanner sc, Game game, Random rand) {
        if(protectedPlayer != null) protectedPlayer.setProtected(false);
    }
}