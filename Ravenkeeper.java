import java.util.Random;
import java.util.Scanner;

public class Ravenkeeper extends PlayerCharacter {
    
    public Ravenkeeper(char charType, String name) {
        super(charType, name);
        super.canAct = false;
        picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand, boolean badAbility){
        if (canAct == true){
            boolean examined = false;
            Player examinee = null;
            
            do{
                System.out.println("Who would they like to examine?");
                String examineChoice = sc.nextLine();
                for (Player player : game.getPlayers()) {
                    if (player.getName().equals(examineChoice)) {
                        examinee = player;
                    }
                }
                if (!examined) System.out.print("Please pick a valid target.");
            }while(examined == false);

            PlayerCharacter examinedChar = examinee.getCharacter();
            System.out.println("Please show the " + examinedChar + " token to the Ravenkeeper.");
            if(examinedChar.getName().equals("Recluse")) System.out.println("You may lie and show a Minion or a Demon token to the Ravenkeeper.");
            if(examinedChar.getName().equals("Spy")) System.out.println("You may lie and show a Townsfolk or a Outsider token to the Ravenkeeper.");
            super.canAct = false;
        }
    }
}
