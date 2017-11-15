package sistemaVendas;

public enum OpcoesMenuEnum {
	REALIZAR_VENDA,
	GRAVAR_DADOS,
	MOSTRAR_FILA,
	BUSCAR_PRIMEIRO_ELEMENTO_FILA,
	MOSTRAR_TAMANHO_FILA,
	MOSTRAR_ITENS_BASE_DADOS;
	
	
	public static OpcoesMenuEnum fromInteger(int opcao) {
		switch (opcao) {
		case 0:
			return OpcoesMenuEnum.REALIZAR_VENDA;
		case 1:
			return OpcoesMenuEnum.GRAVAR_DADOS;
		case 2:
			return OpcoesMenuEnum.MOSTRAR_FILA;
		case 3:
			return OpcoesMenuEnum.BUSCAR_PRIMEIRO_ELEMENTO_FILA;
		case 4:
			return OpcoesMenuEnum.MOSTRAR_FILA;
		case 5:
			return OpcoesMenuEnum.MOSTRAR_ITENS_BASE_DADOS;
		}
		
		return null;
	}
}
