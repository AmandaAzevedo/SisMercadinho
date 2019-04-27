package sisMercadinhoV_Final;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SistemaMercado implements InterfaceSisMercado {

	private List<Usuario> usuarios;
	private List<Produto> produtos;
	private List<Cliente> clientes;
	private List<Venda> vendas;

	private GerenciadorDeDadosEmArquivo gravador;

	private static final String ARQUIVO_PRODUTOS = "produtos.txt";
	private static final String ARQUIVO_CLIENTES = "clientes.txt";
	private static final String ARQUIVO_USUARIOS = "usuarios.txt";
	private static final String ARQUIVO_VENDAS = "vendas.txt";

	public SistemaMercado() {
		this.usuarios = new ArrayList<Usuario>();
		this.produtos = new ArrayList<Produto>();
		this.clientes = new ArrayList<Cliente>();
		this.vendas = new ArrayList<Venda>();
		this.gravador = new GerenciadorDeDadosEmArquivo();
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	@Override
	public void cadastrarUsuario(Usuario usuario) throws UsuarioJaExisteException {
		if (verificaSeOUsuarioJaExiste(usuario) == false) {
			this.usuarios.add(usuario);

		} else {
			throw new UsuarioJaExisteException("Já existe um usuário com este login: " + usuario.getLogin());
		}
	}

	public boolean verificaSeOUsuarioJaExiste(Usuario usuarioE) {
		for (Usuario usuario : this.usuarios) {
			if (usuario.equals(usuarioE)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void cadastrarCliente(Cliente cliente) throws ClienteJaExisteException {
		if (verificaSeOClienteJaExiste(cliente) == false) {
			this.clientes.add(cliente);

		} else {
			throw new ClienteJaExisteException("Já existe um cliente com este código: " + cliente.getCodigo());
		}

	}

	public boolean verificaSeOClienteJaExiste(Cliente clienteE) {
		for (Cliente cliente : this.clientes) {
			if (cliente.equals(clienteE)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void cadastrarProduto(Produto produto) throws ProdutoJaExisteException {
		if (verificaSeOProdutoJaExiste(produto) == false) {
			this.produtos.add(produto);

		} else {
			throw new ProdutoJaExisteException("Já existe um produto com este código: " + produto.getCodigo());
		}

	}

	public boolean verificaSeOProdutoJaExiste(Produto produtoE) {
		for (Produto produto : this.produtos) {
			if (produto.equals(produtoE)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void cadastrarVenda(Venda venda) {
		vendas.add(venda);
	}

	public void cadastrarVenda(List<Produto> produtos, double total, Cliente cliente, Data dataDaCompra,
			String usuario) {
		Venda venda = new Venda(produtos, total, cliente, dataDaCompra, usuario);
		vendas.add(venda);
	}

	@Override
	public boolean verificarLogin(String login, String senha) {
		for (Usuario usuario : this.usuarios) {
			if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Usuario> pesquisaUsuariosComNomeComecadoCom(String prefixo) {
		List<Usuario> listaRetornada = new ArrayList<Usuario>();
		for (Usuario usuario : this.usuarios) {
			if (usuario.getNome().startsWith(prefixo)) {
				listaRetornada.add(usuario);
			}
		}
		return listaRetornada;
	}

	@Override
	public List<Cliente> obterListaDeClientes() {
		return this.clientes;
	}

	@Override
	public List<Usuario> recuperaUsuarios() throws IOException {
		// TODO Auto-generated method stub

		List<Usuario> listaRetornadaDeUsuariosRecuperados = new ArrayList<Usuario>();

		for (String dado : gravador.recuperaTextoDeArquivo(ARQUIVO_USUARIOS)) {
			String[] item = dado.split("\\|");

			Usuario usuario = new Usuario(item[0], item[1], item[2], Integer.parseInt(item[3]));
			listaRetornadaDeUsuariosRecuperados.add(usuario);

		}

		return listaRetornadaDeUsuariosRecuperados;

	}

	@Override
	public List<Cliente> recuperaClientes() throws IOException {
		// TODO Auto-generated method stub

		List<Cliente> listaRetornadaDeClientesRecuperados = new ArrayList<Cliente>();

		List<String> lista = gravador.recuperaTextoDeArquivo(ARQUIVO_CLIENTES);
		for (String linha : lista) {
			String[] arrayDeItem = linha.split("\\|");
			if (arrayDeItem[0].equals(Cliente.CLIENTE_PF)) {
				Cliente cliente = new ClientePF(arrayDeItem[1], arrayDeItem[2]);
				listaRetornadaDeClientesRecuperados.add(cliente);
			} else if (arrayDeItem[0].equals(Cliente.CLIENTE_PJ)) {
				Cliente cliente = new ClientePJ(arrayDeItem[1], arrayDeItem[2]);
				listaRetornadaDeClientesRecuperados.add(cliente);
			}

		}
		return listaRetornadaDeClientesRecuperados;

		// throw new ProdutoJaExisteException("Já existe um produto com este código: " +
		// produto.getCodigo());

	}

	@Override
	public List<Produto> recuperaProdutos() throws IOException {
		// TODO Auto-generated method stub

		List<Produto> listaRetornadaDeProdutosRecuperados = new ArrayList<Produto>();

		for (String dado : gravador.recuperaTextoDeArquivo(ARQUIVO_PRODUTOS)) {
			String[] item = dado.split("\\|");

			String codigoDoProduto = item[0];
			String nomeDoProduto = item[1];
			double precoAtacado = Double.parseDouble(item[2]);
			double precoVarejo = Double.parseDouble(item[3]);
			boolean ehPerecivel = Boolean.parseBoolean(item[4]);
			Data dataDeValidade = new Data(item[5]);

			Produto produto = new Produto(codigoDoProduto, nomeDoProduto, precoAtacado, precoVarejo, ehPerecivel,
					dataDeValidade);
			listaRetornadaDeProdutosRecuperados.add(produto);
		}

		return listaRetornadaDeProdutosRecuperados;
	}

	@Override
	public List<Venda> recuperaVendas() throws IOException {
		// TODO Auto-generated method stub

		List<Venda> listaRetornadaDeVendasRecuperados = new ArrayList<Venda>();

		for (String dado : gravador.recuperaTextoDeArquivo(ARQUIVO_VENDAS)) {
			String[] item = dado.split("\\|");
			String[] itensVendidos = item[8].split("\\&");

			List<Produto> listaDeProdutos = new ArrayList<Produto>();

			for (int k = 0; k < itensVendidos.length; k = k + 2) {
				String codigoDoProduto = itensVendidos[k];
				String nomeDoProduto = itensVendidos[k + 1];
				Produto produto = new Produto(codigoDoProduto, nomeDoProduto);
				listaDeProdutos.add(produto);
			}

			int dia = Integer.parseInt(item[0]);
			int mes = Integer.parseInt(item[1]);
			int ano = Integer.parseInt(item[2]);

			String nomeDoCliente = item[5];
			String codigoDoCliente = item[4];

			double total = Double.parseDouble(item[6]);

			String loginDoUsuario = item[7];

			String tipoDeCliente = item[3];

			if (tipoDeCliente.equals(Cliente.CLIENTE_PF)) {

				Venda venda = new Venda(listaDeProdutos, total, new ClientePF(nomeDoCliente, codigoDoCliente),
						new Data(dia, mes, ano), loginDoUsuario);
				listaRetornadaDeVendasRecuperados.add(venda);
				System.out.println(vendas);
			} else if (tipoDeCliente.equals(Cliente.CLIENTE_PJ)) {

				Venda venda = new Venda(listaDeProdutos, total, new ClientePJ(nomeDoCliente, codigoDoCliente),
						new Data(dia, mes, ano), loginDoUsuario);
				listaRetornadaDeVendasRecuperados.add(venda);
			}

		}
		return listaRetornadaDeVendasRecuperados;
	}

	@Override
	public void gravaUsuarios() throws IOException {
		// TODO Auto-generated method stub
		List<String> usuarios = new ArrayList<String>();
		String usuario = "";
		for (Usuario u : this.usuarios) {
			usuario = u.getNome() + "|" + u.getLogin() + "|" + u.getSenha() + "|" + u.getNumeroDeVendasRealizadas();
			usuarios.add(usuario);
		}

		gravador.gravaTextoEmArquivo(usuarios, ARQUIVO_USUARIOS);
	}

	@Override
	public void gravaClientes() throws IOException {
		// TODO Auto-generated method stub
		List<String> clientes = new ArrayList<String>();
		String cliente = "";
		for (Cliente c : this.clientes) {
			if (c.getTipo().equals(Cliente.CLIENTE_PF)) {
				cliente = Cliente.CLIENTE_PF + "|" + c.getNome() + "|" + c.getCodigo();
				clientes.add(cliente);
			} else {
				cliente = Cliente.CLIENTE_PJ + "|" + c.getNome() + "|" + c.getCodigo();
				clientes.add(cliente);
			}

		}

		gravador.gravaTextoEmArquivo(clientes, ARQUIVO_CLIENTES);
	}

	@Override
	public void gravaProdutos() throws IOException {
		// TODO Auto-generated method stub
		List<String> produtos = new ArrayList<String>();
		String produto = "";
		for (Produto p : this.produtos) {
			produto = p.getCodigo() + "|" + p.getNome() + "|" + p.getPrecoAtacado() + "|" + p.getPrecoVarejo() + "|"
					+ p.getPerecivel() + "|" + p.getDataDeValidade();
			produtos.add(produto);
		}

		gravador.gravaTextoEmArquivo(produtos, ARQUIVO_PRODUTOS);

	}

	@Override
	public void gravaVendas() throws IOException {
		// TODO Auto-generated method stub
		List<String> vendas = new ArrayList<String>();
		String venda = "";
		for (Venda v : this.vendas) {
			venda = v.getDataDaCompra().getDia() + "|" // 0
					+ v.getDataDaCompra().getMes() + "|"// 1
					+ v.getDataDaCompra().getAno() + "|" // 2

					+ v.getCliente().getTipo() + "|" // 3

					+ v.getCliente().getCodigo() + "|" // 4
					+ v.getCliente().getNome() + "|" // 5
					+ v.getTotal() + "|"// 6
					+ v.getUsuario() + "|"; // 7

			for (Produto p : v.getListaDeProdutosVendidos()) {
				venda += p.getCodigo() + "&" + p.getNome() + "&";// 8
			}
			vendas.add(venda);

		}

		gravador.gravaTextoEmArquivo(vendas, ARQUIVO_VENDAS);

	}

}
