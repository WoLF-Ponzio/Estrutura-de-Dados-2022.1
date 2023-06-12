import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

public class Util {
	public static Lista<carga> Navio = new Lista<carga>();
	double pesoTotal = 10000;
	
	public void menu() {
		int menu = 0;
		String msg = "1 - Reservar Carga\n2 - Pesquisar Carga\n3 - Lista Cargas\n4 - Excluir Carga\n5 - Finalizar";

		while (menu != 5) {
			try {
				menu = parseInt(showInputDialog(msg));

				switch (menu) {
				case 1:
					reservarCarga();
					break;
				case 2:
					PesquisarCarga();
					break;
				case 3:
					listarCarga();
					break;
				case 4:
					excluirCarga();
					break;
				}

			} catch (Exception e) {
				showConfirmDialog(null, "Entrada Inválida!");
			}

		}
	}

	public void reservarCarga() {
		carga Carga = new carga();
		Carga.setNomeDaEmpresa(showInputDialog("Digite o nome da Empresa"));
		Carga.setCnpj(showInputDialog("Digite o cnpj da Empresa"));
		Carga.setDestinoDaCarga(showInputDialog("Digite o destino da carga"));
			double peso;
			peso = parseDouble(showInputDialog("Digite o peso em KG da carga"));
			if(peso > 10000 || (pesoTotal - peso) < 0) {
				showConfirmDialog(null, "O valor da carga digita está indisponível para ser colocado no navio. Cheque novamente");
				return;
			}else {
				Carga.setPeso(peso);
				pesoTotal -= peso;
				
				Navio.inserirFim(Carga);
			}
		
	}

	

	public void PesquisarCarga() {
		carga Carga = new carga();
		Carga.setCnpj(showInputDialog("Digite o CNPJ para procurar a carga da empresa"));
		if(Navio.pesquisarTeste(Carga) == true) {
			showConfirmDialog(null, "Carga encontrada!", "Pesquisar",OK_OPTION);
			showConfirmDialog(null, Navio.pesquisarImprimir(Carga));
		}else {
			showConfirmDialog(null, "Carga não encontrada! Tente novamente","Pesquisar",OK_OPTION);
		}
	}
	
	public void listarCarga() {
		if (Navio.imprimir() == "") {
			showConfirmDialog(null, "Não há cargas cadastradas", "Erro!", OK_OPTION, ERROR_MESSAGE);
			return;
		}
		showConfirmDialog(null, Navio.imprimir(), "Listando Todos as Cargas", OK_OPTION, INFORMATION_MESSAGE);	
	}
	
	public void excluirCarga() {
		carga Carga = new carga();
		Carga.setCnpj(showInputDialog("Digite o CNPJ para procurar a carga da empresa"));
		if(Navio.pesquisarTeste(Carga) == true) {
			showConfirmDialog(null, "Carga encontrada! Excluindo ela do navio", "Excluindo Carga", OK_OPTION);
			pesoTotal += Navio.pesquisar(Carga).dado.getPeso();
			Navio.remover(Carga);
		}else {
			showConfirmDialog(null, "Carga não encontrada! Tente novamente", "Erro", OK_OPTION);
		}
	}
}
