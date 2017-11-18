package projetoIntegrador;

import javax.swing.table.AbstractTableModel;

import entidades.Venda;
import estruturaDados.Fila;

public class TableModelVenda extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private String[] colunas = {"Codigo Produto" , "Qtd." ,"Vlr. Unit.", "Desc.", "Alíq. ICMS", "Obs."};
	Fila<Venda> vendas;
	
	public TableModelVenda(Fila<Venda> vendas) {
		super();
		this.vendas = vendas;
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
	public int getRowCount() {
		return this.vendas.retornaTamanhoFila();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch(coluna) {
			case 0: return this.vendas.item(coluna).getCodigoProduto();
			case 1: return this.vendas.item(coluna).getQuantidade();
			case 2: return this.vendas.item(coluna).getValorUn();
			case 3: return this.vendas.item(coluna).getDesconto();
			case 4: return this.vendas.item(coluna).getAliquotaICMS();
			case 5: return this.vendas.item(coluna).getObs();
		}
		return null;
	}

}
