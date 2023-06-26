package shopkeeper;

import java.util.*;

public class BorderTowers {
	/*
	 * private Map<String, List<String>> borderTowers; - Declara uma variável de
	 * instância borderTowers, que é um mapa que mapeia o nome de uma cidade para
	 * uma lista de suas cidades vizinhas. O mapa é do tipo Map<String,
	 * List<String>>, onde a chave é uma String que representa o nome da cidade e o
	 * valor é uma lista de strings que representa as cidades vizinhas.
	 */

	private Map<String, List<String>> borderTowers;
	/*
	 * declara uma variável de instância powerJewel, que é uma mapa que mapeia o
	 * nome da cidade e sua respectiva influencia no poder da joia do personagem.
	 */

	private Map<String, Integer> powerJewel;

	/*
	 * O construtor BorderTowers() é definido. Quando um objeto BorderTowers é
	 * criado, ele inicializa o mapa da cidade chamando o método
	 * playedBorderTowres(). O mesmo ocorre com o powerJewel
	 * 
	 * No caso da classe BorderTowers, o mapa borderTowers é criado usando new
	 * HashMap<>(), ou seja, cria-se uma nova instância de um HashMap vazio. Esse
	 * mapa é usado para armazenar as informações das cidades vizinhas.
	 */

	public BorderTowers() {
		borderTowers = new HashMap<>();
		powerJewel = new HashMap<>();
		playedBorderTowres();
	}

	/*
	 * private void playedBorderTowres()) - Este método é responsável por
	 * preencher o mapa da cidade com as cidades vizinhas. Aqui, chamamos o método
	 * put() do mapa para adicionar as informações das cidades e suas vizinhas.
	 * Por exemplo, a cidade "Ubud" tem as vizinhas "Kingdom of Legmod" e
	 * "Principality of Nekikh".
	 * 
	 * também inserimos o limiar da joia de cada cidade ao final usando
	 * powerJewel.put
	 */
	private void playedBorderTowres() {
		borderTowers.put("Ubud", Arrays.asList("Kingdom of Legmod", "Principality of Nekikh"));
		borderTowers.put("Kingdom of Legmod", Arrays.asList("Kingdom of Oldcalia", "Protectorate of Dogrove",
				"Principality of Gritesthr", "Principality of Nekikh", "Ubud"));
		borderTowers.put("Principality of Nekikh",
				Arrays.asList("Kingdom of Legmod", "Principality of Gritesthr", "Ubud"));
		borderTowers.put("Principality of Gritesthr", Arrays.asList("Principality of Nekikh", "Kingdom of Legmod",
				"Protectorate of Dogrove", "Kingdom of Lastwatch"));
		borderTowers.put("Protectorate of Dogrove", Arrays.asList("Kingdom of Legmod", "Principality of Gritesthr",
				"Kingdom of Lastwatch", "Kingdom of Oldcalia"));
		borderTowers.put("Kingdom of Lastwatch", Arrays.asList("Principality of Gritesthr", "Protectorate of Dogrove",
				"Grand Duchy of Smalia", "Kingdom of Oldcalia"));
		borderTowers.put("Grand Duchy of Smalia", Arrays.asList("Kingdom of Lastwatch", "Kingdom of Oldcalia"));
		borderTowers.put("Kingdom of Oldcalia", Arrays.asList("Grand Duchy of Smalia", "Kingdom of Lastwatch",
				"Protectorate of Dogrove", "Kingdom of Legmod", "Kingdom of Kalb", "Aymar League", "Defalsia"));
		borderTowers.put("Kingdom of Kalb", Arrays.asList("Kingdom of Oldcalia", "Aymar League", "Vunese of Empire"));
		borderTowers.put("Aymar League", Arrays.asList("Defalsia", "Kingdom of Oldcalia", "Kingdom of Kalb",
				"Vunese of Empire", "Chandir Sultanate", "Bun", "Nargumun", "Principality of Karhora"));
		borderTowers.put("Defalsia", Arrays.asList("Kingdom of Oldcalia", "Aymar League"));
		borderTowers.put("Vunese of Empire", Arrays.asList("Kingdom of Kalb", "Aymar League", "Chandir Sultanate"));
		borderTowers.put("Principality of Karhora", Arrays.asList("Aymar League", "Nargumun"));
		borderTowers.put("Chandir Sultanate",
				Arrays.asList("Principality of Kasya", "Vunese of Empire", "Aymar League", "Bun"));
		borderTowers.put("Bun", Arrays.asList("Nargumun", "Aymar League", "Chandir Sultanate"));
		borderTowers.put("Principality of Kasya", Arrays.asList("Chandir Sultanate"));
		borderTowers.put("Nargumun", Arrays.asList("Bun", "Aymar League", "Principality of Karhora"));

		powerJewel.put("Ubud", 0);
		powerJewel.put("Kingdom of Legmod", 2);
		powerJewel.put("Principality of Nekikh", 1);
		powerJewel.put("Principality of Gritesthr", 5);
		powerJewel.put("Protectorate of Dogrove", 3);
		powerJewel.put("Kingdom of Lastwatch", -2);
		powerJewel.put("Grand Duchy of Smalia", 1);
		powerJewel.put("Kingdom of Oldcalia", 4);
		powerJewel.put("Kingdom of Kalb", 2);
		powerJewel.put("Aymar League", 1);
		powerJewel.put("Defalsia", -3);
		powerJewel.put("Vunese of Empire", 0);
		powerJewel.put("Principality of Karhora", -2);
		powerJewel.put("Chandir Sultanate", 1);
		powerJewel.put("Bun", 5);
		powerJewel.put("Principality of Kasya", -7);
		powerJewel.put("Nargumun", 0);
	}

