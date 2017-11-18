package projetoIntegrador;

import entidades.Produto;
import estruturaDados.Fila;
import sistemaVendas.SistemaVendas;
import utilidades.ExcecaoSql;

public class MenuAplicacao {
	
	private static SistemaVendas sistema = new SistemaVendas();
	
	public static void abrirVendas() throws ExcecaoSql {
		Fila<Produto> produtos = sistema.buscarProdutos();
		Compra compra = Compra.novaVenda(sistema);			
		int tam = produtos.retornaTamanhoFila();
		for (int i = 0; i <= tam; i++) {
			Produto produto = produtos.desenfileirar();
			if(produto != null) {
				compra.comboProduto.addItem(produto.getCodigoProduto());
			}
		}	
		compra.setVisible(true);		
	}
	
	
	public static void recriarBancoDados() throws ExcecaoSql {
		sistema.recriarBancoDados();
	}

}
