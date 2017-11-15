package sistemaVendas;

import java.sql.SQLException;

import conexaoBancoDados.Conexao;
import estruturaDados.Fila;

public class SistemaVendas {

	Conexao banco;
	Fila fila;
	private static String CONEXAO = "jdbc:sqlserver://localhost:1433;databaseName=PROJETO_INTEGRADOR;user=sa;password=123";
	
	public SistemaVendas() {
		
	}
	
	private void IniciarConexaoBanco() throws SQLException, ClassNotFoundException {
		banco = new Conexao();
		banco.ConectarBancoDados(CONEXAO);
	}
	
	private void IniciarFila() {
		this.fila = new Fila();
	}
	
	public void IniciarSistema() throws Exception {
		
		try {
			IniciarConexaoBanco();
			IniciarFila();
		} catch (ClassNotFoundException e) {
			throw new Exception("Ocorreu o seguinte erro ao realizar a conexão com o banco de dados: "+e.getMessage());
		} catch (SQLException e) {
			throw new Exception("Ocorreu o seguinte erro ao realizar a conexão com o banco de dados: "+e.getMessage());
		}
		
	}

	

}
