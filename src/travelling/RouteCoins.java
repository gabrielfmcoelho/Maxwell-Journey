package travelling;

public class RouteCoins {
	private int routeCoins;
	/*
	 * A classe RouteCoins possui um atributo routeCoins que representa a 
	 * quantidade de moedas de viagem que Maxwell possui.
	 */
	public RouteCoins() {
		this.routeCoins = 3;
		/*
		 * O construtor da classe define que Maxwell começa com 3 moedas de transporte.
		 */
	}
	public boolean canTravel() {
		return routeCoins > 0;
		/*
		 * O método canTravel() verifica se Maxwell pode viajar verificando se ele tem pelo 
		 * menos 1 moeda de transporte.
		 */
	}
	public void spendCoin() {
		if (routeCoins > 0) {
			routeCoins--;
			/*
			 * O método spendCoin() é chamado quando Maxwell se move de uma cidade para outra, 
			 * gastando 1 moeda de transporte, desde que ele tenha pelo menos 1 moeda disponível.
			 */
		}
	}
	public void refillCoins(int amount) {
		routeCoins += amount;
		/*
		 * O método refillCoins(int amount) é usado para reabastecer as moedas de viagem, 
		 * adicionando a quantidade especificada ao total atual.
		 */
	}
}
