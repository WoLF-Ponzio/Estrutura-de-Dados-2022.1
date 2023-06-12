
public class carga {
	private String nomeDaEmpresa;
	private String cnpj;
	private String destinoDaCarga;
	private double peso;

	public carga() {

	}

	public String getNomeDaEmpresa() {
		return nomeDaEmpresa;
	}

	public void setNomeDaEmpresa(String nomeDaEmpresa) {
		this.nomeDaEmpresa = nomeDaEmpresa;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDestinoDaCarga() {
		return destinoDaCarga;
	}

	public void setDestinoDaCarga(String destinoDaCarga) {
		this.destinoDaCarga = destinoDaCarga;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		String aux = "";

		aux += "Nome da empresa: " + getNomeDaEmpresa() + "\n";
		aux += "CNPJ da empresa: " + getCnpj() + "\n";
		aux += "Destino da Carga: " + getDestinoDaCarga() + "\n";
		aux += "Peso da Carga: " + getPeso() + "\n";
		return aux;
	}

	@Override
	public boolean equals(Object obj) {
		carga Carga = (carga) obj;
		if (this.getCnpj().equals(Carga.getCnpj())) {
			return true;
		} else {
			return false;
		}
	}
}
