package lista;
// English motherfucker, do you speak?
public class ListaExemplo {
// fwfwfdsfewfwef
    private int[] valores;
    private int tamanho;

    public ListaExemplo(int tam) {
        if (tam > 0) {
            this.tamanho = tam;
            this.valores = new int[tam];
        }
    }

    public int removeElemento(int pos) {
        int valor = -1;
        if (pos >= 0 && pos < this.valores.length) {
            valor = this.valores[pos];
            this.valores[pos] = 0;
        }
        return valor;
    }

    public void consultaElemento(int pos) {
        if (pos >= 0 && pos < this.valores.length) {
            System.out.println("O valor da posição [" + pos + "] é "
                    + this.valores[pos]);
        } else {
            System.out.println("Esta posição [" + pos + "] "
                    + "não foi encontrada.\nO número total de posições é "
                    + (this.valores.length - 1));
        }
    }

    public void consultaElemento() {
        for (int i = 0; i < this.valores.length; i++) {
            System.out.println("O valor da posição [" + i + "] é "
                    + this.valores[i]);
        }
    }

    public int insereElemento(int valor) {
        for (int i = 0; i < this.valores.length; i++) {
            if (this.valores[i] == 0) {
                this.valores[i] = valor;
                break;
            }
        }
        return -1;
    }

    public void remover(int valor) {
        for (int i = 0; i < this.valores.length; i++) {
            if (this.valores[i] == valor) {
                this.valores[i] = 0;
                return;
            }
        }
    }

    public int consultarValor(int valor) {
        for (int i = 0; i < this.valores.length; i++) {
            if (this.valores[i] == valor) {
                return i;
            }
        }
        return -1;
    }
    
    public int insereElemento(int[] vals) {
        int cont = 0;
        for (int i = 0; i < this.valores.length; i++) {
            if (this.valores[i] == 0) {
                this.valores[i] = vals[cont++];
            }
        }
        return -1;
    }
    
    public int insereElemento(int[] vals, int[] pos) {
        for (int i = 0; i < pos.length; i++) {
            this.valores[pos[i]] = vals[i];
        }
        return -1;
    }

}
