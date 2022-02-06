package mediareader;
import java.util.*;
import javax.sound.sampled.*;

public class Tone {
    public static float SAMPLE_RATE = 8000f;

    public static void sound(int hz, int msecs, double vol)
    throws LineUnavailableException {

        byte[] buf = new byte[(int)SAMPLE_RATE * msecs / 1000];

        for (int i=0; i<buf.length; i++) {
            double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
            buf[i] = (byte)(Math.sin(angle) * 127.0 * vol);
        }

        // shape the front and back 10ms of the wave form
        for (int i=0; i < SAMPLE_RATE / 100.0 && i < buf.length / 2; i++) {
            buf[i] = (byte)(buf[i] * i / (SAMPLE_RATE / 100.0));
            buf[buf.length-1-i] =
            (byte)(buf[buf.length-1-i] * i / (SAMPLE_RATE / 100.0));
        }

        AudioFormat af = new AudioFormat(SAMPLE_RATE,8,1,true,false);
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
        sdl.write(buf,0,buf.length);
        //sdl.drain();
        sdl.close();
    }

    // public static void main(String[] args) throws
    // LineUnavailableException {
    //     System.out.println("Playing with audio in Java");
    //     Tone.sound(800,1000,0.8);
    // }

    public static void main ( String[] args )
      {
      int seconds = 10;
      int sampleRate = 8000;
      double frequency = 1000.0;
      double RAD = 2.0 * Math.PI;
      try
         {
         AudioFormat af = new
                          AudioFormat( (float)sampleRate, 8, 1, true, true );
         DataLine.Info info = new
                              DataLine.Info ( SourceDataLine.class, af );
         SourceDataLine source =
         (SourceDataLine) AudioSystem.getLine( info );
         source.open( af );
         source.start();
         byte[] buf = new byte[sampleRate * seconds];
         for ( int i=0; i<buf.length; i++ )
            {
            buf[i] =
            (byte)( Math.sin( RAD * frequency / sampleRate * i ) * 127.0 );
            }
         source.write( buf, 0, buf.length );
         source.drain();
         source.stop();
         source.close();
         }
      catch ( Exception e )
         {
         System.out.println( e );
         }
      System.exit( 0 );
      }
}