package dominio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexaoBancoDados.Conexao;
import entidades.MovimentoEstoque;
import estruturaDados.Fila;
import utilidades.ExcecaoSql;

public class RepositorioMovimentoEstoque extends RepositorioAbstract<MovimentoEstoque>{
	
	private static final String SelectOneString = "SELECT IdMovimento, DataHora, Produto, Quantidade FROM MOVIMENTOESTOQUE WHERE Produto = ?";

	@Override
	public void atualizarEntidade(MovimentoEstoque entidade) throws ExcecaoSql {
	}

	@Override
	public void inserirEntidade(MovimentoEstoque entidade) throws ExcecaoSql {

	}

	@Override
	public void excluirEntidade(String uniqueKey) throws ExcecaoSql {

	}

	@Override
	public Fila<MovimentoEstoque> buscarEntidades() throws ExcecaoSql {
		return null;
	}

	@Override
	public MovimentoEstoque buscarUnicaEntidade(String uniqueKey) throws ExcecaoSql {
		PreparedStatement preparedStatement = null;
		MovimentoEstoque movEstoque = null;		
		try {
			preparedStatement = Conexao.getInstance().buscarConexao().prepareStatement(SelectOneString);
			try {
				preparedStatement.setString(1, uniqueKey);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()) {
					movEstoque = new MovimentoEstoque();
					movEstoque.setIdMovimento(rs.getInt(1));
					movEstoque.setDataHora(rs.getDate(2));
					movEstoque.setCodigoProduto(rs.getString(3));
					movEstoque.setQuantidade(rs.getInt(4));
				}
			}
			finally {
				preparedStatement.close();
			}
			
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}		
		return movEstoque;
	}

}
