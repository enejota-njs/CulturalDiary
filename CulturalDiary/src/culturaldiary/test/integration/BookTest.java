package culturaldiary.test.integration;

import culturaldiary.book.BookController;
import org.junit.jupiter.api.Test;

public class BookTest {

    @Test
    void test() {
        BookController bookController = new BookController();

        bookController.sortListByLowRated();
        bookController.listBooks();

        bookController.registerBook("Harry Potter 1", "Marina Costa", "Editora Aurora", "9788598743998", "2001", "Fantasia", "Sim", "Sim");
        bookController.registerBook("        ", "Lucas Antunes", "Estrela Guia", "8532530988", "1995", "Aventura", "Não", "Sim");
        bookController.registerBook("Harry Potter 2", "Lucas Antunes", "Estrela Guia", "8532530988", "1995", "Aventura", "Não", "Sim");
        bookController.registerBook("Harry Potter 3", "Paulo Henrique", "Mundo Literário", "9781400034719", "2003", "Juvenil", "Sim", "Sim");
        bookController.registerBook("Harry Potter 4", "Renata Lopes", "Letras & Magia", "9788532530140", "2000", "Mistério", "Não", "Sim");
        bookController.registerBook("Harry Potter 5", "Amanda Rocha", "Editora Cristal", "9788598743012", "203304", "Fantasia", "Sim", "Sim");
        bookController.registerBook("Harry Potter 5", "Amanda Rocha", "Editora Cristal", "978859448743012", "2004", "Fantasia", "Sim", "Sim");
        bookController.registerBook("Harry Potter 5", "Amanda Rocha", "Editora Cristal", "9788598743012", "2004", "Fantasia", "Sim", "Sim");
        bookController.registerBook("Harry Potter 6", "Thiago Martins", "Nova Essência", "8532530997", "1998", "Ficção", "Não", "Sim");
        bookController.registerBook("Harry Potter 7", "Fernanda Dias", "Companhia das Letras", "9788598743098", "2007", "Drama", "Sim", "Sim");
        bookController.registerBook("The Witcher 1", "Igor Nunes", "Espada & Magia", "9788535900014", "2005", "Fantasia", "Sim", "Não");
        bookController.registerBook("The Witcher 2", "Mariana Soares", "Selo Vermelho", "8532048725", "2007", "Aventura", "Não", "Não");
        bookController.registerBook("The Witcher 3", " ", "Estúdio Letras", "9788598743104", "2009", "RPG", "Sim", "Não");
        bookController.registerBook("The Witcher 3", "Bruno Vieira", "Estúdio Letras", "9788598743104", "2009", "RPG", "Sim", "Não");
        bookController.registerBook("The Witcher 4", "Juliana Lopes", " ", "9788532530122", "2011", "Fantasia Épica", "Não", "Sim");
        bookController.registerBook("The Witcher 4", "Juliana Lopes", "Editora Runa", "9788532530122", "2011", "Fantasia Épica", "Não", "Sim");
        bookController.registerBook("The Witcher 5", "Carlos Tavares", "Nova Magia", "8571234599", "2013", "Ação", "Sim", "Sim");
        bookController.registerBook("The Witcher 6", "Luciana Campos", "Editora Golem", "9788598743128", "2015", "Mitologia", "Sim", "Não");
        bookController.registerBook("The Witcher 7", "Roberto Lima", "Lendária Press", "9788535900036", "201r7", "Fantasia Sombria", "Não", "Sim");
        bookController.registerBook("The Witcher 7", "Roberto Lima", "Lendária Press", "9788535900036", "2017", "Fantasia Sombria", "Não", "Sim");
        bookController.registerBook("As Crônicas de Nárnia 1", "C. S. Lewis", "Editora Aliança", "9788535900043", "1950", "  ", "Sim", "Sim");
        bookController.registerBook("As Crônicas de Nárnia 1", "C. S. Lewis", "Editora Aliança", "9788535900043", "1950", "  ", "acho que tenho", "Sim");
        bookController.registerBook("As Crônicas de Nárnia 1", "C. S. Lewis", "Editora Aliança", "9788535900043", "1950", "Fantasia", "Sim", "Sim");
        bookController.registerBook("As Crônicas de Nárnia 2", "C. S. Lewis", "Mundo Mágico", "8532048701", "1951", "Aventura", "Não", "Não");
        bookController.registerBook("As Crônicas de Nárnia 3", "C. S. Lewis", "Editora Galáxia", "9788598743159", "1952", "Infantil", "Sim", "Não");
        bookController.registerBook("As Crônicas de Nárnia 4", "C. S. Lewis", "Porto das Letras", "8532530148", "1953", "Fantasia Épica", "Não", "eu nunca li");
        bookController.registerBook("As Crônicas de Nárnia 4", "C. S. Lewis", "Porto das Letras", "9788535900043", "1953", "Fantasia Épica", "Não", "Sim");
        bookController.registerBook("As Crônicas de Nárnia 4", "C. S. Lewis", "Porto das Letras", "8532530148", "1953", "Fantasia Épica", "Não", "Sim");
        bookController.registerBook("As Crônicas de Nárnia 5", "C. S. Lewis", "Universo Editorial", "8571234581", "1954", "Clássico", "Sim", "Sim");
        bookController.registerBook("As Crônicas de Nárnia 6", "C. S. Lewis", "Editora Esperança", "9788598743166", "1955", "Literatura Infantojuvenil", "Sim", "Não");
        bookController.registerBook("As Crônicas de Nárnia 7", "C. S. Lewis", "Leitura Viva", "9788535900021", "2026", "Fantasia", "Não", "Sim");
        bookController.registerBook("As Crônicas de Nárnia 7", "C. S. Lewis", "Leitura Viva", "9788535900021", "1956", "Fantasia", "Não", "Sim");

        bookController.sortListByTopRated();

        bookController.listBooks();

        bookController.searchBookByGenre("Fantasia");
        bookController.searchBookByGenre("   ");
        bookController.searchBookByGenre("   w");
        bookController.searchBookByGenre("letra   ");

        bookController.searchBookByAuthor("renata");
        bookController.searchBookByAuthor("lewis");

        bookController.sortListByTopRated();

        bookController.searchBookByIsbn("9788535900021");
        bookController.searchBookByIsbn("97885359000ss21");
        bookController.searchBookByIsbn("97885359");
        bookController.searchBookByIsbn("97885359");
        bookController.searchBookByIsbn("8571234599");

        bookController.searchBookByYearOfPublication("2005");
        bookController.searchBookByYearOfPublication("2002");
        bookController.searchBookByYearOfPublication("1995");
        bookController.searchBookByYearOfPublication("1995asd");
        bookController.searchBookByYearOfPublication("  ");

        bookController.evaluateBookAgain(8, "4.3", "19/05/2002", "Excelente livro, recomendo!");
        bookController.changeBookReadingStatus(8, "sim");
        bookController.evaluateBookAgain(8, "4.3", "19/05/2002", "Excelente livro, recomendo!");
        bookController.evaluateBook(8, "4.3", "19/05/2023", "Excelente livro, recomendo!");

        bookController.openBook(2);
        bookController.openBook(98);
        bookController.openBook(4);
        bookController.openBook(8);

        bookController.evaluateBook(1, "4.3", "19/05/2002", "Excelente livro, recomendo!");
        bookController.evaluateBook(19, "4.3gh", "19/05/2002", "Excelente livro, recomendo!");
        bookController.evaluateBook(33, "4.3", "19/05/2002", "Excelente livro, recomendo!");
        bookController.evaluateBook(19, "1.3", "19/05/1999", "Excelente livro, recomendo!");
        bookController.evaluateBook(20, "3.7", "14/11/2027", "História interessante.");
        bookController.evaluateBook(21, "5.0", "28/02/2006", "Leitura agradável.");
        bookController.evaluateBook(20, "7.8", "28/02/2006", "Leitura agradável.");
        bookController.evaluateBook(20, "4.8", "28/02/2006", "Leitura agradável.");

        bookController.openBook(20);

        bookController.sortListByTopRated();

        bookController.filterListOfBooksByGenre(" ");
        bookController.filterListOfBooksByGenre("fant");
        bookController.filterListOfBooksByGenre(" fantASIA");
        bookController.filterListOfBooksByGenre("FANTASI");

        bookController.listBooks();

        bookController.evaluateBook(1, "4.3", "19/05/2006", "Excelente!");

        bookController.registerBook("Game of Thrones 1", "Eduardo Barros", "Editora Dragão Azul", "9788598743203", "2002", "Fantasia Épica", "Sim", "Sim");
        bookController.registerBook("Game of Thrones 2", "Júlia Nascimento", "Reino de Papel", "9788535900050", "2004", "Aventura Medieval", "Não", "Não");
        bookController.registerBook("Game of Thrones 3", "Marcelo Farias", "Espada & Letras", "9788598743210", "2006", "Drama Político", "Sim", "Não");
        bookController.registerBook("Game of Thrones 4", "Renata Lima", "Coroa Editorial", "9788532530157", "2008", "Fantasia Sombria", "Não", "Sim");
        bookController.registerBook("Game of Thrones 5", "Luana Torres", "Estúdios do Norte", "9788571234567", "2010", "Ficção Histórica", "Sim", "Sim");
        bookController.registerBook("Game of Thrones 6", "Rafael Martins", "Editora da Muralha", "9788598743227", "2012", "Épico Medieval", "Sim", "Não");
        bookController.registerBook("Game of Thrones 7", "Tatiane Souza", "Trono de Livros", "9788535900078", "2014", "Fantasia", "Não", "Sim");

        bookController.listBooks();
        bookController.sortListByTopRated();
        bookController.searchBookByGenre("suspense");

        bookController.evaluateBook(9, "5.0", "15/03/2005", "Narrativa interessante, mas lenta.");
        bookController.evaluateBook(10, "7.8", "08/11/2007", "Boa construção de mundo.");
        bookController.evaluateBook(11, "4.8", "22/06/2010", "Personagens pouco envolventes.");
        bookController.evaluateBook(12, "0.0", "30/09/2012", "Não consegui terminar a leitura.");
        bookController.evaluateBook(14, "7.8", "03/01/2020", "Trama empolgante e reviravoltas ótimas.");
        bookController.evaluateBook(223, "4.8", "27/12/2004", "Leitura morna, mas com bons momentos.");
        bookController.evaluateBook(2, "5.0", "09/10/2015", "Razoável, mas esperava mais.");
        bookController.evaluateBook(22, "7.8", "25/11/2025", "Muito bem escrito, personagens marcantes.");
        bookController.evaluateBook(23, "0.0", "18/07/2009", "Não me conectei com a história.");
        bookController.evaluateBook(24, "5.0", "12/03/2006", "Estilo de escrita simples, porém eficaz.");
        bookController.evaluateBook(25, "7.8", "01/05/2013", "Ambientação maravilhosa.");
        bookController.evaluateBook(30, "4.8", "06/09/2008", "Alguns capítulos cansativos.");

        bookController.sortListByLowRated();
        bookController.listBooks();
        bookController.sortListByTopRated();

        bookController.changeBookReadingStatus(16, "sim");
        bookController.changeBookReadingStatus(17, "sim");
        bookController.changeBookReadingStatus(20, "li");

        bookController.evaluateBook(16, "4.5", "12/02/2018", "Trama envolvente, ótimos personagens.");
        bookController.evaluateBook(17, "3.0", "23/06/2019", "Ritmo um pouco lento, mas interessante.");
        bookController.evaluateBook(18, "5.0", "14/09/2020", "Incrível! Melhor da série até agora.");
        bookController.evaluateBook(19, "2.5", "05/04/2021", "Esperava mais, mas ainda é bom.");
        bookController.evaluateBook(20, "4.0", "30/11/2022", "Ótimo encerramento da história.");

        bookController.listBooks();
        bookController.sortListByTopRated();

        bookController.openBook(2);
        bookController.openBook(20);
        bookController.openBook(9);

    } // Teste de integração geral
}