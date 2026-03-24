import java.util.Random;
import java.util.Scanner;

public class Slayer extends PlayerCharacter {
    
    public Slayer(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand){
        if (canAct == true){
            boolean shot = false;
            do{
                System.out.println("Who would they like to shoot?");
                String target = sc.nextLine();
                for (int i = 0; i < game.getPlayers().length; i++){
                    if (game.getPlayers()[i].getName().equals(target)){
                        if (game.getPlayers()[i].getCharacter().getBaseAlignment() == 'd'){
                            game.getPlayers()[i].kill();
                            shot = true;
                        }
                    }
                }
            }while(shot == false);
        }
        canAct = false;
    }
}
