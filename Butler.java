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
                for (int i = 0; i < game.getPlayers().length; i++){
                    if (game.getPlayers()[i].getName().equals(master) && !(game.getPlayers()[i].getCharacter().getName().equals("Butler"))){
                        System.out.print("The Butler will vote with " + game.getPlayers()[i].getCharacter().getName());
                        votable = true;
                    }
                }
            }while(votable == false);
        }
    }
}
