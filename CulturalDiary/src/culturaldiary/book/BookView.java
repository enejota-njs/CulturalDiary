package culturaldiary.book;

import culturaldiary.review.ReviewModel;

public class BookView {
    static final String BOLD = "\033[1m";
    static final String RESET = "\033[0m";

    public void emptyValueMessage(String name) {
        System.out.println("\n" + name + " não pode ficar vazio."); // Exibe uma mensagem de erro quando o valor de um campo está vazio
    } // Exibe mensagem indicando que um campo não pode ser vazio

    public void emptyInformationMessage() {
        System.out.println("\nA informação está vazia."); // Exibe uma mensagem indicando que a informação está vazia
    } // Exibe mensagem de informação vazia

    public void nonExistentIsbnMessage() {
        System.out.println("\nIsbn inexistente."); // Exibe uma mensagem quando o ISBN não existe
    } // Exibe mensagem para ISBN inexistente

    public String centerHeader(String text, int width) {
        if (text.length() >= width) {
            return text;
        }

        int spaces = width - text.length();
        int left = spaces / 2;
        int right = spaces - left;

        return " ".repeat(left) + text + " ".repeat(right);
    } // Centraliza um texto dentro de um determinado tamanho, adicionando espaços à esquerda e à direita

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
    } // Imprime o cabeçalho da tabela de livros formatada

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
    } // Exibe as informações de um livro formatadas para visualização em tabela

    public void registeredIsbnMessage() {
        System.out.println("\nIsbn já cadastrado.");
    } // Exibe mensagem informando que o ISBN já está cadastrado

    public void integerMessage() {
        System.out.println("\nO valor do ano precisa ser um número inteiro.");
    } // Exibe mensagem de erro quando o valor do ano não é um número inteiro

    public void invalidYearMessage(int currentYear) {
        System.out.println("\nO valor do ano precisa estar entre 1700 e " + currentYear + ".");
    } // Exibe mensagem de erro quando o ano informado está fora do intervalo permitido

    public void invalidNumberMessage() {
        System.out.println("\nPor favor, digite um número válido.");
    } // Exibe mensagem quando o valor inserido não é um número válido

    public void invalidHasCopyMessage() {
        System.out.println("\nNa pergunta do exemplar, digite Sim ou Não.");
    } // Exibe mensagem quando a resposta sobre exemplar não é válida

    public void invalidReadMessage() {
        System.out.println("\nNa pergunta da leitura, digite Sim ou Não");
    } // Exibe mensagem quando a resposta sobre leitura não é válida

    public void messageOfBookAlreadyEvaluated() {
        System.out.println("\nEsse livro já foi avaliado.");
    } // Informa que o livro já foi avaliado anteriormente

    public void unratedBookMessage() {
        System.out.println("\nEsse livro ainda não foi avaliado.");
    } // Informa que o livro ainda não possui avaliação

    public void invalidScoreMessage() {
        System.out.println("\nO valor precisa estar entre 1 e 5.");
    } // Exibe mensagem quando a nota está fora do intervalo permitido

    public void updatedReadMessage() {
        System.out.println("\nSituação de leitura atualizada.");
    } // Confirma que a situação de leitura foi atualizada com sucesso

    public void fullBookInformation(BookModel book) {
        int index = book.getBookIndex();
        System.out.println("\n+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Livro n°", index);
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
            System.out.printf("| %-20s -> %.2f\n", "Nota", book.getBookReview().getScore());
            System.out.printf("| %-20s -> %s\n", "Data de leitura", book.getBookReview().getConsumptionDate());
            System.out.printf("| %-20s -> %s\n", "Comentários", book.getBookReview().getComment());
            System.out.println("+-----------------------+");
        }
    } // Exibe todas as informações detalhadas de um livro, incluindo a avaliação se existente

    public void invalidDateFormatMessage() {
        System.out.println("\nFormato de data inválida.");
    } // Informa que o formato da data inserida está incorreto

    public void invalidDateMessage() {
        System.out.println("\nData inválida.");
    } // Informa que a data inserida é inválida

    public void emptyEvaluatedListMessage() {
        System.out.println("\nA lista de livros avaliados está vazia.");
    } // Informa que não há livros avaliados na lista

    public void emptyListMessage() {
        System.out.println("\nA lista de livros está vazia.");
    } // Informa que a lista de livros está vazia

    public void registeredEvaluationMessage() {
        System.out.println("\nAvaliação cadastrada.");
    } // Confirma que a avaliação do livro foi registrada com sucesso

    public void unreadBookMessage() {
        System.out.println("\nEsse livro ainda não foi lido.");
    } // Informa que o livro ainda não foi lido

    public void wrongReadMessage() {
        System.out.println("\nNão pode dizer que não leu um livro que já marcou como lido.");
    } // Informa que não é permitido marcar como "não lido" um livro previamente marcado como lido

    public void invalidMessage() {
        System.out.println("\nInválido.");
    } // Exibe mensagem genérica de valor inválido

    public void registeredBookMessage(String title) {
        System.out.println("\n" + title + " cadastrado.");
    } // Informa que o livro foi cadastrado com sucesso

    public void nonExistentDateMessage() {
        System.out.println("\nData inexistente.");
    } // Informa que a data informada não existe

    public void invalidFutureDatesMessage() {
        System.out.println("\nNão é permitido inserir datas futuras.");
    } // Informa que não se pode inserir datas futuras

    public void invalidYearPeriodMessage(int a) {
        System.out.println(String.format("\nInválido. O ano de publicação do livro é %d.", a));
    } // Informa que o ano inserido é anterior ao ano de publicação do livro

    public void tryAgainMessage() {
        System.out.println("\nTente novamente.");
    } // Solicita que o usuário tente novamente após erro

    public void noBookFoundMessage() {
        System.out.println("\nNenhum livro encontrado.");
    } // Informa que nenhum livro foi encontrado na busca
}