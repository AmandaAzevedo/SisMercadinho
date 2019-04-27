package sisMercadinhoV_Final;

import java.io.IOException;
import java.util.List;

public interface InterfaceSisMercado {

	public void cadastrarUsuario (Usuario usuario) throws UsuarioJaExisteException;
	public void cadastrarCliente(Cliente cliente) throws ClienteJaExisteException;
	public void cadastrarProduto(Produto produto) throws ProdutoJaExisteException;
	public void cadastrarVenda(Venda venda);
	
	public boolean verificarLogin(String login, String senha);
	
	public List<Usuario> pesquisaUsuariosComNomeComecadoCom(String prefixo);
	
	public List<Cliente> obterListaDeClientes();
	
	public List<Usuario> recuperaUsuarios() throws IOException;
	public List<Cliente> recuperaClientes() throws IOException;
	public List<Produto> recuperaProdutos() throws IOException;
	public List<Venda> recuperaVendas() throws IOException;
	
	public void gravaUsuarios() throws IOException;
	public void gravaClientes() throws IOException;
	public void gravaProdutos() throws IOException;
	public void gravaVendas() throws IOException;
	
	
}
