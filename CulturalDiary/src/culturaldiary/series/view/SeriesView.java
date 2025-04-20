package culturaldiary.series.view;

import culturaldiary.series.model.SeriesModel;

public class SeriesView {
    static final String BOLD = "\033[1m";
    static final String RESET = "\033[0m";

    public void registrationMsg(int a, int b) {
        switch (a) {
            case 1 : {
                System.out.print("\nDigite o título: ");
                break;
            }
            case 2 : {
                System.out.print("\nDigite o ano de lançamento: ");
                break;
            }
            case 3 : {
                System.out.print("\nDigite o ano de encerramento: ");
                break;
            }
            case 4 : {
                System.out.print("\nDigite o título original: ");
                break;
            }
            case 5 : {
                System.out.print("\nOnde assitir ? ");
                break;
            }
            case 6 : {
                System.out.print("\nQuantas temporadas tem a série ? ");
                break;
            }
            case 7 : {
                System.out.println(String.format("\nSérie n° %d cadastrada.", b));
                break;
            }
            case 8 : {
                System.out.println("\nDeve haver pelo menos 1 temporada.");
                break;
            }
            default:
                break;
        }
    }

    public void additionalRegistrationMsg() {
        System.out.println("\n[ 1 ] - Abrir série");
        System.out.println("[ 2 ] - Avaliar série");
        System.out.println("[ 3 ] - Cadastrar série");
        System.out.println("[ 4 ] - Menu inicial");
    }

    public void searchMsg(int a) {
        switch (a) {
            case 1 : {
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Buscar por");
                System.out.println("+-------------------------+");
                System.out.printf("| %-23s |\n", "[ 1 ] - Título");
                System.out.println("+-------------------------+");
                System.out.printf("| %-23s |\n", "[ 2 ] - Menu inicial");
                System.out.println("+-------------------------+");
                break;
            }
            case 2 : {
                System.out.print("\nEscreva a informação da série: ");
                break;
            }
            case 3 : {
                System.out.println("\nNenhuma série encontrada.");
                break;
            }
            default:
                break;
        }
    }

    public void additionalSearchMsg() {
        System.out.println("\n[ 1 ] - Abrir série");
        System.out.println("[ 2 ] - Avaliar série");
        System.out.println("[ 3 ] - Buscar série");
        System.out.println("[ 4 ] - Menu Inicial");
    }

    public void additionalListMsg() {
        System.out.println("\n[ 1 ] - Abrir série");
        System.out.println("[ 2 ] - Avaliar série");
        System.out.println("[ 3 ] - Ordenar série");
        System.out.println("[ 4 ] - Filtrar série");
        System.out.println("[ 5 ] - Listar série");
        System.out.println("[ 6 ] - Menu inicial");
    }

    public void chooseAnOptionMsg() {
        System.out.print("\nEscolha uma opção: ");
    }

    public String centerHeader(String text, int width) {
        if (text.length() >= width) {
            return text;
        }

        int spaces = width - text.length();
        int left = spaces / 2;
        int right = spaces - left;

        return " ".repeat(left) + text + " ".repeat(right);
    }

