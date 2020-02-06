package HiQ_AI;
/**
 * @author Cole Schaar
 * CS-481 Artificial Intelligence
 *
 * Class: Config
 *
 * Class used to store the information about every possible move in the tree
 */
public class Config {
    // values used to figure out what each move does during the transition from state to state
    private int fromx;
    private int fromy;
    private int removx;
    private int removy;
    private int tox;
    private int toy;
    private int value;
    public char dir; // debug variable

    /**
     * Constructor: Config
     * @param fromx - peg original x coordinate
     * @param fromy - peg original y coordinate
     * @param removx - peg x coordinate between old and new x coordinate
     * @param removy - peg y coordinate between old and new y coordinate
     * @param tox - new peg x coordinate
     * @param toy - new peg y coordinate
     *
     * Constructor for the Config class object
     */
    public Config(int fromx, int fromy, int removx, int removy, int tox, int toy){
        this.fromx = fromx;
        this.fromy = fromy;
        this.removx = removx;
        this.removy = removy;
        this.tox = tox;
        this.toy = toy;
    } // end of constructor

    /**
     * Constructor: Config
     * @param copy - Config object to be copied over to new one
     *
     * Constructor for the Config class Object
     */
    public Config(Config copy){
        this.fromx = copy.getFromX();
        this.fromy = copy.getFromY();
        this.removx = copy.getRemovX();
        this.removy = copy.getRemovY();
        this.tox = copy.getToX();
        this.toy = copy.getToY();
        this.value = copy.getValue();
    } // end of constructor

    /**
     * Getters for each private value of the class
     * @return - value of a private variable of the Config Object
     */
    public int getFromX() { return fromx; }
    public int getFromY() { return fromy; }
    public int getRemovX() { return removx; }
    public int getRemovY() { return removy; }
    public int getToX() { return tox; }
    public int getToY() { return toy; }
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; } // sets the value variable of the Config object
}
