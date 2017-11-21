package dominio;

import entidades.Venda;
import estruturaDados.Fila;
import utilidades.ExcecaoSql;
import utilidades.ValidacaoException;

public class DominioVenda {
	
	private Fila<Venda> vendas;
	private RepositorioAbstract<Venda> repositorio;
	
	//https://docs.microsoft.com/pt-br/sql/connect/jdbc/using-basic-data-types
	
	public DominioVenda() {
		this.vendas = new Fila<Venda>();
		this.repositorio = new RepositorioVenda();
	}
	
	public void realizarVenda(Venda venda) {
		this.vendas.enfileirar(venda);		
	}
	
	public Fila<Venda> buscarVendasEnfileiradas(){
		return this.vendas;
	}
	
	public Fila<Venda> buscarVendasGravadas() throws ExcecaoSql{
		return this.repositorio.buscarEntidades();
	}
	
	public void gravarVendas() throws ExcecaoSql, ValidacaoException {
		if(vendas.filaVazia()) {
			throw new ValidacaoException("Não há nenhuma venda no carrinho");
		}
		for (int i = 0; i <= vendas.retornaTamanhoFila(); i++) {
			Venda venda = vendas.desenfileirar();
			this.repositorio.inserirEntidade(venda);
		}	
	}

}
