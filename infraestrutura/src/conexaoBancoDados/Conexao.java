package conexaoBancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.ExcecaoSql;

public class Conexao {	
	
	Connection conn;
	private Boolean conectado;
	
	private static final Conexao Instance = new Conexao();
	
	public Conexao() {
		conectado = false;
	}	
	
	public static Conexao getInstance() {
		return Instance;
	}
	
	public void conectarBancoDados(String stringConnection) throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(stringConnection);
		conectado = true;
	}
	
	public void desconectarBancoDados() throws SQLException {
		if(conectado) {
			conn.close();
		}		
		conectado = false;
	}
	
	public void executarSqlIndependente(String sql) throws ExcecaoSql {
		Statement st = null;
		try {				
			st = Conexao.getInstance().buscarConexao().createStatement();
			st.execute(sql);
			st.close();
		} catch (SQLException e) {
			throw new ExcecaoSql(e.getMessage());
		}
	}
	
	public Connection buscarConexao() {		
		return conn;
	}	

}