	/*
	 * public List<String> getBordersCities(String cityName) - Este m�todo recebe o
	 * nome de uma cidade como par�metro e retorna a lista de cidades vizinhas para
	 * essa cidade. O m�todo getOrDefault() � usado para lidar com o caso em que o
	 * nome da cidade fornecido n�o est� presente no mapa. Nesse caso, ele retorna
	 * uma lista vazia (Collections.emptyList()).
	 */
	public List<String> getBordersCities(String cityName) {
		return borderTowers.getOrDefault(cityName, Collections.emptyList());
		/*
		 * borderTowers.getOrDefault(cityName, Collections.emptyList()): Nesta linha, �
		 * utilizado o m�todo getOrDefault para obter o valor correspondente � chave
		 * cityName no mapa borderTowers. O mapa borderTowers � um mapa que mapeia o
		 * nome de uma cidade para uma lista de suas cidades vizinhas.
		 * 
		 * Se a chave cityName estiver presente no mapa, o m�todo retorna o valor
		 * associado a essa chave, ou seja, a lista de cidades vizinhas da cidade
		 * especificada. Se a chave cityName n�o estiver presente no mapa, o m�todo
		 * retorna um valor padr�o, que � Collections.emptyList(). Esse valor padr�o �
		 * uma lista vazia, indicando que n�o h� cidades vizinhas para a cidade
		 * especificada. O resultado da chamada borderTowers.getOrDefault(cityName,
		 * Collections.emptyList()) � retornado como o resultado do m�todo
		 * getBordersCities.
		 */
	}

	public int getJewelPower(String cityName, int currentJewelPower) {
		int cityPower = powerJewel.getOrDefault(cityName, 0);
		/*
		 * Nesta linha, o m�todo getOrDefault � usado para obter o poder da joia da
		 * cidade especificada (cityName) do mapa powerJewel. O m�todo retorna o valor
		 * correspondente � chave cityName se ela estiver presente no mapa. Caso
		 * contr�rio, retorna o valor padr�o 0. O poder da joia da cidade � armazenado
		 * na vari�vel cityPower.
		 */
		return cityPower + currentJewelPower;
	}
}
