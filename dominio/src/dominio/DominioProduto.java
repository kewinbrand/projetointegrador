package dominio;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import estruturaDados.Fila;
import utilidades.ExcecaoSql;
import conexaoBancoDados.Conexao;
import entidades.Produto;

public class DominioProduto {
	
	private static final String SelectString = "SELECT * FROM PRODUTO";
	
	public Fila<Produto> buscarProdutos() throws ExcecaoSql {
		Fila<Produto> fila = new Fila<Produto>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = Conexao.getInstance().buscarConexao().prepareStatement(SelectString);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Produto produto = new Produto();
				
				produto.setCodigoProduto(rs.getString("Produto"));
				produto.setNomeCompleto(rs.getString("NomeCompleto"));
				produto.setQtdMinEstoque(rs.getInt("QtdMiniEstoque"));
				produto.setQtdLoteComprar(rs.getInt("QtdLoteComprar"));				
				
				fila.enfileirar(produto);
			}		
			
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}
		
		return fila;
	}

}
