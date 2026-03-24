import java.util.Random;
import java.util.Scanner;

public class Empath extends PlayerCharacter {

    public Empath(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        super.picksPlayer = false;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand){
        if (canAct == true){
            boolean spyChance = false;
            boolean recChance = false;
            int pos = -1;
            
            int evilBordering = 0;
            for (int i = 0; i < game.getPlayers().length; i++){
                if (game.getPlayers()[i].getCharacter().getName().equals("Spy")){spyChance = true;}
            }
            for (int i = 0; i < game.getPlayers().length; i++){
                if (game.getPlayers()[i].getCharacter().getName().equals("Recluse")){recChance = true;}
            }
            for (int i = 0; i < game.getPlayers().length; i++){
                if(game.getPlayers()[i].getCharacter().getName().equals("Empath")){pos = i;}
            }
            
            Player neighbor1 = null;
            Player neighbor2 = null;
            
            int counter = 1;
            boolean foundNeighbor = false;
            while(foundNeighbor == false){
                if(game.getPlayers()[pos%(game.getPlayers().length)-counter].getIsAlive() == false){
                    counter++;
                }else{
                    neighbor1 = game.getPlayers()[pos%(game.getPlayers().length)-counter];
                    foundNeighbor = true;
                }
            }
            foundNeighbor = false;
            counter = 1;
            while(neighbor2 == null){
                if(game.getPlayers()[pos%(game.getPlayers().length)+counter].getIsAlive() == false){
                    counter++;
                }else{
                    neighbor2 = game.getPlayers()[pos%(game.getPlayers().length)+counter];
                    foundNeighbor = true;
                }
            }
            
            if(neighbor1.getAlignment() == 'e' && neighbor2.getAlignment() == 'e'){
                        evilBordering = 2;
                    }else if(neighbor1.getAlignment() == 'e' || neighbor2.getAlignment() == 'e'){
                        evilBordering = 1;
                    }
            
            System.out.println("The Empath has " + evilBordering + " Evil neighbor(s)");
            if (spyChance == true){
                System.out.println("You may lie and say the Spy is Good");
            }
            if (recChance == true){
                System.out.println("You may lie and say the Recluse is Evil");
            }
        }
    }
}
