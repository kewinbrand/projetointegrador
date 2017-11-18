package projetoIntegrador;


import java.awt.Window;

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
	
	private static void configurarFormPadrao(Window form) {
		form.setLocationRelativeTo(null);
		form.setVisible(true);
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
		configurarFormPadrao(compra);
	}
	
	public static void abrirEstoque() throws ExcecaoSql {
		Fila<Produto> produtos = sistema.buscarProdutos();
		AbstractTableModel model = new TableModelProduto(produtos);
		Estoque estoque = Estoque.abrirEstoque();
		estoque.table.setModel(model);
		configurarFormPadrao(estoque);
	}
	
	public static void abrirCarrinho() {
		Fila<Venda> vendas = sistema.buscarVendasEnfileiradas();
		AbstractTableModel model = new TableModelVenda(vendas);		
		Carrinho carrinho = Carrinho.mostrarCarrinho();
		carrinho.table.setModel(model);
		configurarFormPadrao(carrinho);
	}
	
	public static int quantidadeItensCarrinho() {
		return sistema.buscarVendasEnfileiradas().retornaTamanhoFila();
	}
	
	public static void abrirPrimeiroItemCarrinho() {
		Compra compra = Compra.novaVenda(sistema);
		Venda venda = sistema.buscarPrimeiraVendaEnfileirada();
		compra.comboProduto.addItem(venda.getCodigoProduto());
		compra.comboProduto.setSelectedItem(venda.getCodigoProduto());
		compra.textFieldAliq.setText(venda.getAliquotaICMS().toString());
		compra.textFieldDesc.setText(venda.getDesconto().toString());
		compra.textFieldObs.setText(venda.getObs());
		compra.textFieldQuantidade.setText(String.valueOf(venda.getQuantidade()));
		compra.textFieldValor.setText(venda.getValorUn().toString());
		configurarFormPadrao(compra);
	}
	
	public static void recriarBancoDados() throws ExcecaoSql {
		sistema.recriarBancoDados();
	}

}
