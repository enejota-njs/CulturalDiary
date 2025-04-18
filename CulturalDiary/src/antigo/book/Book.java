package antigo.book;

import antigo.media.MediaReview;

import java.util.Scanner;

public class Book {
    private static int bookCounter = 1;

    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private int yearOfPublication;
    private String genre;
    private boolean hasCopy;
    private MediaReview bookReview;
    private boolean evaluatedBook;
    private int bookIndex;

    Scanner input = new Scanner(System.in);

    public Book(String title, String author, String publisher, String isbn,
                int yearOfPublication, String genre, boolean hasCopy) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.yearOfPublication = yearOfPublication;
        this.genre = genre;
        this.hasCopy = hasCopy;
        this.bookReview = null;
        this.evaluatedBook = false;
        this.bookIndex = bookCounter++;
    }

    /*public void displayInformation() {
        System.out.println("\n+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Livro n°", this.getBookIndex());
        System.out.println("+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Título", this.getTitle());
        System.out.printf("| %-20s -> %s\n", "Gênero", this.getGenre());
        System.out.printf("| %-20s -> %d\n", "Ano de Publicação", this.getYearOfPublication());
        System.out.printf("| %-20s -> %s\n", "Autor", this.getAuthor());
        System.out.printf("| %-20s -> %s\n", "Editora", this.getPublisher());
        System.out.printf("| %-20s -> %s\n", "ISBN", this.getIsbn());
        System.out.printf("| %-20s -> %s\n", "Tem um exemplar?", (this.isHasCopy() ? "Sim" : "Não"));
        System.out.println("+-----------------------+");
    }*/

    public void displayInformation() {
        System.out.println("\n+-----------------------+");
        System.out.printf("| %-20s -> %s, %s\n", "Livro n° " + this.getBookIndex(), this.getTitle(), this.getYearOfPublication());
        System.out.println("+-----------------------+");
    }

    /*public void displayReview() {
        if (bookReview == null) {
            System.out.println("+-----------------------+");
            System.out.printf("| %-20s -> %s\n", "Avaliação", "Livro não avaliado");
            System.out.println("+-----------------------+");
        } else {
            System.out.println("+-----------------------+");
            System.out.printf("| %-20s -> %s\n", "Já foi lido?", bookReview.isConsumed() ? "Sim" : "Não");
            System.out.printf("| %-20s -> %.2f\n", "Nota", bookReview.getScore());
            System.out.printf("| %-20s -> %s\n", "Data de leitura", bookReview.getConsumptionDate());
            System.out.printf("| %-20s -> %s\n", "Comentários", bookReview.getComment());
            System.out.println("+-----------------------+");
        }
        System.out.println("\n##################################################");
    }*/

    public void displayReview() {
        if (bookReview == null) {
            System.out.printf("| %-20s -> %s\n", "Avaliação", "Livro não avaliado");
            System.out.println("+-----------------------+");
        } else {
            System.out.printf("| %-20s -> %.2f\n", "Nota", bookReview.getScore());
            System.out.printf("| %-20s -> %s\n", "Data de leitura", bookReview.getConsumptionDate());
            System.out.printf("| %-20s -> %s\n", "Comentários", bookReview.getComment());
            System.out.println("+-----------------------+");
        }

    }

    public void checkBookReview() {

        if (!this.evaluatedBook) {
            System.out.print("\nVocê já leu esse livro ? ");
            String reading = input.nextLine().trim();

            if (reading.equalsIgnoreCase("s")
                    || reading.equalsIgnoreCase("sim")
                    || reading.equalsIgnoreCase("li")
                    || reading.equalsIgnoreCase("ja")
                    || reading.equalsIgnoreCase("já")) {
                evaluateBook();
            }
            else {
                System.out.println("\n+----------------------------------+");
                System.out.println("| Esse livro não pode ser avaliado |");
                System.out.println("+----------------------------------+");
            }
        } else {
            System.out.println("\nEsse livro já foi avaliado");
            System.out.print("Deseja avaliar novamente ? ");
            String evaluateAgain = input.nextLine().trim();

            if (evaluateAgain.equalsIgnoreCase("s")
                    || evaluateAgain.equalsIgnoreCase("sim")
                    || evaluateAgain.equalsIgnoreCase("quero")) {
                evaluateBook();
            } else if (evaluateAgain.equalsIgnoreCase("n")
                    || evaluateAgain.equalsIgnoreCase("não")
                    || evaluateAgain.equalsIgnoreCase("nao")
                    || evaluateAgain.equalsIgnoreCase("não quero")
                    || evaluateAgain.equalsIgnoreCase("nao quero")) {
                return;
            }
            else {
                System.out.println("\n+----------+");
                System.out.println("| Inválido |");
                System.out.println("+----------+");
                checkBookReview();
            }
        }
    }

    public void evaluateBook() {
        float temporaryScore;
        String temporaryConsumptionDate;
        String temporaryComment;

        while (true) {
            System.out.print("Dê uma nota entre 1 e 5: ");
            temporaryScore = input.nextFloat();
            input.nextLine();

            if (temporaryScore < 1 || temporaryScore > 5) {
                System.out.println("\n+-----------------------------------+");
                System.out.println("| O valor precisa estar entre 1 e 5 |");
                System.out.println("+-----------------------------------+");
                continue;
            }
            break;
        }

        System.out.print("Data de leitura: ");
        temporaryConsumptionDate = input.nextLine();

        System.out.print("Comentários: ");
        temporaryComment = input.nextLine();

        System.out.println("\n+----------------------+");
        System.out.println("| Avaliação cadastrada |");
        System.out.println("+----------------------+");

        bookReview = new MediaReview(true, temporaryScore, temporaryConsumptionDate, temporaryComment);
        this.evaluatedBook = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isHasCopy() {
        return hasCopy;
    }

    public void setHasCopy(boolean hasCopy) {
        this.hasCopy = hasCopy;
    }

    public MediaReview getBookReview() {
        return bookReview;
    }

    public void setBookReview(MediaReview bookReview) {
        this.bookReview = bookReview;
    }

    public boolean isEvaluatedBook() {
        return evaluatedBook;
    }

    public void setEvaluatedBook(boolean evaluatedBook) {
        this.evaluatedBook = evaluatedBook;
    }

    public static int getBookCounter() {
        return bookCounter;
    }

    public static void setBookCounter(int bookCounter) {
        Book.bookCounter = bookCounter;
    }

    public int getBookIndex() {
        return bookIndex;
    }

    public void setBookIndex(int bookIndex) {
        this.bookIndex = bookIndex;
    }
}