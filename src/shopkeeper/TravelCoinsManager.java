package shopkeeper;

public class TravelCoinsManager {
	private int travelCoins;
	/*
	 * A classe TravelCoinsManager possui um atributo travelCoins que representa a 
	 * quantidade de moedas de viagem que Maxwell possui.
	 */
	public TravelCoinsManager() {
		this.travelCoins = 3;
		/*
		 * O construtor da classe define que Maxwell começa com 3 moedas de transporte.
		 */
	}
	
	public boolean canTravel() {
		return travelCoins > 0;
		/*
		 * O método canTravel() verifica se Maxwell pode viajar verificando se ele tem pelo 
		 * menos 1 moeda de transporte.
		 */
	}

	public void spendCoin() {
		if (travelCoins > 0) {
			travelCoins--;
			/*
			 * O método spendCoin() é chamado quando Maxwell se move de uma cidade para outra, 
			 * gastando 1 moeda de transporte, desde que ele tenha pelo menos 1 moeda disponível.
			 */
		}
	}

	public void refillCoins(int amount) {
		travelCoins += amount;
		/*
		 * O método refillCoins(int amount) é usado para reabastecer as moedas de viagem, 
		 * adicionando a quantidade especificada ao total atual.
		 */
	}
	
}
