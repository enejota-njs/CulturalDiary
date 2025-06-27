package series.series;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import review.ReviewModel;
import series.season.SeasonController;
import series.season.SeasonModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Set;

/**
 * Controller class for managing series-related operations.
 *
 * @author Nathan de Jesus dos Santos
 * @version 1.1
 */
public class SeriesController {
    SeriesModel seriesModel;
    SeriesView seriesView = new SeriesView();
    private ArrayList<SeriesModel> listOfSeries = new ArrayList<SeriesModel>();

    SeasonController seasonController = new SeasonController();

    Calendar calendar = Calendar.getInstance();

    Gson gson = new Gson(); // Instância do Gson para manipulação de JSON
    File repository = new File("src/main/java/series/repository/"); // Diretório do repositório de séries
    File file = new File(repository,"series_file.json"); // Arquivo JSON dentro do repositório de séries

    /**
     * Registers a new series with the given details.
     *
     * @param title The title of the series.
     * @param yearOfReleaseString The release year of the series as a string.
     * @param yearOfConclusionString The conclusion year of the series as a string.
     * @param originalTitle The original title of the series.
     * @param whereToWatch The platform or location where the series can be watched.
     * @param listOfSeasonString A 2D array containing season information as strings.
     * @return true if the series was successfully registered; false otherwise.
     */
    public boolean registerSeries(String title, String yearOfReleaseString, String yearOfConclusionString, String originalTitle, String whereToWatch, String[][] listOfSeasonString) {
        // Remove espaços em branco antes/depois dos valores recebidos
        title = title.trim();
        yearOfReleaseString = yearOfReleaseString.trim();
        yearOfConclusionString = yearOfConclusionString.trim();
        originalTitle = originalTitle.trim();
        whereToWatch = whereToWatch.trim();

        // Valida cada campo individualmente
        boolean validTitle = validateTitle(title);
        boolean validYearOfRelease = validateYearOfRelease(yearOfReleaseString);
        boolean validYearOfConclusion = validateYearOfConclusion(yearOfConclusionString);
        boolean validOriginalTitle = validateOriginalTitle(originalTitle);
        boolean validWhereToWatch = validateWhereToWatch(whereToWatch);

        // Se qualquer uma das validações falhar, exibe mensagem e retorna falso
        if (validTitle == false || validYearOfRelease == false || validYearOfConclusion == false || validOriginalTitle == false || validWhereToWatch == false) {
            seriesView.tryAgainMessage();
            return false;
        }

        try {
            // Converte os anos de string para inteiro
            int yearOfRelease = Integer.parseInt(yearOfReleaseString);
            int yearOfConclusion = Integer.parseInt(yearOfConclusionString);

            // Garante que o ano de conclusão não seja menor que o de lançamento
            if (yearOfConclusion < yearOfRelease) {
                seriesView.invalidYearsMessage();
                seriesView.tryAgainMessage();
                return false;
            }

            // Lista que armazenará os objetos SeasonModel
            ArrayList<SeasonModel> listOfSeasons = new ArrayList<SeasonModel>();

            // Percorre a lista de temporadas fornecidas
            for (int season = 0; season <= listOfSeasonString.length-1; season++) {
                // Extrai os dados da temporada atual
                String genreSeason = listOfSeasonString[season][0];
                String castStringSeason = listOfSeasonString[season][1];
                String yearStringSeason = listOfSeasonString[season][2];
                String watchedStringSeason = listOfSeasonString[season][3];

                // Valida os dados da temporada
                boolean validSeason = seasonController.validateSeason(genreSeason, castStringSeason, yearStringSeason, watchedStringSeason, season+1);

                // Se a temporada for inválida, interrompe o processo
                if (!validSeason) {
                    return false;
                }

                // Separa os nomes do elenco usando vírgula e espaço
                String[] castPeople = castStringSeason.split(",\\s*");

                // Cria lista de pessoas do elenco
                ArrayList<String> castSeason = new ArrayList<String>();
                for (String personal : castPeople) {
                    if (!personal.isEmpty()) {
                        castSeason.add(personal.trim()); // Remove espaços e adiciona à lista
                    }
                }

                // Se a lista de elenco estiver vazia, exibe mensagem e retorna falso
                if (castSeason.isEmpty()) {
                    seriesView.emptyCastMessage(season+1);
                    return false;
                }

                // Converte o ano da temporada de string para inteiro
                int yearSeason = Integer.parseInt(yearStringSeason);

                // Verifica se o ano da temporada está dentro do intervalo permitido
                if (yearSeason < yearOfRelease || yearSeason > yearOfConclusion) {
                    seriesView.invalidSeasonYearMessage(season+1);
                    return false;
                }

                // Define as respostas aceitas como "assistido"
                Set<String> positiveResponsesWatched = Set.of(
                        "sim", "s", "assisti", "sim assisti", "assisti sim", "s assisti", "assisti s", "já assisti", "ja assisti", "já", "ja"
                );

                // Define as respostas aceitas como "não assistido"
                Set<String> negativeResponsesWatched = Set.of(
                        "não", "nao", "n", "não assisti", "nao assisti", "n assisti",
                        "assisti não", "assisti nao", "assisti n"
                );

                // Interpreta a resposta e transforma em booleano
                boolean watchedSeason = false;
                if (positiveResponsesWatched.contains(watchedStringSeason.toLowerCase())) {
                    watchedSeason = true;
                } else if (negativeResponsesWatched.contains(watchedStringSeason.toLowerCase())) {
                    watchedSeason = false;
                }

                // Cria objeto da temporada com todos os dados processados
                SeasonModel seasonModel = new SeasonModel(genreSeason, castSeason, yearSeason, watchedSeason, season+1);
                listOfSeasons.add(seasonModel); // Adiciona à lista de temporadas
            }

            // Cria o objeto da série com todas as informações e temporadas
            seriesModel = new SeriesModel(title, yearOfRelease, yearOfConclusion, originalTitle, whereToWatch, listOfSeasons, listOfSeries.size() + 1);

            // Adiciona a série no repositório (persistência)
            listOfSeries.add(seriesModel);
            saveFile();

            // Exibe mensagem de sucesso
            seriesView.registeredSeriesMessage(title);

            return true;
        } catch (Exception e) {
            // Captura exceções inesperadas e exibe mensagem genérica de erro
            seriesView.invalidMessage();
            return false;
        }
    }

