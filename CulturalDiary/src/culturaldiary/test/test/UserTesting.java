package culturaldiary.test.test;

import java.util.Scanner;

public class UserTesting {
    public static void main(String[] args) {
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
                    test.controlMedia(1);
                    break;
                case "2" :
                    test.controlMedia(2);
                    break;
                case "3" :
                    test.controlMedia(3);
                    break;
                case "4" :
                    finished = true;
                    break;
                default:
                    System.out.println("\nInválido.");
                    break;
            }
        } while (finished == false);

        System.out.println("\n+-------------+");
        System.out.println("| Até logo! \uD83D\uDC4B|");
        System.out.println("+-------------+");
    } // Menu inicial
}
