package world;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import main.GamePanel;
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
        this.cityDestination = this.cityCurrent;
    }

    public City getCityCurrent(){
        return cityCurrent;
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
        }
    }
}
