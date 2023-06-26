package shopkeeper;

import java.util.HashMap;
import java.util.Map;

public class PowerJewelControl {
	
    /*
     * PASSO 1
     * Variável para armazenar o valor máximo inicial do poder da joia
     * Mapa para armazenar as missões disponíveis em cada cidade
     * Mapa para armazenar o status de cada missão (concluída ou não)
     * Variável para armazenar a missão atual de Maxwell
     */
	private static int maxPowerJewel = 7; 	
	private static Map<String, Mission> missions = new HashMap<>(); 
	private static Map<String, Boolean> missionStatus = new HashMap<>();
	private static String currentMission = null;
	
	/* 
	 * PASSO 2
	 * Bloco estático para adicionar as missões disponíveis
	 * variável estática que armazena o valor máximo do poder da joia. 
	 * Atualmente, o valor padrão é 7, mas pode ser alterado se necessário
	 */
	static{
		/*
		 * PASSO 5
		 * 
		 * O bloco estático é executado quando a classe é carregada e é usado para adicionar as missões no mapa 
		 * missions. Ele é definido usando o bloco static {}.
		 * Dentro do bloco estático, chamamos o método addMission para adicionar as missões desejadas. 
		 * Cada chamada a esse método cria uma nova instância da classe Mission e a adiciona ao mapa missions.
		 */
		addMission("Kingdom of Kalb", "Grand Duchy of Smalia", 2);
		addMission("Defalsia", "Principality of Kasya", 1);
		addMission("Vunese Empire", "Ubud", -4);
	}
	private static void addMission(String cityName, String cityDestination, int awardCity) {
		missions.put(cityName, new Mission(cityDestination, awardCity));
		missionStatus.put(cityDestination, false);
	/*
	 * PASSO 4
	 * 
	 * Método para adicionar uma missão ao mapa de missões disponíveis
	 * 
	 * O método addMission é responsável por adicionar uma nova missão ao mapa missions.
	 * Ele recebe três parâmetros: cityName, que é o nome da cidade que vai oferecer a missão; 
	 * cityDestination, que é o nome da cidade de destino que irá completar a missão; 
	 * e reward, que é o valor a ser adicionado ao limite máximo do poder da joia como recompensa pela conclusão da missão. 
	 * 
	 * Dentro do método, criamos uma nova instância da classe Mission com os valores fornecidos e a adicionamos ao 
	 * mapa missions, usando o nome da cidade como chave.
	 */	
	}
	
	public static boolean acceptMission(String cityName) {
		/*
		 * Método para aceitar uma missão em uma determinada cidade
		 */
		if (currentMission != null) {
			System.out.println("Maxwell já está em uma missão!");
			return false;
		}
		if (missionStatus.containsKey(cityName) && !missionStatus.get(cityName)) {
			// Define a missão atual e exibe uma mensagem
			currentMission = cityName;
			System.out.println("Maxwell aceitou a missão em " + cityName + ".");
			return true;
		} else {
			System.out.println("Não há missão disponível em " + cityName + ".");
			return false;
		}	
	}
	// Método para abandonar a missão atual
	public static void abandonmentMission() {
		// Verifica se há uma missão em andamento
		if (currentMission !=null) {
            // Define a missão como nula e exibe uma mensagem
			System.out.println("Maxwell abandonou a missão em " + currentMission + ".");
			currentMission = null;
		} else {
			System.out.println("Maxwell não está em nenhuma missão atualmente.");
		}
	}
	
    // Método para concluir uma missão
	public static void missionComplete (String cityDestination){
		/* 
		 * PASSO 5
		 * 
		 * O método missionComplete é responsável por marcar uma missão como concluída.
		 * Ele recebe como parâmetro o nome da cidade de destino que completou a missão.
		 * Dentro do método, obtemos a instância da classe Mission correspondente à cidade de 
		 * destino do mapa missions e definimos o atributo completed como true, indicando que a missão foi concluída.
		 * 
		 */
		
        // Verifica se há uma missão em andamento e se é a missão correta para a cidade de destino
		if (currentMission != null && currentMission.equals(cityDestination)) {
            // Verifica se a missão existe no mapa de missões
			if (missions.containsKey(cityDestination)) {
                // Define o status da missão como concluída e exibe uma mensagem
				missionStatus.put(cityDestination, true);
				System.out.println("Maxwell completou a missão em " + cityDestination + "e recebeu o prêmio!");
			}
            // Define a missão atual como nula
			currentMission = null;
		}
	}
	
    // Método para atualizar o valor máximo do poder da joia com base nas missões concluídas
	public static int updateMaxPowerJewel() {
		/*
		 * O método updateMaxJewelPower é responsável por atualizar o limite máximo do poder da joia de acordo 
		 * com as missões concluídas. Ele percorre todas as missões do mapa missions e verifica se estão concluídas.
		 * Se uma missão estiver concluída, o valor de reward (prêmio) da missão é adicionado ao limite máximo do 
		 * poder da joia.
		 * Antes de atualizar o limite máximo, é verificado se o novo valor excede o valor padrão definido em 
		 * maxJewelPower. Se exceder, o limite máximo é ajustado para o valor padrão.
		 * No final, o método retorna o novo valor do limite máximo do poder da joia.
		 */
		int newMaxJewewlPower = maxPowerJewel;
        // Percorre todas as missões concluídas e adiciona o valor do prêmio ao novo valor máximo
		for (Map.Entry<String, Boolean> entry : missionStatus.entrySet()) {
			if (entry.getValue()){
				Mission mission = missions.get(entry.getKey());
				if (mission != null) {
					newMaxJewewlPower += mission.getAwardCity();
				}
			}
		}
		if (newMaxJewewlPower > maxPowerJewel) {
			newMaxJewewlPower = maxPowerJewel;
		}
		return newMaxJewewlPower;
	}
	
	public static class Mission {
		/*
		 * PASSO 3
		 * 
		 * A classe Mission representa uma missão disponível em uma cidade. Ela possui três atributos:
		 * destinationCity: O nome da cidade de destino que irá completar a missão.
		 * reward: O prêmio por concluir a missão, que é o valor a ser adicionado ao limite máximo do poder da joia.
		 * completed: Uma variável booleana que indica se a missão foi concluída ou não.

		 */
		private String cityDestination;
		private int awardCity;
		
		public Mission(String cityDestination, int awardCity) {
			this.cityDestination = cityDestination;
			this.awardCity = awardCity;
		}
		
		public String getCityDestination() {
			return cityDestination;
		}
		
		public int getAwardCity() {
			return awardCity;
		}	
	}	
}	


















