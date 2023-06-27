package quest;

import world.City;

public class Quest {
    public final String name;
    public final City destination;
    public final int coinsAid;
    public Reward reward;
    boolean isActive;
    boolean isComplete;

    public Quest(String name, City destination, int coinsAid, Reward reward){
        this.name = name;
        this.destination = destination;
        this.coinsAid = coinsAid;
        this.reward = reward;
        this.isActive = false;
        this.isComplete = false;
    }

    public Quest acceptQuest(){
        this.isActive = true;
        return this;
    }

    public Reward completeQuest(){
        this.isComplete = true;
        return reward;
    }

    public boolean isNotComplete(){
        return !this.isComplete;
    }

    public void abandonQuest(){
        this.isActive = false;
    }
}
