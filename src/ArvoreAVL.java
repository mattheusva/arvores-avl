public class ArvoreAVL {
    private No raiz;

    // MÉTODOS PÚBLICOS
    /* vitoria */
    public void inserir(int valor) {
        if (raiz == null) {
            raiz = new No(valor);
        } else {
            inserirRec(raiz, valor);
        }
    }

    public void remover(int valor) {
        // só para compilar por enquanto
    }

    public boolean buscar(int valor) {
        return false; // só para compilar por enquanto
    }

    public String percursoPreOrdem() {
        return ""; // só para compilar por enquanto
    }

    public String percursoEmOrdem() {
        return ""; // só para compilar por enquanto
    }

    public String percursoPosOrdem() {
        return ""; // só para compilar por enquanto
    }

    public String imprimirArvore() {
        return ""; // só para compilar por enquanto
    }

    public boolean contem(int valor) {
        return false; // só para compilar por enquanto
    }

    // MÉTODOS PRIVADOS
    /* vitoria */
    private int altura(No no) {
        if (no == null) {
            return 0;
        } else {
            int alturaEsquerda = altura(no.getEsquerda());
            int alturaDireita = altura(no.getDireita());

            return 1 + Math.max(alturaEsquerda, alturaDireita);
        }
    }

    /* vitoria */
    private int fatorBalanceamento(No no) {
        return altura(no.getEsquerda()) - altura(no.getDireita());
    }

    /* vitoria */
    private No rotacaoDireita(No y) {
        No x = y.getEsquerda();
        No temp = x.getDireita();

        x.setDireita(y);
        y.setEsquerda(temp);

        y.setAltura(1 + Math.max(altura(y.getEsquerda()), altura(y.getDireita())));
        x.setAltura(1 + Math.max(altura(x.getEsquerda()), altura(x.getDireita())));

        return x;
    }
    
    /* vitoria */
    private No rotacaoEsquerda(No x) {
        No y = x.getDireita();
        No temp = y.getEsquerda();

        y.setEsquerda(x);
        x.setDireita(temp);

        x.setAltura(1 + Math.max(altura(x.getEsquerda()), altura(x.getDireita())));
        y.setAltura(1 + Math.max(altura(y.getEsquerda()), altura(y.getDireita())));

        return y;
    }

    /* vitoria */
    private No inserirRec(No no, int valor) {
        if (valor < no.getValor()) {
            if (no.getEsquerda() == null) {
                no.setEsquerda(new No(valor));
            } else {
                inserirRec(no.getEsquerda(), valor);
            }
        } else if (valor > no.getValor()) {
            if (no.getDireita() == null) {
                no.setDireita(new No(valor));
            } else {
                inserirRec(no.getDireita(), valor);
            }
        } else {
            return no;
        }

        no.setAltura(1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita())));
        int fb = fatorBalanceamento(no);

        if (fb > 1 && valor < no.getEsquerda().getValor()) {
            rotacaoDireita(no);
        } else if (fb > 1 && valor > no.getEsquerda().getValor()) {
            no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
            return rotacaoDireita(no);
        } else if (fb < -1 && valor > no.getDireita().getValor()) {
            rotacaoEsquerda(no);
        } else if (fb < -1 && valor < no.getDireita().getValor()) {
            no.setDireita(rotacaoDireita(no.getDireita()));
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private No removerRec(No no, int valor) {
        return null; // só para compilar por enquanto
    }

    private void pre(No n, StringBuilder sb) {
        // só para compilar por enquanto
    }

    private void em(No n, StringBuilder sb) {
        // só para compilar por enquanto
    }

    private void pos(No n, StringBuilder sb) {
        // só para compilar por enquanto
    }
}


