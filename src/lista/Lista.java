package lista;

public class Lista {

    public static void main(String[] args) {
        ListaExemplo listaSimples = new ListaExemplo(5);
        
        listaSimples.insereElemento(1);
        listaSimples.insereElemento(2);
        listaSimples.insereElemento(3);
        listaSimples.insereElemento(4);
        listaSimples.insereElemento(5);
        
        System.out.println("\nTeste para a 1ª questão:");
        listaSimples.removeElemento(2);
        listaSimples.insereElemento(10);
        listaSimples.consultaElemento();
        
        System.out.println("\nTeste para a 2ª questão:");
        listaSimples.remover(10);
        listaSimples.consultaElemento();
        
        System.out.println("\nTeste para a 3ª questão:");
        System.out.println("O valor 4 está na posição: "
                + listaSimples.consultarValor(4));
        listaSimples.consultaElemento();
        
        System.out.println("\nTeste para a 4ª questão:");
        listaSimples.removeElemento(0);
        listaSimples.removeElemento(4);
        listaSimples.insereElemento(new int[]{10, 9, 8});
        listaSimples.consultaElemento();
        
        System.out.println("\nTeste para a 5ª questão:");
        listaSimples.insereElemento(new int[]{1, 3, 5}, new int[]{0, 2, 4});
        listaSimples.consultaElemento();
    }
    
}
