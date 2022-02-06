package mediareader;
import java.io.File;
import javax.sound.sampled.*;
public class Program {
    public static void main(String[] args ) throws Exception {
        if(args.length < 1) {
            System.out.println("Usage: program <audio.wav>");
            System.exit(1);
        }
            
       new AudioReader(args[0]);
    }

    public static void copyAdio(String source, String destination) {
        File fileDest = new File(source);
        File file = new File(destination);
        try {
            // AudioFormat format =
            //         new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 8, 1, 1, 44100, false);
            // AudioSystem.write(AudioSystem.getAudioInputStream(file), AudioFileFormat.Type.WAVE, fileDest);

        } catch (final Exception ignored) {
            return; // the test is not applicable
        }
        
    }
}