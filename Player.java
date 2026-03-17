public class Player {
    private boolean isAlive;
    private boolean hasGhostVote;
    private char alignment; // 'g' for good, 'e' for evil
    private PlayerCharacter character;
    private String name;

    private boolean canNominate;
    private boolean canBeNominated;

    public Player(String name) {
        isAlive = true;
        hasGhostVote = true;
        canNominate = true;
        canBeNominated = true;
        this.name = name;
        // TODO define character and alignment
    }

    // getters
    public boolean getIsAlive() { return isAlive; }
    public char getAlignment() { return alignment; }
    public String getName() { return name; }
    public boolean getCanNominate() { return canNominate; }
    public boolean getCanBeNominated() { return canBeNominated; }

    public boolean canVote() {
        // if the player is alive, they can vote
        // if the player has a ghost vote, they can vote
        // otherwise, they cannot vote
        return (isAlive || hasGhostVote);
    }
    
    // setters
    public void kill() {
        if(isAlive) {
            isAlive = false;
            hasGhostVote = true;
        }
    }
    
    public void revive() {
        if(!isAlive) {
            isAlive = true;
            // restore character abilities
        }
    }

    public void afterNominated() { // TODO better method name BEFORE WE START INTEGRATING THINGS
        canBeNominated = false;
    }

    public void afterNomination() { // TODO better method name BEFORE WE START INTEGRATING THINGS
        canNominate = false;
    }

    public void daybreak() {
        canNominate = true;
        canBeNominated = true;
    }
}