    public void headerForSeries() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-45s | %-12s | %-12s | %-12s | %-12s |\n",
                BOLD + centerHeader("Índice", 8) + RESET,
                BOLD + centerHeader("Título", 45) + RESET,
                BOLD + centerHeader("Início", 12)+ RESET,
                BOLD + centerHeader("Fim", 12) + RESET,
                BOLD + centerHeader("Temporadas", 12) + RESET,
                BOLD + centerHeader("Nota média", 12) + RESET);
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }

    public void seriesInformation(SeriesModel series) {
        String temporaryIndex = Integer.toString(series.getSeriesIndex());
        String temporaryTitle = series.getTitle();
        float temporaryReview = series.getSeriesReview();

        if (temporaryIndex.length() > 8) {
            temporaryIndex = temporaryIndex.substring(0,  5) + "...";
        }

        if (temporaryTitle.length() > 45) {
            temporaryTitle = temporaryTitle.substring(0,  42) + "...";
        }

        String temporaryScore;
        if (temporaryReview == 0) {
            temporaryScore = "Vazio";
        }
        else {
            temporaryScore = String.valueOf(temporaryReview);
        }

        int number;
        if (series.getListOfSeasons() != null) {
            number = series.getListOfSeasons().size();
        } else {
            number = 0;
        }

        System.out.printf("| %-8s | %-45s | %-12d | %-12d | %-12d | %-12s |\n", temporaryIndex, temporaryTitle,
                series.getYearOfRelease(), series.getYearOfConclusion(), number, temporaryScore);
        System.out.println("------------------------------------------------------------------------------------------------------------------------");

    }

    public void filterMsg(int a) {
        switch (a){
            case 1 : {
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Filtrar por");
                System.out.println("+-------------------------+");
                System.out.printf("| %-23s |\n", "[ 1 ] - Gênero");
                System.out.printf("| %-23s |\n", "[ 2 ] - Ano");
                System.out.println("+-------------------------+");
                System.out.printf("| %-23s |\n", "[ 3 ] - Menu inicial");
                System.out.println("+-------------------------+");
                break;
            }
            case 2 : {
                System.out.print("\nDigite o gênero: ");
                break;
            }
            case 3 : {
                System.out.print("\nDigite o ano de lançamento: ");
                break;
            }
            case 4 : {
                System.out.println("\nNenhuma série encontrada.");
                break;
            }
            default:
                break;
        }
    }

    public void orderingMsg(int a) {
        switch (a) {
            case 1 : {
                System.out.println("\nNenhuma temporada avaliada.");
                break;
            }
            case 2 : {
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Ordenar por");
                System.out.println("+-------------------------+");
                System.out.printf("| %-23s |\n", "[ 1 ] - Bem avaliado");
                System.out.printf("| %-23s |\n", "[ 2 ] - Mal avaliado");
                System.out.println("+-------------------------+");
                System.out.printf("| %-23s |\n", "[ 3 ] - Menu inicial");
                System.out.println("+-------------------------+");
                break;
            }
            default:
                break;
        }
    }

    public void evaluationMsg(int a) {
        switch (a) {
            case 1 : {
                System.out.print("\nEscreva o n° da série: ");
                break;
            }
            case 2 : {
                System.out.println("\nSérie não encontrada.");
                break;
            }
            case 3 : {
                System.out.println("\nPor favor, digite um número válido.");
                break;
            }
            case 4 : {
                System.out.print("\nDigite o n° da temporada: ");
                break;
            }
            case 5 : {
                System.out.println("\nTemporada não encontrada.");
                break;
            }
            default:
                break;

        }
    }

    public void additionalSeriesInformation(SeriesModel series, int number) {
        System.out.println("\n+-----------------------+");
        System.out.printf("| %-23s -> %s\n", "Série n°", series.getSeriesIndex());
        System.out.println("+-----------------------+");
        System.out.printf("| %-23s -> %s\n", "Título", series.getTitle());
        System.out.printf("| %-23s -> %d\n", "Ano de lançamento", series.getYearOfRelease());
        System.out.printf("| %-23s -> %d\n", "Ano da última temporada", series.getYearOfConclusion());
        System.out.printf("| %-23s -> %s\n", "Título original", series.getOriginalTitle());
        System.out.printf("| %-23s -> %s\n", "Onde assitir", series.getWhereToWatch());
        System.out.printf("| %-23s -> %d\n", "Número de temporadas", number);

        if (series.getSeriesReview() == 0) {
            System.out.printf("| %-23s -> %s\n", "Nota média", "Série não avaliada");
        }
        else {
            System.out.printf("| %-23s -> %f\n", "Nota média", series.getSeriesReview());
        }
        System.out.println("+-----------------------+");
    }

    public void emptyValueMsg() {
        System.out.println("\nValor vazio.");
        System.out.println("Tente novamente.");
    }

    public void invalidInt() {
        System.out.println("\nO valor precisa ser um número inteiro.");
        System.out.println("Tente novamente.");
    }

    public void invalidYearMsg(int a, int start, int end) {
        switch (a) {
            case 1 : {
                System.out.println("\nO valor precisa ser um número inteiro.");
                System.out.println("Tente novamente.");
                break;
            }
            case 2 : {
                System.out.println("\nO valor precisa estar entre " + start + " e " + end + ".");
                System.out.println("Tente novamente.");
                break;
            }
            default:
                break;
        }
    }

    public void invalidValueMsg() {
        System.out.println("\nInválido.");
        System.out.println("Tente novamente.");

    }

    public void emptyListMsg() {
        System.out.println("\nNenhuma série cadastrada.");
    }
}