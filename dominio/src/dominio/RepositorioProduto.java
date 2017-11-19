package dominio;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import estruturaDados.Fila;
import utilidades.ExcecaoSql;
import conexaoBancoDados.Conexao;
import entidades.Produto;
import entidades.ProdutoMovimentacao;

public class RepositorioProduto extends RepositorioAbstract<Produto> {
	
	private static final String SelectAllString = "SELECT Produto, NomeCompleto, QtdMiniEstoque, QtdLoteComprar FROM PRODUTO";
	private static final String DeleteString = "DELETE FROM PRODUTO WHERE Produto = ?";
	private static final String InsertString = "INSERT INTO PRODUTO (Produto, NomeCompleto, QtdMiniEstoque, QtdLoteComprar) values (?, ?, ?, ?)";
	private static final String SelectOneString = "SELECT Produto, NomeCompleto, QtdMiniEstoque, QtdLoteComprar FROM PRODUTO WHERE Produto = ?";
	private static final String UpdateString = "UPDATE PRODUTO SET NomeCompleto = ?, QtdMiniEstoque = ?, QtdLoteComprar = ? WHERE Produto = ?";	
	private static final String ProdMovimentacaoString = "SELECT PRODUTO.Produto, NomeCompleto, QtdMiniEstoque, QtdLoteComprar, ISNULL(SUM(QUANTIDADE), 0) as SaldoDisponivel "+ 
														  "FROM PRODUTO "+ 
														  "LEFT JOIN MOVIMENTOESTOQUE ON PRODUTO.Produto = MOVIMENTOESTOQUE.Produto "+
														  "GROUP BY PRODUTO.Produto, NomeCompleto, QtdMiniEstoque, QtdLoteComprar ";

	@Override
	public void atualizarEntidade(Produto entidade) throws ExcecaoSql {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = Conexao.getInstance().buscarConexao().prepareStatement(UpdateString);
			preparedStatement.setString(1, entidade.getNomeCompleto());
			preparedStatement.setInt(2, entidade.getQtdMinEstoque());
			preparedStatement.setInt(3, entidade.getQtdLoteComprar());
			preparedStatement.setString(4, entidade.getCodigoProduto());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (Exception e) {
			throw new ExcecaoSql(e.getMessage());
		}
	}

	@Override
	public Fila<Produto> buscarEntidades() throws ExcecaoSql {
		Fila<Produto> fila = new Fila<Produto>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = Conexao.getInstance().buscarConexao().prepareStatement(SelectAllString);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Produto produto = new Produto();
				
				produto.setCodigoProduto(rs.getString("Produto"));
				produto.setNomeCompleto(rs.getString("NomeCompleto"));
				produto.setQtdMinEstoque(rs.getInt("QtdMiniEstoque"));
				produto.setQtdLoteComprar(rs.getInt("QtdLoteComprar"));				
				
				fila.enfileirar(produto);
			}	
			
			preparedStatement.close();
			
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}
		
		return fila;
	}

	@Override
	public Produto buscarUnicaEntidade(String codigoProduto) throws ExcecaoSql {
		PreparedStatement preparedStatement = null;
		Produto produto = null;		
		try {
			preparedStatement = Conexao.getInstance().buscarConexao().prepareStatement(SelectOneString);
			try {
				preparedStatement.setString(1, codigoProduto);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()) {
					produto = new Produto();
					
					produto.setCodigoProduto(rs.getString("Produto"));
					produto.setNomeCompleto(rs.getString("NomeCompleto"));
					produto.setQtdMinEstoque(rs.getInt("QtdMiniEstoque"));
					produto.setQtdLoteComprar(rs.getInt("QtdLoteComprar"));
				}
			}
			finally {
				preparedStatement.close();
			}
			
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}		
		return produto;
	}

	@Override
	public void inserirEntidade(Produto entidade) throws ExcecaoSql {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = Conexao.getInstance().buscarConexao().prepareStatement(InsertString);
			try {
				preparedStatement.setString(1, entidade.getCodigoProduto());
				preparedStatement.setString(2, entidade.getNomeCompleto());
				preparedStatement.setInt(3, entidade.getQtdMinEstoque());
				preparedStatement.setInt(4, entidade.getQtdLoteComprar());
				preparedStatement.executeUpdate();
			}
			finally {
				preparedStatement.close();
			}
			
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}		
	}

	@Override
	public void excluirEntidade(String uniqueKey) throws ExcecaoSql {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = Conexao.getInstance().buscarConexao().prepareStatement(DeleteString);
			try {
				preparedStatement.setString(1, uniqueKey);
				preparedStatement.executeUpdate();
			}
			finally {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}	
	}
	
	public Fila<ProdutoMovimentacao> buscarProdutosMovimentacao() throws ExcecaoSql{
		Fila<ProdutoMovimentacao> fila = new Fila<ProdutoMovimentacao>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = Conexao.getInstance().buscarConexao().prepareStatement(ProdMovimentacaoString);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ProdutoMovimentacao produto = new ProdutoMovimentacao();
				
				produto.setCodigoProduto(rs.getString("Produto"));
				produto.setNomeCompleto(rs.getString("NomeCompleto"));
				produto.setQtdMinEstoque(rs.getInt("QtdMiniEstoque"));
				produto.setQtdLoteComprar(rs.getInt("QtdLoteComprar"));
				produto.setSaldoDisponivel(rs.getInt("SaldoDisponivel"));
				
				fila.enfileirar(produto);
			}	
			
			preparedStatement.close();
			
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}
		
		return fila;
	}
	


}
