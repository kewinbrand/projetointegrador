package projetoIntegrador;


import java.awt.Window;

import javax.swing.table.AbstractTableModel;

import entidades.Produto;
import entidades.Venda;
import estruturaDados.Fila;
import sistemaVendas.SistemaVendas;
import utilidades.ExcecaoSql;
import utilidades.ValidacaoException;

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
		for (int i = 0; i < tam; i++) {
			Produto produto = produtos.desenfileirar();			
			compra.comboProduto.addItem(produto.getCodigoProduto());			
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
	
	public static void abrirPrimeiroItemCarrinho() throws ValidacaoException {
		Compra compra = Compra.novaVenda(sistema);
		Venda venda = sistema.buscarPrimeiraVendaEnfileirada();
		if(venda == null) {
			throw new ValidacaoException("Não há nenhuma venda.");
		}
		compra.comboProduto.addItem(venda.getCodigoProduto());
		compra.comboProduto.setSelectedItem(venda.getCodigoProduto());
		compra.textFieldAliq.setText(venda.getAliquotaICMS().toString());
		compra.textFieldDesc.setText(venda.getDesconto().toString());
		compra.textFieldObs.setText(venda.getObs());
		compra.textFieldQuantidade.setText(String.valueOf(venda.getQuantidade()));
		compra.textFieldValor.setText(venda.getValorUn().toString());
		compra.btnFinalizar.setVisible(false);
		compra.textFieldAliq.setEditable(false);
		compra.textFieldDesc.setEditable(false);
		compra.textFieldObs.setEditable(false);
		compra.textFieldQuantidade.setEditable(false);
		compra.textFieldValor.setEditable(false);
		configurarFormPadrao(compra);
	}
	
	public static void recriarBancoDados() throws ExcecaoSql {
		sistema.recriarBancoDados();
	}

}
