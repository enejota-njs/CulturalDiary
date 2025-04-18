package culturaldiary.main.view;

public class CulturalDiaryView {

    public void menuOptions() {
        System.out.println("\n+-------------------------+");
        System.out.printf("| %-23s |\n", "Diário Cultural");
        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Cadastrar Mídia");
        System.out.printf("| %-23s |\n", "[ 2 ] - Buscar Mídia");
        System.out.printf("| %-23s |\n", "[ 3 ] - Listar Mídia");
        System.out.printf("| %-23s |\n", "[ 4 ] - Fechar");
        System.out.println("+-------------------------+");
    }

    public void chooseAnOption () {
        System.out.print("\nEscolha uma opção: ");
    }

    public void invalidMessage() {
        System.out.println("\nInválido.");
        System.out.println("Tente novamente.");
    }

    public void returnMessages() {
        System.out.println("\n[ 1 ] - Menu inicial");
        System.out.println("[ 2 ] - Fechar");
    }

    public void close() {
        System.out.println("\nAté logo! \uD83D\uDC4B");
    }

    public void emptyValue() {
        System.out.println("\nValor vazio.");
        System.out.println("Tente novamente.");
    }
}