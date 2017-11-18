package utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Utilidades {
	
	public static String lerArquivoDisco(String caminho) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(caminho)));
		return content;
	}
	
	public static void tocarSom(String somCaixa) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(somCaixa));
		Clip clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();
	}

}
