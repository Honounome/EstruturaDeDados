/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fila;

/**
 *
 * @author Edimax 
 */
public class Fila {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        FilaExemplo f1 = new FilaExemplo();
        f1.enfileira("elemento1");
        f1.enfileira("elemento2");
        System.out.println(f1.desenfileira());
        f1.enfileira("elemento3");
        System.out.println(f1.desenfileira());
    }
    
}
