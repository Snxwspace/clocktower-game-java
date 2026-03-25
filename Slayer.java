import java.util.Random;
import java.util.Scanner;

public class Slayer extends PlayerCharacter {
    
    public Slayer(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand, boolean badAbility){
        if (canAct == true){
            boolean shot = false;
            do{
                System.out.println("Who would they like to shoot?");
                String target = sc.nextLine();
                for (Player player : game.getPlayers()) {
                    if (player.getName().equals(target)) {
                        System.out.print("They shoot " + target + "... ");
                        if (player.getCharacter().getCharacterType() == 'd') {
                            if(!badAbility) {
                                player.kill();
                                System.out.println("...and " + target + " dies!");
                            } else {
                                System.out.println("...and nothing happens.");
                            }
                        } else if (player.getCharacter().getName().equals("Recluse")) {
                            if(!badAbility) {
                                boolean validChoice = false;
                                while(validChoice) {
                                    System.out.print("...should the Slayer kill the Recluse? (y/n) ");
                                    String choice = sc.nextLine();
                                    switch(choice.toLowerCase()){
                                        case "y":
                                            System.out.println(target + " dies!");
                                            player.kill();
                                            validChoice = true;
                                            break;
                                        case "n": 
                                            System.out.println("Nothing happens.");
                                            validChoice = true;
                                        default:
                                            break;
                                    }
                                }
                            } else {
                                System.out.println("...and nothing happens.");
                            }
                        } else {
                            System.out.print("...and nothing happens.");
                        }
                        shot = true;
                    }
                }
                if (!shot) System.out.println("Please choose a valid target.");
            } while(shot == false);
        }
        canAct = false;
    }
}
