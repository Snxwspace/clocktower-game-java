import java.util.Random;
import java.util.Scanner;

public class Poisoner extends PlayerCharacter {
    public Poisoner(char charType, String name) {
        super(charType, name);
        baseAlignment = 'e';
        canAct = true;
        picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand){
        if (canAct == true){
            boolean poison = false;
            do{
                System.out.println("Who would they like to poison?");
                String protectee = sc.nextLine();
                for (Player player : game.getPlayers()) {
                    if (player.getName().equals(protectee)) {
                        player.setProtected(true);
                        poison = true;
                    }
                }
                if(!poison) System.out.println("Choose a valid target.");
            } while(poison == false);
        }
    }
}