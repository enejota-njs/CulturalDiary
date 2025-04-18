package antigo.media;

import antigo.book.BookManager;
import antigo.movie.MovieManager;
import antigo.series.SeriesManager;

import java.util.Scanner;

public class MediaControl {
    private BookManager bookManager = new BookManager();
    private MovieManager movieManager = new MovieManager();
    private SeriesManager seriesManager = new SeriesManager();

    private Scanner input = new Scanner(System.in);

    public void controlMedia(int chosenMethod) {
        int chosenMedia;

        switch (chosenMethod) {
            case 1 :
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Cadastro de Mídia");
                System.out.println("+-------------------------+");

                chosenMedia = displayMethodMenu();

                switch (chosenMedia) {
                    case 0 :
                        controlMedia(1);
                        break;
                    case 1 :
                        bookManager.registerBook();
                        break;
                    case 2 :
                        movieManager.registerMovie();
                        break;
                    case 3 :
                        seriesManager.registerSeries();
                        break;
                    default :
                        break;
                }
                break;
            case 2 :
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Busca de Mídia");
                System.out.println("+-------------------------+");

                chosenMedia = displayMethodMenu();

                switch (chosenMedia) {
                    case 0 :
                        controlMedia(2);
                        break;
                    case 1 :
                        bookManager.searchBook();
                        break;
                    case 2 :
                        movieManager.searchMovie();
                        break;
                    case 3 :
                        seriesManager.searchSeries();
                        break;
                    default :
                        break;
                }
                break;
            case 3 :
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Lista de Mídia");
                System.out.println("+-------------------------+");

                chosenMedia = displayMethodMenu();

                switch (chosenMedia) {
                    case 0 :
                        controlMedia(3);
                        break;
                    case 1 :
                        bookManager.listBook();
                        break;
                    case 2 :
                        movieManager.listMovie();
                        break;
                    case 3 :
                        seriesManager.listSeries();
                        break;
                    default :
                        break;
                }
                break;
            default :
                break;
        }
    }

    public int displayMethodMenu() {
        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Livro");
        System.out.printf("| %-23s |\n", "[ 2 ] - Filme");
        System.out.printf("| %-23s |\n", "[ 3 ] - Série");
        System.out.printf("| %-23s |\n", "[ 4 ] - Cancelar");
        System.out.println("+-------------------------+");

        System.out.print("\nEscolha uma opção: ");
        String mediaOption = input.nextLine().trim();

        if (mediaOption.equals("1") || mediaOption.equalsIgnoreCase("livro")) {
            return 1;
        } else if (mediaOption.equals("2") || mediaOption.equalsIgnoreCase("filme")) {
            return 2;
        } else if (mediaOption.equals("3")
                || mediaOption.equalsIgnoreCase("série")
                || mediaOption.equalsIgnoreCase("serie")) {
            return 3;
        } else if (mediaOption.equals("4") || mediaOption.equalsIgnoreCase("cancelar")) {
            return 4;
        } else {
            System.out.println("\n+----------+");
            System.out.println("| Inválido |");
            System.out.println("+----------+");
            return 0;
        }
    }
}
