public class ArvoreAVL {
    private No raiz;

    // MÉTODOS PÚBLICOS
    /* vitoria */
    public void inserir(int valor) {
        // só para compilar por enquanto
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
    /* calcula a diferença da altura da esquerda e direita
     * é utilizado para saber se a árvore precisa ser rotacionada */
    private int fatorBalanceamento(No no) {
        return altura(no.getEsquerda()) - altura(no.getDireita());
    }

    /* vitoria */
    private No rotacaoDireita(No y) {
        return null; // só para compilar por enquanto
    }
    
    /* vitoria */
    private No rotacaoEsquerda(No x) {
        return null; // só para compilar por enquanto
    }

    /* vitoria */
    private No inserirRec(No no, int valor) {
        if (no == null) {
            no = new No(valor);
            return no;
        } else if (no.getValor() < valor) {
            // no.esquerda = inserir(no.esquerda, valor);
        } else if (no.getValor() > valor) {
            // no.direita = inserir(no.direita, valor);
        }

        no.setAltura(1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita())));
        int fb = fatorBalanceamento(no);


        // left-left
        if (fb > 1 && valor < no.getEsquerda().getValor()) {
            rotacaoDireita(no);
        // left-right
        } else if (fb > 1 && valor > no.getEsquerda().getValor()) {
            // no.getEsquerda = rotacaoEsquerda(no.getEsquerda());
            return rotacaoDireita(no);
        // right-right
        } else if (fb < -1 && valor > no.getDireita().getValor()) {
            rotacaoEsquerda(no);
        // right-left
        } else if (fb < -1 && valor < no.getDireita().getValor()) {
            // no.getDireita = rotacaoDireita(no.getDireita());
            return rotacaoEsquerda(no);
        } else {
            return no;
        }

        // cada vez que eu passar, devo salvar em uma lista o caminho percorrido?
        // String arvore = imprimirArvore("");
        // return arvore; 
        return null;
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


