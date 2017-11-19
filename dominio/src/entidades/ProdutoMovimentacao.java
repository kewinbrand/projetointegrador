package entidades;

public class ProdutoMovimentacao extends Produto {
	
	private int saldoDisponivel;
	
	public void setSaldoDisponivel(int SaldoDisponivel) {
		this.saldoDisponivel = SaldoDisponivel;
	}
	
	public int getSaldoDisponivel() {
		return this.saldoDisponivel;
	}

}
