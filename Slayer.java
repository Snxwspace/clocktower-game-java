import java.util.Random;
import java.util.Scanner;

public class Slayer extends PlayerCharacter {
    
    public Slayer(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand, boolean badAbility){
        if (canAct == true){
            boolean shot = false;
            do{
                System.out.println("Who would they like to shoot?");
                String target = sc.nextLine();
                for (Player player : game.getPlayers()) {
                    if (player.getName().equals(target)) {
                        if (player.getCharacter().getBaseAlignment() == 'd') {
                            if(!badAbility) player.kill();
                        }
                        shot = true;
                    }
                }
                if (!shot) System.out.println("Please choose a valid target.");
            } while(shot == false);
        }
        canAct = false;
    }
}
