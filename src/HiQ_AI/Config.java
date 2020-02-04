package HiQ_AI;

public class Config {
    private int fromx;
    private int fromy;
    private int removx;
    private int removy;
    private int tox;
    private int toy;
    private int value;

    public Config(int fromx, int fromy, int removx, int removy, int tox, int toy){
        this.fromx = fromx;
        this.fromy = fromy;
        this.removx = removx;
        this.removy = removy;
        this.tox = tox;
        this.toy = toy;
    }

    public int getFromX() { return fromx; }
    public void setFromX(int fromx) { this.fromx = fromx; }

    public int getFromY() { return fromy; }
    public void setFromY(int fromy) { this.fromy = fromy; }

    public int getRemovX() { return removx; }
    public void setRemovX(int removx) { this.removx = removx; }

    public int getRemovY() { return removy; }
    public void setRemovY(int removy) { this.removy = removy; }

    public int getToX() { return tox; }
    public void setToX(int tox) { this.tox = tox; }

    public int getToY() { return toy; }
    public void setToY(int toy) { this.toy = toy; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}
