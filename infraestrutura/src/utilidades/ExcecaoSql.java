package utilidades;

public class ExcecaoSql extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcecaoSql(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	@Override
	public String getMessage() {
		String except = String.format("Oh não! O seguinte problema relacionado a SQL aconteceu: %n %s ", super.getMessage());
		return except;
	}
	
}
