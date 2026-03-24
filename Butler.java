import java.util.Random;
import java.util.Scanner;

public class Butler extends PlayerCharacter {
    
    public Butler(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand){
        if (canAct == true){
            boolean votable = false;
            do{
                System.out.println("Who would they like to vote with?");
                String master = sc.nextLine();
                for (Player player : game.getPlayers()) {
                    if (player.getName().equals(master) && !(player.getCharacter().getName().equals("Butler"))) {
                        System.out.print("The Butler will vote with " + player.getCharacter().getName());
                        votable = true;
                    }
                }
            }while(votable == false);
        }
    }
}
