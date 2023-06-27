package world;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Collections;

import main.GamePanel;
import entity.Player;
import quest.Reward;
import world.City;
import item.Item;

import quest.Quest;

public class CityManager {
    private HashMap<String, City> cities;
    GamePanel gp;
    City cityCurrent;
    City cityLast;
    City cityDestination;
    Scanner scanner;

    public CityManager(GamePanel gp){
        this.gp = gp;
        this.cities = new HashMap<String, City>();
        try {
            loadCityInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            loadCityFrontiers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            loadCityQuests();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setDefaultValues();
    }

    public void setDefaultValues(){
        this.cityCurrent = this.cities.get("Ubud");
        this.cityLast = this.cityCurrent;
        this.cityDestination = this.cities.get(("Nargumun"));
    }

    public void activateEndGame(){
        if (this.cityCurrent.equals(this.cityDestination)){
            gp.gameState = gp.endState;
        }
    }

    public City getCityCurrent(){
        return cityCurrent;
    }

    public void setCityCurrent(City city){
        this.cityCurrent = city;
    }

    public HashMap<String, City> getCities(){
        return cities;
    }

    public void loadCityInfo() throws IOException {
        String info = "src/resource/cityInfo.txt";
        FileReader is = new FileReader(info);
        BufferedReader br = new BufferedReader(is);

        int col = 0;
        int row = 0;

        int cityId = 0, cityX = 0, cityY = 0, cityPowerInfluence = 0;
        String cityName = null;
        String line;

        while ((line = br.readLine())!=null) {
            String[] content = line.split(",");
            cityName = content[1];
            row++;
            this.cities.put(cityName, new City(Integer.parseInt(content[0]), cityName, Integer.parseInt(content[4]), Integer.parseInt(content[2]), Integer.parseInt(content[3])));
        }
        br.close();
    }

    public void loadCityFrontiers() throws IOException {
        String frontiers = "src/resource/cityFrontiers.txt";
        FileReader is = new FileReader(frontiers);
        BufferedReader br = new BufferedReader(is);

        int col = 0;
        int row = 0;

        String cityName = null, cityFrontier = null;
        City city;
        String line;

        while ((line = br.readLine())!=null) {
            String[] content = line.split(",");
            cityName = content[0];
            city = this.cities.get(cityName);
            for (col = 1; col < 8; col++) {
                if (content[col].equals("-")) {
                    if (col == 7){
                        row++;
                    }
                    continue;
                }
                else {
                    cityFrontier = content[col];
                }
                city.addFrontier(this.cities.get(cityFrontier), 1);
                if (col == 7){
                    row++;
                }
            }
        }
        br.close();
    }

    public void loadCityQuests() throws IOException{
        String quests = "src/resource/cityQuests.txt";
        FileReader is = new FileReader(quests);
        BufferedReader br = new BufferedReader(is);

        int col = 0;
        int row = 0;

        String cityName = null, questName = null;
        City city;
        String line;

        while ((line = br.readLine())!=null) {
            String[] content = line.split(",");
            cityName = content[0];
            city = this.cities.get(cityName);
            city.addQuest(new Quest(content[1], this.cities.get(content[2]), Integer.parseInt(content[3]), new Reward(new Item(content[4], Integer.parseInt(content[5])), Integer.parseInt(content[6]))));
            row++;
        }
    }

    public void draw(Graphics2D g2){
        int xNewPosition, yNewPosition;
        for(City city : cities.values()){
            xNewPosition = gp.tileSizeScaled * city.xPosition;
            yNewPosition = gp.tileSizeScaled * city.yPosition;
            g2.drawImage(city.getCity, xNewPosition, yNewPosition, gp.tileSizeScaled, gp.tileSizeScaled, null);
            yNewPosition += gp.tileSizeScaled * 1.5;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,12F));
            g2.setColor(Color.BLACK);
            g2.drawString(city.name, xNewPosition, yNewPosition);
            yNewPosition += 2;
            xNewPosition += 2;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,12F));
            g2.setColor(Color.GRAY);
            g2.drawString(city.name, xNewPosition, yNewPosition);
        }
    }
    public void navigate(City city, int coinsCost) {
        this.cityCurrent = city;
        gp.player.updateStats(cityCurrent.getPowerInfluence(), coinsCost);
        gp.player.updatePosition(city);
        gp.player.completeQuest();
        activateEndGame();
	}

    public List<City> navigateShortestPath() {
        City destination = gp.player.activeQuest.destination;
        City current = cityCurrent;
        Queue<City> queue = new LinkedList<>();
        boolean[] visited = new boolean[cities.size()];
        City[] parent = new City[cities.size()];
        queue.add(current);
        visited[current.code-1] = true;
        parent[current.code-1] = current;
        while (!queue.isEmpty()) {
            City currentNode = queue.poll();
            if (currentNode == destination) {
                break;
            }
            for (Frontier frontier : currentNode.frontiers) {
                if (!visited[frontier.destination.code-1]) {
                    queue.add(frontier.destination);
                    visited[frontier.destination.code-1] = true;
                    parent[frontier.destination.code-1] = currentNode;
                }
            }
        }
        List<City> path = new ArrayList<>();
        City currentNode = destination;

        while (currentNode != current){
            path.add(currentNode);
            currentNode = parent[currentNode.code-1];
        }

        Collections.reverse(path);

        return path;
    }

    public String pathListToString(List<City> path) {
        String pathString = "";
        for (City city : path) {
            pathString += city.name + "\n";
        }
        return pathString;
    }
}
