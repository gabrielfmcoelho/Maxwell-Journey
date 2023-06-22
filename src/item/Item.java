package item;

public class Item {
    private final String name;
    private final int powerInfluence;

    public Item(String name, int powerInfluence){
        this.name = name;
        this.powerInfluence = powerInfluence;
    }

    public String getName(){
        return name;
    }

    public int getPowerInfluence(){
        return powerInfluence;
    }
}
