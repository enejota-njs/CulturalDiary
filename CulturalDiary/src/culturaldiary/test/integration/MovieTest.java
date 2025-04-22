package culturaldiary.test.integration;

import culturaldiary.movie.MovieController;
import org.junit.jupiter.api.Test;

class MovieTest {

    @Test
    void test() {
        MovieController movieController = new MovieController();

        movieController.sortListByLowRated();
        movieController.listMovies();

        movieController.registerMovie("   ", "Fantasia", "2001", "02:32", "Marina Costa", "Dragões brilham em um torneio...", "Luna Martins, Caio Vargas, Isadora Nunes, Daniela Moura", "Harry Potter and the Sorcerer's Stone", "Globoplay", "sim");
        movieController.registerMovie("Harry Potter 1", "Fantasia", "2d001", "02:32", "Marina Costa", "Dragões brilham em um torneio...", "Luna Martins, Caio Vargas, Isadora Nunes, Daniela Moura", "Harry Potter and the Sorcerer's Stone", "Globoplay", "sim");
        movieController.registerMovie("Harry Potter 1", "Fantasia", "2001", "02:32", "Marina Costa", "Dragões brilham em um torneio...", "Luna Martins, Caio Vargas, Isadora Nunes, Daniela Moura", "Harry Potter and the Sorcerer's Stone", "Globoplay", "sim");
        movieController.registerMovie("Harry Potter 2", "Aventura", "1995", "02:41", "Lucas Antunes", "A jornada de magia intensifica enquanto mistérios se desvendam...", "Tiago Beltrão, Helena Farias", "Harry Potter and the Chamber of Secrets", "Netflix", "sim");
        movieController.registerMovie("Harry Potter 3", "Juvenil", "2003", "02:22", "Paulo Henrique", "Uma fuga emocionante enquanto um prisioneiro perigoso escapa...", "Vitória Luz, Daniel Tavares, Camila Rocha", "Harry Potter and the Prisoner of Azkaban", "Prime Video", "sim");
        movieController.registerMovie("Harry Potter 4", "Mistério", "2000", "02,37", "Renata Lopes", "O torneio das três bruxas revela segredos ocultos e perigos mortais...", "Renato Silveira, Juliana Prado, Lucas Figueiredo, Amanda Pires, Fábio Santana", "Harry Potter and the Goblet of Fire", "HBO Max", "sim");
        movieController.registerMovie("Harry Potter 4", "Mistério", "2000", "02:3r7", " ", "O torneio das três bruxas revela segredos ocultos e perigos mortais...", "Renato Silveira, Juliana Prado, Lucas Figueiredo, Amanda Pires, Fábio Santana", "Harry Potter and the Goblet of Fire", "HBO Max", "sim");
        movieController.registerMovie("Harry Potter 4", "Mistério", "2000", "02:37", "Renata Lopes", "O torneio das três bruxas revela segredos ocultos e perigos mortais...", "Renato Silveira, Juliana Prado, Lucas Figueiredo, Amanda Pires, Fábio Santana", "Harry Potter and the Goblet of Fire", "HBO Max", "sim");
        movieController.registerMovie("Harry Potter 5", "Fantasia", "2004", "02:18", "Amanda Rocha", "A luta contra um poder sombrio se intensifica enquanto o mago das trevas ressurge...", "Amanda Rios, Igor Fontes", "Harry Potter and the Order of the Phoenix", "Telecine", "sim");
        movieController.registerMovie("", "Ficção", "1998", "02:33", "Thiago Martins", "Segredos antigos são revelados enquanto uma nova ameaça cresce...", "Patrícia Duarte, Sandro Leme, Lorena Pires", "Harry Potter and the Half-Blood Prince", "Disney+", "sim");
        movieController.registerMovie("Harry Potter 6", "Ficção", "19898", "02:33", "Thiago Martins", "Segredos antigos são revelados enquanto uma nova ameaça cresce...", "Patrícia Duarte, Sandro Leme, Lorena Pires", "Harry Potter and the Half-Blood Prince", "Disney+", "sim");
        movieController.registerMovie("Harry Potter 6", "Ficção", "1998", "02:33", "Thiago Martins", "Segredos antigos são revelados enquanto uma nova ameaça cresce...", "Patrícia Duarte, Sandro Leme, Lorena Pires", "Harry Potter and the Half-Blood Prince", "Disney+", "sim");
        movieController.registerMovie("Harry Potter 7", "Drama", "2007", "02:10", "Fernanda Dias", "A batalha final contra as forças das trevas se aproxima, um último sacrifício será necessário...", "Felipe Novaes, Carla Menezes, Bruno Caldas, Luana Barbosa", "Harry Potter and the Deathly Hallows", "Apple TV+", "sim");
        movieController.registerMovie("The Witcher 1", "Fantasia", "2005", "02:00", "Igor Nunes", "Dragões brilham em um torneio que decide o destino dos reinos...", "Dário Montenegro, Elisa Dorneles, Matheus Camargo", "The Witcher: Blood Origin", "HBO Max", "não");
        movieController.registerMovie("The Witcher 2", "Aventura", "2007", "02:12", "Mariana Soares", "Exploração de terras desconhecidas enquanto forças antigas são despertadas...", "Andréa Trindade, Hugo Fernandes", "The Witcher: Trial of the Grasses", "Globoplay", "não");
        movieController.registerMovie("The Witcher 3", "RPG", "2009", "02:04", "Bruno Vieira", "O destino dos elfos e humanos se entrelaçam enquanto monstros dominam os reinos...", "Vinícius Duarte, Bruna Nogueira", "The Witcher: Wild Hunt", "Netflix", "sim");
        movieController.registerMovie("The Witcher 4", "Fantasia Épica", "2011", "02:07", "Juliana Lopes", "A guerra contra Nilfgaard ameaça devastar os reinos, um herói surge das cinzas...", "Natália Siqueil9ra, Leonardo Rangel, Milena Godoy, Fabrício Antunes", "The Witcher: Rise of Nilfgaard", "Prime Video", "não");
        movieController.registerMovie("The Witcher 4", "Fantasia Épica", "2011", "02:07", "Juliana Lopes", "A guerra contra Nilfgaard ameaça devastar os reinos, um herói surge das cinzas...", ",//o Rangel, Milena Godoy, Fabrício Antunes", "The Witcher: Rise of Nilfgaard", "Prime Video", "não");
        movieController.registerMovie("The Witcher 4", "Fantasia Épica", "2011", "02:07", "Juliana Lopes", "", "Natália Siqueira, Leonardo Rangel, Milena Godoy, Fabrício Antunes", "The Witcher: Rise of Nilfgaard", "Prime Video", "não");
        movieController.registerMovie("The Witcher 4", "Fantasia Épica", "2011", "02:07", "Juliana Lopes", "A guerra contra Nilfgaard ameaça devastar os reinos, um herói surge das cinzas...", "Natália Siqueira, Leonardo Rangel, Milena Godoy, Fabrício Antunes", "The Witcher: Rise of Nilfgaard", "Prime Video", "não");
        movieController.registerMovie("The Witcher 5", "Ação", "2013", "02:30", "Carlos Tavares", "Em busca do destino, guerreiros se enfrentam em batalhas épicas...", "Rodrigo Peçanha, Juliane Torres, Gabriel Lacerda", "The Witcher: Path of Destiny", "Paramount+", "sim");
        movieController.registerMovie("The Witcher 6", "Mitologia", "2015", "02:17", "Luciana Campos", "Antigas profecias são desenterradas enquanto o sangue dos antigos se mistura com os novos...", "Cláudia Neves, Fernando Teixeira", "The Witcher: Elder Blood", "", "sim");
        movieController.registerMovie("The Witcher 6", "Mitologia", "2015", "02:17", "Luciana Campos", "Antigas profecias são desenterradas enquanto o sangue dos antigos se mistura com os novos...", "Cláudia Neves, Fernando Teixeira", "The Witcher: Elder Blood", "Star+", "nao sei");
        movieController.registerMovie("The Witcher 6", "Mitologia", "2015", "02:17", "Luciana Campos", "Antigas profecias são desenterradas enquanto o sangue dos antigos se mistura com os novos...", "Cláudia Neves, Fernando Teixeira", "The Witcher: Elder Blood", "Star+", "sim");
        movieController.registerMovie("The Witcher 7", "Fantasia Sombria", "2017", "02:25", "Roberto Lima", "Uma última batalha contra as trevas se aproxima, segredos milenares são revelados...", "Raul Castro, Tainá Freitas, Marcos Vidal, Érica Lopes, Vinícius Carvalho", "The Witcher: Final Hunt", "YouTube Premium", "não");
        movieController.registerMovie("As Crônicas de Nárnia 1           ", "Fantasia", "1950", "02:20", "C. S. Lewis", "Dragões brilham enquanto um grande rei aparece para salvar um reino...", "Eduarda Melo, Henrique Farias, Rafaela Coimbra", "The Chronicles of Narnia: The Lion, the Witch and the Wardrobe", "Netflix", "sim");
        movieController.registerMovie("As Crônicas de Nárnia 2", " ", "1951", "02:24", "C. S. Lewis", "Uma jornada épica começa, com batalhas e mistérios antigos a serem desvendados...", "Gabriel Varela, Lorena Simões", "The Chronicles of Narnia: Prince Caspian", "HBO Max", "não");
        movieController.registerMovie("As Crônicas de Nárnia 2", "Aventura", "91951", "02:24", "C. S. Lewis", "Uma jornada épica começa, com batalhas e mistérios antigos a serem desvendados...", "Gabriel Varela, Lorena Simões", "The Chronicles of Narnia: Prince Caspian", "HBO Max", "não");
        movieController.registerMovie("As Crônicas de Nárnia 2", "Aventura", "1951", "02:24", "C. S. Lewis", "Uma jornada épica começa, com batalhas e mistérios antigos a serem desvendados...", "Gabriel Varela, Lorena Simões", "The Chronicles of Narnia: Prince Caspian", "HBO Max", "não");
        movieController.registerMovie("As Crônicas de Nárnia 3", "Infantil", "1952", "01:55", "C. S. Lewis", "Exploração de mares desconhecidos leva a novas aventuras e descobertas...", "Juliana Bezerra, Thiago Correia, Beatriz Vasques, Mário Prado", "The Chronicles of Narnia: The Voyage of the Dawn Treader", "Star+", "sim");
        movieController.registerMovie("As Crônicas de Nárnia 4", "Fantasia Épica", "1953", "02:13", "C. S. Lewis", "Uma batalha épica se desenrola enquanto novos heróis aparecem para combater o mal...", "Murilo Andrade, Isis Valverde, Caíque Martins, Tamires Gomes", "The Chronicles of Narnia: The Silver Chair", "Globoplay", "não");
        movieController.registerMovie("As Crônicas de Nárnia 5", "Clássico", "1954", "02:01", "C. S. Lewis", "A luta por liberdade e esperança surge enquanto os heróis enfrentam desafios impensáveis...", " ,, , ", "The Chronicles of Narnia: The Horse and His Boy", "Netflix", "sim");
        movieController.registerMovie("As Crônicas de Nárnia 5", "Clássico", "1954", "02:01", "C. S. Lewis", "A luta por liberdade e esperança surge enquanto os heróis enfrentam desafios impensáveis...", "Débora Lemos 4", "The Chronicles of Narnia: The Horse and His Boy", "Netflix", "sim");
        movieController.registerMovie("As Crônicas de Nárnia 5", "Clássico", "1954", "02:01", "C. S. Lewis", "A luta por liberdade e esperança surge enquanto os heróis enfrentam desafios impensáveis...", "Débora Lemos, Rogério Bastos", "The Chronicles of Narnia: The Horse and His Boy", "Netflix", "sim");
        movieController.registerMovie("As Crônicas de Nárnia 6", "Literatura Infantojuvenil", "1955", "01:58", "C. S. Lewis", "Um grande mago ensina a arte da magia enquanto tenta evitar um cataclismo...", "Tatiane Cruz, Lucas Pinheiro, Viviane Braga", "The Chronicles of Narnia: The Magician's Nephew", "Disney+", "não");
        movieController.registerMovie("As Crônicas de Nárnia 7", " ", "1956", "02:10", "C. S. Lewis", "O último confronto contra forças sombrias, onde o destino de Nárnia será decidido...", "Natanael Dias, Gabriela Pires, Edu Silveira, Renata Prado", "The Chronicles of Narnia: The Last Battle", "Prime Video", "sim");
        movieController.registerMovie("As Crônicas de Nárnia 7", "Fantasia", "1956", "02:10", "C. S. Lewis", "O último confronto contra forças sombrias, onde o destino de Nárnia será decidido...", "Natanael Dias, Gabriela Pires, Edu Silveira, Renata Prado", "The Chronicles of Narnia: The Last Battle", "Prime Video", "sim");

        movieController.sortListByTopRated();
        movieController.listMovies();

        movieController.searchMovieByGenre("Fantasia");
        movieController.searchMovieByGenre("   ");
        movieController.searchMovieByGenre("   w");
        movieController.searchMovieByGenre("letra   ");

        movieController.searchMovieByActorInTheCast("renata");
        movieController.searchMovieByActorInTheCast("lewis");
        movieController.searchMovieByActorInTheCast("letícia");
        movieController.searchMovieByActorInTheCast("VITÓRIA");
        movieController.searchMovieByActorInTheCast("Caio");
        movieController.searchMovieByActorInTheCast("gabriela");

        movieController.searchMovieByDirection("pedro");
        movieController.searchMovieByDirection("Lewis");

        movieController.sortListByTopRated();

        movieController.searchMovieByYearOfRelease("2005");
        movieController.searchMovieByYearOfRelease("2002");
        movieController.searchMovieByYearOfRelease("1995");
        movieController.searchMovieByYearOfRelease("1995asd");
        movieController.searchMovieByYearOfRelease("  ");

        movieController.evaluateMovieAgain(8, "4.3", "19/05/2002", "Excelente filme, recomendo!");
        movieController.changeMovieViewingStatus(8, "sim");
        movieController.evaluateMovieAgain(8, "4.3", "19/05/2002", "Excelente filme, recomendo!");
        movieController.evaluateMovie(8, "4.3", "19/05/2023", "Excelente filme, recomendo!");

        movieController.openMovie(2);
        movieController.openMovie(98);
        movieController.openMovie(4);
        movieController.openMovie(8);

        movieController.evaluateMovie(1, "4.3", "19/05/2002", "Excelente filme, recomendo!");
        movieController.evaluateMovie(19, "4.3gh", "19/05/2002", "Excelente filme, recomendo!");
        movieController.evaluateMovie(33, "4.3", "19/05/2002", "Excelente filme, recomendo!");
        movieController.evaluateMovie(19, "1.3", "19/05/1999", "Excelente filme, recomendo!");
        movieController.evaluateMovie(20, "3.7", "14/11/2027", "História interessante.");
        movieController.evaluateMovie(21, "5.0", "28/02/2006", "Filme agradável.");
        movieController.evaluateMovie(20, "7.8", "28/02/2006", "Filme agradável.");
        movieController.evaluateMovie(20, "4.8", "28/02/2006", "Filme agradável.");

        movieController.openMovie(20);

        movieController.sortListByTopRated();

        movieController.filterListOfMoviesByGenre(" ");
        movieController.filterListOfMoviesByGenre("fant");
        movieController.filterListOfMoviesByGenre(" fantASIA");
        movieController.filterListOfMoviesByGenre("FANTASI");

        movieController.listMovies();

        movieController.evaluateMovie(1, "4.3", "19/05/2006", "Excelente!");

        movieController.registerMovie("Game of Thrones 1", "Fantasia Épica", "2011", "01:58", "David Benioff", "Uma guerra começa no final de uma longa era de paz, quando antigos reinos se dividem...", "Jonas Barreto, Carla Vilela, Rodrigo Antunes", "Game of Thrones: Winter is Coming", "HBO Max", "sim");
        movieController.registerMovie("Game of Thrones 2", "Drama Medieval", "2012", "02:03", "D. B. Weiss", "Conflitos familiares se intensificam enquanto o trono é disputado por forças ocultas...", "Fernanda Luz, Rafael Mendes, Gustavo Lira", "Game of Thrones: War of Crowns", "HBO Max", "sim");
        movieController.registerMovie("Game of Thrones 3", "Aventura", "2013", "01:56", "Alan Taylor", "As muralhas do norte são ameaçadas por um exército ancestral que desperta...", "Bruna Xavier, Luan Salgado, Felipe Prado", "Game of Thrones: Beyond the Wall", "HBO Max", "não");
        movieController.registerMovie("Game of Thrones 4", "Fantasia Sombria", "2014", "02:07", "Jeremy Podeswa", "Traições e batalhas moldam o destino dos reinos enquanto uma ameaça cresce no leste...", "Lorena Barros, Thiago Costa, Mirela Fernandes", "Game of Thrones: Rise of the Dragon", "HBO Max", "sim");
        movieController.registerMovie("Game of Thrones 5", "Épico", "2015", "01:59", "Mark Mylod", "O inverno finalmente chega e as casas rivais se unem contra o verdadeiro inimigo...", "Igor Peixoto, Diana Ribeiro, Caio Vasconcelos", "Game of Thrones: The Long Night", "HBO Max", "sim");
        movieController.registerMovie("Game of Thrones 6", "Mitologia", "2016", "02:11", "Miguel Sapochnik", "Segredos antigos vêm à tona enquanto um herdeiro esquecido retorna para reclamar seu direito...", "Sabrina Azevedo, Marco Andrade, Tânia Leal", "Game of Thrones: Heir of Fire", "HBO Max", "não");
        movieController.registerMovie("Game of Thrones 7", "Drama Político", "2017", "02:06", "David Nutter", "O jogo final é jogado entre alianças frágeis e traições decisivas, selando o destino do reino...", "Rafaela Nogueira, Pedro Dantas, Lívia Correia", "Game of Thrones: The Final Throne", "HBO Max", "sim");

        movieController.listMovies();
        movieController.sortListByTopRated();
        movieController.sortListByLowRated();
        movieController.searchMovieByGenre("suspense");

        movieController.evaluateMovie(9, "5.0", "15/03/2005", "Narrativa interessante, mas lenta.");
        movieController.evaluateMovie(10, "7.8", "08/11/2007", "Boa construção de mundo.");
        movieController.evaluateMovie(11, "4.8", "22/06/2010", "Personagens pouco envolventes.");
        movieController.evaluateMovie(12, "0.0", "30/09/2012", "Não consegui terminar a leitura.");
        movieController.evaluateMovie(14, "7.8", "03/01/2020", "Trama empolgante e reviravoltas ótimas.");
        movieController.evaluateMovie(223, "4.8", "27/12/2004", "Leitura morna, mas com bons momentos.");
        movieController.evaluateMovie(2, "5.0", "09/10/2015", "Razoável, mas esperava mais.");
        movieController.evaluateMovie(22, "7.8", "25/11/2025", "Muito bem escrito, personagens marcantes."); // data futura
        movieController.evaluateMovie(23, "0.0", "18/07/2009", "Não me conectei com a história.");
        movieController.evaluateMovie(24, "5.0", "12/03/2006", "Estilo de escrita simples, porém eficaz.");
        movieController.evaluateMovie(25, "7.8", "01/05/2013", "Ambientação maravilhosa.");
        movieController.evaluateMovie(30, "4.8", "06/09/2008", "Alguns capítulos cansativos.");

        movieController.sortListByLowRated();
        movieController.listMovies();
        movieController.sortListByTopRated();

        movieController.changeMovieViewingStatus(16, "sim");
        movieController.changeMovieViewingStatus(17, "sim");
        movieController.changeMovieViewingStatus(20, "sei não");

        movieController.evaluateMovie(16, "4.5", "12/02/2018", "Trama envolvente, ótimos personagens.");
        movieController.evaluateMovie(17, "3.0", "23/06/2019", "Ritmo um pouco lento, mas interessante.");
        movieController.evaluateMovie(18, "5.0", "14/09/2020", "Incrível! Melhor da série até agora.");
        movieController.evaluateMovie(19, "2.5", "05/04/2021", "Esperava mais, mas ainda é bom.");
        movieController.evaluateMovie(20, "4.0", "30/11/2022", "Ótimo encerramento da história.");

        movieController.listMovies();
        movieController.sortListByTopRated();

        movieController.openMovie(2);
        movieController.openMovie(145);
        movieController.openMovie(17);
    }
}
