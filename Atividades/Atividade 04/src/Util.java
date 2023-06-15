import static javax.swing.JOptionPane.*;

import java.util.Map;

import static java.lang.Integer.parseInt;

public class Util {
	String ultimoCadastrado;
	
	public void menu() {
		Grafo grafo = new Grafo();
		boolean alive = true;
		int menu = 0;
		
		while(alive) {
			menu = parseInt(showInputDialog("1 - Registrar Cidade\n2 - Listar Cidades\n3 - Verificar Distância\n4 - finalizar"));
			switch (menu) {
			case 1:
				registrarCidade(grafo);
				break;
			case 2: 
				listarCidades(grafo);
				break;
			case 3:
				distancia(grafo);
				break;
			case 4:
				alive = false;
				break;
			
			}
		}
	}
	
	public void listarCidades(Grafo grafo) {
		if(ultimoCadastrado == null) {
			showMessageDialog(null, "Não há cidades Cadastradas");
			return;
		}
		
		Map<String, Integer> caminhoMinimo = grafo.calcularCaminhoMinimo(ultimoCadastrado);

		// Imprimir o caminho mínimo do nó de origem até todos os outros nós
		String aux = "Listando as Cidades\n";
		for (String no : caminhoMinimo.keySet()) {
			aux += no + "\n";		
		}
		showMessageDialog(null, aux);
	}
		

	public void registrarCidade(Grafo grafo) {
		String cidadeA = showInputDialog("Digite o nome da cidade de Origem").toLowerCase();
		String cidadeB = showInputDialog("Digite o nome da cidade de Destino").toLowerCase();
		ultimoCadastrado = cidadeA;
		int distancia = parseInt(showInputDialog("Digite a distância entre as duas cidades em Km"));
		grafo.adicionarAresta(new Aresta(cidadeA, cidadeB, distancia));
		grafo.adicionarAresta(new Aresta(cidadeB, cidadeA, distancia));
		
	}
	
	public void distancia(Grafo grafo) {
		String origem = showInputDialog("Digite o nome da cidade de origem").toLowerCase();
		String destino = showInputDialog("Digite que deseja chegar").toLowerCase();
			Map<String, Integer> caminhoMinimo = grafo.calcularCaminhoMinimo(origem);
			if(caminhoMinimo.get(destino) == null) {
				showMessageDialog(null, "Caminho não encontrado ou cidade não registrada");
				return;
			}else {
				int distancia = caminhoMinimo.get(destino);
				showMessageDialog(null,"Distância de " + origem + " até " + destino + " é: " + distancia + "Km");
			}
	}
}
