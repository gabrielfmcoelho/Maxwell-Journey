package travelling;

import java.util.Scanner;

public class TestePercorrerCidade {
	public static void main(String[] args) {
		PathFiding pathFiding = new PathFiding();
		RouteCoins routeCoins = new RouteCoins();
		RoutePower routePower = new RoutePower();
		Scanner scanner = new Scanner(System.in);
		String currentCity = "Ubud";
		int jewelPower = routePower.getRoutePower(currentCity, 0);

		System.out.println("Bem vindo ao jogo!"); 
		System.out.println("Você está em Ubud"); 
		System.out.printf("Sua joia possui %d de poder\n\n", jewelPower);
		boolean sairDoJogo = false; 
		
		while (!sairDoJogo) { 
			CityNavigation cityNavigation = new CityNavigation(pathFiding, currentCity, jewelPower,
					scanner); 
			sairDoJogo = cityNavigation.navigate();
			if (!sairDoJogo && cityNavigation.isCityChanged()) {
				currentCity = cityNavigation.getCurrentCity();
				int currentRoutePower = routePower.getRoutePower(currentCity, jewelPower);
				if (currentRoutePower == 9999) {
					System.out.println("O poder ultrapassou seu limite. A joia explodiu em suas mãos");
					System.out.println("Fim de jogo");
					sairDoJogo = true;
				}
			}
		}

		scanner.close();
	}

}


