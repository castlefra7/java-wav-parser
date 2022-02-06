package mediareader;
import java.io.*;
import java.nio.*;
import javax.sound.sampled.*;

public class AudioReader {
    public byte[] reverse(byte[] buff) {
        byte[] result = new byte[buff.length];
        for(int i = 0, j = buff.length-1; i < buff.length; j--, i++) {
            result[i] = buff[j];
        }
        return result;
    }
    public AudioReader(String fileName) throws IOException, LineUnavailableException{
        FileInputStream audioInput = new FileInputStream(new File(fileName));
        byte[] buff = new byte[4];
        audioInput.read(buff);
        for(byte b: buff) {
            System.out.print((char)b);
        }
        System.out.print("\n");
        audioInput.read(buff);
        byte[] reversed = reverse(buff);
        ByteBuffer size = ByteBuffer.wrap(reversed);
        System.out.println(size.getInt());
        audioInput.read(buff);
        for(byte b: buff) {
            System.out.print((char)b);
        }
        System.out.print("\n");
        
        int samplerate = 0;
        int sampleSize = 0;
        int channel = 0;

        audioInput.read(buff);
        for(byte b: buff) {
            System.out.print((char)b);
        }
        System.out.print("\n");
        audioInput.read(buff);
        reversed = reverse(buff);
        size = ByteBuffer.wrap(reversed);
        System.out.println(size.getInt());
        buff = new byte[2];
        audioInput.read(buff);
        System.out.println(String.format("%d%d", buff[1], buff[0]));
        audioInput.read(buff);
        //channel
        channel = (int)buff[0];
        System.out.println(String.format("Number of channels: %d%d", buff[1], buff[0]));
        buff = new byte[4];
        audioInput.read(buff);
        reversed = reverse(buff);
        size = ByteBuffer.wrap(reversed);
        // samplerate
        samplerate  =  size.getInt();
        System.out.println("Sample rate: " + samplerate);
        audioInput.read(buff);
        reversed = reverse(buff);
        size = ByteBuffer.wrap(reversed);
        System.out.println(size.getInt());
        buff = new byte[2];
        audioInput.read(buff);
        System.out.println(String.format("%d%d", buff[1], buff[0]));
        buff = new byte[2];
        audioInput.read(buff);
        //sample size
        sampleSize = (int)buff[0];
        System.out.println(String.format("Sample size: %d%d", buff[1], buff[0]));
        buff = new byte[4];
        audioInput.read(buff);
        for(byte b: buff) {
            System.out.print((char)b);
        }
        System.out.print("\n");
        buff = new byte[4];
        audioInput.read(buff);
        reversed = reverse(buff);
        size = ByteBuffer.wrap(reversed);
        // System.out.println(size.getInt());
        // TODO: understand the terminology: channels, bitrate etc...
        // TODO: read the actual audio and output it
        /* READ THE ACTUAL SOUND DATA */
        buff = new byte[size.getInt()];
        audioInput.read(buff);
    
        AudioFormat audioFormat = new AudioFormat(samplerate,sampleSize,channel,true,false);
        SourceDataLine sdl = AudioSystem.getSourceDataLine(audioFormat);
        sdl.open(audioFormat);
        sdl.start();
        
        sdl.write(buff,0,buff.length);
        //sdl.drain();
        sdl.close();

        // Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // for(Mixer.Info mixer: mixers) {
        //     System.out.println(mixer.getName() + " "  + "\n");
            
        // }
        // Mixer desiredMixer = AudioSystem.getMixer(mixers[3]);
        // Line[] lines = desiredMixer.getTargetLines();
        // System.out.println(lines.length);
        // for(Line line: lines) {

        // }
        // AudioFormat audioFormat = new AudioFormat(8000, 16, 2, false, false);
        // TargetDataLine line;
        // DataLine.Info info = new DataLine.Info(TargetDataLine.class, 
        // audioFormat); // format is an AudioFormat object
        // if (!AudioSystem.isLineSupported(info)) {
        //     System.out.println("err");
        // }
        // // Obtain and open the line.
        
        // try {
        //     line = (TargetDataLine) AudioSystem.getLine(info);
        //     line.open(audioFormat);
        //     System.out.println("ato ve");
        // } catch (LineUnavailableException ex) {
        //     System.out.println("at");
        // }
    }
}