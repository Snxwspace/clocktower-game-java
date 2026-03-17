import java.util.ArrayList;

public class Script {
    private ArrayList<PlayerCharacter> townsfolk;
    private ArrayList<PlayerCharacter> outsiders;
    private ArrayList<PlayerCharacter> minions;
    private ArrayList<PlayerCharacter> demons;
    // unused as of now, we'll figure out what to do with this

    public Script(ArrayList<PlayerCharacter> townsfolk, ArrayList<PlayerCharacter> outsiders, ArrayList<PlayerCharacter> minions, ArrayList<PlayerCharacter> demons){
        this.townsfolk = townsfolk;
        this.outsiders = outsiders;
        this.minions = minions;
        this.demons = demons;
    }
}
