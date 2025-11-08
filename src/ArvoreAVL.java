public class ArvoreAVL {
    private No raiz;

    public ArvoreAVL() {
        this.raiz = null;
    }

    public String percursoPreOrdem() {
        StringBuilder sb = new StringBuilder();
        percursoPreOrdemRec(raiz, sb);
        // remove última vírgula e espaço, se existirem
        if (sb.length() >= 2)
            sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    private void percursoPreOrdemRec(No no, StringBuilder sb) {
        if (no == null)
            return;

        // 1 visita o nó (raiz)
        sb.append(no.getValor()).append(", ");

        // 2 percorre a subárvore esquerda
        percursoPreOrdemRec(no.getEsquerda(), sb);

        // 3 percorre a subárvore direita
        percursoPreOrdemRec(no.getDireita(), sb);
    }

    public String percursoEmOrdem() {
        StringBuilder sb = new StringBuilder();
        percursoEmOrdemRec(raiz, sb);
        if (sb.length() >= 2)
            sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    private void percursoEmOrdemRec(No no, StringBuilder sb) {
        if (no == null)
            return;
        percursoEmOrdemRec(no.getEsquerda(), sb);
        sb.append(no.getValor()).append(", ");
        percursoEmOrdemRec(no.getDireita(), sb);
    }

    public String percursoPosOrdem() {
        StringBuilder sb = new StringBuilder();
        percursoPosOrdemRec(raiz, sb);
        if (sb.length() >= 2)
            sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    private void percursoPosOrdemRec(No no, StringBuilder sb) {
        if (no == null)
            return;
        percursoPosOrdemRec(no.getEsquerda(), sb);
        percursoPosOrdemRec(no.getDireita(), sb);
        sb.append(no.getValor()).append(", ");
    }

    // Métodos temporários
    public void inserir(int v) {
        // por enquanto
        raiz = inserirRec(raiz, v);
    }

    private No inserirRec(No atual, int v) {
        if (atual == null)
            return new No(v);

        if (v < atual.getValor())
            atual.setEsquerda(inserirRec(atual.getEsquerda(), v));
        else if (v > atual.getValor())
            atual.setDireita(inserirRec(atual.getDireita(), v));

        return atual;
    }

    public boolean contem(int v) {
        return contemRec(raiz, v);
    }

    private boolean contemRec(No atual, int v) {
        if (atual == null)
            return false;
        if (v == atual.getValor())
            return true;
        if (v < atual.getValor())
            return contemRec(atual.getEsquerda(), v);
        else
            return contemRec(atual.getDireita(), v);
    }

    public String imprimirArvore() {
        return imprimirArvoreRec(raiz, "", true);
    }

    private String imprimirArvoreRec(No no, String prefixo, boolean ehFolha) {

        StringBuilder sb = new StringBuilder();
        sb.append(prefixo)
                .append(ehFolha ? "└── " : "├── ")
                .append(no.getValor())
                .append("\n");

        String novoPrefixo = prefixo + (ehFolha ? "    " : "│   ");
        if (no.getEsquerda() != null)
            sb.append(imprimirArvoreRec(no.getEsquerda(), novoPrefixo, false));
        if (no.getDireita() != null)
            sb.append(imprimirArvoreRec(no.getDireita(), novoPrefixo, true));

        return sb.toString();
    }
}
