package culturaldiary.movie.view;

import culturaldiary.movie.model.MovieModel;
import culturaldiary.media.model.ReviewModel;

import java.util.Calendar;

public class MovieView {
    Calendar calendar = Calendar.getInstance();

    static final String BOLD = "\033[1m";
    static final String RESET = "\033[0m";

    public void registrationMsg(int a, int b) {
        switch (a) {
            case 1 : {
                System.out.print("\nDigite o título: ");
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
                System.out.print("\nDigite o tempo de duração (HH:MN): ");
                break;
            }
            case 5 : {
                System.out.print("\nDigite o(a) diretor(a): ");
                break;
            }
            case 6 : {
                System.out.print("\nDigite o roteiro: ");
                break;
            }
            case 7 : {
                System.out.print("\nQuantas pessoas tem no elenco ? ");
                break;
            }
            case 8 : {
                System.out.print(String.format("\n%d° pessoa: ", b));
                break;
            }
            case 9 : {
                System.out.print("\nDigite o título original: ");
                break;
            }
            case 10 : {
                System.out.print("\nOnde assistir ? ");
                break;
            }
            case 11 : {
                System.out.println(String.format("\nFilme n° %d cadastrado.", b));
                break;
            }
            case 12 : {
                System.out.println("\nDeve haver pelo menos 1 pessoa.");
                break;
            }
            case 13 : {
                System.out.println("\nTempo de duração inválido.");
                break;
            }
            default:
                break;
        }
    }

    public void additionalRegistrationMsg() {
        System.out.println("\n[ 1 ] - Abrir filme");
        System.out.println("[ 2 ] - Avaliar filme");
        System.out.println("[ 3 ] - Cadastrar filme");
        System.out.println("[ 4 ] - Menu inicial");
    }

    public void searchMsg(int a) {
        switch (a) {
            case 1 : {
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Buscar por");
                System.out.println("+-------------------------+");
                System.out.printf("| %-23s |\n", "[ 1 ] - Título");
                System.out.printf("| %-23s |\n", "[ 2 ] - Diretor");
                System.out.printf("| %-23s |\n", "[ 3 ] - Ator");
                System.out.printf("| %-23s |\n", "[ 4 ] - Gênero");
                System.out.printf("| %-23s |\n", "[ 5 ] - Ano");
                System.out.println("+-------------------------+");
                System.out.printf("| %-23s |\n", "[ 6 ] - Menu inicial");
                System.out.println("+-------------------------+");
                break;
            }
            case 2 : {
                System.out.print("\nEscreva a informação do filme: ");
                break;
            }
            case 3 : {
                System.out.println("\nNenhum filme encontrado.");
                break;
            }
            default:
                break;
        }
    }

    public void additionalSearchMsg() {
        System.out.println("\n[ 1 ] - Abrir filme");
        System.out.println("[ 2 ] - Avaliar filme");
        System.out.println("[ 3 ] - Buscar filme");
        System.out.println("[ 4 ] - Menu Inicial");
    }

