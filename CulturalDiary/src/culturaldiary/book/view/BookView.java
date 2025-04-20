package culturaldiary.book.view;

import culturaldiary.book.model.BookModel;
import culturaldiary.media.model.ReviewModel;

import java.util.Calendar;

public class BookView {
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
                System.out.print("\nDigite o(a) autor(a): ");
                break;
            }
            case 3 : {
                System.out.print("\nDigite a editora: ");
                break;
            }
            case 4 : {
                System.out.print("\nDigite o ISBN: ");
                break;
            }
            case 5 : {
                System.out.print("\nDigite o ano de publicação: ");
                break;
            }
            case 6 : {
                System.out.print("\nDigite o genêro: ");
                break;
            }
            case 7 : {
                System.out.print("\nTem exemplar (Sim/Não) ? ");
                break;
            }
            case 8 : {
                System.out.println(String.format("\nLivro n° %d cadastrado.", b));

                break;
            }
            default:
                break;
        }
    }

    public void additionalRegistrationMsg() {
        System.out.println("\n[ 1 ] - Abrir Livro");
        System.out.println("[ 2 ] - Avaliar livro");
        System.out.println("[ 3 ] - Cadastrar livro");
        System.out.println("[ 4 ] - Menu inicial");
    }

    public void searchMsg(int a) {
        switch (a) {
            case 1 : {
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Buscar por");
                System.out.println("+-------------------------+");
                System.out.printf("| %-23s |\n", "[ 1 ] - Título");
                System.out.printf("| %-23s |\n", "[ 2 ] - Autor");
                System.out.printf("| %-23s |\n", "[ 3 ] - Gênero");
                System.out.printf("| %-23s |\n", "[ 4 ] - Ano");
                System.out.printf("| %-23s |\n", "[ 5 ] - ISBN");
                System.out.println("+-------------------------+");
                System.out.printf("| %-23s |\n", "[ 6 ] - Menu inicial");
                System.out.println("+-------------------------+");
                break;
            }
            case 2 : {
                System.out.print("\nEscreva a informação do livro: ");
                break;
            }
            case 3 : {
                System.out.println("\nNenhum livro encontrado.");
                break;
            }
            default:
                break;
        }
    }

    public void additionalSearchMsg() {
        System.out.println("\n[ 1 ] - Abrir livro");
        System.out.println("[ 2 ] - Avaliar livro");
        System.out.println("[ 3 ] - Buscar livro");
        System.out.println("[ 4 ] - Menu Inicial");
    }

    public void additionalListMsg() {
        System.out.println("\n[ 1 ] - Abrir livro");
        System.out.println("[ 2 ] - Avaliar livro");
        System.out.println("[ 3 ] - Ordenar livro");
        System.out.println("[ 4 ] - Filtrar livro");
        System.out.println("[ 5 ] - Listar livro");
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

    public void headerForBook() {
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-38s | %-28s | %-28s | %-10s | %-8s |\n",
                BOLD + centerHeader("Índice", 8) + RESET,
                BOLD + centerHeader("Título", 38) + RESET,
                BOLD + centerHeader("Autor", 28)+ RESET,
                BOLD + centerHeader("Gênero", 28) + RESET,
                BOLD + centerHeader("Ano", 10) + RESET,
                BOLD + centerHeader("Nota", 8) + RESET);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void bookInformation(BookModel book) {
        String temporaryBookIndex = Integer.toString(book.getBookIndex());
        String temporaryBookTitle = book.getTitle();
        String temporaryBookAuthor = book.getAuthor();
        String temporaryBookGenre = book.getGenre();
        ReviewModel temporaryReview = book.getBookReview();

        if (temporaryBookIndex.length() > 8) {
            temporaryBookIndex = temporaryBookIndex.substring(0,  5) + "...";
        }

        if (temporaryBookTitle.length() > 38) {
            temporaryBookTitle = temporaryBookTitle.substring(0,  35) + "...";
        }

        if (temporaryBookAuthor.length() > 28) {
            temporaryBookAuthor = temporaryBookAuthor.substring(0,  25) + "...";
        }

        if (temporaryBookGenre.length() > 28) {
            temporaryBookGenre = temporaryBookGenre.substring(0,  25) + "...";
        }

        String temporaryScore;
        if (temporaryReview == null) {
            temporaryScore = "Vazio";
        }
        else {
            temporaryScore = String.valueOf(temporaryReview.getScore());
        }
        System.out.printf("| %-8s | %-38s | %-28s | %-28s | %-10d | %-8s |\n", temporaryBookIndex, temporaryBookTitle,
                temporaryBookAuthor, temporaryBookGenre, book.getYearOfPublication(), temporaryScore);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");

    }

    public void additionalBookInformation(BookModel book) {
        System.out.println("\n+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Livro n°", book.getBookIndex());
        System.out.println("+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Título", book.getTitle());
        System.out.printf("| %-20s -> %s\n", "Gênero", book.getGenre());
        System.out.printf("| %-20s -> %d\n", "Ano de Publicação", book.getYearOfPublication());
        System.out.printf("| %-20s -> %s\n", "Autor", book.getAuthor());
        System.out.printf("| %-20s -> %s\n", "Editora", book.getPublisher());
        System.out.printf("| %-20s -> %s\n", "ISBN", book.getIsbn());
        System.out.printf("| %-20s -> %s\n", "Tem um exemplar?", (book.isHasCopy() ? "Sim" : "Não"));

        if (book.getBookReview() == null) {
            System.out.printf("| %-20s -> %s\n", "Avaliação", "Livro não avaliado");
            System.out.println("+-----------------------+");
        } else {
            System.out.printf("| %-20s -> %s\n", "Já foi lido?", book.getBookReview().isConsumed() ? "Sim" : "Não");
            System.out.printf("| %-20s -> %.2f\n", "Nota", book.getBookReview().getScore());
            System.out.printf("| %-20s -> %s\n", "Data de leitura", book.getBookReview().getConsumptionDate());
            System.out.printf("| %-20s -> %s\n", "Comentários", book.getBookReview().getComment());
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
                System.out.print("\nDigite o ano de publicação: ");
                break;
            }
            case 4 : {
                System.out.println("\nNenhum livro encontrado.");
                break;
            }
            default:
                break;
        }
    }

    public void orderingMsg(int a) {
        switch (a) {
            case 1 : {
                System.out.println("\nNenhum livro avaliado.");
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
                System.out.print("\nVocê já leu esse livro ? ");
                break;
            }
            case 2 : {
                System.out.println("\nEsse livro não pode ser avaliado.");
                break;
            }
            case 3 : {
                System.out.println("\nEsse livro já foi avaliado.");
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
                System.out.print("\nData de leitura (dd/mm/yyyy): ");
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
                System.out.print("\nEscreva o n° do livro: ");
                break;
            }
            case 11 : {
                System.out.println("\nLivro não encontrado.");
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
        System.out.println("\nNenhum livro cadastrado.");
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
                System.out.println(String.format("\nO ano de publicação do livro é %d.", b));
                System.out.println("Digite um valor depois do ano de publicação.");
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

    public void invalidIsbnMsg(int a) {
        switch (a) {
            case 1 : {
                System.out.println("\nIsbn já cadastrado.");
                System.out.println("Tente novamente.");
                break;
            }
            case 2 : {
                System.out.println("\nIsbn inexistente.");
                System.out.println("Tente novamente.");
                break;
            }
            default:
                break;
        }


    }

    public void invalidHasCopyMsg() {
        System.out.println("\nInválido.");
        System.out.println("Digite Sim ou Não.");
        System.out.println("Tente novamente.");
    }
}