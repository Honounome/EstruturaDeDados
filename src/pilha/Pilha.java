/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilha;

/**
 *
 * @author Edimax e Junior
 */
public class Pilha {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PilhaExemplo p1 = new PilhaExemplo();
        p1.Empilha("el01");
        p1.Empilha("el02");
        p1.Empilha("el03");
        p1.Empilha("el04");
        System.out.println(p1.Desempilha());
        System.out.println(p1.Desempilha());
    }
    
}
