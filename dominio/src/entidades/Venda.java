package entidades;

import java.math.BigDecimal;

import utilidades.ValidacaoException;

public class Venda {
	
	public static Venda criarVenda(String codigoProduto, String quantidade, String valorUnitario,
			String desconto, String aliquotaIcms, String obs) throws ValidacaoException {	
		Venda venda = new Venda();
		venda.validarVenda(codigoProduto, quantidade, valorUnitario, desconto, aliquotaIcms, obs);
		venda.setCodigoProduto(codigoProduto);
		venda.setAliquotaICMS(new BigDecimal(aliquotaIcms));
		venda.setQuantidade(Integer.parseInt(quantidade));
		venda.setValorUn(new BigDecimal(valorUnitario));
		venda.setDesconto(new BigDecimal(desconto));
		venda.setObs(obs);
		return venda;
	}
		
	@SuppressWarnings("unused")
	public void validarVenda(String codigoProduto, String quantidade, String valorUnitario,
			String desconto, String aliquotaIcms, String obs) throws ValidacaoException {
		StringBuilder msgErro = new StringBuilder();
		
		BigDecimal _desconto = new BigDecimal(0);
		BigDecimal _aliquotaIcms = new BigDecimal(0);
		BigDecimal valUnit = new BigDecimal(0);
		int qtd = 0;
		
		try {
		    qtd =  Integer.parseInt(quantidade);
		} catch (NumberFormatException e) {
			msgErro.append("A quantidade não é um valor válido.\n");
		}
		
		try {
			valUnit = new BigDecimal(valorUnitario);
		} catch (NumberFormatException e) {
			msgErro.append("O valor unitário não é um valor válido.\n");
		}
		
		try {
			_desconto = new BigDecimal(desconto);
		} catch (NumberFormatException e) {
			msgErro.append("O desconto não é um valor válido.\n");
		}
		
		try {
			_aliquotaIcms = new BigDecimal(aliquotaIcms);
		} catch (NumberFormatException e) {
			msgErro.append("A aliquota ICMS não é um valor válido.\n");
		}		
		
		if(codigoProduto.isEmpty()) {
			msgErro.append("O código do produto não pode estar vazio.\n");
		}
		
		if(qtd == 0) {
			msgErro.append("A quantidade não pode ser 0\n");
		}
		
		if(valUnit.intValue() == 0) {
			msgErro.append("O valor unitário não pode ser 0.");
		}		
		BigDecimal mltp = valUnit.multiply(new BigDecimal(qtd));
		if(_desconto.compareTo(mltp) == 1) {
			msgErro.append("O desconto não pode ser maior que o valor unitário.");
		}
		
		if(msgErro.length() > 0) {
			throw new ValidacaoException(msgErro.toString());
		}
	}
	
	public void validarVenda(Venda venda) throws ValidacaoException {
		StringBuilder msgErro = new StringBuilder();
		if (venda.getQuantidade() == 0) {
			msgErro.append("A quantidade não pode ser 0\n");
		}
		if(venda.getCodigoProduto().isEmpty()) {
			msgErro.append("O código do produto não pode estar vazio.\n");
		}
		if(venda.getValorUn().intValue() == 0) {
			msgErro.append("O valor unitário não pode ser 0.");
		}
		BigDecimal val = venda.getValorUn().multiply(new BigDecimal(venda.getQuantidade()));
		if(venda.getDesconto().compareTo(val) == 1) {
			msgErro.append("O desconto não pode ser maior que o valor unitário.");
		}
		if(msgErro.length() != 0) {
			throw new ValidacaoException(msgErro.toString());
		}
	}	
	
	private String CodigoProduto;
	private int Quantidade;
	private BigDecimal ValorUn;
	private BigDecimal Desconto;
	private BigDecimal AliquotaICMS;
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
	public BigDecimal getValorUn() {
		return ValorUn;
	}
	public void setValorUn(BigDecimal valorUn) {
		ValorUn = valorUn;
	}
	public BigDecimal getDesconto() {
		return Desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		Desconto = desconto;
	}
	public BigDecimal getAliquotaICMS() {
		return AliquotaICMS;
	}
	public void setAliquotaICMS(BigDecimal aliquotaICMS) {
		AliquotaICMS = aliquotaICMS;
	}
	public String getObs() {
		return Obs;
	}
	public void setObs(String obs) {
		Obs = obs;
	}

}
