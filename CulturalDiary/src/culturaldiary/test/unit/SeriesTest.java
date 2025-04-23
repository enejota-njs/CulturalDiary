package culturaldiary.test.unit;

import culturaldiary.review.ReviewModel;
import culturaldiary.series.season.SeasonModel;
import culturaldiary.series.series.SeriesController;
import culturaldiary.series.series.SeriesModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeriesTest {
    ArrayList<SeriesModel> series = new ArrayList<SeriesModel>();

    @BeforeEach
    void list() {
        ArrayList<String> hp1t1Cast = new ArrayList<>(); hp1t1Cast.add("Luna Martins"); hp1t1Cast.add("Caio Vargas"); hp1t1Cast.add("Isadora Nunes"); hp1t1Cast.add("Daniela Moura");
        ArrayList<String> hp1t2Cast = new ArrayList<>(); hp1t2Cast.add("Tiago Beltrão"); hp1t2Cast.add("Helena Farias");
        ArrayList<String> hp1t3Cast = new ArrayList<>(); hp1t3Cast.add("Vitória Luz"); hp1t3Cast.add("Daniel Tavares"); hp1t3Cast.add("Camila Rocha");
        ArrayList<String> hp1t4Cast = new ArrayList<>(); hp1t4Cast.add("Renato Silveira"); hp1t4Cast.add("Juliana Prado"); hp1t4Cast.add("Lucas Figueiredo"); hp1t4Cast.add("Amanda Pires"); hp1t4Cast.add("Fábio Santana");

        ArrayList<String> hp2t1Cast = new ArrayList<>(); hp2t1Cast.add("Amanda Rios"); hp2t1Cast.add("Igor Fontes");
        ArrayList<String> hp2t2Cast = new ArrayList<>(); hp2t2Cast.add("Patrícia Duarte"); hp2t2Cast.add("Sandro Leme"); hp2t2Cast.add("Lorena Pires");
        ArrayList<String> hp2t3Cast = new ArrayList<>(); hp2t3Cast.add("Felipe Novaes"); hp2t3Cast.add("Carla Menezes"); hp2t3Cast.add("Bruno Caldas");

        ArrayList<String> hp3t1Cast = new ArrayList<>(); hp3t1Cast.add("Luana Barbosa"); hp3t1Cast.add("Cláudia Neves"); hp3t1Cast.add("Fernando Teixeira");
        ArrayList<String> hp3t2Cast = new ArrayList<>(); hp3t2Cast.add("Raul Castro"); hp3t2Cast.add("Tainá Freitas");

        ArrayList<String> hp4t1Cast = new ArrayList<>(); hp4t1Cast.add("Marcos Vidal"); hp4t1Cast.add("Érica Lopes"); hp4t1Cast.add("Vinícius Carvalho");

        ArrayList<String> hp5t1Cast = new ArrayList<>(); hp5t1Cast.add("Eduarda Melo"); hp5t1Cast.add("Henrique Farias");
        ArrayList<String> hp5t2Cast = new ArrayList<>(); hp5t2Cast.add("Rafaela Coimbra"); hp5t2Cast.add("Gabriel Varela");
        ArrayList<String> hp5t3Cast = new ArrayList<>(); hp5t3Cast.add("Lorena Simões"); hp5t3Cast.add("Juliana Bezerra"); hp5t3Cast.add("Thiago Correia");
        ArrayList<String> hp5t4Cast = new ArrayList<>(); hp5t4Cast.add("Beatriz Vasques"); hp5t4Cast.add("Mário Prado");

        ArrayList<String> hp6t1Cast = new ArrayList<>(); hp6t1Cast.add("Murilo Andrade"); hp6t1Cast.add("Isis Valverde");
        ArrayList<String> hp6t2Cast = new ArrayList<>(); hp6t2Cast.add("Caíque Martins"); hp6t2Cast.add("Tamires Gomes");
        ArrayList<String> hp6t3Cast = new ArrayList<>(); hp6t3Cast.add("Débora Lemos"); hp6t3Cast.add("Rogério Bastos");
        ArrayList<String> hp6t4Cast = new ArrayList<>(); hp6t4Cast.add("Tatiane Cruz"); hp6t4Cast.add("Lucas Pinheiro");
        ArrayList<String> hp6t5Cast = new ArrayList<>(); hp6t5Cast.add("Viviane Braga"); hp6t5Cast.add("Natanael Dias");

        ArrayList<String> hp7t1Cast = new ArrayList<>(); hp7t1Cast.add("Gabriela Pires"); hp7t1Cast.add("Edu Silveira");
        ArrayList<String> hp7t2Cast = new ArrayList<>(); hp7t2Cast.add("Renata Prado"); hp7t2Cast.add("Luna Martins");

        ArrayList<String> tw1t1Cast = new ArrayList<>(); tw1t1Cast.add("Caio Vargas"); tw1t1Cast.add("Isadora Nunes");
        ArrayList<String> tw1t2Cast = new ArrayList<>(); tw1t2Cast.add("Daniela Moura"); tw1t2Cast.add("Tiago Beltrão");
        ArrayList<String> tw1t3Cast = new ArrayList<>(); tw1t3Cast.add("Helena Farias"); tw1t3Cast.add("Vitória Luz");
        ArrayList<String> tw1t4Cast = new ArrayList<>(); tw1t4Cast.add("Daniel Tavares"); tw1t4Cast.add("Camila Rocha");

        ArrayList<String> tw2t1Cast = new ArrayList<>(); tw2t1Cast.add("Renato Silveira"); tw2t1Cast.add("Juliana Prado");

        ArrayList<String> tw3t1Cast = new ArrayList<>(); tw3t1Cast.add("Lucas Figueiredo"); tw3t1Cast.add("Amanda Pires");
        ArrayList<String> tw3t2Cast = new ArrayList<>(); tw3t2Cast.add("Fábio Santana"); tw3t2Cast.add("Amanda Rios");
        ArrayList<String> tw3t3Cast = new ArrayList<>(); tw3t3Cast.add("Igor Fontes"); tw3t3Cast.add("Patrícia Duarte");

        ArrayList<String> tw4t1Cast = new ArrayList<>(); tw4t1Cast.add("Sandro Leme"); tw4t1Cast.add("Lorena Pires");
        ArrayList<String> tw4t2Cast = new ArrayList<>(); tw4t2Cast.add("Felipe Novaes"); tw4t2Cast.add("Carla Menezes");
        ArrayList<String> tw4t3Cast = new ArrayList<>(); tw4t3Cast.add("Bruno Caldas"); tw4t3Cast.add("Luana Barbosa");
        ArrayList<String> tw4t4Cast = new ArrayList<>(); tw4t4Cast.add("Cláudia Neves"); tw4t4Cast.add("Fernando Teixeira");
        ArrayList<String> tw4t5Cast = new ArrayList<>(); tw4t5Cast.add("Raul Castro"); tw4t5Cast.add("Tainá Freitas");

        ArrayList<String> tw5t1Cast = new ArrayList<>(); tw5t1Cast.add("Marcos Vidal"); tw5t1Cast.add("Érica Lopes");
        ArrayList<String> tw5t2Cast = new ArrayList<>(); tw5t2Cast.add("Vinícius Carvalho"); tw5t2Cast.add("Eduarda Melo");
        ArrayList<String> tw5t3Cast = new ArrayList<>(); tw5t3Cast.add("Henrique Farias"); tw5t3Cast.add("Rafaela Coimbra");

        ArrayList<String> tw6t1Cast = new ArrayList<>(); tw6t1Cast.add("Gabriel Varela"); tw6t1Cast.add("Lorena Simões");

        ArrayList<String> tw7t1Cast = new ArrayList<>(); tw7t1Cast.add("Juliana Bezerra"); tw7t1Cast.add("Thiago Correia");
        ArrayList<String> tw7t2Cast = new ArrayList<>(); tw7t2Cast.add("Beatriz Vasques"); tw7t2Cast.add("Mário Prado");

        ArrayList<String> cn1t1Cast = new ArrayList<>(); cn1t1Cast.add("Murilo Andrade"); cn1t1Cast.add("Isis Valverde");
        ArrayList<String> cn1t2Cast = new ArrayList<>(); cn1t2Cast.add("Caíque Martins"); cn1t2Cast.add("Tamires Gomes");
        ArrayList<String> cn1t3Cast = new ArrayList<>(); cn1t3Cast.add("Débora Lemos"); cn1t3Cast.add("Rogério Bastos");

        ArrayList<String> cn2t1Cast = new ArrayList<>(); cn2t1Cast.add("Tatiane Cruz"); cn2t1Cast.add("Lucas Pinheiro");
        ArrayList<String> cn2t2Cast = new ArrayList<>(); cn2t2Cast.add("Viviane Braga"); cn2t2Cast.add("Natanael Dias");
        ArrayList<String> cn2t3Cast = new ArrayList<>(); cn2t3Cast.add("Gabriela Pires"); cn2t3Cast.add("Edu Silveira");
        ArrayList<String> cn2t4Cast = new ArrayList<>(); cn2t4Cast.add("Renata Prado"); cn2t4Cast.add("Luna Martins");

        ArrayList<String> cn3t1Cast = new ArrayList<>(); cn3t1Cast.add("Caio Vargas"); cn3t1Cast.add("Isadora Nunes");
        ArrayList<String> cn3t2Cast = new ArrayList<>(); cn3t2Cast.add("Daniela Moura"); cn3t2Cast.add("Tiago Beltrão");

        ArrayList<String> cn4t1Cast = new ArrayList<>(); cn4t1Cast.add("Helena Farias"); cn4t1Cast.add("Vitória Luz");

        ArrayList<String> cn5t1Cast = new ArrayList<>(); cn5t1Cast.add("Daniel Tavares"); cn5t1Cast.add("Camila Rocha");
        ArrayList<String> cn5t2Cast = new ArrayList<>(); cn5t2Cast.add("Renato Silveira"); cn5t2Cast.add("Juliana Prado");

        ArrayList<String> cn6t1Cast = new ArrayList<>(); cn6t1Cast.add("Lucas Figueiredo"); cn6t1Cast.add("Amanda Pires");
        ArrayList<String> cn6t2Cast = new ArrayList<>(); cn6t2Cast.add("Fábio Santana"); cn6t2Cast.add("Amanda Rios");
        ArrayList<String> cn6t3Cast = new ArrayList<>(); cn6t3Cast.add("Igor Fontes"); cn6t3Cast.add("Patrícia Duarte");

        ArrayList<String> cn7t1Cast = new ArrayList<>(); cn7t1Cast.add("Sandro Leme"); cn7t1Cast.add("Lorena Pires");
        ArrayList<String> cn7t2Cast = new ArrayList<>(); cn7t2Cast.add("Felipe Novaes"); cn7t2Cast.add("Carla Menezes");

        SeasonModel hp1t1 = new SeasonModel("Aventura", hp1t1Cast, 1997, true, 1);
        SeasonModel hp1t2 = new SeasonModel("Aventura", hp1t2Cast, 1998, false, 2);
        SeasonModel hp1t3 = new SeasonModel("Mistério", hp1t3Cast, 1999, true, 3);
        SeasonModel hp1t4 = new SeasonModel("Comédia", hp1t4Cast, 2000, false, 4);

        SeasonModel hp2t1 = new SeasonModel("Drama", hp2t1Cast, 2001, true, 1);
        SeasonModel hp2t2 = new SeasonModel("Ação", hp2t2Cast, 2002, false, 2);
        SeasonModel hp2t3 = new SeasonModel("Romance", hp2t3Cast, 2003, true, 3);

        SeasonModel hp3t1 = new SeasonModel("Fantasia", hp3t1Cast, 2004, true, 1);
        SeasonModel hp3t2 = new SeasonModel("Mistério", hp3t2Cast, 2005, false, 2);

        SeasonModel hp4t1 = new SeasonModel("Suspense", hp4t1Cast, 2006, true, 1);

        SeasonModel hp5t1 = new SeasonModel("Aventura", hp5t1Cast, 2007, false, 1);
        SeasonModel hp5t2 = new SeasonModel("Comédia", hp5t2Cast, 2008, true, 2);
        SeasonModel hp5t3 = new SeasonModel("Drama", hp5t3Cast, 2009, false, 3);
        SeasonModel hp5t4 = new SeasonModel("Romance", hp5t4Cast, 2010, true, 4);

        SeasonModel hp6t1 = new SeasonModel("Fantasia", hp6t1Cast, 2011, false, 1);
        SeasonModel hp6t2 = new SeasonModel("Mistério", hp6t2Cast, 2012, true, 2);
        SeasonModel hp6t3 = new SeasonModel("Ação", hp6t3Cast, 2013, false, 3);
        SeasonModel hp6t4 = new SeasonModel("Comédia", hp6t4Cast, 2014, true, 4);
        SeasonModel hp6t5 = new SeasonModel("Suspense", hp6t5Cast, 2015, false, 5);

        SeasonModel hp7t1 = new SeasonModel("Fantasia", hp7t1Cast, 2016, true, 1);
        SeasonModel hp7t2 = new SeasonModel("Drama", hp7t2Cast, 2017, false, 2);

        SeasonModel tw1t1 = new SeasonModel("Drama", tw1t1Cast, 2000, true, 1);
        SeasonModel tw1t2 = new SeasonModel("Ação", tw1t2Cast, 2001, false, 2);
        SeasonModel tw1t3 = new SeasonModel("Comédia", tw1t3Cast, 2002, true, 3);
        SeasonModel tw1t4 = new SeasonModel("Romance", tw1t4Cast, 2003, false, 4);

        SeasonModel tw2t1 = new SeasonModel("Fantasia", tw2t1Cast, 2004, true, 1);

        SeasonModel tw3t1 = new SeasonModel("Mistério", tw3t1Cast, 2005, true, 1);
        SeasonModel tw3t2 = new SeasonModel("Suspense", tw3t2Cast, 2006, false, 2);
        SeasonModel tw3t3 = new SeasonModel("Aventura", tw3t3Cast, 2007, true, 3);

        SeasonModel tw4t1 = new SeasonModel("Comédia", tw4t1Cast, 2008, false, 1);
        SeasonModel tw4t2 = new SeasonModel("Romance", tw4t2Cast, 2009, true, 2);
        SeasonModel tw4t3 = new SeasonModel("Drama", tw4t3Cast, 2010, false, 3);
        SeasonModel tw4t4 = new SeasonModel("Suspense", tw4t4Cast, 2011, true, 4);
        SeasonModel tw4t5 = new SeasonModel("Ação", tw4t5Cast, 2012, false, 5);

        SeasonModel tw5t1 = new SeasonModel("Drama", tw5t1Cast, 2013, true, 1);
        SeasonModel tw5t2 = new SeasonModel("Romance", tw5t2Cast, 2014, false, 2);
        SeasonModel tw5t3 = new SeasonModel("Aventura", tw5t3Cast, 2015, true, 3);

        SeasonModel tw6t1 = new SeasonModel("Mistério", tw6t1Cast, 2016, true, 1);

        SeasonModel tw7t1 = new SeasonModel("Drama", tw7t1Cast, 2017, false, 1);
        SeasonModel tw7t2 = new SeasonModel("Suspense", tw7t2Cast, 2018, true, 2);

        SeasonModel cn1t1 = new SeasonModel("Suspense", cn1t1Cast, 1949, true, 1);
        SeasonModel cn1t2 = new SeasonModel("Suspense", cn1t2Cast, 1950, false, 2);
        SeasonModel cn1t3 = new SeasonModel("Drama", cn1t3Cast, 1951, true, 3);

        SeasonModel cn2t1 = new SeasonModel("Suspense", cn2t1Cast, 1952, false, 1);
        SeasonModel cn2t2 = new SeasonModel("Comédia", cn2t2Cast, 1953, true, 2);
        SeasonModel cn2t3 = new SeasonModel("Romance", cn2t3Cast, 1954, false, 3);
        SeasonModel cn2t4 = new SeasonModel("Fantasia", cn2t4Cast, 1955, true, 4);

        SeasonModel cn3t1 = new SeasonModel("Suspense", cn3t1Cast, 1956, false, 1);
        SeasonModel cn3t2 = new SeasonModel("Mistério", cn3t2Cast, 1957, true, 2);

        SeasonModel cn4t1 = new SeasonModel("Comédia", cn4t1Cast, 1958, false, 1);

        SeasonModel cn5t1 = new SeasonModel("Drama", cn5t1Cast, 1959, true, 1);
        SeasonModel cn5t2 = new SeasonModel("Aventura", cn5t2Cast, 1960, false, 2);

        SeasonModel cn6t1 = new SeasonModel("Suspense", cn6t1Cast, 1961, true, 1);
        SeasonModel cn6t2 = new SeasonModel("Comédia", cn6t2Cast, 1962, false, 2);
        SeasonModel cn6t3 = new SeasonModel("Romance", cn6t3Cast, 1963, true, 3);

        SeasonModel cn7t1 = new SeasonModel("Fantasia", cn7t1Cast, 1964, true, 1);
        SeasonModel cn7t2 = new SeasonModel("Drama", cn7t2Cast, 1965, false, 2);

        ArrayList<SeasonModel> hp1 = new ArrayList<>(); hp1.add(hp1t1); hp1.add(hp1t2); hp1.add(hp1t3); hp1.add(hp1t4);
        ArrayList<SeasonModel> hp2 = new ArrayList<>(); hp2.add(hp2t1); hp2.add(hp2t2); hp2.add(hp2t3);
        ArrayList<SeasonModel> hp3 = new ArrayList<>(); hp3.add(hp3t1); hp3.add(hp3t2);
        ArrayList<SeasonModel> hp4 = new ArrayList<>(); hp4.add(hp4t1);
        ArrayList<SeasonModel> hp5 = new ArrayList<>(); hp5.add(hp5t1); hp5.add(hp5t2); hp5.add(hp5t3); hp5.add(hp5t4);
        ArrayList<SeasonModel> hp6 = new ArrayList<>(); hp6.add(hp6t1); hp6.add(hp6t2); hp6.add(hp6t3); hp6.add(hp6t4); hp6.add(hp6t5);
        ArrayList<SeasonModel> hp7 = new ArrayList<>(); hp7.add(hp7t1); hp7.add(hp7t2);
        ArrayList<SeasonModel> tw1 = new ArrayList<>(); tw1.add(tw1t1); tw1.add(tw1t2); tw1.add(tw1t3); tw1.add(tw1t4);
        ArrayList<SeasonModel> tw2 = new ArrayList<>(); tw2.add(tw2t1);
        ArrayList<SeasonModel> tw3 = new ArrayList<>(); tw3.add(tw3t1); tw3.add(tw3t2); tw3.add(tw3t3);
        ArrayList<SeasonModel> tw4 = new ArrayList<>(); tw4.add(tw4t1); tw4.add(tw4t2); tw4.add(tw4t3); tw4.add(tw4t4); tw4.add(tw4t5);
        ArrayList<SeasonModel> tw5 = new ArrayList<>(); tw5.add(tw5t1); tw5.add(tw5t2); tw5.add(tw5t3);
        ArrayList<SeasonModel> tw6 = new ArrayList<>(); tw6.add(tw6t1);
        ArrayList<SeasonModel> tw7 = new ArrayList<>(); tw7.add(tw7t1); tw7.add(tw7t2);
        ArrayList<SeasonModel> cn1 = new ArrayList<>(); cn1.add(cn1t1); cn1.add(cn1t2); cn1.add(cn1t3);
        ArrayList<SeasonModel> cn2 = new ArrayList<>(); cn2.add(cn2t1); cn2.add(cn2t2); cn2.add(cn2t3); cn2.add(cn2t4);
        ArrayList<SeasonModel> cn3 = new ArrayList<>(); cn3.add(cn3t1); cn3.add(cn3t2);
        ArrayList<SeasonModel> cn4 = new ArrayList<>(); cn4.add(cn4t1);
        ArrayList<SeasonModel> cn5 = new ArrayList<>(); cn5.add(cn5t1); cn5.add(cn5t2);
        ArrayList<SeasonModel> cn6 = new ArrayList<>(); cn6.add(cn6t1); cn6.add(cn6t2); cn6.add(cn6t3);
        ArrayList<SeasonModel> cn7 = new ArrayList<>(); cn7.add(cn7t1); cn7.add(cn7t2);

        series.add(new SeriesModel("Harry Potter 1", 1980,2001, "Harry Potter and the Sorcerer's Stone", "Globoplay", hp1));
        series.add(new SeriesModel("Harry Potter 2",1950,1995,"Harry Potter and the Chamber of Secrets", "Netflix", hp2));
        series.add(new SeriesModel("Harry Potter 3",2000,2003,"Harry Potter and the Prisoner of Azkaban", "Prime Video", hp3));
        series.add(new SeriesModel("Harry Potter 4",1980,2000,"Harry Potter and the Goblet of Fire", "HBO Max", hp4));
        series.add(new SeriesModel("Harry Potter 5",2000,2004,"Harry Potter and the Order of the Phoenix", "Telecine", hp5));
        series.add(new SeriesModel("Harry Potter 6",1998,1998,"Harry Potter and the Deathly Hallows", "Apple TV+", hp6));
        series.add(new SeriesModel("Harry Potter 7",2000,2007,"Harry Potter and the Deathly Hallows", "Apple TV+", hp7));
        series.add(new SeriesModel("The Witcher 1",2000,2005,"The Witcher: Blood Origin", "HBO Max", tw1));
        series.add(new SeriesModel("The Witcher 2",2000,2007,"The Witcher: Trial of the Grasses", "Globoplay", tw2));
        series.add(new SeriesModel("The Witcher 3",2006,2009,"The Witcher: Wild Hunt", "Netflix", tw3));
        series.add(new SeriesModel("The Witcher 4",1998,2011,"The Witcher: Rise of Nilfgaard", "Prime Video", tw4));
        series.add(new SeriesModel("The Witcher 5",1800,2013,"The Witcher: Path of Destiny", "Paramount+", tw5));
        series.add(new SeriesModel("The Witcher 6",2014,2015,"The Witcher: Elder Blood", "Star+", tw6));
        series.add(new SeriesModel("The Witcher 7",2016,2017,"The Witcher: Final Hunt", "YouTube Premium", tw7));
        series.add(new SeriesModel("As Crônicas de Nárnia 1", 1949, 1950,"The Chronicles of Narnia: The Lion, the Witch and the Wardrobe", "Netflix", cn1));
        series.add(new SeriesModel("As Crônicas de Nárnia 2", 1927, 1951,"The Chronicles of Narnia: Prince Caspian", "HBO Max", cn2));
        series.add(new SeriesModel("As Crônicas de Nárnia 3", 1822, 1952,"The Chronicles of Narnia: The Voyage of the Dawn Treader", "Star+", cn3));
        series.add(new SeriesModel("As Crônicas de Nárnia 4", 1950, 1953,"The Chronicles of Narnia: The Silver Chair", "Globoplay", cn4));
        series.add(new SeriesModel("As Crônicas de Nárnia 5", 1953, 1954,"The Chronicles of Narnia: The Horse and His Boy", "Netflix", cn5));
        series.add(new SeriesModel("As Crônicas de Nárnia 6", 1954, 1955,"The Chronicles of Narnia: The Magician's Nephew", "Disney+", cn6));
        series.add(new SeriesModel("As Crônicas de Nárnia 7", 1700, 1956,"The Chronicles of Narnia: The Last Battle", "Prime Video", cn7));
    }

    @Test
    void creatingSeries() {
        ArrayList<String> hp1t1Cast = new ArrayList<>(); hp1t1Cast.add("Luna Martins"); hp1t1Cast.add("Caio Vargas"); hp1t1Cast.add("Isadora Nunes"); hp1t1Cast.add("Daniela Moura");
        ArrayList<String> hp1t2Cast = new ArrayList<>(); hp1t2Cast.add("Tiago Beltrão"); hp1t2Cast.add("Helena Farias");
        ArrayList<String> hp1t3Cast = new ArrayList<>(); hp1t3Cast.add("Vitória Luz"); hp1t3Cast.add("Daniel Tavares"); hp1t3Cast.add("Camila Rocha");
        ArrayList<String> hp1t4Cast = new ArrayList<>(); hp1t4Cast.add("Renato Silveira"); hp1t4Cast.add("Juliana Prado"); hp1t4Cast.add("Lucas Figueiredo"); hp1t4Cast.add("Amanda Pires"); hp1t4Cast.add("Fábio Santana");

        SeasonModel hp1t1 = new SeasonModel("Aventura", hp1t1Cast, 1997, true, 1);
        SeasonModel hp1t2 = new SeasonModel("Aventura", hp1t2Cast, 1998, false, 2);
        SeasonModel hp1t3 = new SeasonModel("Mistério", hp1t3Cast, 1999, true, 3);
        SeasonModel hp1t4 = new SeasonModel("Comédia", hp1t4Cast, 2000, false, 4);

        ArrayList<SeasonModel> hp1 = new ArrayList<>(); hp1.add(hp1t1); hp1.add(hp1t2); hp1.add(hp1t3); hp1.add(hp1t4);

        SeriesModel seriesModel = new SeriesModel("Harry Potter 1", 1980,2001, "Harry Potter and the Sorcerer's Stone", "Globoplay", hp1);
    }

    @Test
    void registeringSeries() {
        SeriesController seriesController = new SeriesController();

        String[][] list1 = {
                {"Fantasia", "Felipe, ,,", "2020", "assisti"},
                {" ", "Joana, Pedro, Luana", "22021", "não assisti"},
                {"Ficção Científica", "Beto, Andreia", "2020", "não assisti"}
        };

        String[][] list2 = {
                {"Fantasia", "Felipe, ,,", "2020", "assisti"},
                {"Aventura", "Joana, Pedro, Luana", "20221", "não assisti"},
                {"Ficção Científica", "Beto, Andreia", "2020", "não assisti"}
        };

        String[][] list3 = {
                {"Fantasia", "Felipe, ,,", "2020", "assisti"},
                {"Aventura", "Joana, Pedro, Luana", "2021", "não assisti"},
                {"Ficção Científica", "Beto, Andreia", "2020", "não sei"}
        };

        String[][] list4 = {
                {"Fantasia", "Felipe, ,,", "2020", "assisti"},
                {"", "Joana, Pedro, Luana", "20221", "não assisti"},
                {"Ficção Científica", "Beto, Andreia", "2020", "não assisti"}
        };

        String[][] list5 = {
                {"Fantasia", " ", "2020", "assisti"},
                {"", "Joana, Pedro, Luana", "20221", "não assisti"},
                {"Ficção Científica", "   ", "2020", "não assisti"}
        };

        String[][] list6 = {
                {"Fantasia", "Felipe, ,,", "2020", "assisti"},
                {"", "Joana, Pedro, Luana", "20221", "não assisti"},
                {"Ficção Científica", "Beto, Andreia", " ", "não assisti"}
        };

        String[][] list7 = {
                {"Fantasia", "Felipe, ,,", "2020", "assisti"},
                {"", "Joana, Pedro, Luana", "20221", "não assisti"},
                {"Ficção Científica", "Beto, Andreia", "2020", "  "}
        };

        String[][] list8 = {
                {"Fantasia", "Felipe, Marcelo Campos", "2020", "assisti"},
                {"Comédia", "Joana, Pedro, Luana", "2021", "não assisti"},
                {"Ficção Científica", "Beto, Andreia", "2020", "não assisti"}
        };

        assertFalse(seriesController.registerSeries("A casa coberta", "1990", "2025", "The covered house", "MAX", list1));
        assertFalse(seriesController.registerSeries(" ", "1990", "2025", "The covered house", "MAX", list1));
        assertFalse(seriesController.registerSeries("A casa coberta", "19290", "2025", "  ", "MAX", list1));
        assertFalse(seriesController.registerSeries("A casa coberta", "1990", "2025", "The covered house", "  ", list1));
        assertFalse(seriesController.registerSeries("A casa coberta", "1990", "1991", "The covered house", "MAX", null));
        assertFalse(seriesController.registerSeries("A casa coberta", "1990", "1985", "The covered house", "MAX", list1));
        assertFalse(seriesController.registerSeries("A casa coberta", "1990", "2025", "The covered house", "MAX", list2));
        assertFalse(seriesController.registerSeries("A casa coberta", "1990", "2025", "The covered house", "MAX", list3));
        assertFalse(seriesController.registerSeries("A casa coberta", "1990", "2025", "The covered house", "MAX", list4));
        assertFalse(seriesController.registerSeries("A casa coberta", "1990", "2025", "The covered house", "MAX", list5));
        assertFalse(seriesController.registerSeries("A casa coberta", "1990", "2025", "The covered house", "MAX", list6));
        assertFalse(seriesController.registerSeries("A casa coberta", "1990", "2025", "The covered house", "MAX", list7));
        assertTrue(seriesController.registerSeries("A casa coberta", "1990", "2025", "The covered house", "MAX", list8));
    }

    @Test
    void searchingMovieByTitle() {
        SeriesController seriesController = new SeriesController();
        seriesController.setListOfSeries(series);

        assertTrue(seriesController.searchSeriesByTitle("Harry   "));
        assertTrue(seriesController.searchSeriesByTitle("   1"));
        assertTrue(seriesController.searchSeriesByTitle("Potter"));
        assertTrue(seriesController.searchSeriesByTitle("O Pequeno Príncipe  "));
        assertTrue(seriesController.searchSeriesByTitle("sertão "));
        assertFalse(seriesController.searchSeriesByTitle(""));
        assertTrue(seriesController.searchSeriesByTitle("Dom Casmurro"));
        assertFalse(seriesController.searchSeriesByTitle("     "));
        assertTrue(seriesController.searchSeriesByTitle("nárnia"));
        assertFalse(seriesController.searchSeriesByTitle("   "));
    }

    @Test
    void listingSeries() {
        SeriesController seriesController = new SeriesController();
        seriesController.setListOfSeries(series);

        assertTrue(seriesController.listSeries());
    }

    @Test
    void filteringBySeriesGenre() {
        SeriesController seriesController = new SeriesController();
        seriesController.setListOfSeries(series);

        assertTrue(seriesController.filterListOfSeriesByGenre("rpg"));
        assertFalse(seriesController.filterListOfSeriesByGenre("     "));
        assertFalse(seriesController.filterListOfSeriesByGenre(" "));
        assertTrue(seriesController.filterListOfSeriesByGenre("Sci-Fi"));
        assertTrue(seriesController.filterListOfSeriesByGenre("genero inventado"));
        assertTrue(seriesController.filterListOfSeriesByGenre("   fantasia"));
        assertTrue(seriesController.filterListOfSeriesByGenre("épica"));
        assertTrue(seriesController.filterListOfSeriesByGenre("Fantasia"));
        assertTrue(seriesController.filterListOfSeriesByGenre("Poesia"));
        assertTrue(seriesController.filterListOfSeriesByGenre("literatura"));
    }

    @Test
    void filteringSeriesByYearOfRelease() {
        SeriesController seriesController = new SeriesController();
        seriesController.setListOfSeries(series);

        assertFalse(seriesController.filterListOfSeriesByYearOfRelease("200u0"));
        assertFalse(seriesController.filterListOfSeriesByYearOfRelease("169d9"));
        assertTrue(seriesController.filterListOfSeriesByYearOfRelease("2025"));
        assertTrue(seriesController.filterListOfSeriesByYearOfRelease("2005"));
        assertTrue(seriesController.filterListOfSeriesByYearOfRelease("2007"));
        assertTrue(seriesController.filterListOfSeriesByYearOfRelease("19925"));
        assertTrue(seriesController.filterListOfSeriesByYearOfRelease("1699"));
        assertFalse(seriesController.filterListOfSeriesByYearOfRelease("   "));
        assertFalse(seriesController.filterListOfSeriesByYearOfRelease("ano2020"));
        assertFalse(seriesController.filterListOfSeriesByYearOfRelease(""));
    }

    @Test
    void sortingListByTopRated() {
        SeriesController seriesController = new SeriesController();
        seriesController.setListOfSeries(series);

        ArrayList<ReviewModel> reviews = new ArrayList<ReviewModel>();

        reviews.add(new ReviewModel(4.3f, "19/05/2024", "Excelente filme, recomendo!"));
        reviews.add(new ReviewModel(3.7f, "14/11/2024", "História interessante."));
        reviews.add(new ReviewModel(4.8f, "28/02/2024", "Filme agradável."));
        reviews.add(new ReviewModel(2.9f, "23/06/2024", "Bom, mas poderia ser melhor."));
        reviews.add(new ReviewModel(5.0f, "10/07/2024", "Muito bom, gostei bastante."));
        reviews.add(new ReviewModel(4.1f, "17/09/2024", "Amei o filme, muito emocionante!"));
        reviews.add(new ReviewModel(3.5f, "01/12/2024", "Não foi tão bom quanto eu esperava."));
        reviews.add(new ReviewModel(4.6f, "12/03/2024", "Enredo envolvente e atuações incríveis."));
        reviews.add(new ReviewModel(2.8f, "05/08/2024", "Não me prendeu muito, achei lento."));
        reviews.add(new ReviewModel(3.9f, "30/01/2024", "Interessante, mas com alguns clichês."));
        reviews.add(new ReviewModel(4.0f, "21/04/2024", "Gostei da direção e da trilha sonora."));
        reviews.add(new ReviewModel(5.0f, "09/06/2024", "Simplesmente sensacional!"));
        reviews.add(new ReviewModel(3.2f, "15/10/2024", "Algumas partes boas, outras nem tanto."));
        reviews.add(new ReviewModel(4.4f, "02/11/2024", "Filme bem construído e cativante."));
        reviews.add(new ReviewModel(2.5f, "27/07/2024", "Esperava mais da história."));
        reviews.add(new ReviewModel(3.6f, "13/02/2024", "Vale a pena assistir, mas não é o melhor."));

        series.get(0).getListOfSeasons().get(0).setSeasonReview(reviews.get(0));
        series.get(0).getListOfSeasons().get(1).setSeasonReview(reviews.get(1));
        series.get(0).getListOfSeasons().get(2).setSeasonReview(reviews.get(2));
        seriesController.updateAverage(series.get(0));

        series.get(2).getListOfSeasons().get(0).setSeasonReview(reviews.get(3));
        series.get(2).getListOfSeasons().get(1).setSeasonReview(reviews.get(4));
        seriesController.updateAverage(series.get(2));

        series.get(20).getListOfSeasons().get(0).setSeasonReview(reviews.get(5));
        seriesController.updateAverage(series.get(20));

        series.get(5).getListOfSeasons().get(0).setSeasonReview(reviews.get(6));
        series.get(5).getListOfSeasons().get(1).setSeasonReview(reviews.get(7));
        series.get(5).getListOfSeasons().get(2).setSeasonReview(reviews.get(8));
        series.get(5).getListOfSeasons().get(3).setSeasonReview(reviews.get(9));
        series.get(5).getListOfSeasons().get(4).setSeasonReview(reviews.get(10));
        seriesController.updateAverage(series.get(5));

        series.get(10).getListOfSeasons().get(0).setSeasonReview(reviews.get(11));
        series.get(10).getListOfSeasons().get(1).setSeasonReview(reviews.get(12));
        series.get(10).getListOfSeasons().get(2).setSeasonReview(reviews.get(13));
        series.get(10).getListOfSeasons().get(3).setSeasonReview(reviews.get(14));
        series.get(10).getListOfSeasons().get(4).setSeasonReview(reviews.get(15));
        seriesController.updateAverage(series.get(10));

        assertTrue(seriesController.sortListByTopRated());
    }

    @Test
    void sortingListByLowRated() {
        SeriesController seriesController = new SeriesController();
        seriesController.setListOfSeries(series);

        ArrayList<ReviewModel> reviews = new ArrayList<ReviewModel>();

        reviews.add(new ReviewModel(4.3f, "19/05/2024", "Excelente filme, recomendo!"));
        reviews.add(new ReviewModel(3.7f, "14/11/2024", "História interessante."));
        reviews.add(new ReviewModel(4.8f, "28/02/2024", "Filme agradável."));
        reviews.add(new ReviewModel(2.9f, "23/06/2024", "Bom, mas poderia ser melhor."));
        reviews.add(new ReviewModel(5.0f, "10/07/2024", "Muito bom, gostei bastante."));
        reviews.add(new ReviewModel(4.1f, "17/09/2024", "Amei o filme, muito emocionante!"));
        reviews.add(new ReviewModel(3.5f, "01/12/2024", "Não foi tão bom quanto eu esperava."));
        reviews.add(new ReviewModel(4.6f, "12/03/2024", "Enredo envolvente e atuações incríveis."));
        reviews.add(new ReviewModel(2.8f, "05/08/2024", "Não me prendeu muito, achei lento."));
        reviews.add(new ReviewModel(3.9f, "30/01/2024", "Interessante, mas com alguns clichês."));
        reviews.add(new ReviewModel(4.0f, "21/04/2024", "Gostei da direção e da trilha sonora."));
        reviews.add(new ReviewModel(5.0f, "09/06/2024", "Simplesmente sensacional!"));
        reviews.add(new ReviewModel(3.2f, "15/10/2024", "Algumas partes boas, outras nem tanto."));
        reviews.add(new ReviewModel(4.4f, "02/11/2024", "Filme bem construído e cativante."));
        reviews.add(new ReviewModel(2.5f, "27/07/2024", "Esperava mais da história."));
        reviews.add(new ReviewModel(3.6f, "13/02/2024", "Vale a pena assistir, mas não é o melhor."));
        reviews.add(new ReviewModel(4.9f, "18/05/2024", "Obra-prima do cinema moderno."));

        series.get(0).getListOfSeasons().get(0).setSeasonReview(reviews.get(0));
        series.get(0).getListOfSeasons().get(1).setSeasonReview(reviews.get(1));
        series.get(0).getListOfSeasons().get(2).setSeasonReview(reviews.get(2));
        seriesController.updateAverage(series.get(0));

        series.get(2).getListOfSeasons().get(0).setSeasonReview(reviews.get(3));
        series.get(2).getListOfSeasons().get(1).setSeasonReview(reviews.get(4));
        seriesController.updateAverage(series.get(2));

        series.get(20).getListOfSeasons().get(0).setSeasonReview(reviews.get(5));
        seriesController.updateAverage(series.get(20));

        series.get(5).getListOfSeasons().get(0).setSeasonReview(reviews.get(6));
        series.get(5).getListOfSeasons().get(1).setSeasonReview(reviews.get(7));
        series.get(5).getListOfSeasons().get(2).setSeasonReview(reviews.get(8));
        series.get(5).getListOfSeasons().get(3).setSeasonReview(reviews.get(9));
        series.get(5).getListOfSeasons().get(4).setSeasonReview(reviews.get(10));
        seriesController.updateAverage(series.get(5));

        series.get(10).getListOfSeasons().get(0).setSeasonReview(reviews.get(11));
        series.get(10).getListOfSeasons().get(1).setSeasonReview(reviews.get(12));
        series.get(10).getListOfSeasons().get(2).setSeasonReview(reviews.get(13));
        series.get(10).getListOfSeasons().get(3).setSeasonReview(reviews.get(14));
        series.get(10).getListOfSeasons().get(4).setSeasonReview(reviews.get(15));
        seriesController.updateAverage(series.get(10));

        assertTrue(seriesController.sortListByLowRated());
    }

    @Test
    void openingSeries() {
        SeriesController seriesController = new SeriesController();
        seriesController.setListOfSeries(series);

        assertTrue(seriesController.openSeries(1));
        assertFalse(seriesController.openSeries(33));
    }

    @Test
    void changingSeriesViewingStatus() {
        SeriesController seriesController = new SeriesController();
        seriesController.setListOfSeries(series);

        assertFalse(seriesController.changeSeasonViewingStatus(1, 1, "não assisti"));
        assertFalse(seriesController.changeSeasonViewingStatus(8, 1, "não assisti"));
        assertTrue(seriesController.changeSeasonViewingStatus(1, 1, "assisti"));
        assertTrue(seriesController.changeSeasonViewingStatus(8, 1, "assisti"));
        assertFalse(seriesController.changeSeasonViewingStatus(1, 1, "  "));
        assertFalse(seriesController.changeSeasonViewingStatus(99, 1, "assisti"));
        assertFalse(seriesController.changeSeasonViewingStatus(-2, 1, "   "));
    }

    @Test
    void evaluatingSeason() {
        SeriesController seriesController = new SeriesController();
        seriesController.setListOfSeries(series);

        assertTrue(seriesController.evaluateSeason(1, 1, "4.3", "19/05/2002", "Excelente temporada, recomendo!"));
        assertFalse(seriesController.evaluateSeason(19, 1, "4.3gh", "19/05/2002", "Excelente temporada, recomendo!"));
        assertFalse(seriesController.evaluateSeason(33, 1, "4.3", "19/05/2002", "Excelente temporada, recomendo!"));
        assertTrue(seriesController.evaluateSeason(19, 1, "4.3", "19/05/1999", "Excelente temporada, recomendo!"));
        assertTrue(seriesController.evaluateSeason(20, 1, "3.7", "14/11/1998", "História interessante."));
        assertTrue(seriesController.evaluateSeason(21, 1, "4.8", "28/02/2006", "Temporada agradável."));
        assertFalse(seriesController.evaluateSeason(20, 1, "4.8", "28/02/2006", "Temporada agradável."));
    }

    @Test
    void evaluatingSeasonAgain() {
        SeriesController seriesController = new SeriesController();
        seriesController.setListOfSeries(series);

        seriesController.evaluateSeason(1, 1, "4.3", "19/05/2002", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(19, 1, "4.3", "19/05/1999", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(20, 1, "3.7", "14/11/1998", "História interessante.");
        seriesController.evaluateSeason(21, 1, "4.8", "28/02/2006", "Temporada agradável.");

        assertTrue(seriesController.evaluateSeasonAgain(1, 1, "4.3", "19/05/2002", "Excelente temporada, recomendo!"));
        assertFalse(seriesController.evaluateSeasonAgain(19, 1, "4.3gh", "19/05/2002", "Excelente temporada, recomendo!"));
        assertFalse(seriesController.evaluateSeasonAgain(81, 1, "4.3", "19/05/2002", "Excelente temporada, recomendo!"));
        assertFalse(seriesController.evaluateSeasonAgain(2, 1, "4.3", "19/01/2025", "Excelente temporada, recomendo!"));
        assertFalse(seriesController.evaluateSeasonAgain(3, 1, "3.7", "14/11/1998", "História interessante."));
        assertTrue(seriesController.evaluateSeasonAgain(21, 1, "4.8", "28/02/2005", "temporada agradável."));
        assertTrue(seriesController.evaluateSeasonAgain(21, 1, "3.5", "01/12/2006", "Não foi tão bom quanto eu esperava."));
        assertFalse(seriesController.evaluateSeasonAgain(19, 1, "3.5", "01/12/2006", "Não foi tão bom quanto eu esperava."));
        assertTrue(seriesController.evaluateSeasonAgain(20, 1, "3.5", "01/12/2006", "Não foi tão bom quanto eu esperava."));
    }
}