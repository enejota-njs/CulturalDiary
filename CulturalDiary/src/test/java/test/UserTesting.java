package test;

import book.BookController;
import movie.MovieController;
import series.series.SeriesController;

import java.util.Scanner;

public class UserTesting {
    static BookController bookController = BookController.getInstance();
    static MovieController movieController = MovieController.getInstance();
    static SeriesController seriesController = SeriesController.getInstance();

    public static void main(String[] args) {
        bookController.openFile();
        movieController.openFile();
        seriesController.openFile();

        Test test = new Test();
        Scanner input = new Scanner(System.in);

        boolean finished = false;
        do {
            System.out.println("\n+-------------------------+");
            System.out.printf("| %-23s |\n", "Diário Cultural");
            System.out.println("+-------------------------+");
            System.out.printf("| %-23s |\n", "[ 1 ] - Cadastrar Mídia");
            System.out.printf("| %-23s |\n", "[ 2 ] - Buscar Mídia");
            System.out.printf("| %-23s |\n", "[ 3 ] - Listar Mídia");
            System.out.printf("| %-23s |\n", "[ 4 ] - Fechar");
            System.out.println("+-------------------------+");

            System.out.print("\nEscolha uma opção: ");
            String menuOption = input.nextLine().trim();

            switch (menuOption.toLowerCase()) {
                case "1" :
                    test.controlMedia(1, bookController, movieController, seriesController);
                    break;
                case "2" :
                    test.controlMedia(2, bookController, movieController, seriesController);
                    break;
                case "3" :
                    test.controlMedia(3, bookController, movieController, seriesController);
                    break;
                case "4" :
                    finished = true;
                    break;
                default:
                    System.out.println("\nInválido.");
                    break;
            }
        } while (!finished);

        System.out.println("\n+-------------+");
        System.out.println("| Até logo! \uD83D\uDC4B|");
        System.out.println("+-------------+");
    } // Menu inicial
}