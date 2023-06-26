package travelling;

import java.util.*;

public class RoutePower {
	private Map<String, Integer> routePower;
//	protected int acumuladoJewel;
	
	public RoutePower() {
		routePower = new HashMap<>();
		playedRoutePower();
	}
	
	private void playedRoutePower() {
		routePower.put("Ubud", 0);
		routePower.put("Kingdom of Legmod", 2);
		routePower.put("Principality of Nekikh", 1);
		routePower.put("Principality of Gritesthr", 5);
		routePower.put("Protectorate of Dogrove", 3);
		routePower.put("Kingdom of Lastwatch", -2);
		routePower.put("Grand Duchy of Smalia", 1);
		routePower.put("Kingdom of Oldcalia", 4);
		routePower.put("Kingdom of Kalb", 2);
		routePower.put("Aymar League", 1);
		routePower.put("Defalsia", -3);
		routePower.put("Vunese of Empire", 0);
		routePower.put("Principality of Karhora", -2);
		routePower.put("Chandir Sultanate", 1);
		routePower.put("Bun", 5);
		routePower.put("Principality of Kasya", -7);
		routePower.put("Nargumun", 0);

	}
	
	public int getRoutePower(String cityName, int currentRoutePower) {
		
		int cityPower = routePower.getOrDefault(cityName, 0);
		//return cityPower + currentRoutePower;
		
		int totalPower = cityPower + currentRoutePower;
		
		if (totalPower < 0) {
			totalPower = 0; // Ajusta o valor para zero caso seja menor que zero
		}
		
		if (totalPower > 7) {
			return 9999; // Retornar 9999 para simbolizar o fim do jogo
		}
//		acumuladoJewel += totalPower;
//		System.out.println("######### ESSE É O current " + currentRoutePower + "\n");
//		System.out.println(acumuladoJewel);
		
		return totalPower;
	}
	
}


























