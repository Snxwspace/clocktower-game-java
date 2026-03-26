import java.util.Random;
import java.util.Scanner;

public class Undertaker extends PlayerCharacter {

    public Undertaker(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        super.picksPlayer = false;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand, boolean badAbility){
        if (canAct == true){
            boolean recChance = false;
            boolean spyChance = false;
            Player lastExecuted = game.getLastExecuted();
            if(lastExecuted == null) {
                System.out.println("Do not wake the Undertaker -- no one was executed tonight.");
            } else {
                if (lastExecuted.getCharacter().getName().equals("Recluse")) recChance = true;
                else if (lastExecuted.getCharacter().getName().equals("Spy")) spyChance = true;
                
                System.out.println("Tell the Undertaker the last player executed was the " + lastExecuted.getCharacter().getName() + ".");
                if (recChance) System.out.println("You may lie and say the Recluse is any Minion or Demon.");
                if (spyChance) System.out.println("You may lie and say the Spy is any Townsfolk or Outsider.");
            }
        }
    }
}