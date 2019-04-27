package sisMercadinhoV_Final;

public abstract class Cliente {
	private String nome;
	protected static final String CLIENTE_PF = "PF";
	protected static final String CLIENTE_PJ = "PJ";

	public Cliente(String nome) {
		this.nome = nome;
	}

	public abstract String getCodigo();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", código= " + getCodigo() + "]" ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCodigo() == null) ? 0 : getCodigo().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (getCodigo() == null) {
			if (other.getCodigo() != null)
				return false;
		} else if (!getCodigo().equals(other.getCodigo()))
			return false;
		return true;
	}
	
	public abstract String getTipo();

}
