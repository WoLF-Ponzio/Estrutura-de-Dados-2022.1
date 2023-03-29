
public class Usuario {
	private String nome;
	private String cpf;
	private int tipoDeTarifa;
	
	public Usuario(String nome, String cpf, int tipoDeTarifa) {
		this.nome = nome;
		this.cpf = formatar_cpf(cpf);
		this.tipoDeTarifa = tipoDeTarifa;
	}
	
	public Usuario(String cpf) {
		this.cpf = formatar_cpf(cpf);
	}
	
	public String formatar_cpf(String cpf) {
		return
				cpf.substring(0, 3) + "." +
	            cpf.substring(3, 6) + "." +
	            cpf.substring(6, 9) + "-" +
	            cpf.substring(9, 11);
	}
	
	@Override
	public String toString() {
		String TdT;
		if(getTipoDeTarifa() == 1) {
			TdT = "Estudante";
		}else if (getTipoDeTarifa() == 2) {
			TdT = "Professor";
		}else {
			TdT = "Comum";
		}
		
		String aux = "";
			aux += "Proprietário do Bilhete: " + getNome() +"\n";
			aux += "CPF do titular: " + getCpf() + "\n";
			aux += "Tipo da Tarifa: " + TdT + "\n";
		
			return aux;
	}		
			
	@Override
	public boolean equals(Object obj) {
		Usuario usuario = (Usuario) obj;
		if(usuario.cpf.equalsIgnoreCase(getCpf())) {
			return true;
		}else {
			return false;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getTipoDeTarifa() {
		return tipoDeTarifa;
	}

	public void setTipoDeTarifa(int tipoDeTarifa) {
		this.tipoDeTarifa = tipoDeTarifa;
	}
	
}