    /**
     * Validates the given title.
     *
     * @param title The title to validate.
     * @return true if the title is valid; false otherwise.
     */
    public boolean validateTitle(String title) {
        return validateNewString(title, "Título");
    } // Valida título

    /**
     * Validates the given year of release.
     *
     * @param yearOfRelease The year of release as a string.
     * @return true if the year of release is valid; false otherwise.
     */
    public boolean validateYearOfRelease(String yearOfRelease) {
        return validateNewYear(yearOfRelease, "Ano de lançamento");
    } // Valida ano de lançamento

    /**
     * Validates the given year of conclusion.
     *
     * @param yearOfConclusion The year of conclusion as a string.
     * @return true if the year of conclusion is valid; false otherwise.
     */
    public boolean validateYearOfConclusion(String yearOfConclusion) {
        return validateNewYear(yearOfConclusion, "Ano de encerramento");
    } // Valida ano de conclusão

    /**
     * Validates the given original title.
     *
     * @param originalTitle The original title to validate.
     * @return true if the original title is valid; false otherwise.
     */
    public boolean validateOriginalTitle(String originalTitle) {
        return validateNewString(originalTitle, "Título original");
    } // Valida título original

    /**
     * Validates the given string value based on the specified field name.
     *
     * @param value The string value to validate.
     * @param name The name of the field being validated.
     * @return true if the value is valid; false otherwise.
     */
    public boolean validateNewString(String value, String name) {
        if (value.isEmpty()) {
            seriesView.emptyValueMessage(name);
            return false;
        }
        return true;
    } // Valida nova string

    /**
     * Validates the given platform or location where the series can be watched.
     *
     * @param whereToWatch The platform or location string to validate.
     * @return true if the value is valid; false otherwise.
     */
    public boolean validateWhereToWatch(String whereToWatch) {
        return validateNewString(whereToWatch, "Onde assistir");
    } // Valida onde assistir

    /**
     * Validates the given year string based on the specified field name.
     *
     * @param value The year value as a string to validate.
     * @param name The name of the field being validated.
     * @return true if the year is valid; false otherwise.
     */
    public boolean validateNewYear(String value, String name) {

        // Primeiro, valida se a string não está vazia ou nula
        if (validateNewString(value, name)) {
            int valueInt = 0;
            int currentYear = calendar.get(Calendar.YEAR); // Obtém o ano atual do sistema

            try {
                valueInt = Integer.parseInt(value); // Tenta converter a string para inteiro
            } catch (Exception e) {
                seriesView.integerMessage(); // Se falhar, exibe mensagem de erro
                return false; // E retorna false
            }

            // Verifica se o ano está dentro do intervalo permitido (entre 1700 e o ano atual)
            if (valueInt < 1700 || valueInt > currentYear) {
                seriesView.invalidYearMessage(currentYear); // Mostra mensagem de ano inválido
                return false;
            }

            return true; // Retorna true se todas as validações forem passadas
        }

        return false; // Retorna false se a string for inválida
    } // Valida novo ano

