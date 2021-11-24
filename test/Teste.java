
import java.io.BufferedInputStream;
import java.util.Arrays;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Teste {

    public static void main(String args[]) {
        int[] a = {1, 2, 3, 4, 5};
        int b = 3;
        
        tocarSom("agua0", false, 1f);
       
//       a = shuffle(a);
       
        //System.out.println(intDouble_2(b));
       
//       for(int i = 0; i < a.length; i++) {
//           System.out.println(a[i]);
//       }
    }
    
    private static void tocarSom(String nome, boolean loop, float volume) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(Teste.class.getResourceAsStream("/pilha/midia/" + nome + ".wav")));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volume));
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clip.addLineListener(new LineListener() {

                    @Override
                    public void update(final LineEvent event) {
                        if (event.getType().equals(javax.sound.sampled.LineEvent.Type.STOP)) {
                            System.out.println("Do something");
                        }
                    }
                });
        } catch (Exception ex) {
            System.out.println("Erro");
            ex.printStackTrace();
        }
    }
    
    private static int[] shuffle(int[] vet) {
        int[] sort = vet;
        int[] aux;
        int[] fim = new int[vet.length];
        int pos;
        
        for(int i = 0; i < vet.length-1; i++) {
            pos = (int) (Math.random()*sort.length);
            fim[i] = sort[pos];
            aux = new int[sort.length-1];
            for(int j = 0; j <= aux.length; j++) {
                if(j == pos)
                    continue;
                if(j > pos)
                    aux[j-1] = sort[j];
                else
                    aux[j] = sort[j];
            }
            sort = aux;
        }
        fim[fim.length-1] = sort[0];
        
        return fim;
    }
    
    private static double intDouble_2(int n) {
        return (double)n / 2;
    }
    
}
