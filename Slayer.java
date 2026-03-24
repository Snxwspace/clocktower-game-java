import java.util.Random;
import java.util.Scanner;

public class Slayer extends PlayerCharacter {
    
    public Slayer(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Player[] players, Random rand){
        if (canAct == true){
            boolean shot = false;
            do{
                System.out.println("Who would they like to shoot?");
                String target = sc.nextLine();
                for (int i = 0; i < players.length; i++){
                    if (players[i].getName() == target){
                        if (players[i].getCharacter().getBaseAlignment() == 'd'){
                            players[i].kill();
                            shot = true;
                        }
                    }
                }
            }while(shot == false);
        }
        canAct = false;
    }
}
