package projetoIntegrador;

import javax.swing.table.AbstractTableModel;

import entidades.Produto;
import estruturaDados.Fila;

public class TableModelProduto extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private String[] colunas = {"Codigo Produto" , "Descrição" ,"Qtd. Mín. Estoque", "Qtd. Lote Comprar"};
	Fila<Produto> produtos;
	
	public TableModelProduto(Fila<Produto> produtos) {
		super();
		this.produtos = produtos;
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.colunas.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.produtos.retornaTamanhoFila();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch(coluna) {
			case 0: return this.produtos.Item(linha).getCodigoProduto();
			case 1: return this.produtos.Item(linha).getNomeCompleto();
			case 2: return this.produtos.Item(linha).getQtdMinEstoque();
			case 3: return this.produtos.Item(linha).getQtdLoteComprar();
		}
		return null;
	}

}
