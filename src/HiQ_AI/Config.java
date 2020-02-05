package HiQ_AI;

public class Config {
    private int fromx;
    private int fromy;
    private int removx;
    private int removy;
    private int tox;
    private int toy;
    private int value;
    public char dir;

    public Config(int fromx, int fromy, int removx, int removy, int tox, int toy){
        this.fromx = fromx;
        this.fromy = fromy;
        this.removx = removx;
        this.removy = removy;
        this.tox = tox;
        this.toy = toy;
    }

    public Config(Config copy){
        this.fromx = copy.getFromX();
        this.fromy = copy.getFromY();
        this.removx = copy.getRemovX();
        this.removy = copy.getRemovY();
        this.tox = copy.getToX();
        this.toy = copy.getToY();
        this.value = copy.getValue();
    }

    public int getFromX() { return fromx; }

    public int getFromY() { return fromy; }
    public int getRemovX() { return removx; }

    public int getRemovY() { return removy; }

    public int getToX() { return tox; }

    public int getToY() { return toy; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}
