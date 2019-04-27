package sisMercadinhoV_Final;

public class ClientePJ extends Cliente {

	private String cnpj;

	public ClientePJ(String nome, String cnpj) {
		super(nome);
		this.cnpj = cnpj;
		
	}
	

	@Override
	public String getCodigo() {
		return this.cnpj;
	}

	public void setCpf(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return Cliente.CLIENTE_PJ;
	}

}
