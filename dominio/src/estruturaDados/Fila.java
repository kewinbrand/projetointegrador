package estruturaDados;

public class Fila {
	
	private static final int ARRAY_TAM_CRESCER = 10;
	private int vezesArrayCresceu;
	private int ultimoElemento;
	
	private Object[] elementos;
	
	public Fila(){
		this.criarFila();
	}
	
	public void enfileirar(Object elemento) {
		if(this.filaCheia()) {
			this.atualizarFila();
		}
		this.ultimoElemento++;
		this.elementos[this.ultimoElemento] = elemento;
	}
	
	public Object desenfileirar() {
		if (this.filaVazia()) {
			return null;
		}
		else {
			this.ultimoElemento--;
			for (int i = 0; i <= this.ultimoElemento; i++)
			{
				this.elementos[i] = this.elementos[i + 1];
			};
			return this.elementos[this.ultimoElemento + 1];
		}
	}
	
	public Object[] mostraFila() {
		return this.elementos;
	}
	
	public Object retornaPrimeiroElemento() {
		if(this.filaVazia()) {
			return null;
		}else {
			return this.elementos[0];
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
