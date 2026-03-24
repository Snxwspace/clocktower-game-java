import java.util.Random;
import java.util.Scanner;

public class Imp extends PlayerCharacter {

    public Imp(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        super.picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Player[] players, Random rand){
        if (canAct == true){
            boolean killed = false;
            do{
                System.out.println("Who would they like to kill?");
                String victim = sc.nextLine();
                for (int i = 0; i < players.length; i++){
                    if (players[i].getName() == victim){
                        if(players[i].getProtected() == false || (players[i].getCharacter().getName() == "Soldier" && players[i].getPoisoned())){
                            if(players[i].getCharacter().getName() == "Mayor"){
                                System.out.println("Would you like to switch the kill? (1=yes, 2=no)");
                                int mayor = sc.nextInt();
                                switch(mayor){
                                    case 1: break;
                                    case 2: default:
                                        players[i].kill();
                                        killed = true;
                                }
                            }
                        }
                    }
                }
                System.out.println("That isn't a player in the game!");
            }while(killed == false);
        }
    }
    public boolean getCanAct() { return canAct; } 
}