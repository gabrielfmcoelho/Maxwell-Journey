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
		 * O construtor da classe define que Maxwell come�a com 3 moedas de transporte.
		 */
	}
	public boolean canTravel() {
		return routeCoins > 0;
		/*
		 * O m�todo canTravel() verifica se Maxwell pode viajar verificando se ele tem pelo 
		 * menos 1 moeda de transporte.
		 */
	}
	public void spendCoin() {
		if (routeCoins > 0) {
			routeCoins--;
			/*
			 * O m�todo spendCoin() � chamado quando Maxwell se move de uma cidade para outra, 
			 * gastando 1 moeda de transporte, desde que ele tenha pelo menos 1 moeda dispon�vel.
			 */
		}
	}
	public void refillCoins(int amount) {
		routeCoins += amount;
		/*
		 * O m�todo refillCoins(int amount) � usado para reabastecer as moedas de viagem, 
		 * adicionando a quantidade especificada ao total atual.
		 */
	}
}
