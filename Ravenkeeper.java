import java.util.Random;
import java.util.Scanner;

public class Ravenkeeper extends PlayerCharacter {
    
    public Ravenkeeper(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand){
        if (canAct == true){
            boolean examined = false;
            boolean spyChance = false;
            boolean recChance = false;
            for (int i = 0; i < game.getPlayers().length; i++){
                if (game.getPlayers()[i].getCharacter().getName() == "Spy"){spyChance = true;}
            }
            for (int i = 0; i < game.getPlayers().length; i++){
                if (game.getPlayers()[i].getCharacter().getName() == "Recluse"){recChance = true;}
            }
            
            do{
                System.out.println("Who would they like to examine?");
                String examinee = sc.nextLine();
                for (int i = 0; i < game.getPlayers().length; i++){
                    if (game.getPlayers()[i].getName() == examinee && game.getPlayers()[i].getCharacter().getName() != "Ravenkeeper"){
                        examined = true;
                    }else{
                        System.out.print("They can't do that!");
                    }
                }
            }while(examined == false);
            super.canAct = false;
        }
    }
}