    public void additionalListMsg() {
        System.out.println("\n[ 1 ] - Abrir filme");
        System.out.println("[ 2 ] - Avaliar filme");
        System.out.println("[ 3 ] - Ordenar filme");
        System.out.println("[ 4 ] - Filtrar filme");
        System.out.println("[ 5 ] - Listar filme");
        System.out.println("[ 6 ] - Menu inicial");
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

    public void headerForMovie() {
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-38s | %-28s | %-28s | %-10s | %-8s |\n",
                BOLD + centerHeader("Índice", 8) + RESET,
                BOLD + centerHeader("Título", 38) + RESET,
                BOLD + centerHeader("Direção", 28)+ RESET,
                BOLD + centerHeader("Gênero", 28) + RESET,
                BOLD + centerHeader("Ano", 10) + RESET,
                BOLD + centerHeader("Nota", 8) + RESET);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void movieInformation(MovieModel movie) {
        String temporaryMovieIndex = Integer.toString(movie.getMovieIndex());
        String temporaryMovieTitle = movie.getTitle();
        String temporaryMovieDirection = movie.getDirection();
        String temporaryMovieGenre = movie.getGenre();
        ReviewModel temporaryReview = movie.getMovieReview();

        if (temporaryMovieIndex.length() > 8) {
            temporaryMovieIndex = temporaryMovieIndex.substring(0,  5) + "...";
        }

        if (temporaryMovieTitle.length() > 38) {
            temporaryMovieTitle = temporaryMovieTitle.substring(0,  35) + "...";
        }

        if (temporaryMovieDirection.length() > 28) {
            temporaryMovieDirection = temporaryMovieDirection.substring(0,  25) + "...";
        }

        if (temporaryMovieGenre.length() > 28) {
            temporaryMovieGenre = temporaryMovieGenre.substring(0,  25) + "...";
        }

        String temporaryScore;
        if (temporaryReview == null) {
            temporaryScore = "Vazio";
        }
        else {
            temporaryScore = String.valueOf(temporaryReview.getScore());
        }
        System.out.printf("| %-8s | %-38s | %-28s | %-28s | %-10d | %-8s |\n", temporaryMovieIndex, temporaryMovieTitle,
                temporaryMovieDirection, temporaryMovieGenre, movie.getYearOfRelease(), temporaryScore);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");

    }

    public void additionalMovieInformation(MovieModel movie) {
        System.out.println("\n+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Filme n°", movie.getMovieIndex());
        System.out.println("+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Título", movie.getTitle());
        System.out.printf("| %-20s -> %s\n", "Gênero", movie.getGenre());
        System.out.printf("| %-20s -> %d\n", "Ano de Lançamento", movie.getYearOfRelease());
        System.out.printf("| %-20s -> %s\n", "Tempo de duração", movie.getDurationTime());
        System.out.printf("| %-20s -> %s\n", "Direção", movie.getDirection());
        System.out.printf("| %-20s -> %s\n", "Roteiro", movie.getScreenplay());
        System.out.printf("| %-20s -> %s\n", "Elenco", movie.getCastAsString());
        System.out.printf("| %-20s -> %s\n", "Título original", movie.getOriginalTitle());
        System.out.printf("| %-20s -> %s\n", "Onde assitir", movie.getWhereToWatch());

        if (movie.getMovieReview() == null) {
            System.out.printf("| %-20s -> %s\n", "Avaliação", "Filme não avaliado");
            System.out.println("+-----------------------+");
        } else {
            System.out.printf("| %-20s -> %s\n", "Já foi assistido?", movie.getMovieReview().isConsumed() ? "Sim" : "Não");
            System.out.printf("| %-20s -> %.2f\n", "Nota", movie.getMovieReview().getScore());
            System.out.printf("| %-20s -> %s\n", "Data de visualização", movie.getMovieReview().getConsumptionDate());
            System.out.printf("| %-20s -> %s\n", "Comentários", movie.getMovieReview().getComment());
            System.out.println("+-----------------------+");
        }
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
                System.out.println("\nNenhum filme encontrado.");
                break;
            }
            default:
                break;
        }
    }

    public void orderingMsg(int a) {
        switch (a) {
            case 1 : {
                System.out.println("\nNenhum filme avaliado.");
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
                System.out.print("\nVocê já assistiu esse filme ? ");
                break;
            }
            case 2 : {
                System.out.println("\nEsse filme não pode ser avaliado.");
                break;
            }
            case 3 : {
                System.out.println("\nEsse filme já foi avaliado.");
                System.out.print("Deseja avaliar novamente ? ");
                break;
            }
            case 4 : {
                System.out.print("\nDê uma nota entre 1 e 5: ");
                break;
            }
            case 5 : {
                System.out.println("\nO valor precisa estar entre 1 e 5.");
                break;
            }
            case 6 : {
                System.out.print("\nData de visualização (dd/mm/yyyy): ");
                break;
            }
            case 7 : {
                System.out.print("\nComentários: ");
                break;
            }
            case 8 : {
                System.out.println("\nAvaliação cadastrada.");
                break;
            }
            case 9 : {
                System.out.println("\nInválido.");
                System.out.println("Digite Sim ou Não.");
                System.out.println("Tente novamente.");
                break;
            }
            case 10 : {
                System.out.print("\nEscreva o n° do filme: ");
                break;
            }
            case 11 : {
                System.out.println("\nFilme não encontrado.");
                break;
            }
            case 12 : {
                System.out.println("\nPor favor, digite um número válido.");
                break;
            }
            default:
                break;

        }
    }

    public void chooseAnOptionMsg() {
        System.out.print("\nEscolha uma opção: ");
    }

    public void emptyValueMsg() {
        System.out.println("\nValor vazio.");
        System.out.println("Tente novamente.");
    }

    public void emptyListMsg() {
        System.out.println("\nNenhum filme cadastrado.");
    }

    public void invalidYearMsg(int a) {
        switch (a) {
            case 1 : {
                System.out.println("\nO valor precisa ser um número inteiro.");
                System.out.println("Tente novamente.");
                break;
            }
            case 2 : {
                System.out.println("\nO valor precisa estar entre 1700 e " + calendar.get(Calendar.YEAR) + ".");
                System.out.println("Tente novamente.");
                break;
            }
            default:
                break;
        }
    }

    public void invalidDateMsg(int a, int b) {
        switch (a) {
            case 1 : {
                System.out.println("\nNão é permitido inserir datas futuras.");
                System.out.println("Tente novamente.");
                break;
            }
            case 2 : {
                System.out.println("\nData inválida.");
                System.out.println("Tente novamente.");
                break;
            }
            case 3 : {
                System.out.println(String.format("\nO ano de lançamento do filme é %d.", b));
                System.out.println("Digite um valor depois do ano de lançamento.");
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
}