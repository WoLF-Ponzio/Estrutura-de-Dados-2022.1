import java.util.Random;

public class Pessoa {
	Random gerador = new Random();
	String nome;
	int operacao = gerador.nextInt(0,8);
	int tempo;
	
	public Pessoa() {
		switch (operacao) {
			case 0: 
				tempo = 10;
				break;
			case 1: 
				tempo = 20;
				break;
			case 2: 
				tempo = 30;
				break;
			case 3: 
				tempo = 40;
				break;
			case 4: 
				tempo = 50;
				break;
			case 5: 
				tempo = 60;
				break;
			case 6: 
				tempo = 70;
				break;
			case 7: 
				tempo = 80;
				break;
		}
	}
	
	@Override
	public String toString() {
		String aux = "";
		aux += "Nome: " + nome + "\n";
		aux += "Operacao a realizar: " + descOperacao() + "\n"; //descOperacao() + "\n";
		return aux;
	}
	
	private String descOperacao() {
		switch (operacao) {
		case 0: 
			return "Saldo ";
		case 1: 
			return "Saque ";
		case 2: 
			return "Aplicação ";
		case 3: 
			return "Extrato Semanal ";
		case 4: 
			return "Extrato Mensal ";
		case 5: 
			return "Pagamento de Conta com dinheiro ";
		case 6: 
			return "Pagamento de Conta com cheque ";
		case 7: 
			return "Pagamento de Conta com saque ";
		}
		return null;
	}
}
