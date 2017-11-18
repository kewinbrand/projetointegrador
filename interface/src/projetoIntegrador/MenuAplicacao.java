package projetoIntegrador;


import javax.swing.table.AbstractTableModel;

import entidades.Produto;
import entidades.Venda;
import estruturaDados.Fila;
import sistemaVendas.SistemaVendas;
import utilidades.ExcecaoSql;

public class MenuAplicacao {
	
	private static SistemaVendas sistema;
	
	static {
		sistema = new SistemaVendas();
		try {
			sistema.IniciarSistema();
		} catch (ExcecaoSql e) {
			e.printStackTrace();
		}
	}
	
	public static void abrirVendas() throws ExcecaoSql {
		Fila<Produto> produtos = sistema.buscarProdutos();
		Compra compra = Compra.novaVenda(sistema);			
		int tam = produtos.retornaTamanhoFila();
		for (int i = 0; i <= tam; i++) {
			Produto produto = produtos.desenfileirar();
			if(produto != null) {
				compra.comboProduto.addItem(produto.getCodigoProduto());
			}
		}	
		compra.setVisible(true);		
	}
	
	public static void abrirEstoque() throws ExcecaoSql {
		Fila<Produto> produtos = sistema.buscarProdutos();
		AbstractTableModel model = new TableModelProduto(produtos);
		Estoque estoque = Estoque.abrirEstoque();
		estoque.table.setModel(model);
		estoque.setVisible(true);
	}
	
	public static void abrirCarrinho() {
		Fila<Venda> vendas = sistema.buscarVendasEnfileiradas();
		AbstractTableModel model = new TableModelVenda(vendas);		
		Carrinho carrinho = Carrinho.mostrarCarrinho();
		carrinho.table.setModel(model);
		carrinho.setVisible(true);
	}
	
	public static int quantidadeItensCarrinho() {
		return sistema.buscarVendasEnfileiradas().retornaTamanhoFila();
	}
	
	public static void recriarBancoDados() throws ExcecaoSql {
		sistema.recriarBancoDados();
	}

}
