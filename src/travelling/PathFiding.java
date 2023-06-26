package travelling;

import java.util.*;

public class PathFiding {
	private Map<String, List<String>> pathFiding;

	public PathFiding() {
		pathFiding = new HashMap<>();
		playedPathFiding();
	}

	private void playedPathFiding() {
		pathFiding.put("Ubud", Arrays.asList("Kingdom of Legmod", "Principality of Nekikh"));
		pathFiding.put("Kingdom of Legmod", Arrays.asList("Kingdom of Oldcalia", "Protectorate of Dogrove",
				"Principality of Gritesthr", "Principality of Nekikh", "Ubud"));
		pathFiding.put("Principality of Nekikh",
				Arrays.asList("Kingdom of Legmod", "Principality of Gritesthr", "Ubud"));
		pathFiding.put("Principality of Gritesthr", Arrays.asList("Principality of Nekikh", "Kingdom of Legmod",
				"Protectorate of Dogrove", "Kingdom of Lastwatch"));
		pathFiding.put("Protectorate of Dogrove", Arrays.asList("Kingdom of Legmod", "Principality of Gritesthr",
				"Kingdom of Lastwatch", "Kingdom of Oldcalia"));
		pathFiding.put("Kingdom of Lastwatch", Arrays.asList("Principality of Gritesthr", "Protectorate of Dogrove",
				"Grand Duchy of Smalia", "Kingdom of Oldcalia"));
		pathFiding.put("Grand Duchy of Smalia", Arrays.asList("Kingdom of Lastwatch", "Kingdom of Oldcalia"));
		pathFiding.put("Kingdom of Oldcalia", Arrays.asList("Grand Duchy of Smalia", "Kingdom of Lastwatch",
				"Protectorate of Dogrove", "Kingdom of Legmod", "Kingdom of Kalb", "Aymar League", "Defalsia"));
		pathFiding.put("Kingdom of Kalb", Arrays.asList("Kingdom of Oldcalia", "Aymar League", "Vunese of Empire"));
		pathFiding.put("Aymar League", Arrays.asList("Defalsia", "Kingdom of Oldcalia", "Kingdom of Kalb",
				"Vunese of Empire", "Chandir Sultanate", "Bun", "Nargumun", "Principality of Karhora"));
		pathFiding.put("Defalsia", Arrays.asList("Kingdom of Oldcalia", "Aymar League"));
		pathFiding.put("Vunese of Empire", Arrays.asList("Kingdom of Kalb", "Aymar League", "Chandir Sultanate"));
		pathFiding.put("Principality of Karhora", Arrays.asList("Aymar League", "Nargumun"));
		pathFiding.put("Chandir Sultanate",
				Arrays.asList("Principality of Kasya", "Vunese of Empire", "Aymar League", "Bun"));
		pathFiding.put("Bun", Arrays.asList("Nargumun", "Aymar League", "Chandir Sultanate"));
		pathFiding.put("Principality of Kasya", Arrays.asList("Chandir Sultanate"));
		pathFiding.put("Nargumun", Arrays.asList("Bun", "Aymar League", "Principality of Karhora"));

	}
	
	public List<String> getPathFiding(String cityFiding){
		return pathFiding.getOrDefault(cityFiding, Collections.emptyList());
	}
}






























