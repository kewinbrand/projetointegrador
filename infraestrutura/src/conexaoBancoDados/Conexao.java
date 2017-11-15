package conexaoBancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	Connection conn;
	
	public Conexao() {
		
	}	
	
	public void ConectarBancoDados(String stringConnection) throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(stringConnection);
	}
	
	public void DesconectarBancoDados() throws SQLException {
		conn.close();
	}
	
	public Connection BuscarConexao() {
		return conn;
	}	

}
