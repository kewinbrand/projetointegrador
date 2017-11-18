package utilidades;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utilidades {
	
	public static String lerArquivoDisco(String caminho) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(caminho)));
		return content;
	}

}
