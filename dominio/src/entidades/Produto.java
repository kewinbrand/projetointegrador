package entidades;

public class Produto {
	
	private String CodigoProduto;
	private String NomeCompleto;
	private int QtdMinEstoque;
	private int QtdLoteComprar;
	

	public String getCodigoProduto() {
		return CodigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		CodigoProduto = codigoProduto;
	}

	public String getNomeCompleto() {
		return NomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		NomeCompleto = nomeCompleto;
	}

	public int getQtdMinEstoque() {
		return QtdMinEstoque;
	}

	public void setQtdMinEstoque(int qtdMinEstoque) {
		QtdMinEstoque = qtdMinEstoque;
	}

	public int getQtdLoteComprar() {
		return QtdLoteComprar;
	}

	public void setQtdLoteComprar(int qtdLoteComprar) {
		QtdLoteComprar = qtdLoteComprar;
	} 

}
