package antigo.main;

import antigo.media.MediaControl;

import java.util.Scanner;

public class CulturalDiary implements CulturalDiaryInterface {
    private MediaControl mediaControl = new MediaControl();
    private Scanner input = new Scanner(System.in);

    public void displayHomeMenu() {
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
            case "cadastrar" :
            case "cadastrar mídia" :
            case "cadastrar midia" :
                mediaControl.controlMedia(1);
                break;
            case "2" :
            case "buscar" :
            case "buscar mídia" :
            case "buscar midia" :
                mediaControl.controlMedia(2);
                break;
            case "3" :
            case "listar" :
            case "listar mídia" :
            case "listar midia" :
                mediaControl.controlMedia(3);
                break;
            case "4" :
            case "fechar" :
                return;
            default:
                System.out.println("\n+----------+");
                System.out.println("| Inválido |");
                System.out.println("+----------+");
        }
        returnOption();
    }

    public void returnOption() {
        System.out.println("\n[ 1 ] - Menu inicial");
        System.out.println("[ 2 ] - Fechar");
        System.out.print("\nEscolha uma opção: ");

        String menuAgain = input.nextLine().trim();

        if (menuAgain.equals("1")
                || menuAgain.equalsIgnoreCase("menu")
                || menuAgain.equalsIgnoreCase("menu inicial")) {
            displayHomeMenu();
        } else if (menuAgain.equals("2") || menuAgain.equals("fechar")) {
            //
        } else {
            System.out.println("\n+----------+");
            System.out.println("| Inválido |");
            System.out.println("+----------+");
            returnOption();
        }
    }

    @Override
    public void open() {
        displayHomeMenu();
    }

    @Override
    public void close() {
        System.out.println("\n+-------------+");
        System.out.println("| Até logo! \uD83D\uDC4B|");
        System.out.println("+-------------+");
    }
}
