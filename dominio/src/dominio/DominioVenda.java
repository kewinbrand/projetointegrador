package dominio;

import entidades.Venda;
import estruturaDados.Fila;
import utilidades.ExcecaoSql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexaoBancoDados.Conexao;

public class DominioVenda {
	
	private Fila<Venda> vendas;
	
	
	/*private static final String InsertString = "INSERT INTO VENDA (Produto, Quantidade, ValorUn, Desconto, AliquotaICMS, Obs) "+
	"VALUES (?, ?, ?, ?, ?, ?)";*/
	
	private static final String ProcedureInsertString = "EXECUTE spInsereVenda ?, ?, ?, ?, ?, ?";
	
	//https://docs.microsoft.com/pt-br/sql/connect/jdbc/using-basic-data-types
	
	public DominioVenda() {
		this.vendas = new Fila<Venda>();
	}
	
	public void realizarVenda(Venda venda) {
		this.vendas.enfileirar(venda);		
	}
	
	public Fila<Venda> buscarVendasEnfileiradas(){
		return this.vendas;
	}
	
	public void gravarVendas() throws ExcecaoSql {
		PreparedStatement preparedStatement = null;
		try {
			
			for (int i = 0; i < vendas.retornaTamanhoFila(); i++) {
				Venda venda = vendas.desenfileirar();
				
				preparedStatement = Conexao.getInstance().buscarConexao().prepareStatement(ProcedureInsertString);
				preparedStatement.setString(1, venda.getCodigoProduto());
				preparedStatement.setInt(2, venda.getQuantidade());
				preparedStatement.setBigDecimal(3, venda.getValorUn());
				preparedStatement.setBigDecimal(4, venda.getDesconto());
				preparedStatement.setBigDecimal(5, venda.getAliquotaICMS());
				preparedStatement.setString(6, venda.getObs());
				
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
			
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}
	}

}
