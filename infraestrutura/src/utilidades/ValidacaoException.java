package utilidades;

public class ValidacaoException extends Exception {

	public ValidacaoException(String exceptionMessage) {
		super(exceptionMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		String except = String.format("Atenção! Os seguintes problemas foram encontrados: %n %s ", super.getMessage());
		return except;
	}
	
}
