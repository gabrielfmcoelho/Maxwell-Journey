class DecisionTree {
    public static int[] processAnswers(int coinCount, int jewelPower, boolean tradeCoins) {
        int[] rewards = new int[2];

        if (coinCount < 5) {
            if (jewelPower < 3) {
                if (tradeCoins) {
                    rewards[0] = -1;  // Perde 1 moeda
                    rewards[1] = 1;   // Ganha 1 de limiar na jóia
                } else {
                    rewards[0] = -1;  // Perde 1 moeda
                    rewards[1] = 0;
                }
            } else {
                if (tradeCoins) {
                    rewards[0] = -1;  // Perde 1 moeda
                    rewards[1] = 2;   // Ganha 2 de limiar na jóia
                } else {
                    rewards[0] = 2;   // Ganha 2 moedas
                    rewards[1] = 0;
                }
            }
        } else {
            if (jewelPower < 3) {
                if (tradeCoins) {
                    rewards[0] = -3;  // Perde 3 moedas
                    rewards[1] = 2;   // Ganha 2 de limiar na jóia
                } else {
                    rewards[0] = -2;  // Perde 2 moedas
                    rewards[1] = 0;
                }
            } else {
                if (tradeCoins) {
                    rewards[0] = -1;  // Perde 1 moeda
                    rewards[1] = 3;   // Ganha 3 de limiar na jóia
                } else {
                    rewards[0] = -3;  // Perde 3 moedas
                    rewards[1] = 0;
                }
            }
        }

        return rewards;
    }
}