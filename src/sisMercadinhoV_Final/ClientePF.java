package sisMercadinhoV_Final;

public class ClientePF extends Cliente {

	private String cpf;

	public ClientePF(String nome, String cpf) {
		super(nome);
		this.cpf = cpf;
	}

	@Override
	public String getCodigo() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return Cliente.CLIENTE_PF;
	}

}
