package world;

public class Frontier {
    City destination;
    int coinsCost;

    public Frontier(City destination, int coinsCost){
        this.destination = destination;
        this.coinsCost = coinsCost;
    }
}
