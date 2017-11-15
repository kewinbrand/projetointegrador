package entidades;

public class Venda {
	
	private String CodigoProduto;
	private int Quantidade;
	private double ValorUn;
	private double Desconto;
	private double AliquotaICMS;
	private String Obs;
	
	public String getCodigoProduto() {
		return CodigoProduto;
	}
	public void setCodigoProduto(String codigoProduto) {
		CodigoProduto = codigoProduto;
	}
	public int getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}
	public double getValorUn() {
		return ValorUn;
	}
	public void setValorUn(double valorUn) {
		ValorUn = valorUn;
	}
	public double getDesconto() {
		return Desconto;
	}
	public void setDesconto(double desconto) {
		Desconto = desconto;
	}
	public double getAliquotaICMS() {
		return AliquotaICMS;
	}
	public void setAliquotaICMS(double aliquotaICMS) {
		AliquotaICMS = aliquotaICMS;
	}
	public String getObs() {
		return Obs;
	}
	public void setObs(String obs) {
		Obs = obs;
	}

}
