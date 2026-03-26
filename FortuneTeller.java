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
            System.out.println("Who would you like to be their red herring?");
            String herring = sc.nextLine();
            for (Player player : players) {
                if (player.getName().equals(herring)) {
                    redHerring = player;
                }
            }
        }while(redHerring == null);
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand, boolean badAbility){
        if (canAct == true){
            if (redHerring == null){
                setRedHerring(sc, game.getPlayers());
            }
            Player firstViewing = null;
            Player secondViewing = null;
            boolean isDemon = false;
            boolean viewable = false;
            
            boolean recChance = false;
            for (Player player : game.getPlayers()) {
                if (player.getCharacter().getName().equals("Recluse")) recChance = true;
            }
            
            String firstGuy;
            String secondGuy;
            do{
                System.out.println("Who would they like to view first?");
                firstGuy = sc.nextLine();
                for (Player player : game.getPlayers()) {
                    if (player.getName().equals(firstGuy)) firstViewing = player;
                }
            } while(firstViewing == null);
            do{
                System.out.println("Who would they like to view second?");
                secondGuy = sc.nextLine();
                for (Player player : game.getPlayers()) {
                    if (player.getName().equals(secondGuy) && !(secondGuy.equals(firstGuy))) secondViewing = player;
                }
            }while(secondViewing == null);
            
            if ((firstViewing.getCharacter().getCharacterType() == 'd' || firstViewing.getName().equals(redHerring.getName())) 
             || (secondViewing.getCharacter().getCharacterType() == 'd' || secondViewing.getName().equals(redHerring.getName()))){
                isDemon = true;
            }
            
            if(isDemon) System.out.println("Tell the Fortune Teller they picked a demon.");
            else {
                System.out.println("Tell the Fortune Teller they did not pick a demon.");
            
                if (recChance == true && (firstViewing.getCharacter().getName().equals("Recluse") || 
                                          secondViewing.getCharacter().getName().equals("Recluse"))){
                    System.out.println("You may lie and say the Recluse is a Demon");
                }
            }
        }
    }
}
