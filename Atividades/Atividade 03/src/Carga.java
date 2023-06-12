
public class Carga {
	private String cnpjDaEmpresa;
	private String nomeDaEmpresa;
	private String destinoDaCarga;
	private double pesoDaCarga;
	
	public Carga() {
		
	}
	
	public String getCnpjDaEmpresa() {
		return cnpjDaEmpresa;
	}
	public void setCnpjDaEmpresa(String cnpjDaEmpresa) {
		this.cnpjDaEmpresa = cnpjDaEmpresa;
	}
	public String getNomeDaEmpresa() {
		return nomeDaEmpresa;
	}
	public void setNomeDaEmpresa(String nomeDaEmpresa) {
		this.nomeDaEmpresa = nomeDaEmpresa;
	}
	public String getDestinoDaCarga() {
		return destinoDaCarga;
	}
	public void setDestinoDaCarga(String destinoDaCarga) {
		this.destinoDaCarga = destinoDaCarga;
	}
	public double getPesoDaCarga() {
		return pesoDaCarga;
	}
	public void setPesoDaCarga(double pesoDaCarga) {
		this.pesoDaCarga = pesoDaCarga;
	}
	
	@Override
	public String toString() {
		String aux = "";
		aux += "Nome da Empresa: " + getNomeDaEmpresa() + "\n";
		aux += "CNPJ: " + getCnpjDaEmpresa() + "\n";
		aux += "Destino da Carga: " + getDestinoDaCarga() + "\n";
		aux += "Peso da Carga: " + getPesoDaCarga() + " Kg\n";
		return aux;
	}
	
}
