import java.util.Random;
import java.util.Scanner;

public class Drunk extends PlayerCharacter {
    private PlayerCharacter fakeRole = null;
    
    public Drunk(char charType, String name) {
        super(charType, name);
        super.canAct = true;
        picksPlayer = true;
        fakeRole = null;
    }
    
    public void setFakeRole(Scanner sc, Game game){
        Script script = game.getScript();
        System.out.println("Who should the drunk play as?");
        boolean selected = false;
        do{
            System.out.println("Who would they like to shoot?");
            String target = sc.nextLine();
            for (int i = 0; i < script.getCharacters().size(); i++){
                if (script.getCharacters().get(i).getName().equals(target) && !(script.getCharacters().get(i).getName().equals("Drunk"))){
                    for (int j = 0; j < game.getPlayers().length; j++){
                        if (!(game.getPlayers()[j].getName().equals(target))){
                            fakeRole = script.getCharacters().get(i);
                        }else{
                            System.out.println("You can't do that!");
                        }
                    }
                }
            }
        }while(selected == false);
    }
    
    @Override
    public void useAbility(Scanner sc, Game game, Random rand){
        if (canAct == true){
            if (fakeRole == null){
                setFakeRole(sc, game);
            }
            
            System.out.println("This is the action of a drunk, and is therefore useless/incorrect");
            fakeRole.useAbility(sc, game, rand);
        }
    }
}
