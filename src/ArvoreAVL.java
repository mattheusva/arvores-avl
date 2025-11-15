public class ArvoreAVL {
    private No raiz;

    public ArvoreAVL() {
        this.raiz = null;
    }

    // MÉTODOS PÚBLICOS
    /* Vitoria */
    public void inserir(int valor) {
        if (raiz == null) {
            raiz = new No(valor);
        } else {
            raiz = inserirRec(raiz, valor);
        }
    }

    // MÉTODOS PRIVADOS
    /* Vitoria */
    private int altura(No no) {
        if (no == null) {
            return 0;
        } else {
            int alturaEsquerda = altura(no.getEsquerda());
            int alturaDireita = altura(no.getDireita());

            return 1 + Math.max(alturaEsquerda, alturaDireita);
        }
    }

    /* Vitoria */
    private int fatorBalanceamento(No no) {
        return altura(no.getEsquerda()) - altura(no.getDireita());
    }

    /* Vitoria */
    private No rotacaoDireita(No y) {
        No x = y.getEsquerda();
        No temp = x.getDireita();

        x.setDireita(y);
        y.setEsquerda(temp);

        y.setAltura(1 + Math.max(altura(y.getEsquerda()), altura(y.getDireita())));
        x.setAltura(1 + Math.max(altura(x.getEsquerda()), altura(x.getDireita())));

        return x;
    }

    /* Vitoria */
    private No rotacaoEsquerda(No x) {
        No y = x.getDireita();
        No temp = y.getEsquerda();

        y.setEsquerda(x);
        x.setDireita(temp);

        x.setAltura(1 + Math.max(altura(x.getEsquerda()), altura(x.getDireita())));
        y.setAltura(1 + Math.max(altura(y.getEsquerda()), altura(y.getDireita())));

        return y;
    }

    /* Vitoria */
    private No inserirRec(No no, int valor) {
        if (valor < no.getValor()) {
            if (no.getEsquerda() == null) {
                no.setEsquerda(new No(valor));
            } else {
                no.setEsquerda(inserirRec(no.getEsquerda(), valor));
            }
        } else if (valor > no.getValor()) {
            if (no.getDireita() == null) {
                no.setDireita(new No(valor));
            } else {
                no.setDireita(inserirRec(no.getDireita(), valor));
            }
        } else {
            return no;
        }

        no.setAltura(1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita())));
        int fb = fatorBalanceamento(no);

        if (fb > 1 && valor < no.getEsquerda().getValor()) {
            return rotacaoDireita(no);
        } else if (fb > 1 && valor > no.getEsquerda().getValor()) {
            no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
            return rotacaoDireita(no);
        } else if (fb < -1 && valor > no.getDireita().getValor()) {
            return rotacaoEsquerda(no);
        } else if (fb < -1 && valor < no.getDireita().getValor()) {
            no.setDireita(rotacaoDireita(no.getDireita()));
            return rotacaoEsquerda(no);
        }

        return no;
    }

    /* Fernando */
    public void remover(int valor) {
        raiz = removerRec(raiz, valor);
    }

    /* Fernando */
    private No removerRec(No no, int valor) {
        // Passo 1: Remoção padrão de BST
        if (no == null) {
            return null;
        }

        if (valor < no.getValor()) {
            no.setEsquerda(removerRec(no.getEsquerda(), valor));
        } else if (valor > no.getValor()) {
            no.setDireita(removerRec(no.getDireita(), valor));
        } else {
            // Nó encontrado - casos de remoção
            // Caso 1: Nó sem filhos ou com um filho
            if (no.getEsquerda() == null) {
                return no.getDireita();
            } else if (no.getDireita() == null) {
                return no.getEsquerda();
            }

            // Caso 2: Nó com dois filhos
            // Encontra o sucessor in-order (menor valor na subárvore direita)
            No sucessor = encontrarMinimo(no.getDireita());
            no.setValor(sucessor.getValor());
            no.setDireita(removerRec(no.getDireita(), sucessor.getValor()));
        }

        // Passo 2: Atualizar altura do nó atual
        no.setAltura(1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita())));

        // Passo 3: Obter fator de balanceamento
        int fb = fatorBalanceamento(no);

        // Passo 4: Rebalancear a árvore
        // Caso Esquerda-Esquerda
        if (fb > 1 && fatorBalanceamento(no.getEsquerda()) >= 0) {
            return rotacaoDireita(no);
        }

        // Caso Esquerda-Direita
        if (fb > 1 && fatorBalanceamento(no.getEsquerda()) < 0) {
            no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
            return rotacaoDireita(no);
        }

        // Caso Direita-Direita
        if (fb < -1 && fatorBalanceamento(no.getDireita()) <= 0) {
            return rotacaoEsquerda(no);
        }

        // Caso Direita-Esquerda
        if (fb < -1 && fatorBalanceamento(no.getDireita()) > 0) {
            no.setDireita(rotacaoDireita(no.getDireita()));
            return rotacaoEsquerda(no);
        }

        return no;
    }

    /* Fernando - Método auxiliar para encontrar o nó com valor mínimo */
    private No encontrarMinimo(No no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }
        return no;
    }

    /* Fernando */
    public boolean buscar(int v) {
        StringBuilder caminho = new StringBuilder();
        boolean encontrado = buscarComCaminho(raiz, v, caminho);

        // Remove último espaço se houver
        if (caminho.length() > 0) {
            caminho.setLength(caminho.length() - 1);
        }

        System.out.println("Caminho: " + caminho.toString());
        System.out.println("Encontrado: " + (encontrado ? "sim" : "não"));

        return encontrado;
    }

    /* Fernando - Método auxiliar recursivo para busca com caminho */
    private boolean buscarComCaminho(No no, int v, StringBuilder caminho) {
        if (no == null) {
            return false;
        }

        // Adiciona o valor atual ao caminho
        caminho.append(no.getValor()).append(" ");

        // Se encontrou, retorna true
        if (no.getValor() == v) {
            return true;
        }

        // Continua a busca recursivamente
        if (v < no.getValor()) {
            return buscarComCaminho(no.getEsquerda(), v, caminho);
        } else {
            return buscarComCaminho(no.getDireita(), v, caminho);
        }
    }

    public String percursoPreOrdem() {
        StringBuilder sb = new StringBuilder();
        pre(raiz, sb);
        if (sb.length() >= 1)
            sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /* Matheus - Método auxiliar para percurso pré-ordem */
    private void pre(No no, StringBuilder sb) {
        if (no == null)
            return;

        // 1 visita o nó (raiz)
        sb.append(no.getValor()).append(" ");

        // 2 percorre a subárvore esquerda
        pre(no.getEsquerda(), sb);

        // 3 percorre a subárvore direita
        pre(no.getDireita(), sb);
    }

    public String percursoEmOrdem() {
        StringBuilder sb = new StringBuilder();
        em(raiz, sb);
        if (sb.length() >= 1)
            sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /* Matheus - Método auxiliar para percurso em-ordem */
    private void em(No no, StringBuilder sb) {
        if (no == null)
            return;
        em(no.getEsquerda(), sb);
        sb.append(no.getValor()).append(" ");
        em(no.getDireita(), sb);
    }

    public String percursoPosOrdem() {
        StringBuilder sb = new StringBuilder();
        pos(raiz, sb);
        if (sb.length() >= 1)
            sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /* Matheus - Método auxiliar para percurso pós-ordem */
    private void pos(No no, StringBuilder sb) {
        if (no == null)
            return;
        pos(no.getEsquerda(), sb);
        pos(no.getDireita(), sb);
        sb.append(no.getValor()).append(" ");
    }

    /* Fernando */
    public boolean contem(int v) {
        return contemRec(raiz, v);
    }

    /* Fernando - Método auxiliar recursivo para contem */
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
        if (raiz == null) {
            return "(Árvore vazia)\n";
        }
        return imprimirArvoreRec(raiz, "", true);
    }

    private String imprimirArvoreRec(No no, String prefixo, boolean ehFolha) {
        if (no == null) {
            return "";
        }

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
