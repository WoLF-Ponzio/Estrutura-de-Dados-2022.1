import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Grafo {
	private List<Aresta> arestas;
	 
	public Grafo() {
		arestas = new ArrayList<>();
	}
	
	// método para adicionar arestas no grafo
	public void adicionarAresta(Aresta aresta) {
		this.arestas.add(aresta);
	}
	
	// algoritmo do caminho mínimo
	public Map<String, Integer> calcularCaminhoMinimo(String origem) {
		Map<String, Integer> distancia = new HashMap<>();
		Map<String, String> caminhoAnterior = new HashMap<>();
		Set<String> visitados = new HashSet<>();

		// Inicialização dos valores
		for (Aresta aresta : arestas) {
			distancia.put(aresta.getOrigem(), Integer.MAX_VALUE);
			distancia.put(aresta.getDestino(), Integer.MAX_VALUE);
			caminhoAnterior.put(aresta.getOrigem(), null);
			caminhoAnterior.put(aresta.getDestino(), null);
		}
		distancia.put(origem, 0);
		
		// Loop principal
		while (!visitados.containsAll(distancia.keySet())) {
			String noAtual = encontrarNoMenorDistancia(distancia, visitados);
			visitados.add(noAtual);
			for (Aresta aresta : arestas) {
				if (aresta.getOrigem().equals(noAtual)) {
					int novaDistancia = distancia.get(noAtual) + aresta.getPeso();
					if (novaDistancia < distancia.get(aresta.getDestino())) {
						distancia.put(aresta.getDestino(), novaDistancia);
						caminhoAnterior.put(aresta.getDestino(), noAtual);
					}
				}
			}
		}
		return distancia;
	}
	
	
	
	private String encontrarNoMenorDistancia(Map<String, Integer> distancia, Set<String> visitados) {
		int menorDistancia = Integer.MAX_VALUE;
		String noMenorDistancia = null;
		for (String no : distancia.keySet()) {
			if (!visitados.contains(no) && distancia.get(no) < menorDistancia) {
				menorDistancia = distancia.get(no);
				noMenorDistancia = no;
			}
		}
		return noMenorDistancia;
	}
	

	
}
