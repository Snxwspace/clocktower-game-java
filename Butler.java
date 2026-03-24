import java.util.Random;
import java.util.Scanner;

public class Butler extends PlayerCharacter {
    
    public Butler(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Player[] players, Random rand){
        if (canAct == true){
            boolean votable = false;
            do{
                System.out.println("Who would they like to vote with?");
                String master = sc.nextLine();
                for (int i = 0; i < players.length; i++){
                    if (players[i].getName() == master && players[i].getCharacter().getName() != "Butler"){
                        players[i].setProtected(true);
                        System.out.print("The Butler will vote with " + players[i].getCharacter().getName());
                        votable = true;
                    }
                }
            }while(votable == false);
        }
    }
}
