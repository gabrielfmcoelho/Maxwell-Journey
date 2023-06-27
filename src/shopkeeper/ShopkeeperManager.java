package shopkeeper;
import entity.Player;
import UI.UI;
import main.GamePanel;

public class ShopkeeperManager {
    Player player;
    Shopkeeper shopkeeper;
    UI ui;
    GamePanel gp;

    public ShopkeeperManager(Player player, UI ui, GamePanel gp) {
        this.player = player;
        this.ui = ui;

        this.gp = gp;
    }

    public void welcome() {
        shopkeeper.welcome();
        talkStatus();

    }

    public void talkStatus(){
        shopkeeper.talkStatus();
        this.ui.choiceCity = true;
        this.ui.dialogOn = false;
        askCityDestination();
    }

    public void askCityDestination(){
        shopkeeper.askCityDestination();
    }

    public void manageShopkeeper() {
        //shopkeeper.askQuestions();

        int[] rewards = shopkeeper.processAnswers();

        int coinReward = rewards[0];
        int powerReward = rewards[1];

        this.player.updateRewards(coinReward, powerReward);

        System.out.println("Recompensa do Herói:");
        System.out.println("Moedas: " + this.player.getCoins());
        System.out.println("Poder: " + this.player.getPowerThreshold());
    }

    public void updateDialogue(){
        shopkeeper.updateDialogue();
    }

    public void Speak(){
        shopkeeper.speak();
    }
}