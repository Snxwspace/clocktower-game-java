import java.util.Random;
import java.util.Scanner;

public class Chef extends PlayerCharacter {

    public Chef(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        super.picksPlayer = false;
    }
    
    @Override
    public void useAbility(Scanner sc, Player[] players, Random rand){
        if (canAct == true){
            int borderingPlayers = 0;
            for (int i = 0; i <= players.length; i++){
                if(players[i%(players.length)].getAlignment() == 'e' && players[i%(players.length)+1].getAlignment() == 'e'){
                    borderingPlayers++;
                }
                if((players[i%(players.length)].getCharacter().getName() == "Recluse" && players[i%(players.length)+1].getAlignment() == 'e')
                    || players[i%(players.length)].getAlignment() == 'e' && players[i%(players.length)+1].getCharacter().getName() == "Recluse"){
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
            System.out.println("Tell the chef there are " + borderingPlayers + " pairs of evil players.");
            canAct = false;
        }
    }
}
