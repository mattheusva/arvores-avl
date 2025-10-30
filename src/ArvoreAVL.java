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
        if (no == null) return 0;
        return no.getAltura();
    }

    /* vitoria */
    /* calcula a diferença da altura da esquerda e direita
     * é utilizado para saber se a árvore precisa ser rotacionada */
    private int fatorBalanceamento(No no) {
        //fb = altura(no.getEsquerda()) - altura(no.getDireita())
        //return fb;
        return 0; // só para compilar por enquanto
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
        return null; // só para compilar por enquanto
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


