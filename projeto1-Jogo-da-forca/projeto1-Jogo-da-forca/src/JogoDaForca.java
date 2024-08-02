import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JogoDaForca {
    public ArrayList<String> palavras;
    public String palavraSorteada;
    String[] partes;
    public int acertos;
    public int penalidade;
    private List<Character> letrasAdivinhadas;
    char letra;

    public JogoDaForca(String path) throws IOException {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Caminho do arquivo não pode ser vazio.");
        }

        palavras = new ArrayList<>();
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha;
        while ((linha = buffRead.readLine()) != null) {
            palavras.add(linha); // Adiciona cada palavra a lista
        }
        buffRead.close();
        letrasAdivinhadas = new ArrayList<>();
    }

    public void iniciar() {
        Random random = new Random();
        int indiceSorteado = random.nextInt(palavras.size());
        palavraSorteada = palavras.get(indiceSorteado); // Atribui à variável de instância

        acertos = 0;
        penalidade = 0;
        partes = palavraSorteada.split(";");
        letrasAdivinhadas.clear(); // Limpa a lista de letras adivinhadas
    }

    public String getDica() {
        if (palavraSorteada == null) {
            return "Nenhuma palavra sorteada ainda.";
        }

        partes = palavraSorteada.split(";");
        if (partes.length >= 2) {
            return partes[1].trim();
        } else {
            return "Dica não encontrada.";
        }
    }

    public int getTamanho() {
        return partes[0].length();
    }

    public List<Integer> getOcorrencias(char letra) throws Exception {
        // Verifica se a letra é válida
        if (letra == ' ' || !Character.isLetter(letra)) {
            throw new IllegalArgumentException("A entrada deve ser uma única letra.");
        }

        // Converte a letra para minúscula para comparação
        letra = Character.toLowerCase(letra);

        // Verifica se a letra já foi adivinhada anteriormente
        if (letrasAdivinhadas.contains(letra)) {
            throw new Exception("Letra já foi adivinhada anteriormente.");
        }

        List<Integer> ocorrencias = new ArrayList<>();
        for (int i = 0; i < partes[0].length(); i++) {
            char letraNaPalavra = Character.toLowerCase(partes[0].charAt(i));
            if (letraNaPalavra == letra) {
                ocorrencias.add(i + 1); // Adiciona a posição da ocorrência (1 a N)
            }
        }

        // Atualiza acertos ou penalidade
        if (!ocorrencias.isEmpty()) {
            acertos += ocorrencias.size();
            letrasAdivinhadas.add(letra); // Adiciona a letra a lista de letras adivinhadas
        } else {
            penalidade++;
        }

        return ocorrencias;
    }

    public boolean terminou() {
        // Retorna true se o jogo terminou (todas as letras foram adivinhadas ou a penalidade atingiu o limite)
        return acertos == partes[0].replaceAll("[\\s-]", "").length() || penalidade >= 6;
    }

    public String getPalavraAdivinhada() {
        StringBuilder palavraAdivinhada = new StringBuilder();
        for (char letra : partes[0].toCharArray()) {
            if (letrasAdivinhadas.contains(Character.toLowerCase(letra))) {
                palavraAdivinhada.append(letra); // Mostra a letra adivinhada
            } else {
                palavraAdivinhada.append('*'); // Substitui letras não adivinhadas por asteriscos
            }
        }
        return palavraAdivinhada.toString();
    }

    public int getAcertos() {
        // Retorna o total de acertos
        return acertos;
    }

    public int getNumeroPenalidade() {
    	
        // Retorna o número (0 a 6) da penalidade atual
    	
        return penalidade;
    }

    public String getNomePenalidade() {
        // Retorna o nome da penalidade atual
    	
        String[] partesDoCorpo = {"", "Perna esquerda", "Perna direita", "Braço esquerdo", "Braço direito", "Tronco", "Cabeça"};

        // Verifica se a penalidade atual está dentro do limite tolerado
        
        if (penalidade >= 0 && penalidade < partesDoCorpo.length) {
            return partesDoCorpo[penalidade];
        } else {
            return "Forca"; // Se ultrapassar o limite, retorne "Forca"
        }
    }

    
    public String getResultado() {
    	
        // Retorna uma das 3 opções: “você venceu” ou “você foi enforcado” (se o jogo terminou) ou “jogo em andamento” (se o jogo ainda não terminou).
    	
        if (acertos == partes[0].replaceAll("[\\s-]", "").length()) {
            return "Você venceu!";
        } else if (penalidade >= 6) {
            return "Você foi enforcado!";
        } else {
            return "Jogo em andamento";
        }
    }
}
