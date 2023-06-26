class ShopkeeperManager {
    public void manageShopkeeper() {
        Shopkeeper shopkeeper = new Shopkeeper();
        Hero hero = new Hero();

        shopkeeper.askQuestions();
        int[] rewards = shopkeeper.processAnswers();

        int coinReward = rewards[0];
        int powerReward = rewards[1];

        hero.updateRewards(coinReward, powerReward);

        System.out.println("Recompensa do Herói:");
        System.out.println("Moedas: " + hero.getCoins());
        System.out.println("Poder: " + hero.getPower());
    }
}