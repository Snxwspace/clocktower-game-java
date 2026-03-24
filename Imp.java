import java.util.Random;
import java.util.Scanner;

public class Imp extends PlayerCharacter {

    public Imp(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        super.picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand){
        if (canAct == true){
            boolean killed = false;
            do{
                System.out.println("Who would they like to kill?");
                String victim = sc.nextLine();
                for (int i = 0; i < game.getPlayers().length; i++){
                    if (game.getPlayers()[i].getName() == victim){
                        if(game.getPlayers()[i].getProtected() == false || (game.getPlayers()[i].getCharacter().getName() == "Soldier" && game.getPlayers()[i].getPoisoned())){
                            if(game.getPlayers()[i].getCharacter().getName() == "Mayor"){
                                System.out.println("Would you like to switch the kill? (1=yes, 2=no)");
                                int mayor = sc.nextInt();
                                switch(mayor){
                                    case 1: break;
                                    case 2: default:
                                        game.getPlayers()[i].kill();
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