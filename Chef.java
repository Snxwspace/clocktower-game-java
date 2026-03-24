import java.util.Random;
import java.util.Scanner;

public class Chef extends PlayerCharacter {

    public Chef(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        super.picksPlayer = false;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand){
        if (canAct == true){
            int borderingPlayers = 0;
            for (int i = 0; i <= game.getPlayers().length; i++){
                if(game.getPlayers()[i%(game.getPlayers().length)].getAlignment() == 'e' && game.getPlayers()[i%(game.getPlayers().length)+1].getAlignment() == 'e'){
                    borderingPlayers++;
                }
                if((game.getPlayers()[i%(game.getPlayers().length)].getCharacter().getName() == "Recluse" && game.getPlayers()[i%(game.getPlayers().length)+1].getAlignment() == 'e')
                    || game.getPlayers()[i%(game.getPlayers().length)].getAlignment() == 'e' && game.getPlayers()[i%(game.getPlayers().length)+1].getCharacter().getName() == "Recluse"){
                        System.out.println("Should the recluse count as evil? (1 = yes, 2 = no)");
                        int choice = sc.nextInt();
                        switch(choice){
                            case 1:
                                borderingPlayers++;
                            case 2: default:
                                break;
                        }
                }
            }
            System.out.println("Tell the chef there are " + borderingPlayers + " pairs of evil game.getPlayers().");
            canAct = false;
        }
    }
}
