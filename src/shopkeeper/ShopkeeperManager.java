package shopkeeper;
import entity.Player;

public class ShopkeeperManager {
    Player hero;

    public ShopkeeperManager(Player player) {
        hero = player;
    }

    public void manageShopkeeper() {
        Shopkeeper shopkeeper = new Shopkeeper();
        

        shopkeeper.askQuestions();
        int[] rewards = shopkeeper.processAnswers();

        int coinReward = rewards[0];
        int powerReward = rewards[1];

        hero.updateRewards(coinReward, powerReward);

        System.out.println("Recompensa do Herói:");
        System.out.println("Moedas: " + hero.getCoins());
        System.out.println("Poder: " + hero.getPowerThreshold());
    }
}