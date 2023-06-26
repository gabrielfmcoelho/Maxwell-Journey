class Hero {
    private int coins;
    private int power;

    public Hero() {
        coins = 5;
        power = 5;
    }

    public void updateRewards(int coinReward, int powerReward) {
        coins += coinReward;
        power += powerReward;
    }

    public int getCoins() {
        return coins;
    }

    public int getPower() {
        return power;
    }
}