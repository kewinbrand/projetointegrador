package projetoIntegrador;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dominio.DominioProduto;
import entidades.Produto;
import estruturaDados.Fila;
import utilidades.ExcecaoSql;
import utilidades.ValidacaoException;

public class TableModelProduto extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private String[] colunas = {"Codigo Produto" , "Descrição" ,"Qtd. Mín. Estoque", "Qtd. Lote Comprar"};
	
	private ArrayList<Produto> produtos;
	private DominioProduto dominioProduto;
	
	public TableModelProduto(DominioProduto dominioProduto, Fila<Produto> produtos) {
		super();
		this.dominioProduto = dominioProduto;
		this.produtos = new ArrayList<Produto>();
		int tam = produtos.retornaTamanhoFila();
		for (int i = 0; i < tam; i++) {
			Produto produto = produtos.desenfileirar();
			this.produtos.add(produto);
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	@Override
	public int getColumnCount() {
		return this.colunas.length;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return !(columnIndex == 0);
	}

	@Override
	public int getRowCount() {
		return this.produtos.size();
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Produto produto = this.produtos.get(rowIndex);
		int value = 0;
		switch(columnIndex) {
			case 1: produto.setNomeCompleto((String)aValue);
					break;
			case 2: value = Integer.parseInt((String)aValue);
				    produto.setQtdMinEstoque(value);
					break;
			case 3: value = Integer.parseInt((String)aValue);
					produto.setQtdLoteComprar(value);
					break;
		}
		try {
			this.dominioProduto.atualizarProduto(produto);
		} catch (ExcecaoSql e) {
			defaultExceptionHandler(e);
		}
		super.setValueAt(aValue, rowIndex, columnIndex);
	}
	
	@Override
    public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		  case 0: return String.class;
		  case 1: return String.class;
		  case 2: return Integer.class;
		  case 3: return Integer.class;
		}
		return null;
    }

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch(coluna) {
			case 0: return this.produtos.get(linha).getCodigoProduto();
			case 1: return this.produtos.get(linha).getNomeCompleto();
			case 2: return this.produtos.get(linha).getQtdMinEstoque();
			case 3: return this.produtos.get(linha).getQtdLoteComprar();
		}
		return null;
	}
	
	public void adicionarProduto(String codigoProduto) throws ExcecaoSql, ValidacaoException {
		Produto produto = new Produto();
		produto.setCodigoProduto(codigoProduto);
		this.dominioProduto.adicionarProduto(produto);
		int tamanhoAntigo = getRowCount();
		this.produtos.add(produto);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);  
	}
	
	public void excluirProduto(int linha) throws ValidacaoException, ExcecaoSql {
		Produto produto = this.produtos.get(linha);
		this.dominioProduto.excluirProduto(produto);
		this.produtos.remove(produto);
		fireTableRowsDeleted(linha, linha);
	}
	
	private void defaultExceptionHandler(Exception e) {
		JOptionPane.showMessageDialog(null,  e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	}

}
