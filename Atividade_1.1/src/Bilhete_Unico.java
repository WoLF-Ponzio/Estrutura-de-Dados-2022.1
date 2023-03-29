
public class Bilhete_Unico {
	private int id;
	private double saldo = 0;
	private double valorDaTarifa;
	private Usuario usuario;

	Bilhete_Unico(int id, double saldo, double valorDaTarifa, Usuario usuario) {
		this.id = id;
		this.saldo = saldo;
		this.valorDaTarifa = validarTarifa(usuario);
		this.usuario = usuario;

	}

	Bilhete_Unico(int id) {
		this.id = getId();
	}

	Bilhete_Unico(Usuario usuario) {
		this.usuario = usuario;
	}
	
	Bilhete_Unico(Bilhete_Unico dado){
		
	}

	@Override
	public boolean equals(Object obj) {
		Bilhete_Unico bilhete = (Bilhete_Unico) obj;
			if (this.getUsuario().getCpf().equals(bilhete.getUsuario().getCpf())) {
			return true;
			}else {
			return false;
			}		
	}


	@Override
	public String toString() {
		String aux = "";
		aux += "ID: " + getId() + "\n";
		aux += getUsuario();
		aux += "Saldo: " + getSaldo() + "\n";
		aux += "\n";

		return aux;
	}

	public boolean carregar(double recarga) {
		if (recarga < 0) {
			return false;
		} else {
			saldo += recarga;
			return true;
		}

	}

	public double validarTarifa(Usuario usuario) {
		double aux;
		if (usuario.getTipoDeTarifa() == 1 || usuario.getTipoDeTarifa() == 2) {
			aux = 4.4 * 0.5;
			return aux;
		} else {
			aux = 4.4;
			return aux;
		}
	}

	public boolean passarNaCatraca() {
		if (saldo < valorDaTarifa) {
			return false;
		} else {
			if (getUsuario().getTipoDeTarifa() == 1 || getUsuario().getTipoDeTarifa() == 2) {
				saldo -= valorDaTarifa / 2;
				return true;
			} else {
				saldo -= valorDaTarifa;
				return true;
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getValorDaTarifa() {
		return valorDaTarifa;
	}

	public void setValorDaTarifa(double valorDaTarifa) {
		this.valorDaTarifa = valorDaTarifa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}