package dominio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexaoBancoDados.Conexao;
import entidades.Venda;
import estruturaDados.Fila;
import utilidades.ExcecaoSql;

public class RepositorioVenda extends RepositorioAbstract<Venda>{
	
	private static final String ProcedureInsertString = "EXECUTE spInsereVenda ?, ?, ?, ?, ?, ?";
	private static final String SelectAllString = "SELECT Produto, Quantidade, ValorUn, Desconto, AliquotaICMS, Obs FROM VENDA";
	private static final String SelectOneString = "SELECT Produto, Quantidade, ValorUn, Desconto, AliquotaICMS, Obs FROM VENDA WHERE Produto = ?";
	
	@Override
	public void atualizarEntidade(Venda entidade) throws ExcecaoSql {
	}

	@Override
	public void inserirEntidade(Venda entidade) throws ExcecaoSql {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = Conexao.getInstance().buscarConexao().prepareStatement(ProcedureInsertString);
			preparedStatement.setString(1, entidade.getCodigoProduto());
			preparedStatement.setInt(2, entidade.getQuantidade());
			preparedStatement.setBigDecimal(3, entidade.getValorUn());
			preparedStatement.setBigDecimal(4, entidade.getDesconto());
			preparedStatement.setBigDecimal(5, entidade.getAliquotaICMS());
			preparedStatement.setString(6, entidade.getObs());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}
	}

	@Override
	public void excluirEntidade(String uniqueKey) throws ExcecaoSql {
	}

	@Override
	public Fila<Venda> buscarEntidades() throws ExcecaoSql {
		Fila<Venda> fila = new Fila<Venda>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = Conexao.getInstance().buscarConexao().prepareStatement(SelectAllString);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Venda venda = new Venda();
				venda.setCodigoProduto(rs.getString(1));
				venda.setQuantidade(rs.getInt(2));
				venda.setValorUn(rs.getBigDecimal(3));
				venda.setDesconto(rs.getBigDecimal(4));
				venda.setAliquotaICMS(rs.getBigDecimal(5));
				venda.setObs(rs.getString(6));
				fila.enfileirar(venda);
			}	
			preparedStatement.close();
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}
		return fila;
	}

	@Override
	public Venda buscarUnicaEntidade(String uniqueKey) throws ExcecaoSql {
		PreparedStatement preparedStatement = null;
		Venda venda = null;		
		try {
			preparedStatement = Conexao.getInstance().buscarConexao().prepareStatement(SelectOneString);
			try {
				preparedStatement.setString(1, uniqueKey);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()) {
					venda = new Venda();
					venda.setCodigoProduto(rs.getString(1));
					venda.setQuantidade(rs.getInt(2));
					venda.setValorUn(rs.getBigDecimal(3));
					venda.setDesconto(rs.getBigDecimal(4));
					venda.setAliquotaICMS(rs.getBigDecimal(5));
					venda.setObs(rs.getString(6));
				}
			}
			finally {
				preparedStatement.close();
			}
			
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}		
		return venda;
	}

}
