package projetoIntegrador;


import java.awt.Window;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import conexaoBancoDados.Conexao;
import dominio.DominioProduto;
import dominio.RepositorioAbstract;
import dominio.RepositorioProduto;
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
		RepositorioAbstract<Produto> repositorio = new RepositorioProduto();
		Fila<Produto> produtos = repositorio.buscarEntidades();
		AbstractTableModel model = new TableModelProduto(new DominioProduto(), produtos);
		Estoque estoque = Estoque.abrirEstoque((TableModelProduto)model);
		estoque.table.setModel(model);
		estoque.table.setDefaultEditor(Integer.class, new CellEditorInteger(new JTextField()));
		estoque.table.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer());
		int tam = produtos.retornaTamanhoFila();
		JComboBox<String> comboBoxProdutos = new JComboBox<String>();
		for (int i = 0; i < tam; i++) {
			Produto produto = produtos.item(i);		
			comboBoxProdutos.addItem(produto.getCodigoProduto());
		}
		estoque.table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboBoxProdutos));		
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
	
	public static void gravarVendas() throws ExcecaoSql {
		sistema.gravarVendas();
	}
	
	public static void recriarBancoDados() throws ExcecaoSql {
		sistema.recriarBancoDados();
	}
	
	public static void sair() {
		try {
			Conexao.getInstance().desconectarBancoDados();
		} catch (SQLException e) {
			//podemos ignorar
		}
	}

}
