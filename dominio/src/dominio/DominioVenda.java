package dominio;

import entidades.Venda;
import estruturaDados.Fila;
import utilidades.ExcecaoSql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexaoBancoDados.Conexao;

public class DominioVenda {
	
	private Fila<Venda> vendas;
	
	private static final String InsertString = "INSERT INTO VENDA (Produto, Quantidade, ValorUn, Desconto, AliquotaICMS, Obs) "+
	"VALUES (?, ?, ?, ?, ?, ?)";
	
	//https://docs.microsoft.com/pt-br/sql/connect/jdbc/using-basic-data-types
	
	public DominioVenda() {
		this.vendas = new Fila<Venda>();
	}
	
	public void realizarVenda(Venda venda) {
		this.vendas.enfileirar(venda);		
	}
	
	public void gravarVendas() throws ExcecaoSql{
		PreparedStatement preparedStatement = null;
		try {
			
			for (int i = 0; i < vendas.retornaTamanhoFila(); i++) {
				Venda venda = vendas.desenfileirar();
				
				preparedStatement = Conexao.getInstance().buscarConexao().prepareStatement(InsertString);
				preparedStatement.setString(0, venda.getCodigoProduto());
				preparedStatement.setInt(1, venda.getQuantidade());
				preparedStatement.setBigDecimal(2, venda.getValorUn());
				preparedStatement.setBigDecimal(3, venda.getDesconto());
				preparedStatement.setBigDecimal(4, venda.getAliquotaICMS());
				preparedStatement.setString(5, venda.getObs());
				
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
			
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}
	}

}
