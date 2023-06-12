import static javax.swing.JOptionPane.*;
import java.util.Map;
import java.util.TreeMap;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Util {

	double pesoTotal = 10000;
	TreeMap<String, Carga> navio = new TreeMap<>();
	
	public void menu() {
		boolean alive = true;
		int menu = 0;

		while (alive) {
			menu = parseInt(showInputDialog(null,
					"Controle de Carga do Navio\n1 - Reservar Carga\n2 - Pesquisar Carga\n3 - Imprimir Cargas Reservadas\n4 - Excluir Cargas\n5 - Finalizar"));
			switch (menu) {
			case 1:
				cadastra();
				break;
			case 2:
				pesquisar();
				break;
			case 3:
				imprimir();
				break;
			case 4:
				remover();
				break;
			case 5:
				alive = false;
				break;

			}
		}
	}

	private void cadastra() {
		if (pesoTotal <= 0) {
			showMessageDialog(null, "O navio atingiu a sua capacidade máxima!", "CADASTRO", ERROR_MESSAGE);
			return;
		}
		
		boolean teste = true;
		Carga carga = new Carga();
		showMessageDialog(null, "Atualmente, o navio está com " + pesoTotal + " Kg disponíveis");
		int resposta = showConfirmDialog(null, "O navio tem a capacidade para a carga que deseja registrar?\n(Só clickar sim ou não)", "CADASTRO", YES_OPTION, QUESTION_MESSAGE);
			if(resposta == NO_OPTION || resposta == CLOSED_OPTION) {
				showMessageDialog(null, "Retornando ao Menu", "CADASTRO", INFORMATION_MESSAGE);
			}else{
				carga.setNomeDaEmpresa(showInputDialog(null, "Digite o nome da Empresa dona da carga", "CADASTRO", QUESTION_MESSAGE));
				carga.setCnpjDaEmpresa(showInputDialog(null, "Digite o cnpj da Empresa dona da carga", "CADASTRO", QUESTION_MESSAGE));
				carga.setDestinoDaCarga(showInputDialog(null, "Digite o destino da Carga", "CADASTRO", QUESTION_MESSAGE));
					while(teste) {
						carga.setPesoDaCarga(parseDouble(showInputDialog(null, "Digite o peso em Kg da Carga", "CADASTRO", QUESTION_MESSAGE)));
							if((pesoTotal - carga.getPesoDaCarga()) < 0) {
								showMessageDialog(null, "Peso Inválido!\nA carga excedeu o limite disponível do navio", "CADASTRO", ERROR_MESSAGE);
								teste = true;
							}else {
								teste = false;
							}
					}
				navio.put(carga.getCnpjDaEmpresa(), carga);
				pesoTotal -= carga.getPesoDaCarga();
				showMessageDialog(null, "Carga Registrada com sucesso!", "CADASTRO", INFORMATION_MESSAGE);
			}
		}
	

	private void pesquisar() {
		if(navio.isEmpty()) {
			showMessageDialog(null, "Não há Cargas Registradas", "CADASTRO", ERROR_MESSAGE);
			return;
		}
		
		String pesquisa;
		pesquisa = showInputDialog(null, "Digite o CNPJ da carga que deseja pesquisar", "PESQUISA", QUESTION_MESSAGE);
			for (Map.Entry<String, Carga> carga : navio.entrySet()) {
				if(carga.getKey().equalsIgnoreCase(pesquisa)) {
					showMessageDialog(null,"Carga Encontrada", "PESQUISA", INFORMATION_MESSAGE);
					showMessageDialog(null,carga.getValue(), "PESQUISA", INFORMATION_MESSAGE);
					return;
				}
				
			}
		showMessageDialog(null,"Carga não encontrada! Tente novamente", "PESQUISA", ERROR_MESSAGE);
	}

	private void imprimir() {
		if(navio.isEmpty()) {
			showMessageDialog(null, "Não há Cargas Registradas", "CADASTRO", ERROR_MESSAGE);
			return;
		}
		
		String barco = "";
		for (Map.Entry<String, Carga> carga : navio.entrySet()) {
			barco += carga.getValue() + "\n";
		}
		showMessageDialog(null, barco, "CARGAS", INFORMATION_MESSAGE);
	}

	private void remover() {
		if(navio.isEmpty()) {
			showMessageDialog(null, "Não há Cargas Registradas", "REMOÇÃO", ERROR_MESSAGE);
			return;
		}
		
		String pesquisa;
		pesquisa = showInputDialog(null, "Digite o CNPJ da carga que deseja remover", "REMOÇÃO", QUESTION_MESSAGE);
			for (Map.Entry<String, Carga> carga : navio.entrySet()) {
				if(carga.getKey().equalsIgnoreCase(pesquisa)) {
					showMessageDialog(null,"Carga Encontrada", "REMOÇÃO", INFORMATION_MESSAGE);
					int resposta = showConfirmDialog(null, "Deseja mesmo remover a carga da empresa " + carga.getValue().getNomeDaEmpresa() + ", com destino a " + carga.getValue().getDestinoDaCarga() + "\n(Só clickar sim ou não)", "CADASTRO", YES_OPTION, QUESTION_MESSAGE);
						if(resposta == NO_OPTION || resposta == CLOSED_OPTION) {
							showMessageDialog(null, "Retornando ao Menu", "REMOÇÃO", INFORMATION_MESSAGE);
							return;
						}else{
							pesoTotal += carga.getValue().getPesoDaCarga();
							navio.remove(pesquisa);
							showMessageDialog(null, "Carga Removida com sucesso!", "REMOÇÃO", INFORMATION_MESSAGE);
							return;
						}
				}
				
			}
	}
	
}
