package world;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import quest.Quest;

public class City {
    int code;
    public String name;
    List<Frontier> frontiers;
    Quest quest;
    int powerInfluence;
    int xPosition;
    int yPosition;
    public BufferedImage getCity;
    public boolean arrival;

    public City(int code, String name, int powerInfluence, int xPosition, int yPosition){
        this.code = code;
        this.name = name;
        this.frontiers = new ArrayList<>();
        this.quest = null;
        this.powerInfluence = powerInfluence;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.arrival = false;
        getCityImage();
    }

    public int getxPosition(){
        return xPosition;
    }

    public int getyPosition(){
        return yPosition;
    }

    public void addFrontier(City destination, int cost) {
        Frontier frontier = new Frontier(destination, cost);
        frontiers.add(frontier);
    }

    public void addQuest(Quest quest){
        this.quest = quest;
    }
    public Quest getQuest(){
        return quest;
    }
    
    public List<Frontier> getFrontier() {
    	return frontiers;
    }

    public void getCityImage(){
        try{
            getCity = ImageIO.read(getClass().getResourceAsStream("/tiles/18_city.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public int getPowerInfluence() {
    	return powerInfluence;
    }

    public City getShortestPath(City destination) {
    	List<City> visited = new ArrayList<>();
    	List<City> unvisited = new ArrayList<>();
    	unvisited.add(this);
    	while(!unvisited.isEmpty()) {
    		City current = unvisited.get(0);
    		for(Frontier f : current.getFrontier()) {
    			if(f.destination == destination) {
    				return current;
    			}
    			if(!visited.contains(f.destination)) {
    				unvisited.add(f.destination);
    			}
    		}
    		visited.add(current);
    		unvisited.remove(current);
    	}
    	return null;
    }
}
