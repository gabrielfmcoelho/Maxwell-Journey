package travelling;

import java.util.List;
import java.util.Scanner;

public class CityNavigation {

	PathFiding pathFiding = new PathFiding();
	RoutePower routePower = new RoutePower();
	RouteCoins routeCoins = new RouteCoins();
	Scanner scanner = new Scanner(System.in);
	private String currentCity;
	private boolean cityChanged;
	private int updatedJewelPower;
	private int jewelPower;

	/*
	 * Essas variáveis são usadas na classe CityNavigation para armazenar
	 * informações relevantes. pathFiding, routePower, routeCoins e scanner são
	 * objetos de outras classes, enquanto currentCity, cityChanged,
	 * updatedJewelPower e jewelPower são variáveis primitivas para rastrear o
	 * estado da navegação.
	 * Novo teste
	 */ 

	public CityNavigation(PathFiding pathFiding, String currentCity, int jewelPower, Scanner scanner) {
		this.currentCity = currentCity;
		this.cityChanged = false;
		this.updatedJewelPower = jewelPower;
		this.scanner = scanner;
		/*
		 * Este é o construtor da classe CityNavigation. Ele recebe um objeto
		 * PathFiding, uma string currentCity e um inteiro jewelPower. O construtor
		 * inicializa as variáveis currentCity, cityChanged e updatedJewelPower com os
		 * valores passados como argumentos.
		 */
		System.out.println("sai aqui?");
	}

	public boolean navigate() {
		/*
		 * Este método é responsável por realizar a navegação na cidade. Ele exibe as
		 * cidades vizinhas, solicita a entrada do usuário para o destino desejado e
		 * lida com a entrada do usuário. Aqui está o que acontece dentro do método:
		 */
		
		
		List<String> cities = pathFiding.getPathFiding(currentCity);
		System.out.println("Cidades vizinhas:");
		/*
		 * List<String> cities = pathFiding.getPathFiding(currentCity);: Obtém a lista
		 * de cidades vizinhas usando o objeto pathFiding e o valor atual de
		 * currentCity.
		 */

		for (String cidade : cities) {
			System.out.println(cidade);
			/*
			 * for (String cidade: cities) {...}: Percorre a lista de cidades vizinhas e as
			 * exibe na tela.
			 */
		}

		System.out.println("\nDigite o nome do destino ou SAIR para encerrar o jogo:");
		String input = scanner.nextLine(); // lê a entrada do usuário usando o objeto scanner.

		if (input.equalsIgnoreCase("sair")) {
			System.out.println("Obrigdo por jogar, até logo!");
			return true;
			/*
			 * Verifica se a entrada é "sair" (ignorando o caso). Se for, exibe uma mensagem
			 * de encerramento e retorna true para indicar que o jogo deve ser encerrado.
			 */
		} else if (cities.contains(input)) {

			currentCity = input;			
			updatedJewelPower = routePower.getRoutePower(currentCity, jewelPower);
			System.out.printf("\nVocê chegou em %s.\n", currentCity);
			System.out.printf("O poder da joia agora é de %d\n\n", updatedJewelPower);
			cityChanged = true;
			/*
			 * Verifica se a entrada do usuário(input) corresponde a uma cidade vizinha
			 * válida (cities.contains). Se for, atualiza currentCity com a cidade de
			 * destino (currentCity = input), obtém o poder da rota usando o objeto
			 * routePower (routePower.getRoutePower(currentCity,jewelPower) e atualiza
			 * updatedJewelPower com o novo valor. Também exibe mensagens informando a
			 * chegada na nova cidade e o poder atual da joia.
			 */

			
		} else {
			System.out.println("Cidade inválida! Escolha uma cidade.");
			/*
			 * Se a entrada do usuário não for "sair" nem corresponder a uma cidade vizinha
			 * válida, exibe uma mensagem de cidade inválida. Retorna false para indicar que
			 * o jogo deve continuar.
			 */
		}
		
		
		return false;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public int getUpdateJewelPower() {
		return updatedJewelPower;
	}

	public boolean isCityChanged() {
		return cityChanged;
	}
}