    /**
     * Performs a search for a series by its title.
     *
     * @param value The title to search for.
     * @return true if the search was performed (even if no series was found); false if an error occurred.
     */
    public boolean searchSeriesByTitle(String value) {
        value = value.trim(); // Remove espaços em branco nas extremidades

        // Valida se a string fornecida é válida (não vazia, por exemplo)
        if (validateNewInputString(value)) {

            boolean seriesFound = false; // Flag para saber se alguma série foi encontrada

            if (!listOfSeries.isEmpty()) { // Verifica se a lista de séries não está vazia

                for (SeriesModel series : listOfSeries) {
                    // Compara o título da série (em minúsculas) com o valor buscado (também em minúsculas)
                    if (series.getTitle().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!seriesFound) {
                            seriesView.headerForSeries(); // Mostra o cabeçalho antes da primeira série encontrada
                            seriesFound = true; // Marca que encontrou pelo menos uma série
                        }

                        seriesView.seriesInformation(series); // Exibe as informações da série encontrada
                    }
                }
            }

            // Se nenhuma série foi encontrada, mostra mensagem apropriada
            if (!seriesFound) {
                seriesView.noSeriesFoundMessage();
            }

            return true; // A busca foi realizada (mesmo que não tenha encontrado nada)
        }

        return false; // Entrada inválida, então não realiza a busca
    } // Busca série por título

    /**
     * Lists all the series.
     *
     * @return true if the listing was successful; false if an error occurred.
     */
    public boolean listSeries() {
        try {
            // Verifica se a lista de séries está vazia
            if (listOfSeries.isEmpty()) {
                seriesView.emptyListMessage(); // Exibe mensagem informando que não há séries cadastradas
            } else {
                seriesView.headerForSeries(); // Exibe o cabeçalho da listagem

                // Percorre todas as séries na lista e exibe suas informações
                for (SeriesModel series : listOfSeries) {
                    seriesView.seriesInformation(series);
                }
            }
            return true; // Retorna verdadeiro se a listagem foi bem-sucedida
        } catch (Exception e) {
            seriesView.invalidMessage(); // Caso ocorra algum erro, exibe mensagem de erro genérica
            return false; // Retorna falso indicando que houve falha na listagem
        }
    } // Lista séries

    /**
     * Filters the list of series by the specified genre.
     *
     * @param value The genre to filter the series by.
     * @return true if the filtering was successful; false if an error occurred.
     */
    public boolean filterListOfSeriesByGenre(String value) {
        value = value.trim(); // Remove espaços em branco do início e fim da entrada

        if (validateNewInputString(value)) { // Valida se o valor informado não está vazio ou inválido

            boolean seriesFound = false; // Flag para indicar se alguma série foi encontrada

            if (!listOfSeries.isEmpty()) { // Verifica se há séries cadastradas

                // Percorre a lista de séries
                for (SeriesModel series : listOfSeries) {

                    // Para cada série, percorre suas temporadas
                    for (SeasonModel season : series.getListOfSeasons()) {

                        // Verifica se o gênero da temporada contém o valor informado (ignorando maiúsculas/minúsculas)
                        if (season.getGenre().toLowerCase().contains(value.toLowerCase().trim())) {

                            // Exibe o cabeçalho uma única vez, na primeira ocorrência encontrada
                            if (!seriesFound) {
                                seriesView.headerForSeries();
                                seriesFound = true;
                            }

                            // Exibe as informações da série que possui uma temporada com o gênero procurado
                            seriesView.seriesInformation(series);
                        }
                    }
                }
            }

            // Caso nenhuma série tenha sido encontrada com o gênero procurado
            if (!seriesFound) {
                seriesView.noSeriesFoundMessage();
            }

            return true; // Retorna verdadeiro se o processo foi executado corretamente
        }

        return false; // Retorna falso se a entrada for inválida
    } // Filtra séries por gênero

    /**
     * Filters the list of series by the specified year of release.
     *
     * @param value The year of release to filter the series by.
     * @return true if the filtering was successful; false if an error occurred.
     */
    public boolean filterListOfSeriesByYearOfRelease(String value) {
        value = value.trim(); // Remove espaços em branco no início e fim da string

        // Verifica se a entrada é válida (string não vazia e valor numérico)
        if (validateNewInputString(value) && validateNewInputInt(value)) {

            boolean seriesFound = false; // Flag para controlar se alguma série foi encontrada

            if (!listOfSeries.isEmpty()) { // Verifica se a lista de séries não está vazia

                int valueInt = 0;
                try {
                    valueInt = Integer.parseInt(value); // Converte a string para inteiro
                } catch (Exception e) {
                    seriesView.invalidMessage(); // Mensagem de erro caso a conversão falhe
                    return false;
                }

                // Percorre todas as séries da lista
                for (SeriesModel series : listOfSeries) {

                    // Compara o ano de lançamento da série com o valor informado
                    if (series.getYearOfRelease() == valueInt) {

                        // Exibe o cabeçalho apenas uma vez, na primeira ocorrência
                        if (!seriesFound) {
                            seriesView.headerForSeries();
                            seriesFound = true;
                        }

                        // Exibe as informações da série encontrada
                        seriesView.seriesInformation(series);
                    }
                }
            }

            // Se nenhuma série foi encontrada, exibe a mensagem correspondente
            if (!seriesFound) {
                seriesView.noSeriesFoundMessage();
            }

            return true; // Retorna verdadeiro se tudo foi executado corretamente
        }

        return false; // Retorna falso se a entrada foi inválida
    } // Filtra séries por ano de lançamento

    /**
     * Sorts the list of series by top-rated criteria.
     *
     * @return true if the sorting was successful; false if an error occurred.
     */
    public boolean sortListByTopRated() {
        try {
            // Verifica se a lista de séries não está vazia
            if (!listOfSeries.isEmpty()) {
                ArrayList<SeriesModel> listOfReviewedSeries = new ArrayList<SeriesModel>();

                // Adiciona apenas as séries que foram avaliadas (nota diferente de 0)
                for (SeriesModel series : listOfSeries) {
                    if (series.getSeriesReview() != 0) {
                        listOfReviewedSeries.add(series);
                    }
                }

                // Cria uma nova lista com as séries avaliadas
                ArrayList<SeriesModel> highlyRatedSeries = new ArrayList<SeriesModel>(listOfReviewedSeries);

                // Se houver séries avaliadas
                if (!highlyRatedSeries.isEmpty()){
                    // Ordena as séries pela nota, da maior para a menor
                    highlyRatedSeries.sort(Comparator.comparing(
                            seriesModel -> seriesModel.getSeriesReview(),
                            Comparator.reverseOrder()
                    ));
                } else {
                    // Exibe mensagem caso nenhuma série tenha sido avaliada
                    seriesView.emptyEvaluatedListMessage();
                    return true;
                }

                // Exibe cabeçalho das séries
                seriesView.headerForSeries();
                // Exibe informações das séries ordenadas
                for (SeriesModel series : highlyRatedSeries) {
                    seriesView.seriesInformation(series);
                }

            } else {
                // Exibe mensagem se a lista principal estiver vazia
                seriesView.emptyListMessage();
            }

            return true; // Retorna true se tudo ocorreu corretamente
        } catch (Exception e) {
            // Captura e trata exceções genéricas
            seriesView.invalidMessage();
            return false;
        }
    } // Ordena séries por bem avaliados

    /**
     * Sorts the list of series by lowest-rated criteria.
     *
     * @return true if the sorting was successful; false if an error occurred.
     */
    public boolean sortListByLowRated() {
        try {
            // Verifica se a lista principal de séries não está vazia
            if (!listOfSeries.isEmpty()) {
                ArrayList<SeriesModel> listOfReviewedSeries = new ArrayList<SeriesModel>();

                // Adiciona à nova lista apenas as séries que possuem avaliação (nota diferente de 0)
                for (SeriesModel series : listOfSeries) {
                    if (series.getSeriesReview() != 0) {
                        listOfReviewedSeries.add(series);
                    }
                }

                // Cria uma cópia da lista de séries avaliadas
                ArrayList<SeriesModel> poorlyRatedSeries = new ArrayList<SeriesModel>(listOfReviewedSeries);

                // Verifica se existem séries avaliadas
                if (!poorlyRatedSeries.isEmpty()){
                    // Ordena as séries pela menor avaliação (ordem crescente)
                    poorlyRatedSeries.sort(Comparator.comparing(seriesModel -> seriesModel.getSeriesReview()));
                } else {
                    // Exibe mensagem informando que nenhuma série foi avaliada
                    seriesView.emptyEvaluatedListMessage();
                    return true;
                }

                // Exibe o cabeçalho para exibição das séries
                seriesView.headerForSeries();
                // Exibe as informações das séries ordenadas por menor nota
                for (SeriesModel series : poorlyRatedSeries) {
                    seriesView.seriesInformation(series);
                }

            } else {
                // Se a lista principal estiver vazia, exibe mensagem correspondente
                seriesView.emptyListMessage();
            }

            return true; // Retorna true se tudo ocorreu normalmente
        } catch (Exception e) {
            // Trata possíveis exceções e exibe mensagem genérica de erro
            seriesView.invalidMessage();
            return false;
        }
    }

    /**
     * Opens the series at the specified index.
     *
     * @param index The index of the series to open.
     * @return true if the series was successfully opened; false if an error occurred.
     */
    public boolean openSeries(int index) {
        try {
            // Declara a variável para armazenar o objeto SeriesModel
            SeriesModel series;

            // Tenta acessar a série pelo índice (index-1 devido à indexação começar do 0)
            try {
                series = listOfSeries.get(index-1);
            } catch (Exception e) {
                // Caso ocorra uma exceção (índice inválido ou fora de alcance), exibe mensagem de erro
                seriesView.noSeriesFoundMessage();
                return false;
            }

            // Exibe as informações completas da série
            seriesView.fullSeriesInformation(series);

            // Itera pelas temporadas da série e exibe as informações de cada temporada
            for (SeasonModel season : series.getListOfSeasons()) {
                seasonController.openSeason(season); // Chama o método para abrir e exibir a temporada
            }

            return true; // Retorna true se tudo ocorreu corretamente
        } catch (Exception e) {
            // Em caso de erro, exibe mensagem genérica de erro
            seriesView.invalidMessage();
            return false; // Retorna false indicando que houve um erro
        }
    } // Abri série

    /**
     * Changes the viewing status of a season in a specified series.
     *
     * @param indexSeries The index of the series.
     * @param indexSeason The index of the season within the series.
     * @param value The new viewing status value.
     * @return true if the viewing status was successfully changed; false if an error occurred.
     */
    public boolean changeSeasonViewingStatus(int indexSeries, int indexSeason, String value) {
        SeriesModel series;
        SeasonModel season;

        // Tenta buscar a série pelo índice fornecido, se não encontrar, mostra mensagem de erro
        try {
            series = listOfSeries.get(indexSeries-1);
        } catch (Exception e) {
            seriesView.noSeriesFoundMessage();
            return false;
        }

        // Tenta buscar a temporada da série pelo índice fornecido, se não encontrar, mostra mensagem de erro
        try {
            season = series.getListOfSeasons().get(indexSeason-1);
        } catch (Exception e) {
            seriesView.noSeasonFoundMessage();
            return false;
        }

        value = value.trim(); // Remove espaços extras do valor fornecido

        // Verifica se a série ou a temporada são nulas, caso sejam, exibe mensagem de erro
        if (series == null || season == null) {
            seriesView.invalidMessage();
            return false;
        }

        // Valida a entrada do usuário sobre o status de visualização
        boolean validWatched = validateNewWatched(value);

        if (!validWatched) {
            seriesView.tryAgainMessage(); // Mensagem indicando que a entrada foi inválida
            return false;
        }

        try {
            // Conjunto de respostas positivas sobre visualização
            Set<String> positiveResponsesWatched = Set.of(
                    "sim", "s", "assisti", "sim assisti", "assisti sim", "s assisti", "assisti s", "já assisti", "ja assisti", "já", "ja"
            );

            // Conjunto de respostas negativas sobre visualização
            Set<String> negativeResponsesWatched = Set.of(
                    "não", "nao", "n", "não assisti", "nao assisti", "n assisti",
                    "assisti não", "assisti nao", "assisti n"
            );

            boolean watched = false;

            // Verifica se a resposta é positiva e marca a temporada como assistida
            if (positiveResponsesWatched.contains(value.toLowerCase())) {
                watched = true;
            }
            // Verifica se a resposta é negativa e a temporada ainda não foi assistida, então marca como não assistida
            else if ((season.isWatched() == false) && negativeResponsesWatched.contains(value.toLowerCase())){
                watched = false;
            }
            // Se a temporada já foi assistida e a resposta é negativa, exibe mensagem de erro
            else if ((season.isWatched() == true) && negativeResponsesWatched.contains(value.toLowerCase())) {
                seriesView.wrongWatchedMessage();
                return false;
            }

            // Atualiza o status de visualização da temporada
            season.setWatched(watched);
            seriesView.updatedWatchedMessage(); // Mensagem indicando que o status foi atualizado
            saveFile();
            return true;
        } catch (Exception e) {
            seriesView.invalidMessage(); // Exibe mensagem de erro em caso de exceção
            return false;
        }
    } // Altera situação de visualização de temporada

    /**
     * Evaluates a season of a series with a score, consumption date, and comment.
     *
     * @param indexSeries The index of the series.
     * @param indexSeason The index of the season within the series.
     * @param score The score given to the season.
     * @param consumptionDate The date when the season was watched.
     * @param comment Additional comments about the season.
     * @return true if the evaluation was successfully registered; false if an error occurred.
     */
    public boolean evaluateSeason(int indexSeries, int indexSeason, String score, String consumptionDate, String comment) {
        try {
            // Remove espaços extras do valor fornecido
            score = score.trim();
            consumptionDate = consumptionDate.trim();
            comment = comment.trim();

            // Inicializa as variáveis para a série e temporada
            SeriesModel series;
            SeasonModel season;

            // Tenta buscar a série pelo índice fornecido, se não encontrar, mostra mensagem de erro
            try {
                series = listOfSeries.get(indexSeries-1);
            } catch (Exception e) {
                seriesView.noSeriesFoundMessage();
                return false;
            }

            // Tenta buscar a temporada pelo índice fornecido, se não encontrar, mostra mensagem de erro
            try {
                season = series.getListOfSeasons().get(indexSeason-1);
            } catch (Exception e) {
                seriesView.noSeasonFoundMessage();
                return false;
            }

            // Verifica se a temporada já foi avaliada
            if (!checkSeasonReview(season)) {
                // Se a temporada foi assistida, valida os dados de avaliação
                if (season.isWatched()) {
                    boolean validScore = validateNewScore(score); // Valida a nota
                    boolean validConsumptionDate = validateNewDate(season, consumptionDate); // Valida a data de consumo
                    boolean validComment = validateNewString(comment, "Comentários"); // Valida o comentário

                    // Se qualquer validação falhar, mostra mensagem de erro e retorna falso
                    if (validScore == false || validConsumptionDate == false || validComment == false) {
                        seriesView.tryAgainMessage();
                        return false;
                    }

                    float scoreFloat = 0f;
                    try {
                        scoreFloat = Float.parseFloat(score); // Converte a pontuação para float
                    } catch (Exception e) {
                        seriesView.invalidMessage(); // Exibe mensagem de erro se a conversão falhar
                        return false;
                    }

                    // Cria o modelo de revisão e define na temporada
                    ReviewModel reviewModel = new ReviewModel(scoreFloat, consumptionDate, comment);
                    season.setSeasonReview(reviewModel);
                    season.setEvaluatedSeason(true); // Marca a temporada como avaliada

                    // Exibe mensagem de sucesso
                    seriesView.registeredEvaluationMessage();

                    // Atualiza a média da série
                    updateAverage(series);
                    saveFile();
                    return true;
                } else {
                    // Se a temporada não foi assistida, exibe mensagem de erro
                    seriesView.unwatchedSeasonMessage();
                    return false;
                }
            } else {
                // Se a temporada já foi avaliada, exibe mensagem de erro
                seriesView.messageOfSeasonAlreadyEvaluated();
                return false;
            }
        } catch (Exception e) {
            // Exibe mensagem de erro em caso de exceção
            seriesView.invalidMessage();
            return false;
        }
    } // Avalia temporada

    /**
     * Re-evaluates a season of a series with a new score, consumption date, and comment.
     *
     * @param indexSeries The index of the series.
     * @param indexSeason The index of the season within the series.
     * @param score The new score given to the season.
     * @param consumptionDate The new date when the season was watched.
     * @param comment Additional comments about the season.
     * @return true if the re-evaluation was successfully registered; false if an error occurred.
     */
    public boolean evaluateSeasonAgain(int indexSeries, int indexSeason, String score, String consumptionDate, String comment) {
        try {
            // Remove espaços extras dos valores fornecidos
            score = score.trim();
            consumptionDate = consumptionDate.trim();
            comment = comment.trim();

            // Busca a série e a temporada pelo índice fornecido
            SeriesModel series = listOfSeries.get(indexSeries-1);
            SeasonModel season = series.getListOfSeasons().get(indexSeason-1);

            // Verifica se a temporada já foi avaliada
            if (checkSeasonReview(season)) {
                // Se a temporada já foi avaliada, a função limpa a avaliação para permitir nova avaliação
                season.setEvaluatedSeason(false);
                // Chama novamente a função para avaliar a temporada
                return evaluateSeason(indexSeries, indexSeason, score, consumptionDate, comment);
            } else {
                // Se a temporada não foi avaliada, exibe mensagem de erro
                seriesView.unratedSeasonMessage();
                return false;
            }
        } catch (Exception e) {
            // Exibe mensagem de erro em caso de exceção
            seriesView.invalidMessage();
            return false;
        }
    } // Avalia temporada novamente

    /**
     * Checks if the specified season has already been reviewed.
     *
     * @param season The season object to check.
     * @return true if the season has a review; false otherwise.
     */
    public boolean checkSeasonReview(SeasonModel season) {
        if (season.isEvaluatedSeason()) {
            return true;
        }
        return false;
    } // Verifica se temporada já foi atualizada

    /**
     * Updates the average rating of the specified series.
     *
     * @param series The series object whose average rating will be updated.
     * @return true if the average was successfully updated; false if an error occurred.
     */
    public boolean updateAverage(SeriesModel series) {
        // Variáveis para somar as notas e contar o número de avaliações
        float sum = 0;
        int count = 0;

        // Cria uma variável para armazenar a avaliação da temporada
        ReviewModel review;

        // Itera sobre as temporadas da série
        for (SeasonModel season : series.getListOfSeasons()) {
            // Obtém a avaliação da temporada
            review = season.getSeasonReview();

            // Se a temporada foi avaliada, adiciona a pontuação à soma e incrementa o contador
            if (review != null) {
                sum += review.getScore();
                count++;
            }
        }

        // Se pelo menos uma temporada foi avaliada, calcula a média
        if (count != 0) {
            // Define a média de avaliação da série
            series.setSeriesReview(sum/count);
            return true;
        } else {
            // Se nenhuma temporada foi avaliada, retorna falso
            return false;
        }
    } // Atualiza nota média da série

    /**
     * Validates a new date value for the specified season.
     *
     * @param season The season object related to the date.
     * @param value The date string to validate.
     * @return true if the date is valid; false otherwise.
     */
    public boolean validateNewDate(SeasonModel season, String value) {
        // Remove espaços extras do início e final da string de entrada
        value = value.trim();

        // Valida se o valor de entrada é uma string válida
        if (validateNewInputString(value)) {
            // Divide a data em partes usando o separador "/"
            String[] parts = value.split("/");

            // Verifica se a data possui o formato correto (dd/mm/aaaa)
            if (parts.length != 3) {
                seriesView.invalidDateFormatMessage(); // Exibe mensagem de formato inválido
                return false;
            }

            // Separa o dia, mês e ano da data
            String day = parts[0];
            String month = parts[1];
            String year = parts[2];

            // Verifica se o comprimento do dia, mês ou ano está incorreto
            if (day.length() > 2 || month.length() > 2 || year.length() != 4) {
                seriesView.invalidDateMessage(); // Exibe mensagem de data inválida
                return false;
            }

            // Valida se a data existe (dia, mês e ano válidos)
            boolean valid = validateExistingDate(day, month, year);
            if (!valid) {
                return false; // Retorna falso se a data não for válida
            }

            try {
                // Tenta converter o ano para um valor inteiro
                int yearInt = Integer.parseInt(year);

                // Verifica se o ano da data é anterior ao ano da temporada
                if (yearInt < season.getYearSeason()) {
                    seriesView.invalidYearPeriodMessage(season.getYearSeason()); // Exibe mensagem de período inválido
                    return false;
                }
            } catch (NumberFormatException e) {
                seriesView.invalidDateMessage(); // Exibe mensagem de erro se não for possível converter o ano
                return false;
            }

            // Retorna verdadeiro se todos os testes passarem
            return true;
        }

        return false; // Retorna falso se a validação da string falhar
    } // Valida nova data

    /**
     * Validates an existing date based on day, month, and year strings.
     *
     * @param day The day part of the date as a string.
     * @param month The month part of the date as a string.
     * @param year The year part of the date as a string.
     * @return true if the date is valid; false otherwise.
     */
    public boolean validateExistingDate(String day, String month, String year) {
        try {
            // Converte o dia, mês e ano para inteiros
            int d = Integer.parseInt(day);
            int m = Integer.parseInt(month);
            int y = Integer.parseInt(year);

            // Verifica se o mês está dentro do intervalo válido (1-12)
            if (m < 1 || m > 12) {
                seriesView.nonExistentDateMessage(); // Exibe mensagem de data inexistente
                return false;
            }

            // Ajusta o mês (de 1-12 para 0-11, conforme exigido pelo Calendar)
            m = m - 1;

            // Cria um objeto Calendar para a data fornecida
            Calendar cal = Calendar.getInstance();
            cal.setLenient(false); // Define o modo não permissivo para garantir que a data seja válida
            cal.set(y, m, d); // Define o ano, mês e dia
            cal.getTime(); // Obtém a data

            // Reseta a hora para 00:00:00 para comparar apenas a data
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            // Cria um objeto Calendar para a data de hoje
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);

            // Verifica se a data fornecida é futura
            if (cal.after(today)) {
                seriesView.invalidFutureDatesMessage(); // Exibe mensagem de data futura inválida
                return false;
            }

            // Retorna verdadeiro se a data for válida e não for futura
            return true;

        } catch (Exception e) {
            seriesView.invalidDateMessage(); // Exibe mensagem de erro caso ocorra alguma exceção
            return false;
        }
    } // Valida existência de data

    /**
     * Validates a new score value.
     *
     * @param value The score value as a string to validate.
     * @return true if the score is valid; false otherwise.
     */
    public boolean validateNewScore(String value) {
        // Remove espaços em branco antes e depois da string
        value = value.trim();

        // Verifica se a string fornecida é válida (não vazia ou inválida)
        if (validateNewInputString(value)) {
            try {
                // Tenta converter a string para um valor de ponto flutuante (float)
                float score = Float.parseFloat(value);

                // Verifica se a pontuação está dentro do intervalo válido (1 a 5)
                if (score < 1 || score > 5) {
                    seriesView.invalidScoreMessage(); // Exibe mensagem de pontuação inválida
                    return false;
                }

                // Retorna verdadeiro se a pontuação for válida
                return true;
            } catch (Exception e) {
                // Se ocorrer uma exceção durante a conversão para float, exibe a mensagem de erro
                seriesView.invalidNumberMessage(); // Exibe mensagem de número inválido
                return false;
            }
        }

        // Retorna falso caso a entrada seja inválida
        return false;
    } // Valida nova nota

    /**
     * Validates if the given string can be converted to an integer.
     *
     * @param value The string value to validate.
     * @return true if the string represents a valid integer; false otherwise.
     */
    public boolean validateNewInputInt(String value) {
        try {
            int valueInt = Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            seriesView.integerMessage();
            return false;
        }
    } //  Valida nova entrada de número inteiro

    /**
     * Validates the given string input.
     *
     * @param value The string value to validate.
     * @return true if the input string is valid; false otherwise.
     */
    public boolean validateNewInputString(String value) {
        if (value.isEmpty()) {
            seriesView.emptyInformationMessage();
            return false;
        }
        return true;
    } // Valida nova string

    /**
     * Validates the given watched status input.
     *
     * @param value The watched status string to validate.
     * @return true if the watched status is valid; false otherwise.
     */
    public boolean validateNewWatched(String value) {
        // Valida se o valor fornecido é uma string válida, no caso de "Visualização"
        if (validateNewString(value, "Visualização")) {

            // Conjunto de respostas válidas para a visualização
            Set<String> validAnswers = Set.of(
                    "sim", "s", // Respostas afirmativas
                    "não", "nao", "n", // Respostas negativas
                    "não assisti", "nao assisti", "n assisti", "assisti nao", "assisti não", "assisti n", // Variações de respostas negativas
                    "sim assisti", "assisti sim", "s assisti", "assisti s", "assisti", "já assisti", "ja assisti", "já", "ja" // Variações de respostas afirmativas
            );

            // Verifica se o valor da entrada corresponde a alguma resposta válida (ignorando maiúsculas/minúsculas)
            if (validAnswers.contains(value.toLowerCase())) {
                return true; // Se for uma resposta válida, retorna verdadeiro
            } else {
                seriesView.invalidWatchedMessage(); // Se a resposta não for válida, exibe uma mensagem de erro
            }
        }

        // Retorna falso se a entrada for inválida ou não estiver no conjunto de respostas válidas
        return false;
    } // Valida nova visualização

    /**
     * Opens a file located in the repository.
     */
    public void openFile() {
        if (!repository.exists()) { // Verifica se o diretório não existe
            repository.mkdirs(); // Cria o diretório
        }

        if (!file.exists()){ // Verifica se o arquivo não existe
            try {
                file.createNewFile(); // Tenta criar o arquivo
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (file != null && file.length() > 0) { // Se o arquivo existe e não está vazio
                uploadFile(); // Carrega os dados do arquivo
            }
        }
    } // Abre um arquivo do repositório

    /**
     * Loads the file from the repository into the main list.
     */
    public void uploadFile() {
        try (FileReader reader = new FileReader(file)) { // Lê o conteúdo do arquivo JSON
            Type typeList = new TypeToken<ArrayList<SeriesModel>>() {}.getType(); // Define o tipo da lista
            listOfSeries = gson.fromJson(reader, typeList); // Converte o JSON para lista de séries
        } catch (IOException e) {
            e.printStackTrace(); // Imprime erro caso ocorra falha na leitura
        }
    } // Carrega um arquivo do repositório

    /**
     * Saves the main list to the file in the repository.
     */
    public void saveFile() {
        try (FileWriter writer = new FileWriter(file)) { // Abre o arquivo para escrita
            gson.toJson(listOfSeries, writer); // Converte a lista de séries para JSON e grava no arquivo
        } catch (IOException e) {
            e.printStackTrace(); // Imprime erro caso ocorra falha na escrita
        }
    } // Salva um arquivo no repositório

    public ArrayList<SeriesModel> getListOfSeries() {
        return listOfSeries;
    }

    public void setListOfSeries(ArrayList<SeriesModel> listOfSeries) {
        this.listOfSeries = listOfSeries;
    }
}