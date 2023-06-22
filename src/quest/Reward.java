package quest;

import item.Item;

public class Reward {
    private final Item itemReward;
    private final int coinsReward;

    public Reward(Item itemReward, int coinsReward){
        this.itemReward = itemReward;
        this.coinsReward = coinsReward;
    }

    public Item getItemReward(){
        return itemReward;
    }

    public int getCoinsReward(){
        return coinsReward;
    }
}
