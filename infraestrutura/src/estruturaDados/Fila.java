package estruturaDados;

public class Fila<T> {
	
	private static final int ARRAY_TAM_CRESCER = 10;
	private int vezesArrayCresceu;
	private int ultimoElemento;
	
	private Object[] elementos;
	
	public Fila(){
		this.criarFila();
	}
	
	public void enfileirar(T elemento) {
		if(this.filaCheia()) {
			this.atualizarFila();
		}
		this.ultimoElemento++;
		this.elementos[this.ultimoElemento] = elemento;
	}
	
	@SuppressWarnings("unchecked")
	public T desenfileirar() {
		if (this.filaVazia()) {
			return null;
		}
		else {
			this.ultimoElemento--;			
			/*for (int i = 0; i <= this.ultimoElemento; i++)
			{
				this.elementos[i] = this.elementos[i + 1];
			};*/
			return (T) this.elementos[this.ultimoElemento + 1];
		}
	}
	
	@SuppressWarnings("unchecked")
	public T item(int index) {
		if(index > this.ultimoElemento) {
			return null;
		}
		return (T) this.elementos[index];
	}
	
	@SuppressWarnings("unchecked")
	public T[] mostraFila() {
		return (T[]) this.elementos;
	}
	
	@SuppressWarnings("unchecked")
	public T retornaPrimeiroElemento() {
		if(this.filaVazia()) {
			return null;
		}else {
			return (T) this.elementos[0];
		}
	}
	
	public int retornaTamanhoFila() {
		return this.ultimoElemento + 1;
	}
	
	private void criarFila() {
		this.elementos = new Object[ARRAY_TAM_CRESCER];
		this.ultimoElemento = -1;
		this.vezesArrayCresceu = 1;
	}
		
	private void atualizarFila() {
		this.vezesArrayCresceu++;
		Object[] elem = new Object[this.ultimoElemento];
		for (int i = 0; i < elem.length; i++) {
			elem[i] = this.elementos[i];
		}
		
		this.elementos = null;
		this.elementos = new Object[this.vezesArrayCresceu * ARRAY_TAM_CRESCER];
		
		for (int i = 0; i < elem.length; i++) {
			this.elementos[i] = elem[i];
		}
	}
	
	private boolean filaCheia() {
		return (this.elementos.length == (this.ultimoElemento - 1)); 
	}
	
	private boolean filaVazia() {
		return (this.ultimoElemento == -1);
	}
	
}
