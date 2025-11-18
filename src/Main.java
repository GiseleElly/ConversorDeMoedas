import com.google.gson.Gson;
import models.Taxas;

import java.util.Scanner;

public class Main {

    static final String URL =
            "https://v6.exchangerate-api.com/v6/0a36cb763a2adab1a5af7657/latest/USD";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Gson gson = new Gson();

        System.out.println("Buscando taxas de câmbio...");
        String json = ClienteHttp.buscarDados(URL);

        if (json == null) {
            System.out.println("Não foi possível obter os dados da API.");
            return;
        }

        // Agora mapeando o JSON diretamente para a classe Taxas
        Taxas taxas = gson.fromJson(json, Taxas.class);

        if (taxas == null || taxas.getRates() == null) {
            System.out.println("Erro ao interpretar o JSON da API.");
            return;
        }

        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== CONVERSOR DE MOEDAS ===");
            System.out.println("1 - USD → BRL");
            System.out.println("2 - BRL → USD");
            System.out.println("3 - EUR → BRL");
            System.out.println("4 - BRL → EUR");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();

            if (opcao == 5) {
                System.out.println("Encerrando...");
                rodando = false;
                continue;
            }

            System.out.print("Digite o valor para converter: ");
            double valor = sc.nextDouble();

            double taxaUSD = taxas.getRates().get("USD");
            double taxaBRL = taxas.getRates().get("BRL");
            double taxaEUR = taxas.getRates().get("EUR");

            double convertido = 0;
            String msg = "";

            switch (opcao) {
                case 1:
                    convertido = ConversorMoedas.converter(valor, taxaUSD, taxaBRL);
                    msg = "USD → BRL";
                    break;

                case 2:
                    convertido = ConversorMoedas.converter(valor, taxaBRL, taxaUSD);
                    msg = "BRL → USD";
                    break;

                case 3:
                    convertido = ConversorMoedas.converter(valor, taxaEUR, taxaBRL);
                    msg = "EUR → BRL";
                    break;

                case 4:
                    convertido = ConversorMoedas.converter(valor, taxaBRL, taxaEUR);
                    msg = "BRL → EUR";
                    break;

                default:
                    System.out.println("Opção inválida!");
                    continue;
            }

            System.out.printf("Conversão (%s): %.2f → %.2f\n", msg, valor, convertido);
        }

        sc.close();
    }
}

