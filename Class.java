import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class Class {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome da receita: ");
        String nomeReceita = scanner.nextLine().trim();
        if (nomeReceita.isEmpty()) {
            System.out.println("O nome da receita não pode ficar vazio.");
            scanner.close();
            return;
        }

        List<String> ingredientes = new ArrayList<>();
        int ingredienteNumero = 1;
        while (true) {
            System.out.print("Descrição do ingrediente " + ingredienteNumero + " (ou pressione Enter para finalizar): ");
            String descricao = scanner.nextLine().trim();
            if (descricao.isEmpty()) {
                break;
            }

            System.out.print("Quantidade do ingrediente " + ingredienteNumero + ": ");
            String quantidade = scanner.nextLine().trim();
            if (quantidade.isEmpty()) {
                System.out.println("Quantidade não pode ficar vazia. Tente novamente.");
                continue;
            }

            ingredientes.add(ingredienteNumero + ". " + quantidade + " - " + descricao);
            ingredienteNumero++;
        }

        List<String> instrucoes = new ArrayList<>();
        int passoNumero = 1;
        while (true) {
            System.out.print("Passo " + passoNumero + " (ou pressione Enter para finalizar): ");
            String passo = scanner.nextLine().trim();
            if (passo.isEmpty()) {
                break;
            }
            instrucoes.add(passoNumero + ". " + passo);
            passoNumero++;
        }

        scanner.close();

        if (ingredientes.isEmpty()) {
            System.out.println("Nenhum ingrediente informado. A receita não será salva.");
            return;
        }

        if (instrucoes.isEmpty()) {
            System.out.println("Nenhuma instrução informada. A receita não será salva.");
            return;
        }

        String arquivoNome = criarNomeDeArquivo(nomeReceita);
        try {
            salvarReceita(arquivoNome, nomeReceita, ingredientes, instrucoes);
            System.out.println("Receita salva em: " + arquivoNome);
            System.out.println("Fique feliz! Sua receita foi registrada com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a receita: " + e.getMessage());
        }
    }

    private static String criarNomeDeArquivo(String nomeReceita) {
        String nomeSanitizado = nomeReceita.replaceAll("[\\/:*?\"<>|]", "_");
        return nomeSanitizado + ".txt";
    }

    private static void salvarReceita(
            String arquivoNome,
            String nomeReceita,
            List<String> ingredientes,
            List<String> instrucoes) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoNome))) {
            writer.write(nomeReceita);
            writer.newLine();
            writer.newLine();
            writer.write("Ingredientes:");
            writer.newLine();
            for (String ingrediente : ingredientes) {
                writer.write(ingrediente);
                writer.newLine();
            }
            writer.newLine();
            writer.write("Instruções:");
            writer.newLine();
            for (String passo : instrucoes) {
                writer.write(passo);
                writer.newLine();
            }
        }
    }
}