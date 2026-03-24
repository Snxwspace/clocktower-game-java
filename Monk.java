import java.util.Random;
import java.util.Scanner;

public class Monk extends PlayerCharacter {
    
    public Monk(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Player[] players, Random rand){
        if (canAct == true){
            boolean protec = false;
            do{
                System.out.println("Who would they like to protect?");
                String protectee = sc.nextLine();
                for (int i = 0; i < players.length; i++){
                    if (players[i].getName() == protectee && players[i].getCharacter().getName() != "Monk"){
                        players[i].setProtected(true);
                        protec = true;
                    }
                }
            }while(protec == false);
        }
    }
}