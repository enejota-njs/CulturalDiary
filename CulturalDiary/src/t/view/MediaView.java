package t.view;

public class MediaView {

    public void menuMethods(int a) {
        switch (a) {
            case 1 : {
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Cadastro de Mídia");
                System.out.println("+-------------------------+");
                break;
            }
            case 2 : {
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Busca de Mídia");
                System.out.println("+-------------------------+");
                break;
            }
            case 3 : {
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Lista de Mídia");
                System.out.println("+-------------------------+");
                break;
            }
        }

        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Livro");
        System.out.printf("| %-23s |\n", "[ 2 ] - Filme");
        System.out.printf("| %-23s |\n", "[ 3 ] - Série");
        System.out.printf("| %-23s |\n", "[ 4 ] - Menu inicial");
        System.out.println("+-------------------------+");
    }

    public void chooseAnOption () {
        System.out.print("\nEscolha uma opção: ");
    }

    public void invalidMessage() {
        System.out.println("\nInválido.");
        System.out.println("Tente novamente.");
    }

    public void emptyValue() {
        System.out.println("\nValor vazio.");
        System.out.println("Tente novamente.");
    }
}