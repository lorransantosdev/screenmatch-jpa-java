package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.model.SerieData;
import br.com.alura.screenmatch.model.SeasonData;
import br.com.alura.screenmatch.service.ConsumeApi;
import br.com.alura.screenmatch.service.ConvertData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private Scanner leitura = new Scanner(System.in);
    private ConsumeApi consumo = new ConsumeApi();
    private ConvertData conversor = new ConvertData();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    private List<SerieData> seriesData = new ArrayList<>();

    public void exibeMenu() {
        int option = -1;
        while (option != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar series buscadas
                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            var opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;

                case 3:
                    listSeries();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarSerieWeb() {
        SerieData data = getDadosSerie();
        seriesData.add(data);
        System.out.println(data);
    }

    private SerieData getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        return conversor.obterDados(json, SerieData.class);
    }

    private void buscarEpisodioPorSerie(){
        SerieData serieData = getDadosSerie();
        List<SeasonData> temporadas = new ArrayList<>();

        for (int i = 1; i <= serieData.totalSeason(); i++) {
            var json = consumo.obterDados(ENDERECO + serieData.title().replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonData dadosTemporada = conversor.obterDados(json, SeasonData.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    private void listSeries() {
        List<Serie> series = seriesData.stream()
                        .map(Serie::new)
                                .toList();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenders))
                .forEach(System.out::println);
    }
}