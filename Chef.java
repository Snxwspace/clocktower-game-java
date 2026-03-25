import java.util.Random;
import java.util.Scanner;

public class Chef extends PlayerCharacter {

    public Chef(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        super.picksPlayer = false;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand, boolean badAbility){
        if (canAct == true){
            int borderingPlayers = 0;
            Player[] players = game.getPlayers();
            int numPlayers = players.length;
            for (int i = 0; i < numPlayers; i++){
                if(players[i % numPlayers].getAlignment() == 'e' && players[(i+1) % numPlayers].getAlignment() == 'e'){
                    borderingPlayers++;
                }
                if((players[i%(numPlayers)].getCharacter().getName().equals("Recluse") && players[i%(numPlayers)+1].getAlignment() == 'e')
                 || players[i%(numPlayers)].getAlignment() == 'e' && players[i%(numPlayers)+1].getCharacter().getName().equals("Recluse")){
                    boolean validChoice = false;
                    while(validChoice) {
                        System.out.println("Should the recluse count as evil? (1 = yes, 2 = no)");
                        int choice = sc.nextInt();
                        switch(choice){
                            case 1:
                                borderingPlayers++;
                            case 2: 
                                validChoice = true;
                            default:
                                break;
                        }
                    }
                }
            }
            System.out.println("Tell the chef there are " + borderingPlayers + " pairs of evil players.");
            canAct = false;
        }
    }
}
