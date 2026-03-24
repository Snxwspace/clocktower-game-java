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
                for (int i = 0; i < game.getPlayers().length; i++){
                    if (game.getPlayers()[i].getName() == protectee && !(game.getPlayers()[i].getCharacter().getName().equals("Monk"))){
                        game.getPlayers()[i].setProtected(true);
                        protec = true;
                    }else{
                        System.out.print("They can't do that!");
                    }
                }
            }while(protec == false);
        }
    }
}