package sisMercadinhoV_Final;
// DEIXAR PARA GRAVAR TUDO APENAS NO FINAL, PARA N�O FICAR UM C�DIGO MUITO SUJO

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProgramaMercadinho {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		SistemaMercado sistema = new SistemaMercado();

		try {
			List<Cliente> clientes = sistema.recuperaClientes();
			for (Cliente cliente : clientes) {
				try {
					sistema.cadastrarCliente(cliente);
				} catch (ClienteJaExisteException e) {
					// TODO Auto-generated catch block
					System.out.println("ERRO: N�O FOI POSS�VEL CADASTRAR LISTA DE CLIENTES RECUPERADO");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("N�O FOI POSSIVEL RECUPERAR A LISTA DE CLIENTES");
		}

		try {
			List<Produto> produtos = sistema.recuperaProdutos();
			for (Produto produto : produtos) {
				try {
					sistema.cadastrarProduto(produto);
				} catch (ProdutoJaExisteException e) {
					// TODO Auto-generated catch block
					System.out.println("ERRO: N�O FOI POSSS�VEL CADASTRAR LISTA DE PRODUTOS RECUPERADOS");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("N�O FOI POSSIVEL RECUPERAR A LISTA DE PRODUTOS");
		}

		try {
			List<Usuario> usuarios = sistema.recuperaUsuarios();
			if (usuarios.size() == 0) {
				System.out.print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n"
						+ "                   MERCADINHO RIO TINTO                            \n"
						+ "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n" + "PRIMEIRO ACESSO\n"
						+ "-------------------------------------------------------------------"
						+ "\nINFORME UM NOME DO USU�RIO: ");
				String nomeDeUsuarioDoPrimeiroLogin = input.nextLine().toUpperCase();
				System.out.print("INFORME O LOGIN DE ACESSO: ");
				String loginDeUsuarioDoPrimeiroLogin = input.nextLine();
				System.out.print("INFORME A SENHA DE ACESSO: ");
				String senhaDeUsuarioDoPrimeiroLogin = input.nextLine();
				Usuario primeiroUsuario = new Usuario(nomeDeUsuarioDoPrimeiroLogin, loginDeUsuarioDoPrimeiroLogin,
						senhaDeUsuarioDoPrimeiroLogin, 0);
				try {
					sistema.cadastrarUsuario(primeiroUsuario);
				} catch (UsuarioJaExisteException e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
				}
			} else {
				for (Usuario usuario : usuarios) {
					try {
						sistema.cadastrarUsuario(usuario);
					} catch (UsuarioJaExisteException e) {
						// TODO Auto-generated catch block
						System.out.println("ERRO: N�O FOI POSS�VEL CADASTRAR A LISTA DE USU�RIOS RECUPERADOS");
					}

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("N�O FOI POSSIVEL RECUPERAR A LISTA DE USU�RIOS");
		}

		try {
			List<Venda> vendas = sistema.recuperaVendas();
			for (Venda venda : vendas) {
				sistema.cadastrarVenda(venda);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("N�O FOI POSSIVEL RECUPERAR A LISTA DE VENDAS");
		}

		int tentativasDeLogin = 3;
		boolean continuarLogado = true;

		while (tentativasDeLogin > 0 && continuarLogado) {
			System.out.print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n"
					+ "                   MERCADINHO RIO TINTO                            \n"
					+ "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n" + "LOGIN: ");
			String login = input.nextLine();
			System.out.print("SENHA: ");
			String senha = input.nextLine();

			if (sistema.verificarLogin(login, senha)) {
				boolean continuarComOLogin = true;
				while (continuarComOLogin) {

					System.out.print("\nBem vindo ao Sistema do Mercadinho Rio Tinto\n");
					System.out.print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n"
							+ "                   MERCADINHO RIO TINTO                            \n"
							+ "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + "\nMENU\n"
							+ "-------------------------------------------------------------------\n"
							+ "1.  CADASTRAR USU�RIO NO SISTEMA\n" + "2.  CADASTRAR CLIENTE NO SISTEMA\n"
							+ "3.  CADASTRAR PRODUTO NO ESTOQUE\n" + "4.  CADASTRAR COMPRA NO SISTEMA\n"
							+ "5.  OBTER REL�TORIO DE VENDAS REALIZADAS\n"
							+ "6.  OBTER REL�TORIO DE VENDAS REALIZADAS POR USU�RIO\n"
							+ "7.  PESQUISAR CLIENTE CADASTRADOS NO SISTEMA\n"
							+ "8.  PESQUISAR PRODUTO CADASTRADOS NO ESTOQUE\n"
							+ "9.  PESQUISAR USU�RIO CADASTRADOS NO SISTEMA\n" + "10. LISTAR ALIMENTOS PEREC�VEIS\n"
							+ "11. LISTAR ALIMENTOS N�O PEREC�VEIS\n" + "12. SAIR\n");

					System.out.print("\nDigite o n�mero da op��o acima: ");
					try {
						int opcao = Integer.parseInt(input.nextLine());

						switch (opcao) {
						case 1:
							boolean confirmar = true;
							while (confirmar) {
								System.out.print("\nCADASTRAR USU�RIOS\n"
										+ "-------------------------------------------------------------------\n"
										+ "INFORME O NOME DO USU�RIO: ");
								String nome = input.nextLine().toUpperCase();
								System.out.print("INFORME O LOGIN DO USU�RIO: ");
								String novoLogin = input.nextLine();
								System.out.print("INFORME A SENHA DO USU�RIO: ");
								String novaSenha = input.nextLine();

								System.out.println("\nCONFIRME OS DADOS ABAIXO: \n" + "USU�RIO: " + nome + "\nLOGIN: "
										+ novoLogin + "\nSENHA: " + novaSenha);

								System.out.print(
										"\nDIGITE '1' SE OS DADOS ESTIVEREM CORRETOS,\nSE N�O DIGITE '2' PARA CORRIGIR: ");

								int opConfirmar = Integer.parseInt(input.nextLine());

								if (opConfirmar == 1) {
									try {
										sistema.cadastrarUsuario(new Usuario(nome, novoLogin, novaSenha, 0));
									} catch (UsuarioJaExisteException e) {
										// TODO Auto-generated catch block
										System.out.print("\nJ� EXISTE USU�RIO COM ESTE LOGIN: " + novoLogin + "\n");
										;
									}
									confirmar = false;

								}

							}
							System.out.println("\nUSU�RIO CADASTRADO COM SUCESSO!");

							break;

						case 2:
							// CADASTRAR CLIENTE NO SISTEMA

							boolean continuarCliente = true;

							while (continuarCliente) {

								System.out.print("\nCADASTRAR CLIENTE\n"
										+ "-------------------------------------------------------------------\n"
										+ "DIGITE '1' PARA CADASTRAR PESSOA F�SICA\nOU '2' PARA PEESOA JUR�DICA: ");
								int opPessoa = Integer.parseInt(input.nextLine());
								if (opPessoa == 1) {
									System.out.print("\nINFORME O NOME DO CLIENTE: ");
									String novoNomeClientePF = input.nextLine().toUpperCase();
									System.out.print("INFORME O CPF DO CLIENTE: ");
									String cpf = input.nextLine();

									System.out.println("\nCONFIRME OS DADOS ABAIXO: \n" + "NOME: " + novoNomeClientePF
											+ "\nCPF: " + cpf);

									System.out.print(
											"\nDIGITE '1' SE OS DADOS ESTIVEREM CORRETOS,\nSE N�O DIGITE '2' PARA CORRIGIR: ");

									int opConfirmarCliente = Integer.parseInt(input.nextLine());

									if (opConfirmarCliente == 1) {

										try {
											System.out.println("\nCLIENTE CADASTRADO CCM SUCESSO!");
											sistema.cadastrarCliente(new ClientePF(novoNomeClientePF, cpf));
											continuarCliente = false;
										} catch (ClienteJaExisteException e) {
											// TODO Auto-generated catch block
											e.getMessage();
										}

									}

								} else if (opPessoa == 2) {
									System.out.print("\nINFORME O NOME DO CLIENTE: ");
									String novoNomeClientePJ = input.nextLine().toUpperCase();
									System.out.print("INFORME O CNPJ DO CLIENTE: ");
									String cnpj = input.nextLine();

									System.out.println("\nCONFIRME OS DADOS ABAIXO: \n" + "NOME: " + novoNomeClientePJ
											+ "\nCNPJ: " + cnpj);

									System.out.print(
											"\nDIGITE '1' SE OS DADOS ESTIVEREM CORRETOS,\nSE N�O DIGITE '2' PARA CORRIGIR: ");

									int opConfirmarCliente = Integer.parseInt(input.nextLine());

									if (opConfirmarCliente == 1) {

										try {
											System.out.println("\nCLIENTE CADASTRADO CCM SUCESSO!");
											sistema.cadastrarCliente(new ClientePJ(novoNomeClientePJ, cnpj));
											continuarCliente = false;
										} catch (ClienteJaExisteException e) {
											// TODO Auto-generated catch block
											e.getMessage();
										}

									}

								}
							}

							break;

						case 3:
							// CADASTRA PRODUTO NO ESTOQUE
							boolean confirmarProduto = false;
							while (!confirmarProduto) {
								System.out.print("\nCADASTRAR PRODUTOS\n"
										+ "-------------------------------------------------------------------\n"
										+ "INFORME O NOME DO PRODUTO: ");
								String nomeDoProduto = input.nextLine().toUpperCase();
								System.out.print("INFORME O C�DIGO DO PRODUTO: ");
								String codigo = input.nextLine();
								System.out.print("INFORME O PRE�O DO PRODUTO NO ATACADO: ");
								Double precoAtacado = Double.parseDouble(input.nextLine());
								System.out.print("INFORME O PRE�O DO PRODUTO NO VAREJO: ");
								Double precoVarejo = Double.parseDouble(input.nextLine());
								System.out.print("O ALIMENTO � PEREC�VEL (SIM/N�O)? ");
								String ehPerecivelStr = input.nextLine().toUpperCase();

								boolean ehPerecivel;

								if (ehPerecivelStr.startsWith("S")) {
									ehPerecivel = true;
								} else {
									ehPerecivel = false;
								}

								System.out.print("QUAL � A DATA DE V�LIDADE DO PRODUTO (dd/mm/aaaa)? ");
								String[] dataDeValidade = input.nextLine().split("/");

								int dia = Integer.parseInt(dataDeValidade[0]);
								int mes = Integer.parseInt(dataDeValidade[1]);
								int ano = Integer.parseInt(dataDeValidade[2]);

								System.out.println("\nCONFIRME OS DADOS ABAIXO: \n" + "\nC�DIGO: " + codigo
										+ "\nNOME DO PRODUTO: " + nomeDoProduto

										+ "\nPRE�O ATACADO: " + precoAtacado + "\nPRE�O VAREJO: " + precoVarejo);
								if (ehPerecivel == true) {
									System.out.print("� UM ALIMENTO PEREC�VEL");
								} else {
									System.out.print("N�O � UM ALIMENTO PEREC�VEL");
								}

								System.out.println("\nDATA DE V�LIDADE: " + dia + "/" + mes + "/" + ano);
								System.out.print(
										"\nDIGITE '1' SE OS DADOS ESTIVEREM CORRETOS,\nSE N�O DIGITE '2' PARA CORRIGIR: ");

								int opConfirmarProduto = Integer.parseInt(input.nextLine());

								if (opConfirmarProduto == 1) {
									try {

										Produto novoProduto = new Produto(codigo, nomeDoProduto, precoAtacado,
												precoVarejo, ehPerecivel, new Data(dia, mes, ano));
										sistema.cadastrarProduto(novoProduto);
										confirmarProduto = true;

									} catch (ProdutoJaExisteException e) {
										// TODO Auto-generated catch block
										e.getMessage();
									}
								}
							}
							break;

						case 4:
							// CADASTRA COMPRA NO SISTEMA
							boolean confirmarVenda = false;

							double total = 0;
							List<Produto> listaDeCompras = new ArrayList<Produto>();

							while (!confirmarVenda) {
								System.out.print("\nCADASTRAR COMPRAS\n"
										+ "-------------------------------------------------------------------\n"
										+ "INFORME O CPF OU CNPJ DO CLIENTE: ");
								String codigoDoCliente = input.nextLine();
								System.out.println("PARA SAIR DO SISTEMA DO CAIXA DIGITE 'SAIR' QUANDO PEDIR"
										+ "\nPARA INFORMAR O C�DIGO DO ITEM");
								for (Cliente c : sistema.getClientes()) {
									if (c.getCodigo().equals(codigoDoCliente)) {

										boolean continuarAInformarCodigo = false;

										while (!continuarAInformarCodigo) {
											System.out.print("\n\nINFORME O C�GIDO DO PRODUTO: ");
											String codigoDoProduto = input.nextLine().toUpperCase();
											if (codigoDoProduto.equals("SAIR")) {
												continuarAInformarCodigo = true;
											} else {
												for (Produto p : sistema.getProdutos()) {
													if (p.getCodigo().equals(codigoDoProduto)) {
														listaDeCompras.add(p);
														System.out.print("INFORME A QUANTIDADE DE ITENS DE: \n"
																+ p.getNome() + " = ");
														int quantDeProdutos = Integer.parseInt(input.nextLine());
														double subtotal = 0.0;

														if (c.getTipo() == Cliente.CLIENTE_PF) {

															subtotal = quantDeProdutos * p.getPrecoVarejo();

														} else if (c.getTipo() == Cliente.CLIENTE_PJ) {

															subtotal = quantDeProdutos * p.getPrecoAtacado();

														}

														System.out.println(
																"                                         SUBTOTAL = "+
																subtotal);
														total = total + subtotal;
													} // if
												} // for
											} // if
										} // while

										Date dataAtual = new Date();
										String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM)
												.format(dataAtual);

										System.out.println(
												"-------------------------------------------------------------------");
										System.out.println("\nNOTA FISCAL: \n" + "NOME DO CLIENTE: "+
												c.getNome() + "                       " + dStr + "\n");
										System.out.println("\nC�DIGO            NOME DO PRODUTO");

										for (Produto produto : listaDeCompras) {
											System.out.println(produto.getCodigo() + "     "+ produto.getNome());
										}
										System.out.println(
												"\n                                          TOTAL =  " + total);

										System.out.print("DIGITE '1' PARA CONFIRMAR A VENDA: ");
										int opcaoDeCompra = Integer.parseInt(input.nextLine());
										if (opcaoDeCompra == 1) {
											System.out.println(
													"-------------------------------------------------------------------");
											System.out.println("\nNOTA FISCAL: \n" + "NOME DO CLIENTE: " +
													c.getNome() + "                       " + dStr + "\n");
											System.out.println("\nC�DIGO            NOME DO PRODUTO");

											for (Produto produto : listaDeCompras) {
												System.out.println(produto.getCodigo() + "  " + produto.getNome());
											}
											System.out.println(
													"\n                                          TOTAL = " + total);

											Data data = new Data(dStr);
											Venda novaVenda = new Venda(listaDeCompras, total, c, data, login);
											for (Usuario u : sistema.getUsuarios()) {
												if (u.getLogin().equals(login)) {
													int numeroDeVendas = u.getNumeroDeVendasRealizadas();
													numeroDeVendas++;
													u.setNumeroDeVendasRealizadas(numeroDeVendas);
												}
											}

											sistema.cadastrarVenda(novaVenda);
											System.out.println("\nVENDA CADASTRADA COM SUCESSO!");
											confirmarVenda = true;
										} /* if */
									} // if

								} // for
							} // while primeiro

							break;
						case 5:
							// OBTER REL�TORIO DE VENDAS REALIZADAS

							System.out.print("\nRELAT�RIO DE VENDAS REALIZADAS\n"
									+ "-------------------------------------------------------------------\n");

							double valorTotalArrecadado = 0.0;
							int numeroDeVendas = 0;

							System.out.println("LISTA DE PRODUTOS VENDIDOS\n");
							List<Venda> listaDeVendas = sistema.getVendas();

							if (listaDeVendas.size() == 0) {
								System.out.println("NENHUMA VENDA ENCONTRADA");
							} else {

								for (Venda v : listaDeVendas) {
									System.out.println("DATA: " + v.getDataDaCompra() + ", TOTAL: " + v.getTotal() + " "
											+ "\nNOME DO CLIENTE: " + v.getCliente().getNome() + " ("
											+ v.getCliente().getCodigo() + ")\n");

									numeroDeVendas++;
									valorTotalArrecadado += v.getTotal();

								}
								System.out
										.println("\n-------------------------------------------------------------------"
												+ "\nVALOR TOTAL ARRECADADO: " + valorTotalArrecadado
												+ "\nN�MERO DE VENDAS: " + numeroDeVendas
												+ "\n-------------------------------------------------------------------");
							}
							break;
						case 6:
							// OBTER REL�TORIO DE VENDAS REALIZADAS POR USU�RIO
							System.out.print("\nRELAT�RIO DE VENDAS REALIZADAS POR CADA USU�RIO\n"
									+ "-------------------------------------------------------------------\n");

							for (Usuario u : sistema.getUsuarios()) {
								System.out.println("NOME DE USU�RIO = " + u.getNome() + ", LOGIN: " + u.getLogin()
										+ ", N�MERO DE VENDAS = " + u.getNumeroDeVendasRealizadas());

							}

							break;
						case 7:
							// PESQUISAR CLIENTE CADASTRADOS NO SISTEMA
							System.out.print("\nPESQUISAR CLIENTES CADASTRADOS NO SISTEMA\n"
									+ "-------------------------------------------------------------------\n"
									+ "INFORME O CPF, CNPJ OU NOME DO CLIENTE: ");

							int contNumerosDeClientesPrintados = 0;

							String codigoDoClientePesquisado = input.nextLine().toUpperCase();
							for (Cliente c : sistema.getClientes()) {
								if (c.getCodigo().equals(codigoDoClientePesquisado)) {
									System.out.println("NOME DO CLIENTE: " + c.getNome() + " (" + c.getCodigo() + ")");
									contNumerosDeClientesPrintados++;
								} else if (c.getNome().equals(codigoDoClientePesquisado)) {
									System.out.println("NOME DO CLIENTE: " + c.getNome() + " (" + c.getCodigo() + ")");
									contNumerosDeClientesPrintados++;
								} else if (c.getNome().startsWith(codigoDoClientePesquisado)) {
									System.out.println("NOME DO CLIENTE: " + c.getNome() + " (" + c.getCodigo() + ")");
									contNumerosDeClientesPrintados++;
								} else if (c.getCodigo().startsWith(codigoDoClientePesquisado)) {
									System.out.println("NOME DO CLIENTE: " + c.getNome() + " (" + c.getCodigo() + ")");
									contNumerosDeClientesPrintados++;
								}
							}

							if (contNumerosDeClientesPrintados == 0) {
								System.out.println("NENHUM USU�RIO ENCONTRADO");
							}

							System.out.println("\n");
							break;
						case 8:
							// PESQUISAR PRODUTO CADASTRADOS NO ESTOQUE
							System.out.print("\nPESQUISAR PRODUTOS CADASTRADOS NO ESTOQUE\n"
									+ "-------------------------------------------------------------------\n"
									+ "INFORME O C�DIGO OU NOME DO PRODUTO PESQUISADO: ");
							int contNumerosDeProdutosPrintados = 0;
							String codigoDoProdutoPesquisado = input.nextLine().toUpperCase();
							for (Produto p : sistema.getProdutos()) {
								if (p.getCodigo().equals(codigoDoProdutoPesquisado)) {

									System.out.println("NOME = " + p.getNome() + ", C�DIGO = " + p.getCodigo()
											+ ", PRE�O ATACADO = " + p.getPrecoAtacado() + ", PRE�O VAREJO = "
											+ p.getPrecoVarejo());

									contNumerosDeProdutosPrintados++;
								} else if (p.getNome().equals(codigoDoProdutoPesquisado)) {
									System.out.println("NOME = " + p.getNome() + ", C�DIGO = " + p.getCodigo()
											+ ", PRE�O ATACADO = " + p.getPrecoAtacado() + ", PRE�O VAREJO = "
											+ p.getPrecoVarejo());
									contNumerosDeProdutosPrintados++;
								} else if (p.getNome().startsWith(codigoDoProdutoPesquisado)) {
									System.out.println("NOME = " + p.getNome() + ", C�DIGO = " + p.getCodigo()
											+ ", PRE�O ATACADO = " + p.getPrecoAtacado() + ", PRE�O VAREJO = "
											+ p.getPrecoVarejo());
									contNumerosDeProdutosPrintados++;
								} else if (p.getCodigo().startsWith(codigoDoProdutoPesquisado)) {
									System.out.println("NOME = " + p.getNome() + ", C�DIGO = " + p.getCodigo()
											+ ", PRE�O ATACADO = " + p.getPrecoAtacado() + ", PRE�O VAREJO = "
											+ p.getPrecoVarejo());
									contNumerosDeProdutosPrintados++;
								}
							}

							if (contNumerosDeProdutosPrintados == 0) {
								System.out.println("NENHUM PRODUTO ENCONTRADO");
							}

							break;
						case 9:
							// PESQUISAR USU�RIO CADASTRADOS NO SISTEMA
							System.out.print("\nPESQUISAR USU�RIOS CADASTRADOS NO SISTEMA\n"
									+ "-------------------------------------------------------------------\n"
									+ "INFORME O LOGIN OU O NOME DO USU�RIO PESQUISADO: ");

							int contNumerosDeUsuariosPrintados = 0;

							String codigoDoUsuarioPesquisado = input.nextLine();
							for (Usuario u : sistema.getUsuarios()) {
								if (u.getNome().startsWith(codigoDoUsuarioPesquisado)) {
									System.out
											.println("NOME DE USU�RIO = " + u.getNome() + ", LOGIN = " + u.getLogin());
									contNumerosDeUsuariosPrintados++;
								} else if (u.getNome().equals(codigoDoUsuarioPesquisado)) {
									System.out
											.println("NOME DE USU�RIO = " + u.getNome() + ", LOGIN = " + u.getLogin());
									contNumerosDeUsuariosPrintados++;
								} else if (u.getLogin().startsWith(codigoDoUsuarioPesquisado)) {
									System.out
											.println("NOME DE USU�RIO = " + u.getNome() + ", LOGIN = " + u.getLogin());
									contNumerosDeUsuariosPrintados++;
								} else if (u.getLogin().equals(codigoDoUsuarioPesquisado)) {
									System.out
											.println("NOME DE USU�RIO = " + u.getNome() + ", LOGIN = " + u.getLogin());
									contNumerosDeUsuariosPrintados++;
								}
							}

							if (contNumerosDeUsuariosPrintados == 0) {
								System.out.println("\nNUNHUM USU�RIO ENCONTRADO");
							}

							break;

						case 10:
							// "10. LISTAR ALIMENTOS PEREC�VEIS\n"
							System.out.print("\nLISTAR ALIMENTOS PEREC�VEIS CADASTRADOS NO ESTOQUE\n"
									+ "-------------------------------------------------------------------\n");
							int contNumerosDeAlimentosPereciveisPrintados = 0;
							for (Produto p : sistema.getProdutos()) {
								boolean ehPerecivel = p.getPerecivel();
								if (ehPerecivel) {
									System.out.println("NOME = " + p.getNome() + ", C�DIGO = " + p.getCodigo()
											+ ", PRE�O ATACADO = " + p.getPrecoAtacado() + ", PRE�O VAREJO = "
											+ p.getPrecoVarejo());
									contNumerosDeAlimentosPereciveisPrintados++;
								}
							}

							if (contNumerosDeAlimentosPereciveisPrintados == 0) {
								System.out.println("\nNENHUM ALIMENTO PEREC�VEL FOI ENCONTRADO");
							}
							break;
						case 11:
							// + "11. LISTAR ALIMENTOS N�O PEREC�VEIS\n"
							System.out.print("\nLISTAR ALIMENTOS N�O PEREC�VEIS CADASTRADOS NO ESTOQUE\n"
									+ "-------------------------------------------------------------------\n");
							int contNumerosDeAlimentosNaoPereciveisPrintados = 0;
							for (Produto p : sistema.getProdutos()) {
								if (p.getPerecivel() == false) {
									System.out.println("NOME = " + p.getNome() + ", C�DIGO = " + p.getCodigo()
											+ ", PRE�O ATACADO = " + p.getPrecoAtacado() + ", PRE�O VAREJO = "
											+ p.getPrecoVarejo());
									contNumerosDeAlimentosNaoPereciveisPrintados++;
								}
							}

							if (contNumerosDeAlimentosNaoPereciveisPrintados == 0) {
								System.out.println("\nNUNHUM ALIMENTO N�O PEREC�VEL FOI ENCONTRADO");
							}
							break;

						case 12:
							try {
								sistema.gravaUsuarios();

							} catch (IOException e) {
								// TODO Auto-generated catch block
								System.out.println("\nN�O FOI POSS�VEL CADASTRAR LISTA DE USU�RIOS NO SISTEMA");
							}

							try {
								sistema.gravaClientes();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								System.out.println("N�O FOI POSS�VEL CADASTRAR LISTA DE CLIENTES NO SISTEMA");
							}

							try {
								sistema.gravaProdutos();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								System.out.println("N�O FOI POSS�VEL CADASTRAR LISTA DE PRODUTOS NO SISTEMA");
							}

							try {
								sistema.gravaVendas();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								System.out.println("N�O FOI POSS�VEL CADASTRAR LISTA DE VENDAS REALIZADAS NO SISTEMA");
							} // catch
							continuarComOLogin = false;
							continuarLogado = false;
							break;
						}
					} catch (NumberFormatException e) {
						System.out.println("\nOP��O INV�LIDA, TENTE NOVAMENTE");
					}
				}
			} else {
				System.out.println("Login ou senha inv�lido");
				tentativasDeLogin--;

			}

		}

		System.out.println("--------------------------------------------------------------- FIM");
		input.close();
	}

}
