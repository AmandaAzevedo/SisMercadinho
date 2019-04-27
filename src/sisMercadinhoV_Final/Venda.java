package sisMercadinhoV_Final;

import java.util.List;

public class Venda {
	private Data dataDaCompra;
	private Cliente cliente;
	private double total;
	private List<Produto> listaDeProdutosVendidos;
	private String usuario;

	public Venda(List<Produto> listaDeProdutosVendidos, double total, Cliente cliente, Data dataDaCompra,
			String usuario) {
		this.listaDeProdutosVendidos = listaDeProdutosVendidos;
		this.total = total;
		this.cliente = cliente;
		this.dataDaCompra = dataDaCompra;
		this.setUsuario(usuario);
	}

	public List<Produto> getListaDeProdutosVendidos() {
		return listaDeProdutosVendidos;
	}

	public double getTotal() {
		return total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Data getDataDaCompra() {
		return dataDaCompra;
	}

	@Override
	public String toString() {
		return "[Data Da Compra = " + dataDaCompra + ", cliente = " + cliente + ", total = " + total + "]";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
