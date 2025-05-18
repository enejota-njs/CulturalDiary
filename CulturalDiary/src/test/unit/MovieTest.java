package test.unit;

import culturaldiary.movie.MovieController;
import culturaldiary.movie.MovieModel;
import culturaldiary.review.ReviewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovieTest {
    ArrayList<MovieModel> movies = new ArrayList<MovieModel>();

    @BeforeEach
    void list() {
        ArrayList<String> hp1 = new ArrayList<>(); hp1.add("Luna Martins"); hp1.add("Caio Vargas"); hp1.add("Isadora Nunes"); hp1.add("Daniela Moura");
        ArrayList<String> hp2 = new ArrayList<>(); hp2.add("Tiago Beltrão"); hp2.add("Helena Farias");
        ArrayList<String> hp3 = new ArrayList<>(); hp3.add("Vitória Luz"); hp3.add("Daniel Tavares"); hp3.add("Camila Rocha");
        ArrayList<String> hp4 = new ArrayList<>(); hp4.add("Renato Silveira"); hp4.add("Juliana Prado"); hp4.add("Lucas Figueiredo"); hp4.add("Amanda Pires"); hp4.add("Fábio Santana");
        ArrayList<String> hp5 = new ArrayList<>(); hp5.add("Amanda Rios"); hp5.add("Igor Fontes");
        ArrayList<String> hp6 = new ArrayList<>(); hp6.add("Patrícia Duarte"); hp6.add("Sandro Leme"); hp6.add("Lorena Pires");
        ArrayList<String> hp7 = new ArrayList<>(); hp7.add("Felipe Novaes"); hp7.add("Carla Menezes"); hp7.add("Bruno Caldas"); hp7.add("Luana Barbosa");
        ArrayList<String> tw1 = new ArrayList<>(); tw1.add("Dário Montenegro"); tw1.add("Elisa Dorneles"); tw1.add("Matheus Camargo");
        ArrayList<String> tw2 = new ArrayList<>(); tw2.add("Andréa Trindade"); tw2.add("Hugo Fernandes");
        ArrayList<String> tw3 = new ArrayList<>(); tw3.add("Vinícius Duarte"); tw3.add("Bruna Nogueira");
        ArrayList<String> tw4 = new ArrayList<>(); tw4.add("Natália Siqueira"); tw4.add("Leonardo Rangel"); tw4.add("Milena Godoy"); tw4.add("Fabrício Antunes");
        ArrayList<String> tw5 = new ArrayList<>(); tw5.add("Rodrigo Peçanha"); tw5.add("Juliane Torres"); tw5.add("Gabriel Lacerda");
        ArrayList<String> tw6 = new ArrayList<>(); tw6.add("Cláudia Neves"); tw6.add("Fernando Teixeira");
        ArrayList<String> tw7 = new ArrayList<>(); tw7.add("Raul Castro"); tw7.add("Tainá Freitas"); tw7.add("Marcos Vidal"); tw7.add("Érica Lopes"); tw7.add("Vinícius Carvalho");
        ArrayList<String> cn1 = new ArrayList<>(); cn1.add("Eduarda Melo"); cn1.add("Henrique Farias"); cn1.add("Rafaela Coimbra");
        ArrayList<String> cn2 = new ArrayList<>(); cn2.add("Gabriel Varela"); cn2.add("Lorena Simões");
        ArrayList<String> cn3 = new ArrayList<>(); cn3.add("Juliana Bezerra"); cn3.add("Thiago Correia"); cn3.add("Beatriz Vasques"); cn3.add("Mário Prado");
        ArrayList<String> cn4 = new ArrayList<>(); cn4.add("Murilo Andrade"); cn4.add("Isis Valverde"); cn4.add("Caíque Martins"); cn4.add("Tamires Gomes");
        ArrayList<String> cn5 = new ArrayList<>(); cn5.add("Débora Lemos"); cn5.add("Rogério Bastos");
        ArrayList<String> cn6 = new ArrayList<>(); cn6.add("Tatiane Cruz"); cn6.add("Lucas Pinheiro"); cn6.add("Viviane Braga");
        ArrayList<String> cn7 = new ArrayList<>(); cn7.add("Natanael Dias"); cn7.add("Gabriela Pires"); cn7.add("Edu Silveira"); cn7.add("Renata Prado");

        movies.add(new MovieModel("Harry Potter 1", "Fantasia", 2001, "02:32", "Marina Costa", "Dragões brilham em um torneio...", hp1, "Harry Potter and the Sorcerer's Stone", "Globoplay", true, movies.size() + 1));
        movies.add(new MovieModel("Harry Potter 2", "Aventura", 1995, "02:41", "Lucas Antunes", "A jornada de magia intensifica enquanto mistérios se desvendam...", hp2, "Harry Potter and the Chamber of Secrets", "Netflix", true, movies.size() + 1));
        movies.add(new MovieModel("Harry Potter 3", "Juvenil", 2003, "02:22", "Paulo Henrique", "Uma fuga emocionante enquanto um prisioneiro perigoso escapa...", hp3, "Harry Potter and the Prisoner of Azkaban", "Prime Video", true, movies.size() + 1));
        movies.add(new MovieModel("Harry Potter 4", "Mistério", 2000, "02:37", "Renata Lopes", "O torneio das três bruxas revela segredos ocultos e perigos mortais...", hp4, "Harry Potter and the Goblet of Fire", "HBO Max", true, movies.size() + 1));
        movies.add(new MovieModel("Harry Potter 5", "Fantasia", 2004, "02:18", "Amanda Rocha", "A luta contra um poder sombrio se intensifica enquanto o mago das trevas ressurge...", hp5, "Harry Potter and the Order of the Phoenix", "Telecine", true, movies.size() + 1));
        movies.add(new MovieModel("Harry Potter 6", "Ficção", 1998, "02:33", "Thiago Martins", "Segredos antigos são revelados enquanto uma nova ameaça cresce...", hp6, "Harry Potter and the Half-Blood Prince", "Disney+", true, movies.size() + 1));
        movies.add(new MovieModel("Harry Potter 7", "Drama", 2007, "02:10", "Fernanda Dias", "A batalha final contra as forças das trevas se aproxima, um último sacrifício será necessário...", hp7, "Harry Potter and the Deathly Hallows", "Apple TV+", true, movies.size() + 1));
        movies.add(new MovieModel("The Witcher 1", "Fantasia", 2005, "02:00", "Igor Nunes", "Dragões brilham em um torneio que decide o destino dos reinos...", tw1, "The Witcher: Blood Origin", "HBO Max", false, movies.size() + 1));
        movies.add(new MovieModel("The Witcher 2", "Aventura", 2007, "02:12", "Mariana Soares", "Exploração de terras desconhecidas enquanto forças antigas são despertadas...", tw2, "The Witcher: Trial of the Grasses", "Globoplay", false, movies.size() + 1));
        movies.add(new MovieModel("The Witcher 3", "RPG", 2009, "02:04", "Bruno Vieira", "O destino dos elfos e humanos se entrelaçam enquanto monstros dominam os reinos...", tw3, "The Witcher: Wild Hunt", "Netflix", true, movies.size() + 1));
        movies.add(new MovieModel("The Witcher 4", "Fantasia Épica", 2011, "02:07", "Juliana Lopes", "A guerra contra Nilfgaard ameaça devastar os reinos, um herói surge das cinzas...", tw4, "The Witcher: Rise of Nilfgaard", "Prime Video", false, movies.size() + 1));
        movies.add(new MovieModel("The Witcher 5", "Ação", 2013, "02:30", "Carlos Tavares", "Em busca do destino, guerreiros se enfrentam em batalhas épicas...", tw5, "The Witcher: Path of Destiny", "Paramount+", true, movies.size() + 1));
        movies.add(new MovieModel("The Witcher 6", "Mitologia", 2015, "02:17", "Luciana Campos", "Antigas profecias são desenterradas enquanto o sangue dos antigos se mistura com os novos...", tw6, "The Witcher: Elder Blood", "Star+", true, movies.size() + 1));
        movies.add(new MovieModel("The Witcher 7", "Fantasia Sombria", 2017, "02:25", "Roberto Lima", "Uma última batalha contra as trevas se aproxima, segredos milenares são revelados...", tw7, "The Witcher: Final Hunt", "YouTube Premium", false, movies.size() + 1));
        movies.add(new MovieModel("As Crônicas de Nárnia 1", "Fantasia", 1950, "02:20", "C. S. Lewis", "Dragões brilham enquanto um grande rei aparece para salvar um reino...", cn1, "The Chronicles of Narnia: The Lion, the Witch and the Wardrobe", "Netflix", true, movies.size() + 1));
        movies.add(new MovieModel("As Crônicas de Nárnia 2", "Aventura", 1951, "02:24", "C. S. Lewis", "Uma jornada épica começa, com batalhas e mistérios antigos a serem desvendados...", cn2, "The Chronicles of Narnia: Prince Caspian", "HBO Max", false, movies.size() + 1));
        movies.add(new MovieModel("As Crônicas de Nárnia 3", "Infantil", 1952, "01:55", "C. S. Lewis", "Exploração de mares desconhecidos leva a novas aventuras e descobertas...", cn3, "The Chronicles of Narnia: The Voyage of the Dawn Treader", "Star+", true, movies.size() + 1));
        movies.add(new MovieModel("As Crônicas de Nárnia 4", "Fantasia Épica", 1953, "02:13", "C. S. Lewis", "Uma batalha épica se desenrola enquanto novos heróis aparecem para combater o mal...", cn4, "The Chronicles of Narnia: The Silver Chair", "Globoplay", false, movies.size() + 1));
        movies.add(new MovieModel("As Crônicas de Nárnia 5", "Clássico", 1954, "02:01", "C. S. Lewis", "A luta por liberdade e esperança surge enquanto os heróis enfrentam desafios impensáveis...", cn5, "The Chronicles of Narnia: The Horse and His Boy", "Netflix", true, movies.size() + 1));
        movies.add(new MovieModel("As Crônicas de Nárnia 6", "Literatura Infantojuvenil", 1955, "01:58", "C. S. Lewis", "Um grande mago ensina a arte da magia enquanto tenta evitar um cataclismo...", cn6, "The Chronicles of Narnia: The Magician's Nephew", "Disney+", false, movies.size() + 1));
        movies.add(new MovieModel("As Crônicas de Nárnia 7", "Fantasia", 1956, "02:10", "C. S. Lewis", "O último confronto contra forças sombrias, onde o destino de Nárnia será decidido...", cn7, "The Chronicles of Narnia: The Last Battle", "Prime Video", true, movies.size() + 1));

        ArrayList<ReviewModel> reviews = new ArrayList<ReviewModel>();

        reviews.add(new ReviewModel(4.3f, "19/05/2002", "Excelente filme, recomendo!"));
        reviews.add(new ReviewModel(3.7f, "14/11/1998", "História interessante."));
        reviews.add(new ReviewModel(4.8f, "28/02/2005", "Filme agradável."));
        reviews.add(new ReviewModel(2.9f, "23/06/2002", "Bom, mas poderia ser melhor."));
        reviews.add(new ReviewModel(5.0f, "10/07/2004", "Muito bom, gostei bastante."));
        reviews.add(new ReviewModel(4.1f, "17/09/2001", "Amei o filme, muito emocionante!"));
        reviews.add(new ReviewModel(3.5f, "01/12/2006", "Não foi tão bom quanto eu esperava."));

        for (int i = 0; i < 7; i++) {
            movies.get(i).setMovieReview(reviews.get(i));
            movies.get(i).setEvaluatedMovie(true);
        }
    } // Criando lista para usar nos testes

    @Test
    void creatingMovie() {
        ArrayList<String> hp1 = new ArrayList<>(); hp1.add("Luna Martins"); hp1.add("Caio Vargas"); hp1.add("Isadora Nunes"); hp1.add("Daniela Moura");
        MovieModel movieModel = new MovieModel("Harry Potter 1", "Fantasia", 2001, "02:32", "Marina Costa", "Dragões brilham em um torneio...", hp1, "Harry Potter and the Sorcerer's Stone", "Globoplay", true, movies.size() + 1);
    } // Criando filmes

    @Test
    void registeringMovies() {
        MovieController movieController = new MovieController();

        assertFalse(movieController.registerMovie("   ", "Documentário", "2020", "02:22", "Rafael Costa", "Um jovem fazendeiro encontra um mapa que pode levá-lo a um tesouro esquecido há milênios...", "Gustavo Moreira, Vanessa Lima, Jorge Andrade", "The Sleeping City", "HBO", "assisti"));
        assertFalse(movieController.registerMovie("A Cidade Adormecida", "  ", "2020", "02:22", "Rafael Costa", "Um jovem fazendeiro encontra um mapa que pode levá-lo a um tesouro esquecido há milênios...", "Gustavo Moreira, Vanessa Lima, Jorge Andrade", "The Sleeping City", "HBO", "assisti"));
        assertFalse(movieController.registerMovie("A Cidade Adormecida", "Documentário", "20ds20", "02:22", "Rafael Costa", "Um jovem fazendeiro encontra um mapa que pode levá-lo a um tesouro esquecido há milênios...", "Gustavo Moreira, Vanessa Lima, Jorge Andrade", "The Sleeping City", "HBO", "assisti"));
        assertFalse(movieController.registerMovie("A Cidade Adormecida", "Documentário", "2020", "02:22", "Rafael Costa", "Um jovem fazendeiro encontra um mapa que pode levá-lo a um tesouro esquecido há milênios...", "Gustavo Moreira, Vanessa Lima, Jorge Andrade", "The Sleeping City", "HBO", "assisti, eu acho"));
        assertFalse(movieController.registerMovie("A Cidade Adormecida", "Documentário", "2020", "02.22", " ", "Um jovem fazendeiro encontra um mapa que pode levá-lo a um tesouro esquecido há milênios...", "Gustavo Moreira, Vanessa Lima, Jorge Andrade", "The Sleeping City", "HBO", "assisti"));
        assertFalse(movieController.registerMovie("A Cidade Adormecida", "Documentário", "2020", "02:22", "Rafael Costa", "Um jovem fazendeiro encontra um mapa que pode levá-lo a um tesouro esquecido há milênios...", "Gustavo Moreira, Vanessa Lima, Jorge Andrade", "The Sleeping City", "", "assisti"));
        assertFalse(movieController.registerMovie("A Cidade Adormecida", "Documentário", "20280", "02:22", "Rafael Costa", "Um jovem fazendeiro encontra um mapa que pode levá-lo a um tesouro esquecido há milênios...", "Gustavo Moreira, Vanessa Lima, Jorge Andrade", "The Sleeping City", "HBO", "assisti"));
        assertFalse(movieController.registerMovie("A Cidade Adormecida", " ", "20220", "02:22", "Rafael Costa", "Um jovem fazendeiro encontra um mapa que pode levá-lo a um tesouro esquecido há milênios...", "Gustavo Moreira, Vanessa Lima, Jorge Andrade", "The Sleeping City", "HBO", "assisti"));
        assertFalse(movieController.registerMovie("A Cidade Adormecida", "Documentário", "2220", "02:22", "Rafael Costa", "Um jovem fazendeiro encontra um mapa que pode levá-lo a um tesouro esquecido há milênios...", "Gustavo Moreira222, Vanessa Lima, Jorge Andrade", "The Sleeping City", "HBO", "assisti"));
        assertFalse(movieController.registerMovie("A Cidade Adormecida", "Documentário", "2020", "02:22", "Rafael Costa", "Um jovem fazendeiro encontra um mapa que pode levá-lo a um tesouro esquecido há milênios...", ",,,,,", "The Sleeping City", "HBO", "assisti"));
        assertFalse(movieController.registerMovie("A Cidade Adormecida", "Documentário", "2020", "02:22", "Rafael Costa", "Um jovem fazendeiro encontra um mapa que pode levá-lo a um tesouro esquecido há milênios...", "Gustavo Moreira, Vanessa Lima, Jorge Andrade", "                ", "HBO", "assisti"));
        assertFalse(movieController.registerMovie("A Cidade Adormecida", "Documentário", "2020", "02:22", "Rafael Costa", "Um jovem fazendeiro encontra um mapa que pode levá-lo a um tesouro esquecido há milênios...", "Gustavo Moreira, Vanessa Lima, Jorge Andrade", "The Sleeping City", "HBO", "não lembro"));
        assertTrue(movieController.registerMovie("A Cidade Adormecida", "Documentário", "2020", "02:22", "Rafael Costa", "Um jovem fazendeiro encontra um mapa que pode levá-lo a um tesouro esquecido há milênios...", "Gustavo Moreira, Vanessa Lima, Jorge Andrade", "The Sleeping City", "HBO", "assisti"));
    } // Registrando filmes

    @Test
    void searchingMovieByTitle() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertTrue(movieController.searchMovieByTitle("Harry   "));
        assertTrue(movieController.searchMovieByTitle("   1"));
        assertTrue(movieController.searchMovieByTitle("Potter"));
        assertTrue(movieController.searchMovieByTitle("O Pequeno Príncipe  "));
        assertTrue(movieController.searchMovieByTitle("sertão "));
        assertFalse(movieController.searchMovieByTitle(""));
        assertTrue(movieController.searchMovieByTitle("Dom Casmurro"));
        assertFalse(movieController.searchMovieByTitle("     "));
        assertTrue(movieController.searchMovieByTitle("nárnia"));
        assertFalse(movieController.searchMovieByTitle("   "));
    } // Buscando filmes por título

    @Test
    void searchingMovieByDirection() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertTrue(movieController.searchMovieByDirection("pedRO"));
        assertTrue(movieController.searchMovieByDirection("antunes"));
        assertTrue(movieController.searchMovieByDirection("marcelom"));
        assertTrue(movieController.searchMovieByDirection("a"));
        assertFalse(movieController.searchMovieByDirection("  "));
        assertFalse(movieController.searchMovieByDirection("     "));
        assertFalse(movieController.searchMovieByDirection(""));
    } // Buscando filmes por diretor(a)

    @Test
    void searchingMovieByActorInTheCast() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertFalse(movieController.searchMovieByActorInTheCast("  "));
        assertFalse(movieController.searchMovieByActorInTheCast(""));
        assertTrue(movieController.searchMovieByActorInTheCast("carlo"));
        assertTrue(movieController.searchMovieByActorInTheCast("Lucas     "));
        assertTrue(movieController.searchMovieByActorInTheCast("Isa"));
        assertTrue(movieController.searchMovieByActorInTheCast("Bela"));
        assertTrue(movieController.searchMovieByActorInTheCast("FelIPE"));
    } // Buscando filmes por ator no elenco

    @Test
    void searchingMovieByGenre() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertTrue(movieController.searchMovieByGenre("rpg"));
        assertFalse(movieController.searchMovieByGenre("     "));
        assertFalse(movieController.searchMovieByGenre(" "));
        assertTrue(movieController.searchMovieByGenre("Sci-Fi"));
        assertTrue(movieController.searchMovieByGenre("genero inventado"));
        assertTrue(movieController.searchMovieByGenre("   fantasia"));
        assertTrue(movieController.searchMovieByGenre("épica"));
        assertTrue(movieController.searchMovieByGenre("Fantasia"));
        assertTrue(movieController.searchMovieByGenre("Poesia"));
        assertTrue(movieController.searchMovieByGenre("literatura"));
    } // Buscando filmes por gênero

    @Test
    void searchingMovieByYearOfRelease() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertFalse(movieController.searchMovieByYearOfRelease("200u0"));
        assertFalse(movieController.searchMovieByYearOfRelease("169d9"));
        assertTrue(movieController.searchMovieByYearOfRelease("2025"));
        assertTrue(movieController.searchMovieByYearOfRelease("2005"));
        assertTrue(movieController.searchMovieByYearOfRelease("2007"));
        assertTrue(movieController.searchMovieByYearOfRelease("19925"));
        assertTrue(movieController.searchMovieByYearOfRelease("1699"));
        assertFalse(movieController.searchMovieByYearOfRelease("   "));
        assertFalse(movieController.searchMovieByYearOfRelease("ano2020"));
        assertFalse(movieController.searchMovieByYearOfRelease(""));
    } // Buscando filmes por ano de lançamento

    @Test
    void listingMovies() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertTrue(movieController.listMovies());
    } // Listando filmes

    @Test
    void filteringByMovieGenre() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertTrue(movieController.filterListOfMoviesByGenre("rpg"));
        assertFalse(movieController.filterListOfMoviesByGenre("     "));
        assertFalse(movieController.filterListOfMoviesByGenre(" "));
        assertTrue(movieController.filterListOfMoviesByGenre("Sci-Fi"));
        assertTrue(movieController.filterListOfMoviesByGenre("genero inventado"));
        assertTrue(movieController.filterListOfMoviesByGenre("   fantasia"));
        assertTrue(movieController.filterListOfMoviesByGenre("épica"));
        assertTrue(movieController.filterListOfMoviesByGenre("Fantasia"));
        assertTrue(movieController.filterListOfMoviesByGenre("Poesia"));
        assertTrue(movieController.filterListOfMoviesByGenre("literatura"));
    } // Filtrando filmes por gênero

    @Test
    void filteringMovieByYearOfRelease() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertFalse(movieController.filterListOfMoviesByYearOfRelease("200u0"));
        assertFalse(movieController.filterListOfMoviesByYearOfRelease("169d9"));
        assertTrue(movieController.filterListOfMoviesByYearOfRelease("2025"));
        assertTrue(movieController.filterListOfMoviesByYearOfRelease("2005"));
        assertTrue(movieController.filterListOfMoviesByYearOfRelease("2007"));
        assertTrue(movieController.filterListOfMoviesByYearOfRelease("19925"));
        assertTrue(movieController.filterListOfMoviesByYearOfRelease("1699"));
        assertFalse(movieController.filterListOfMoviesByYearOfRelease("   "));
        assertFalse(movieController.filterListOfMoviesByYearOfRelease("ano2020"));
        assertFalse(movieController.filterListOfMoviesByYearOfRelease(""));
    } // Filtrando filmes por ano de lançamento

    @Test
    void sortingListByTopRated() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertTrue(movieController.sortListByTopRated());
    } // Ordenando filmes do melhor avaliado ao pior

    @Test
    void sortingListByLowRated() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertTrue(movieController.sortListByLowRated());
    } // Ordenando filmes do pior avaliado ao melhor

    @Test
    void openingMovie() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertTrue(movieController.openMovie(1));
        assertFalse(movieController.openMovie(33));
    } // Abrindo filmes

    @Test
    void changingMovieViewingStatus() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertFalse(movieController.changeMovieViewingStatus(1, "não assisti"));
        assertTrue(movieController.changeMovieViewingStatus(8, "não assisti"));
        assertTrue(movieController.changeMovieViewingStatus(1, "assisti"));
        assertTrue(movieController.changeMovieViewingStatus(8, "assisti"));
        assertFalse(movieController.changeMovieViewingStatus(1, "  "));
        assertFalse(movieController.changeMovieViewingStatus(99, "assisti"));
        assertFalse(movieController.changeMovieViewingStatus(-2, "   "));
    } // Mundando situação de visualização

    @Test
    void evaluatingMovie() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertFalse(movieController.evaluateMovie(1, "4.3", "19/05/2002", "Excelente filme, recomendo!"));
        assertFalse(movieController.evaluateMovie(19, "4.3gh", "19/05/2002", "Excelente filme, recomendo!"));
        assertFalse(movieController.evaluateMovie(33, "4.3", "19/05/2002", "Excelente filme, recomendo!"));
        assertTrue(movieController.evaluateMovie(19, "4.3", "19/05/1999", "Excelente filme, recomendo!"));
        assertFalse(movieController.evaluateMovie(20, "3.7", "14/11/1998", "História interessante."));
        assertTrue(movieController.evaluateMovie(21, "4.8", "28/02/2006", "Filme agradável."));
        assertFalse(movieController.evaluateMovie(20, "4.8", "28/02/2006", "Filme agradável."));
    } // Avaliando filmes

    @Test
    void evaluatingMovieAgain() {
        MovieController movieController = new MovieController();
        movieController.setListOfMovies(movies);

        assertTrue(movieController.evaluateMovieAgain(1, "4.3", "19/05/2002", "Excelente livro, recomendo!"));
        assertFalse(movieController.evaluateMovieAgain(1, "4.3gh", "19/05/2002", "Excelente livro, recomendo!"));
        assertFalse(movieController.evaluateMovieAgain(81, "4.3", "19/05/2002", "Excelente livro, recomendo!"));
        assertTrue(movieController.evaluateMovieAgain(2, "4.3", "19/01/2025", "Excelente livro, recomendo!"));
        assertFalse(movieController.evaluateMovieAgain(3, "3.7", "14/11/1998", "História interessante."));
        assertFalse(movieController.evaluateMovieAgain(21, "4.8", "28/02/2005", "Leitura agradável."));
        assertFalse(movieController.evaluateMovieAgain(21, "3.5", "01/12/2006", "Não foi tão bom quanto eu esperava."));
    } // Avaliando filmes novamente
}