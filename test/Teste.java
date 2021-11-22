
import java.util.Arrays;

public class Teste {

    public static void main(String args[]) {
        int[] a = {1, 2, 3, 4, 5};
        int b = 3;
        
        System.out.println(Arrays.toString("wefq".split(",")));
       
//       a = shuffle(a);
       
        //System.out.println(intDouble_2(b));
       
//       for(int i = 0; i < a.length; i++) {
//           System.out.println(a[i]);
//       }
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
