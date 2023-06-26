package shopkeeper;

import java.util.List;
import java.util.Scanner;

public class PercorrerCidade {
	public static void main(String[] args) {
		
		
		BorderTowers borderTowers = new BorderTowers();
		Scanner scanner = new Scanner(System.in);
		String currentCity = "Ubud";
		int jewelPower = borderTowers.getJewelPower(currentCity, 0);
		
		System.out.println("Bem vindo ao jogo");
		System.out.println("Você está em Ubud");
		System.out.printf("Sua jóia possui %d de poder!\n", jewelPower);
		
		boolean sairDoJogo = false;
		while(!sairDoJogo) {
			List<String> cities = borderTowers.getBordersCities(currentCity);
			System.out.println("\nCidades vizinhas: \n");
			
			for (String cidades: cities) {
				System.out.println(cidades);
			}
			
			System.out.println("\nDigite o nome do destino ou SAIR para encerrar o jogo:");
			String input = scanner.nextLine();
			
			if (input.equalsIgnoreCase("sair")){
				System.out.println("Obrigado por jogar, até logo!");
				sairDoJogo = true;
			}else if(cities.contains(input)) {
				currentCity = input;
				jewelPower = borderTowers.getJewelPower(currentCity, jewelPower);
				System.out.printf("\nVocê chegou em %s.\n", currentCity);
				System.out.printf("O poder da joia agora é de %d\n", jewelPower);
				if (jewelPower > 7) {
					System.out.println("A joia atingiu o seu limite e exploudiu em suas mãos!");
					System.out.println("Fim de jogo");
					sairDoJogo = true;
				}
			}
			else {
				System.out.println("Cidade inválida. Escolha um destino correto");
			}
			
		}	
			
		scanner.close();
	}
}
