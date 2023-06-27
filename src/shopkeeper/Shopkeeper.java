package shopkeeper;
import java.util.Scanner;
import UI.UI;
import entity.Player;
import world.City;
import world.CityManager;
import main.GamePanel;
import world.Frontier;

public class Shopkeeper {
    private int coinCount;
    private int jewelPower;
    private boolean tradeCoins;
    String[][] dialogues = new String[20][10];
    public int dialogueLevel = 0;
    UI ui;
    public int dialogueIndex = 0;
    CityManager cityManager;
    Player player;
    GamePanel gp;
    int dialogCounter;

    public Shopkeeper(UI ui, CityManager cityManager, Player player, GamePanel gp) {
        coinCount = 0;
        jewelPower = 0;
        tradeCoins = false;
        this.cityManager = cityManager;
        this.player = player;
        this.ui = ui;
        this.gp = gp;
        dialogCounter = 0;
    }

    public void welcome(){
        if (dialogues[dialogueIndex] == null) {
            setDialogueStatus();
            dialogueIndex = 0;
            talkStatus();
        } else {
            speak();
        }
    }

    public void talkStatus(){
        if (dialogues[dialogueIndex] == null) {
            setDialogueCity();
            dialogueIndex = 0;
            this.ui.choiceCity = true;
            this.ui.dialogOn = false;
            askCityDestination();
        } else {
            speak();
        }
    }

    public void askCityDestination(){
        if (dialogues[dialogueIndex] == null) {
            gp.gameState = gp.playerState;
        } else {
            speak();
        }
    }

    public void setDialogue(){
        dialogues[0][0] = "Olá, Herói! Eu sou o comerciante \nda cidade de " + cityManager.getCityCurrent().name + ".";
        dialogues[1][0] = "Estou aqui para ajuda-lo em sua jornada.";
    }

    public void setDialogueStatus(){
        dialogues[0][1] = "Vejo que você tem " + player.coins + " moedas de transporte e \n o poder atual da jóia é " + player.powerCurrent + "\n e o seu limiar é " + player.powerThreshold + ".";
    }

    public void setDialogueQuest(){
        dialogues[0][2] = "Caso tenha interesse, \neu tenho uma missão para você.";
        dialogues[1][2] = "Eu preciso que você vá até a cidade\nde " + cityManager.getCityCurrent().getQuest().destination.name + "\nlevando um pacote especial, \nem troca"+" você receberá " + cityManager.getCityCurrent().getQuest().reward.getItemReward().getName() + "\ne " + cityManager.getCityCurrent().getQuest().coinsAid + " moedas de transporte.\nO que me diz?";
    }

    public void setDialogueCity(){
        dialogues[0][3] = "Para qual cidade você deseja ir?";
    }

    public void setDialogueExchange(){
        dialogues[0][4] = "Você deseja trocar suas\n moedas de transporte por limiar na jóia?";
    }



    public boolean checkQuest(){
        if (cityManager.getCityCurrent().getQuest() != null && cityManager.getCityCurrent().getQuest().isNotComplete() && player.activeQuest == null){
            ui.choiceQuest = true;
            ui.dialogueCounter = ui.dialogueCounter + 1;
            return true;
        } else {
            return false;
        }
    }

    public void speak() {
        setDialogue();
        setDialogueStatus();
        setDialogueCity();
        setDialogueExchange();
        String speach = dialogues[dialogueIndex][dialogueLevel];
        if (speach == null){
            this.dialogueIndex = 0;
            this.dialogueLevel = this.dialogueLevel + 1;
            speach = dialogues[dialogueIndex][dialogueLevel];
        }
        if (dialogueLevel < 2){
            this.ui.dialogOn = true;
            this.ui.currentDialogue = speach;
        }
        if (dialogueLevel == 2){
            this.ui.dialogOn = false;
            if(checkQuest()){
                setDialogueQuest();
                speach = dialogues[dialogueIndex][dialogueLevel];
                this.ui.currentDialogue = speach;
            }
            else {
                this.dialogueLevel = this.dialogueLevel + 1;
                speach = dialogues[dialogueIndex][dialogueLevel];
            }
        }
        if (dialogueLevel == 3){
            this.ui.dialogOn = false;
            this.ui.choiceCity = true;
            this.ui.currentDialogue = speach;
        }
        if (dialogueLevel == 4){
            this.ui.choiceExchange = true;
            this.ui.currentDialogue = speach;
        }
    }
    public void updateDialogue(){
        dialogueIndex++;
    }

    public void endConversation(Frontier destination){
        ui.dialogueCounter = 0;
        dialogueIndex = 0;
        dialogueLevel = 0;
        gp.cityManager.navigate(destination.getCityDestination(), destination.getCoinsCost());
    }

    public void exchange(){
        if (player.coins < 5) {
            player.exchangeCoins(1,1);
        }
        else if (player.coins > 5){
            player.exchangeCoins(3,2);
        }
    }

    public int getCoinCount() {
        return coinCount;
    }

    public int getJewelPower() {
        return jewelPower;
    }
}
