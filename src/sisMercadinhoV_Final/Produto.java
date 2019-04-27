package sisMercadinhoV_Final;

public class Produto {
	private String codigo;
	private String nome;
	private double precoAtacado;
	private double precoVarejo;
	private boolean perecivel;
	private Data dataDeValidade;
	
	public Produto(String codigo, String nome, double precoAtacado, double precoVarejo, boolean perecivel, Data dataDeValidade) {
		this.codigo = codigo;
		this.nome = nome;
		this.precoAtacado = precoAtacado;
		this.precoVarejo = precoVarejo;
		this.perecivel = perecivel;
		this.dataDeValidade = dataDeValidade;
	}
	
	public Produto(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
		this.precoAtacado = 0.0;
		this.precoVarejo = 0.0;
	}

	
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrecoAtacado() {
		return precoAtacado;
	}

	public void setPrecoAtacado(double precoAtacado) {
		this.precoAtacado = precoAtacado;
	}

	public double getPrecoVarejo() {
		return precoVarejo;
	}

	public void setPrecoVarejo(double precoVarejo) {
		this.precoVarejo = precoVarejo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", preço Atacado=" + precoAtacado + ", preço Varejo="
				+ precoVarejo + "]";
	}

	public boolean getPerecivel() {
		return perecivel;
	}

	public void setPerecivel(boolean perecivel) {
		this.perecivel = perecivel;
	}

	public Data getDataDeValidade() {
		return dataDeValidade;
	}

	public void setDataDeValidade(Data dataDeValidade) {
		this.dataDeValidade = dataDeValidade;
	}

	
}
