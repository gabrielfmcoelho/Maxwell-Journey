package world;

public class Frontier {
    City destination;
    int coinsCost;

    public Frontier(City destination, int coinsCost){
        this.destination = destination;
        this.coinsCost = coinsCost;
    }
    public City getCityDestination() {
    	return destination;
    }
    
    public int getCoinsCost() {
    	return coinsCost;
    }
}
