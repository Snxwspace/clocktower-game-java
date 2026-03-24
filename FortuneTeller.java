import java.util.Random;
import java.util.Scanner;

public class FortuneTeller extends PlayerCharacter {
    private Player redHerring;
    
    public FortuneTeller(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        picksPlayer = true;
        redHerring = null;
    }
    
    public void setRedHerring(Scanner sc, Player[] players){
        System.out.println("Who should be the Fortune Teller's red herring?");
        sc.nextLine();
        do{
                System.out.println("Who would they like to protect?");
                String herring = sc.nextLine();
                for (int i = 0; i < players.length; i++){
                    if (players[i].getName() == herring && players[i].getCharacter().getName() != "FortuneTeller" && players[i].getCharacter().getBaseAlignment() != 'e'){
                        redHerring = players[i];
                    }
                }
        }while(redHerring == null);
    }
    
    @Override
    public void useAbility(Scanner sc, Player[] players, Random rand){
        if (canAct == true){
            if (redHerring == null){
                setRedHerring(sc, players);
            }
            Player firstViewing = null;
            Player secondViewing = null;
            int numDemons = 0;
            boolean viewable = false;
            
            boolean recChance = false;
            for (int i = 0; i < players.length; i++){
                if (players[i].getCharacter().getName() == "Recluse"){recChance = true;}
            }
            
            do{
                System.out.println("Who would they like to view first?");
                String firstGuy = sc.nextLine();
                for (int i = 0; i < players.length; i++){
                    if (players[i].getName() == firstGuy){
                        firstViewing = players[i];
                    }
                }
                System.out.println("Who would they like to view second?");
                String secondGuy = sc.nextLine();
                for (int i = 0; i < players.length; i++){
                    if (players[i].getName() == secondGuy && !(secondGuy.equals(firstGuy))){
                        secondViewing = players[i];
                    }
                }
            }while(viewable == false);
            
            if ((firstViewing.getCharacter().getCharacterType() == 'd' || firstViewing.getName().equals(redHerring.getName())) 
            && (secondViewing.getCharacter().getCharacterType() == 'd' || secondViewing.getName().equals(redHerring.getName()))){
                numDemons = 2;
            }else if((firstViewing.getCharacter().getCharacterType() == 'd' || firstViewing.getName().equals(redHerring.getName())) 
            || (secondViewing.getCharacter().getCharacterType() == 'd' || secondViewing.getName().equals(redHerring.getName()))){
                numDemons = 1;
            }
            
            System.out.println("Tell the Fortune Teller they picked " + numDemons + " Demon(s)");
            if (recChance == true){
                System.out.println("You may lie and say the Recluse is a Demon");
            }
        }
    }
}
