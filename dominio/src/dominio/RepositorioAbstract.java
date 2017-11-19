package dominio;

import estruturaDados.Fila;
import utilidades.ExcecaoSql;

public abstract class RepositorioAbstract<T> {
	
	public abstract void atualizarEntidade(T entidade) throws ExcecaoSql;
	
	public abstract void inserirEntidade(T entidade) throws ExcecaoSql;
	
	public abstract void excluirEntidade(String uniqueKey) throws ExcecaoSql;
	
	public abstract Fila<T> buscarEntidades() throws ExcecaoSql;
	
	public abstract T buscarUnicaEntidade(String uniqueKey) throws ExcecaoSql;
}
