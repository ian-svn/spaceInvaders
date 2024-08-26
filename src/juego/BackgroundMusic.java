package juego;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BackgroundMusic {

    private Clip clip;

    public void playMusic(String filepath) {
        try {
            File musicPath = new File(filepath);
            System.out.println("Buscando en: " + musicPath.getAbsolutePath()); // Imprime la ruta completa

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);

                AudioFormat format = audioInput.getFormat();
                System.out.println("Formato de audio: " + format);
                System.out.println("Tasa de muestreo: " + format.getSampleRate());
                System.out.println("Bits por muestra: " + format.getSampleSizeInBits());
                System.out.println("Número de canales: " + format.getChannels());
                
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                audioInput.close(); // Cierra el AudioInputStream después de abrir el clip
            } else {
                System.out.println("Archivo no encontrado en: " + musicPath.getAbsolutePath());
            }
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Formato de archivo de audio no soportado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.out.println("Línea de audio no disponible.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Argumento ilegal. Verifica el formato del archivo de audio.");
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
