package entidades;

import java.util.Date;

public class MovimentoEstoque {
	
	private int IdMovimento;
	private Date DataHora;
	private String CodigoProduto;
	private int Quantidade;

	public int getIdMovimento() {
		return IdMovimento;
	}

	public void setIdMovimento(int idMovimento) {
		IdMovimento = idMovimento;
	}

	public Date getDataHora() {
		return DataHora;
	}

	public void setDataHora(Date dataHora) {
		DataHora = dataHora;
	}

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

}
