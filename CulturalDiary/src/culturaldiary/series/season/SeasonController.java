package culturaldiary.series.season;

import java.util.Calendar;
import java.util.Set;

public class SeasonController {
    SeasonView seasonView = new SeasonView();

    Calendar calendar = Calendar.getInstance();

    public boolean validateSeason(String genre, String castString, String yearSeasonString, String watchedString, int index) {
        genre = genre.trim();  // Remove espaços em branco no início e no final da string 'genre'
        castString = castString.trim();  // Remove espaços em branco no início e no final da string 'castString'
        yearSeasonString = yearSeasonString.trim();  // Remove espaços em branco no início e no final da string 'yearSeasonString'
        watchedString = watchedString.trim();  // Remove espaços em branco no início e no final da string 'watchedString'

        // Valida o gênero da temporada
        boolean validGenre = validateGenre(genre, index);
        // Valida o elenco da temporada
        boolean validCastString = validateCast(castString, index);
        // Valida o ano e a temporada
        boolean validYearSeason = validateYearSeason(yearSeasonString, index);
        // Valida o estado de "assistido" da temporada
        boolean validWatched = validateWatched(watchedString, index);

        // Se qualquer validação falhar, exibe mensagem de erro e tenta novamente
        if (validGenre == false || validCastString == false || validYearSeason == false || validWatched == false) {
            seasonView.errorMessageInSeason(index);  // Exibe uma mensagem de erro para a temporada
            seasonView.tryAgainMessage();  // Solicita ao usuário que tente novamente
            return false;
        } else {
            return true;
        }
    }

    public boolean validateGenre(String genre, int index) {
        return validateNewString(genre, "Gênero da " + index + "° temporada");
    } // Valida gênero

    public boolean validateCast(String cast, int index) {
        return validateNewCast(cast, index);
    } // Valida elenco

    public boolean validateNewCast(String value, int index) {
        if (validateNewString(value, "Elenco da " + index + "° temporada")) {
            if (value.matches("[\\p{L}, ]*")) {
                return true;
            } else {
                seasonView.invalidCastMessage();
                return false;
            }
        } else {
            return false;
        }
    } // Valida novo elenco

    public boolean validateYearSeason(String yearOfRelease, int index) {
        return validateNewYear(yearOfRelease, index);
    } // Valida ano de lançamento

    public boolean validateNewYear(String value, int index) {
        // Verifica se o valor do ano de lançamento é válido (não vazio)
        if (validateNewString(value, "Ano de lançamento da " + index + "° temporada")) {
            int valueInt = 0;  // Variável para armazenar o valor do ano convertido para inteiro
            int currentYear = calendar.get(Calendar.YEAR);  // Obtém o ano atual

            try {
                valueInt = Integer.parseInt(value);  // Tenta converter o valor para um inteiro
            } catch (Exception e) {
                // Caso ocorra um erro durante a conversão, exibe a mensagem de erro e retorna falso
                seasonView.integerMessage();
                return false;
            }

            // Verifica se o ano está dentro do intervalo válido (de 1700 até o ano atual)
            if (valueInt < 1700 || valueInt > currentYear) {
                seasonView.invalidYearMessage(currentYear);  // Exibe uma mensagem de erro se o ano for inválido
                return false;
            }

            return true;
        }

        return false;
    } // Valida novo ano de lançamento

    public boolean validateWatched(String watched, int index) {
        return validateNewWatched(watched, index);
    } // Valida visualização

    public boolean validateNewWatched(String value, int index) {
        // Verifica se a entrada de "visualização" não é vazia e válida
        if (validateNewString(value, "Visualização da " + index + "° temporada")) {

            // Define um conjunto de respostas válidas que o usuário pode fornecer
            Set<String> validAnswers = Set.of(
                    "sim", "s",  // Respostas afirmativas
                    "não", "nao", "n",  // Respostas negativas comuns
                    "não assisti", "nao assisti", "n assisti", "assisti nao", "assisti não", "assisti n", // Variações de não ter assistido
                    "sim assisti", "assisti sim", "s assisti", "assisti s", "assisti", "já assisti", "ja assisti", "já", "ja" // Variações de ter assistido
            );

            // Verifica se a resposta fornecida pelo usuário está no conjunto de respostas válidas
            if (validAnswers.contains(value.toLowerCase())) {
                return true;
            } else {
                // Se a resposta não for válida, exibe a mensagem de erro
                seasonView.invalidWatchedMessage();
            }
        }

        return false;
    } // Valida nova visualização

    public boolean validateNewString(String value, String name) {
        if (value.isEmpty()) {
            seasonView.emptyValueMessage(name);
            return false;
        }
        return true;
    } //  Valida se string não está vazia

    public void openSeason(SeasonModel season) {
        seasonView.fullSeasonInformation(season);
    } // Abre informações de uma temporada
}