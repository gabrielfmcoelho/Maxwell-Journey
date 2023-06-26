import java.util.Scanner;

class Shopkeeper {
    private int coinCount;
    private int jewelPower;
    private boolean tradeCoins;

    public Shopkeeper() {
        coinCount = 0;
        jewelPower = 0;
        tradeCoins = false;
    }

    
    public void askQuestions() {
        Scanner scanner = new Scanner(System.in);

        boolean validAnswer = false;

        while (!validAnswer) {
            System.out.println("Quantas moedas de transporte você tem?");
            String coinInput = scanner.nextLine();

            try {
                coinCount = Integer.parseInt(coinInput);
                validAnswer = true;
            } catch (NumberFormatException e) {
                System.out.println("Resposta inválida. Por favor, digite um número inteiro.");
            }
        }

        validAnswer = false;

        while (!validAnswer) {
            System.out.println("Qual valor de poder atual da jóia?");
            String powerInput = scanner.nextLine();

            try {
                jewelPower = Integer.parseInt(powerInput);
                validAnswer = true;
            } catch (NumberFormatException e) {
                System.out.println("Resposta inválida. Por favor, digite um número inteiro.");
            }
        }

        validAnswer = false;

        while (!validAnswer) {
            System.out.println("Deseja trocar suas moedas por limiar na jóia? (Digite 's/n')");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("s")) {
                tradeCoins = true;
                validAnswer = true;
            } else if (answer.equalsIgnoreCase("n")) {
                tradeCoins = false;
                validAnswer = true;
            } else {
                System.out.println("Resposta inválida. Por favor, digite apenas s ou n.");
            }
        }
        scanner.close();
    }
    

    public int[] processAnswers() {
        int[] rewards = DecisionTree.processAnswers(coinCount, jewelPower, tradeCoins);
        return rewards;
    }


    public int getCoinCount() {
        return coinCount;
    }

    public int getJewelPower() {
        return jewelPower;
    }
}
