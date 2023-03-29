import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import java.util.Random;

public class Menu {

	private static Lista<Usuario> Usuarios = new Lista<Usuario>();
	private static Lista<Bilhete_Unico> Bilhetes = new Lista<Bilhete_Unico>();

	public void menu() {
		int menu;

		do {

			menu = parseInt(showInputDialog(
					"Bem Vindo ao terminal de Bilhetes\n1 - Área Administrativa\n2 - Espaço Usuário\n3 - Finalizar"));

			switch (menu) {

			case 1:
				menuAdm();
				break;

			case 2: menuUsuario();
			break;

			case 3:
				return;
			}

			if (menu < 0 || menu > 3) {

				showConfirmDialog(null, "Entrada Inváida", "Erro!", DEFAULT_OPTION, ERROR_MESSAGE);
			}

		} while (menu > 0 || menu < 5);
	}

	public void menuAdm() {
		int menu;

		do {

			menu = parseInt(
					showInputDialog("1 - Cadastrar Bilhete\n2 - Pesquisar Bilhete\n3 - Listar Bilhete\n4 - Finalizar"));

			switch (menu) {

			case 1:
				Cadastrar();
				;
				break;

			case 2:
				Pesquisar();
				break;

			case 3:
				Listar();
				break;

			case 4:
				return;

			}

			if (menu < 0 || menu > 5) {

				showConfirmDialog(null, "Entrada Inválida", "Erro!", DEFAULT_OPTION, ERROR_MESSAGE);
			}

		} while (menu > 0 || menu < 5);
	}

			public void Cadastrar() {
				Random gerador = new Random();
				String nome, cpf;
				int tipoDeTarifa, id;
				boolean teste;
		
				nome = showInputDialog("Digite o nome do usuário");
				do {
					cpf = showInputDialog("Digite o cpf do usuário (digite APENAS os 11 dígitos)");
					teste = Usuarios.pesquisarTeste(new Usuario(cpf));
					if (teste == true) {
						showConfirmDialog(null, "CPF já cadastrado!", "Erro!", DEFAULT_OPTION, ERROR_MESSAGE);
					}
					
				} while (teste);
				do {
					tipoDeTarifa = parseInt(
							showInputDialog("Digite o tipo da tarifa\n1 - Estudante\n2 - Professor\n3 - Comum"));
					if (tipoDeTarifa < 0 || tipoDeTarifa > 4) {
						showConfirmDialog(null, "Entrada Inválida!", "Erro!", DEFAULT_OPTION, ERROR_MESSAGE);
					}
				} while (tipoDeTarifa < 0 || tipoDeTarifa > 4);
		
				Usuario usuarioProv = new Usuario(nome, cpf, tipoDeTarifa);
				teste = true;
		
				do {
					id = gerador.nextInt(100, 5000);
					if(Bilhetes.pesquisarTeste(new Bilhete_Unico(id)) == true) {
						teste = true;
					}else {
						teste = false;
					}
					System.out.println(teste);
				} while (teste);
		
				Bilhete_Unico bilheteProv = new Bilhete_Unico(id, 0, usuarioProv.getTipoDeTarifa(), usuarioProv);
		
				Usuarios.inserirFim(usuarioProv);
				Bilhetes.inserirFim(bilheteProv);
			}
		
			public void Pesquisar() {
				String pessoa = showInputDialog("Digite o cpf do usuário (digite APENAS os 11 dígitos");
				if (Usuarios.pesquisar(new Usuario(pessoa)) == null) {
					showConfirmDialog(null, "O usuário não consta no sistema", "Pessoa Não Encontrado!", DEFAULT_OPTION,
							ERROR_MESSAGE);
				} else {
					Usuario pesquisarUsuario = new Usuario(pessoa);
					Bilhete_Unico pesquisarBilhete = new Bilhete_Unico(pesquisarUsuario);
					String mensagem =  Bilhetes.pesquisar(pesquisarBilhete).dado.toString();
					showConfirmDialog(null, mensagem,"Pessoa Encontrada!\n", DEFAULT_OPTION, INFORMATION_MESSAGE);
				}
			}
		
			public void Listar() {
				if (Bilhetes.imprimir() == "") {
					showConfirmDialog(null, "Não há usuários cadastrados", "Erro!", DEFAULT_OPTION, ERROR_MESSAGE);
					return;
				}
		
				showConfirmDialog(null, Bilhetes.imprimir(), "Listando Todos os usuários", DEFAULT_OPTION, INFORMATION_MESSAGE);
			}

	public void menuUsuario() {
		Random gerador = new Random();
		String pessoa;
		pessoa = showInputDialog("Digite seu cpf para consulta do bilhete (digite APENAS os 11 dígitos");
		if (Usuarios.pesquisar(new Usuario(pessoa)) == null) {
			showConfirmDialog(null, "O usuário não consta no sistema", "Pessoa Não Encontrado!", DEFAULT_OPTION,
					ERROR_MESSAGE);
		} else {
			Usuario pesquisarUsuario = new Usuario(pessoa);
			System.out.println(pessoa);
			Bilhete_Unico pesquisarBilhete = new Bilhete_Unico(Usuarios.pesquisar(pesquisarUsuario).dado);
			System.out.println(pesquisarBilhete);
			int menu;
			do {

				menu = parseInt(showInputDialog("1 - Consultar Saldo\n2 - Carregar Bilhete\n3 - Passar na catraca\n4 - Finalizar"));

				switch (menu) {

				case 1:
					showConfirmDialog(null, "Saldo disponível \nR$: " + pesquisarBilhete.getSaldo(), "Consultar Saldo", DEFAULT_OPTION);
					;
					break;

				case 2:
					pesquisarBilhete.carregar(parseDouble(showInputDialog(null, "Digite o Valor para recarga")));
					break;

				case 3:
					if(pesquisarBilhete.passarNaCatraca() == true) {
						showConfirmDialog(null, "Bem vindo", "Catráca", DEFAULT_OPTION,INFORMATION_MESSAGE);
					}else {
						showConfirmDialog(null, "Saldo Insuficiente", "Catráca", DEFAULT_OPTION,INFORMATION_MESSAGE);
					}
					break;

				case 4:
					Bilhetes.remover(pesquisarBilhete);
					Bilhetes.inserirFim(pesquisarBilhete);
					return;

				}

				if (menu < 0 || menu > 5) {

					showConfirmDialog(null, "Entrada Inválida", "Erro!", DEFAULT_OPTION, ERROR_MESSAGE);
				}

			} while (menu > 0 || menu < 5);

		}
	}
}
