package sistemaVendas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;


import conexaoBancoDados.Conexao;
import dominio.DominioProduto;
import dominio.DominioVenda;
import entidades.Produto;
import entidades.Venda;
import estruturaDados.Fila;
import utilidades.ExcecaoSql;

public class SistemaVendas {
	
    private DominioVenda _venda;
    
    private static String CONEXAO = "jdbc:sqlserver://localhost:1433;databaseName=PROJETO_INTEGRADOR;user=sa;password=123";
    private static String CONEXAO_MASTER = "jdbc:sqlserver://localhost:1433;databaseName=master;user=sa;password=123";
	
	public SistemaVendas() {
		this._venda = new DominioVenda();
	}
	
	private void IniciarConexaoBanco() throws SQLException, ClassNotFoundException {
		Conexao.getInstance().conectarBancoDados(CONEXAO);
	}
	
	private String buscarArquivoCriacaoBanco(String filePath) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		return content;
	}
	
	public void novaVenda(Venda venda) {
		this._venda.realizarVenda(venda);
	}
	
	public Fila<Produto> buscarProdutos() throws ExcecaoSql{
		DominioProduto _produto = new DominioProduto();
		return _produto.buscarProdutos();
	}	
	
	public Venda buscarPrimeiraVendaEnfileirada() {
		return this._venda.buscarVendasEnfileiradas().retornaPrimeiroElemento();
	}
	
	public Fila<Venda> buscarVendasEnfileiradas(){
		return this._venda.buscarVendasEnfileiradas();
	}
	
	public void recriarBancoDados() throws ExcecaoSql {
		try {
			String sql = null;
			Conexao.getInstance().desconectarBancoDados();
			Conexao.getInstance().conectarBancoDados(CONEXAO_MASTER);
			sql = buscarArquivoCriacaoBanco("..\\sql\\create-database.sql");
			Conexao.getInstance().executarSqlIndependente(sql);
			Conexao.getInstance().desconectarBancoDados();
			Conexao.getInstance().conectarBancoDados(CONEXAO);
			sql = buscarArquivoCriacaoBanco("..\\sql\\InitDataBase-sem-go.sql");
			Conexao.getInstance().executarSqlIndependente(sql);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new ExcecaoSql(e.getMessage());
		}
	}
	
	public void IniciarSistema() throws ExcecaoSql {
		
		try {
			IniciarConexaoBanco();
		} catch (ClassNotFoundException e) {
			throw new ExcecaoSql(e.getMessage());
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}
		
	}

	

}
