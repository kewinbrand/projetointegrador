package sistemaVendas;

import java.io.IOException;
import java.sql.SQLException;

import conexaoBancoDados.Conexao;
import dominio.RepositorioProduto;
import dominio.DominioVenda;
import dominio.RepositorioAbstract;
import entidades.Produto;
import entidades.Venda;
import estruturaDados.Fila;
import utilidades.ExcecaoSql;
import utilidades.Utilidades;
import utilidades.ValidacaoException;

public class SistemaVendas {
	
    private DominioVenda _venda;
    
    private static String CONEXAO = "jdbc:sqlserver://ANGELO-PC\\SQLEXPRESS:1433;databaseName=PROJETO_INTEGRADOR;integratedSecurity=true;";
    private static String CONEXAO_MASTER = "jdbc:sqlserver://ANGELO-PC\\SQLEXPRESS:1433;databaseName=master;integratedSecurity=true;";
	
	public SistemaVendas() {
		this._venda = new DominioVenda();
	}
	
	private void IniciarConexaoBanco() throws SQLException, ClassNotFoundException {
		Conexao.getInstance().conectarBancoDados(CONEXAO);
	}
	
	
	public void novaVenda(Venda venda) {
		this._venda.realizarVenda(venda);
	}
	
	public Fila<Produto> buscarProdutos() throws ExcecaoSql{
		RepositorioAbstract<Produto> _produto = new RepositorioProduto();
		return _produto.buscarEntidades();
	}	
	
	public Venda buscarPrimeiraVendaEnfileirada() {
		return this._venda.buscarVendasEnfileiradas().retornaPrimeiroElemento();
	}
	
	public Fila<Venda> buscarVendasEnfileiradas(){
		return this._venda.buscarVendasEnfileiradas();
	}
	
	public Fila<Venda> buscarVendasGravadas() throws ExcecaoSql{
		return this._venda.buscarVendasGravadas();
	}
	
	public void recriarBancoDados() throws ExcecaoSql {
		try {
			String sql = null;
			Conexao.getInstance().desconectarBancoDados();
			Conexao.getInstance().conectarBancoDados(CONEXAO_MASTER);
			sql = Utilidades.lerArquivoDisco("..\\sql\\create-database.sql");
			Conexao.getInstance().executarSqlIndependente(sql);
			Conexao.getInstance().desconectarBancoDados();
			Conexao.getInstance().conectarBancoDados(CONEXAO);
			sql =Utilidades.lerArquivoDisco("..\\sql\\InitDataBase-sem-go.sql");
			Conexao.getInstance().executarSqlIndependente(sql);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new ExcecaoSql(e.getMessage());
		}
	}
	
	public void gravarVendas() throws ExcecaoSql, ValidacaoException {
		this._venda.gravarVendas();
	}
	
	public void IniciarSistema() throws ExcecaoSql {
		
		try {
			IniciarConexaoBanco();
		} catch (ClassNotFoundException | SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}
	}

	

}
