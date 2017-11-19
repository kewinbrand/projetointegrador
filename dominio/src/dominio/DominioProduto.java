package dominio;

import entidades.MovimentoEstoque;
import entidades.Produto;
import entidades.Venda;
import utilidades.ExcecaoSql;
import utilidades.ValidacaoException;

public class DominioProduto {
	
	private RepositorioAbstract<Produto> repositorio;
	
	public DominioProduto(){
		this.repositorio = new RepositorioProduto();
	}
	
	public void validarExclusaoProduto(String codigoProduto) throws ValidacaoException, ExcecaoSql {
		RepositorioAbstract<Venda> repoVenda = new RepositorioVenda();
		Venda venda = repoVenda.buscarUnicaEntidade(codigoProduto);
		if(venda != null) {
			throw new ValidacaoException(String.format("Não é possível excluir o produto %s pois há uma venda com o mesmo.", codigoProduto));
		}
		RepositorioAbstract<MovimentoEstoque> repoMov = new RepositorioMovimentoEstoque();
		MovimentoEstoque movEstoque =  repoMov.buscarUnicaEntidade(codigoProduto);
		if(movEstoque != null) {
			throw new ValidacaoException(String.format("Não é possível excluir o produto %s pois há uma movimentação de estoque com o mesmo.", codigoProduto));
		}
	}
	
	public void atualizarProduto(Produto produto) throws ExcecaoSql {
		this.repositorio.atualizarEntidade(produto);
	}

	public void adicionarProduto(Produto produto) throws ExcecaoSql, ValidacaoException {
		Produto _prod = this.repositorio.buscarUnicaEntidade(produto.getCodigoProduto());
		if(_prod != null) {
			throw new ValidacaoException(String.format("Um produto com o código %s já existe!", produto.getCodigoProduto()));
		}
		this.repositorio.inserirEntidade(produto);
	}

	public void excluirProduto(Produto produto) throws ValidacaoException, ExcecaoSql {
		this.validarExclusaoProduto(produto.getCodigoProduto());
		this.repositorio.excluirEntidade(produto.getCodigoProduto());
	}

}
