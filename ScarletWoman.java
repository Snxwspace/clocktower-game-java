import java.util.Random;
import java.util.Scanner;

public class ScarletWoman extends PlayerCharacter {
    
    public ScarletWoman(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        picksPlayer = false;
    }

    @Override
    public void useAbility(Scanner sc, Game game, Random rand, boolean badAbility) {
        if(canAct && !badAbility) {
            Player scarletWoman = null;
            PlayerCharacter demon = null;
            int alivePlayers = 0;
            for (Player player : game.getPlayers()) {
                if(player.getIsAlive()) alivePlayers++;
                if(player.getCharacter().getName().equals("Scarlet Woman")) scarletWoman = player;
                else if(player.getCharacter().getCharacterType() == 'd') demon = player.getCharacter();
            }

            if(alivePlayers >= 4) {
                System.out.println(scarletWoman.getName() + " the Scarlet Woman has become the " + demon.getName() + ". Tell them at night before they would act.");
                if(demon.getName().equals("Imp")) scarletWoman.setCharacter(new Imp('d', "Imp"));
            }
        }
    }
}
