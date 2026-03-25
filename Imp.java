import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Imp extends PlayerCharacter {

    public Imp(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        super.picksPlayer = true;
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand, boolean badAbility){
        if (canAct == true){
            boolean killed = false;
            String message = "Who would they like to kill?";
            do{
                System.out.println(message);
                String victim = sc.nextLine();
                for (Player player : game.getPlayers()) {
                    if (player.getName().equals(victim)) {
                        if (player.getProtected() == false || (player.getCharacter().getName().equals("Soldier") && player.getPoisoned())) {
                            if (player.getCharacter().getName().equals("Mayor")) {
                                boolean isValid = false;
                                while(isValid) {
                                    System.out.println("Would you like to switch the kill? (1=yes, 2=no)");
                                    int mayor = sc.nextInt();
                                    switch (mayor) {
                                        case 1: 
                                            message = "Who would you like to switch the kill to?";
                                            isValid = true;
                                            break;
                                        case 2:
                                            if(!badAbility) player.kill();
                                            killed = true;
                                            isValid = true;
                                            break;
                                        default:
                                            System.out.println("Invalid choice, please choose again.");
                                            break;
                                    }
                                }
                            } else if(player.getCharacter().getName().equals("Imp") && player.getIsAlive()) {
                                if(!badAbility) {
                                    ArrayList<Player> starPassTargets = new ArrayList<>(3);
                                    Player starPass = null;
                                    for(Player p : game.getPlayers()) {
                                        if(p.getIsAlive() && p.getCharacter().getCharacterType() == 'm') {
                                            if(p.getCharacter().getName().equals("Scarlet Woman")) {
                                                starPass = p;
                                                break;
                                            } else {
                                                starPassTargets.add(p);
                                            }
                                        }
                                    }

                                    if(!starPassTargets.isEmpty()) {
                                        // WE NEED A FREAKING "CHOOSE PLAYER" FUNCTION OH MY GOFD
                                        while(starPass == null) {
                                            System.out.println("Who would you like the Imp to star pass to?");
                                            String s = sc.nextLine();
                                            for(Player candidate : starPassTargets) {
                                                if(candidate.getName().equals(s)) starPass = candidate;
                                            }
                                        }
                                    }

                                    player.kill();
                                    if(starPass != null) starPass.setCharacter(new Imp('d', "Imp"));
                                }
                                killed = true;
                            } else killed = true;
                        } else killed = true;
                    }
                }
                if(!killed) System.out.println("That isn't a player in the game!");
            }while(killed == false);
        }
    }

    public boolean getCanAct() { return canAct; } 
}