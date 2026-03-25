import java.util.Random;
import java.util.Scanner;

public class Monk extends PlayerCharacter {
    
    public Monk(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand){
        if (canAct == true){
            boolean protec = false;
            do{
                System.out.println("Who would they like to protect?");
                String protectee = sc.nextLine();
                for (Player player : game.getPlayers()) {
                    if (player.getName().equals(protectee)) {
                        if(!(player.getCharacter().getName().equals("Monk"))) {
                            player.setProtected(true);
                            protec = true;
                        } else {
                            System.out.print("They can't protect themself!");
                        }
                    }
                }
                if(!protec) System.out.println("Choose a valid target.");
            } while(protec == false);
        }
    }
}