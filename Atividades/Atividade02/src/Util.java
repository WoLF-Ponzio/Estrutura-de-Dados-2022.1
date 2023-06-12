import static javax.swing.JOptionPane.*;
import java.util.LinkedList;
import java.util.Queue;
import static java.lang.Integer.parseInt;

public class Util {
	int tempoIncial = 480;
	int tempoAtual = 480;
	int tempoAberto;
	int[]atendimento = new int[8];
	Queue<Pessoa>fila = new LinkedList<>();
	Pessoa[]caixa = new Pessoa[3];
	int contagem = 0;
	
	public void menu() {
		int menu;
		
		
		showMessageDialog(null, "Atenção! Para a aplicação, utilizamos 8h como o padrão da abertura. \nO tempo na simulação passará de 10 em 10 minutos, entretando isso não será afetado o término do expediente e apenas o 'relógio'" ,"INFORMAÇÕES IMPORTANTES", INFORMATION_MESSAGE);
		tempoAberto = parseInt(showInputDialog("Informe em MINUTOS o tempo que o banco permanecera aberto"));
		while(tempoAtual < tempoIncial + tempoAberto) {
			try {
				menu = parseInt(showInputDialog("Horário Atual: "+ String.format("%.2f",(double)tempoAtual/60) + "\n1 - Adicionar Pessoa à fila\n2 - Checar Fila\n3 - Simular\n4 - Sair"));
				switch (menu) {
				case 1: adicionarPessoa();
					break;
				case 2: checar();
					break;
				case 3: simular();
					break;
				case 4:
					return;
				default:
					throw new IllegalArgumentException("Valor Inválido!");
				}
				
				
				
				
				
			} catch (Exception e) {
				showMessageDialog(null, "Entrada Inválida" ,"ERRO", ERROR_MESSAGE);
			}
		}
		
	}

	

	private void adicionarPessoa() {
		Pessoa pessoa = new Pessoa(); 
		pessoa.nome = showInputDialog("Digite o nome da pessoa");
		showMessageDialog(null, pessoa + "Foi cadastrada","CADASTRO", INFORMATION_MESSAGE);
			for(int i = 0; i < caixa.length; i++) {
				if(caixa[i] == null) {
					caixa[i] = pessoa;
					showMessageDialog(null,"A pessoa " + pessoa.nome + " foi colocada no caixa " + (i+1), "ATENÇÃO" ,INFORMATION_MESSAGE);
					return;
				}
			}
			fila.add(pessoa);
			showMessageDialog(null,"A pessoa " + pessoa.nome + " foi colocada na fila de espera", "ATENÇÃO" ,INFORMATION_MESSAGE);
			return;
		}
	
	private void checar() {
		String aux = "";
		int tempoTotal = 0, contador = 0;
		
		if(!fila.isEmpty()) {
		for (Pessoa pessoa : fila) {
		    	aux += pessoa;
		    	tempoTotal += pessoa.tempo;
		    	contador++;
		    }
			showMessageDialog(null,"Atualmente, estão na fila:\n" + aux +"\n O tempo média de espera da fila é de " + (tempoTotal/contador), "INFORMAÇÕES" ,INFORMATION_MESSAGE);
		}else {
			showMessageDialog(null,"No momento a fila está vazia! \nO tempo média de espera da fila é de: " + tempoTotal, "INFORMAÇÕES" ,INFORMATION_MESSAGE);
			return;
		}
		
	}
	
	
	//Selmini, pesquisamos apenas o funcionamento do Thread.sleep para a criação desse método.
	//Nossa inteção era que ele não exibisse todo o sysout do horário em alguns milésimos.
	//Caso não tivessemos usado, ele funcionaria do mesmo jeito simulando de 10 em 10 minutos através do for!
	private void simular(){

		if(caixa[0] == null && caixa[1] == null && caixa[2] == null) {
			showMessageDialog(null,"No momento não há clientes no caixa!", "INFORMAÇÕES" ,INFORMATION_MESSAGE);
			return;
		}
	
			int menorTempo = 0, tempoTotal = 0, contador = 0;
			for(int i = 0; i < caixa.length; i++) {
				if(i == 0) {
					menorTempo = caixa[i].tempo;
					tempoTotal += caixa[i].tempo;
					contador++;
			
				}
				if(caixa[i] != null) { 
					menorTempo = caixa[i].tempo;
					tempoTotal += caixa[i].tempo;
					contador++;
					
				}	
			}
		showMessageDialog(null,"O tempo médio das operações do caixa é de " + (tempoTotal/contador), "INFORMAÇÕES" ,INFORMATION_MESSAGE);
		for(int i = 0;i < menorTempo; i+= 10) {
			tempoAtual += 10;
			for(int j = 0; j < caixa.length; j++) {
				if(caixa[j] != null) {
					caixa[j].tempo -= 10;
				}
			}
			
			if(tempoAtual >= tempoIncial + tempoAberto) {
				for(int j = 0; j < caixa.length; j++) {
					if(caixa[j] != null) {
						atendimento[caixa[j].operacao]++;
						contagem++;
					}		
				}
				
			contador = 0;
			tempoTotal = 0;
			if(!fila.isEmpty()) {
				for (Pessoa pessoa : fila) {
			    	tempoTotal = pessoa.tempo;
			    	contador++;
			    }
			}
				showMessageDialog(null,"Hora Atual: " + String.format("%.2f",(double)tempoAtual/60) + "Operações Encerradas", "INFORMAÇÕES" ,INFORMATION_MESSAGE);
				if(!fila.isEmpty()) {
					showMessageDialog(null,"Faltaram atender " + contador + " pessoas" +"\nTempo médio de espera da fila era de: " + String.format("%.2f",(double)tempoTotal/contador)+ " minutos", "INFORMAÇÕES" ,INFORMATION_MESSAGE);
				}else {
					showMessageDialog(null,"Todos foram atendidos!", "INFORMAÇÕES" ,INFORMATION_MESSAGE);
				}
				showMessageDialog(null,"Resumo do dia! Cada operação foi feita:\nSaldo: " + atendimento[0]+ 
						" veze(s)\nSaque: "+atendimento[1]+
						" veze(s)\nAplicação: "+atendimento[2]+
						" veze(s)\nExtrato Semanal: "+atendimento[3]+
						" veze(s)\nExtrato Mensal: "+atendimento[4]+
						" veze(s)\nPagamento de Conta com dinheiro: "+atendimento[5]+
						" veze(s)\nPagamento de Conta com cheque: "+atendimento[6]+
						" veze(s)\nPagamento de Conta com saque: " +atendimento[7]+
						" veze(s)", "INFORMAÇÕES" ,INFORMATION_MESSAGE);
				showMessageDialog(null,"Foram atendidos " + this.contagem + " pessoas", "INFORMAÇÕES" ,INFORMATION_MESSAGE);
				return;
			}else {
				System.out.println("Hora Atual: " + String.format("%.2f",(double)tempoAtual/60));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		for(int i = 0; i < caixa.length; i++) {
			if(caixa[i] != null) {
				if(caixa[i].tempo == 0) {
					atendimento[caixa[i].operacao]++;
					this.contagem++;
					if(fila.peek()!= null) {
						caixa[i] = fila.remove();
					}else {
						caixa[i] = null;
					}
				}
			}
		}
	}
}