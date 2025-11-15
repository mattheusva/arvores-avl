public class ArvoreAVL {
    private No raiz;

    public ArvoreAVL() {
        this.raiz = null;
    }

    // MÉTODOS PÚBLICOS
    /* vitoria */
    public void inserir(int valor) {
        if (raiz == null) {
            raiz = new No(valor);
        } else {
            inserirRec(raiz, valor);
        }
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

    public String percursoPreOrdem() {
        StringBuilder sb = new StringBuilder();
        percursoPreOrdemRec(raiz, sb);
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
