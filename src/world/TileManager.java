package world;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[19];
        mapTileNum = new int [gp.screenNumColumns][gp.screenNumRows];
        getTileImage();
        loadMap();
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0_water.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/1_grass.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/2_tall_grass.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/3_low_grass.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/4_swamp.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/5_florest.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/6_rocky_sand.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/7_dense_grass.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/8_cold_grass.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/9_desert.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/10_mid_snow.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/11_melted_snow.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/12_snow.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/13_snow_florest.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/14_low_lava.png"));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/15_lava.png"));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/16_sand.png"));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/17_mountain.png"));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/18_city.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            String map = "src/resource/MapMatrix.txt";
            FileReader is = new FileReader(map);
            BufferedReader br = new BufferedReader(is);

            int col = 0;
            int row = 0;

            String line;

            while ((line = br.readLine())!=null && row < gp.screenNumRows) {
                String[] numbers = line.split(" ");
                for (col=0;col<gp.screenNumColumns;col++){
                    mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                }
                row++;
            }
            br.close();
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col<gp.screenNumColumns && row < gp.screenNumRows) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSizeScaled, gp.tileSizeScaled, null);
            col++;
            x+=gp.tileSizeScaled;
            if(col == gp.screenNumColumns){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSizeScaled;
            }
        }
    }
}
