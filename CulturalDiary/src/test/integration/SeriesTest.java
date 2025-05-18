package test.integration;

import culturaldiary.series.series.SeriesController;
import org.junit.jupiter.api.Test;

class SeriesTest {

    @Test
    void test() {
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
                {"Fantasia", "Felipe, Marcelo Campos", "2000", "assisti"},
                {"Comédia", "Joana, Pedro, Luana", "2000", "não assisti"},
                {"Ficção Científica", "Beto, Andreia", "2000", "não assisti"}
        };
        String[][] list9 = {
                {"Aventura", "Carlos, Bianca", "2000", "assisti"},
                {"Drama", "Lúcia", "2000", "não assisti"}
        };

        String[][] list10 = {
                {"Mistério", "Renato, Clara, Diego, Marta", "2000", "assisti"},
                {"Terror", "Fábio, Teresa", "2000", "não assisti"},
                {"Comédia", "Juliana", "2000", "assisti"}
        };

        String[][] list11 = {
                {"Romance", "Ana, Rodrigo, Helena", "2000", "assisti"}
        };

        String[][] list12 = {
                {"Suspense", "Eduardo, Camila", "2000", "não assisti"},
                {"Fantasia", "Rafaela, Igor, Paula", "2000", "assisti"},
                {"Ação", "Leonardo", "2000", "não assisti"},
                {"Drama", "Daniel, Sônia", "2000", "assisti"}
        };

        String[][] list13 = {
                {"Histórico", "Tatiane, Guilherme", "2000", "não assisti"},
                {"Ficção Científica", "Vinícius", "2000", "assisti"},
                {"Musical", "Priscila, Tadeu, Fernanda, Jorge", "2000", "não assisti"},
                {"Comédia Romântica", "Sabrina", "2000", "assisti"},
                {"Ação", "Thiago, Mariana", "2000", "assisti"}
        };

        seriesController.registerSeries("Harry Potter 1", "1980","2001", "Harry Potter and the Sorcerer's Stone", "Globoplay", list1);
        seriesController.registerSeries("Harry Potter 1", "1980","2001", "Harry Potter and the Sorcerer's Stone", "Globoplay", list2);
        seriesController.registerSeries("Harry Potter 1", "1980","2001", "Harry Potter and the Sorcerer's Stone", "Globoplay", list3);
        seriesController.registerSeries("Harry Potter 1", "1980","2001", "Harry Potter and the Sorcerer's Stone", "Globoplay", list4);
        seriesController.registerSeries("Harry Potter 1", "1980","2001", "Harry Potter and the Sorcerer's Stone", "Globoplay", list5);
        seriesController.registerSeries("Harry Potter 1", "1980","2001", "Harry Potter and the Sorcerer's Stone", "Globoplay", list6);
        seriesController.registerSeries("Harry Potter 1", "1980","2001", "Harry Potter and the Sorcerer's Stone", "Globoplay", list7);
        seriesController.registerSeries("Harry Potter 1", "1980","2001", "Harry Potter and the Sorcerer's Stone", "Globoplay", list8);
        seriesController.registerSeries("   ","1950","1995","Harry Potter and the Chamber of Secrets", "Netflix", list8);
        seriesController.registerSeries("Harry Potter 2","1950","2015","Harry Potter and the Chamber of Secrets", "Netflix", list8);
        seriesController.registerSeries("Harry Potter 3","22000","2003","Harry Potter and the Prisoner of Azkaban", "Prime Video", list8);
        seriesController.registerSeries("Harry Potter 3","2000","1990","Harry Potter and the Prisoner of Azkaban", "Prime Video", list8);
        seriesController.registerSeries("Harry Potter 3","1999","2003","Harry Potter and the Prisoner of Azkaban", "Prime Video", list8);
        seriesController.registerSeries("Harry Potter 4","1980","w","Harry Potter and the Goblet of Fire", "HBO Max", list9);
        seriesController.registerSeries("Harry Potter 4","1980","2000","Harry Potter and the Goblet of Fire", "HBO Max", list9);
        seriesController.registerSeries("Harry Potter 5","2000","2004","Harry Potter and the Order of the Phoenix", "Telecine", list10);
        seriesController.registerSeries("Harry Potter 6","1998","2015","Harry Potter and the Deathly Hallows", "Apple TV+", list11);
        seriesController.registerSeries("Harry Potter 7","2000","2007","Harry Potter and the Deathly Hallows", "Apple TV+", list12);
        seriesController.registerSeries("The Witcher 1","2000","2005","The Witcher: Blood Origin", "HBO Max", list13);
        seriesController.registerSeries("The Witcher 2","ss2006","2007","The Witcher: Trial of the Grasses", "Globoplay", list8);
        seriesController.registerSeries("The Witcher 2","2000","2007"," ", "Globoplay", list8);
        seriesController.registerSeries("The Witcher 2","2000","2007","The Witcher: Trial of the Grasses", " ", list8);
        seriesController.registerSeries("The Witcher 2","2000","2007","The Witcher: Trial of the Grasses", "Globoplay", list8);
        seriesController.registerSeries("The Witcher 3","1800","2009","The Witcher: Wild Hunt", "Netflix", list9);
        seriesController.registerSeries("The Witcher 4","1998","2011","The Witcher: Rise of Nilfgaard", "Prime Video", list10);
        seriesController.registerSeries("The Witcher 5","","2013","The Witcher: Path of Destiny", "Paramount+", list11);
        seriesController.registerSeries("The Witcher 5","1800 ","2013","The Witcher: Path of Destiny", "Paramount+", list11);
        seriesController.registerSeries("The Witcher 6","1877","2015","The Witcher: Elder Blood", "Star+", list12);
        seriesController.registerSeries("The Witcher 7","1916","2017","The Witcher: Final Hunt", "YouTube Premium", list13);
        seriesController.registerSeries("As Crônicas de Nárnia 1", "1949", "1950","The Chronicles of Narnia: The Lion, the Witch and the Wardrobe", "Netflix", null);
        seriesController.registerSeries("As Crônicas de Nárnia 1", "1949", "1950","The Chronicles of Narnia: The Lion, the Witch and the Wardrobe", "   ", list8);
        seriesController.registerSeries("As Crônicas de Nárnia 1", "1949", "2025","The Chronicles of Narnia: The Lion, the Witch and the Wardrobe", "Netflix", list8);
        seriesController.registerSeries("As Crônicas de Nárnia 2", "1927", "2015","The Chronicles of Narnia: Prince Caspian", "HBO Max", list9);
        seriesController.registerSeries("As Crônicas de Nárnia 3", "1822", "2012","The Chronicles of Narnia: The Voyage of the Dawn Treader", "Star+", list10);
        seriesController.registerSeries("As Crônicas de Nárnia 4", "1950", "2013","The Chronicles of Narnia: The Silver Chair", "Globoplay", list11);
        seriesController.registerSeries("As Crônicas de Nárnia 5", "1953", "1699","The Chronicles of Narnia: The Horse and His Boy", "Netflix", list12);
        seriesController.registerSeries("As Crônicas de Nárnia 5", "1699", "1954","The Chronicles of Narnia: The Horse and His Boy", "Netflix", list12);
        seriesController.registerSeries("As Crônicas de Nárnia 5", "1900", "2026","The Chronicles of Narnia: The Horse and His Boy", "Netflix", list12);
        seriesController.registerSeries("As Crônicas de Nárnia 5", "1953", "2016","The Chronicles of Narnia: The Horse and His Boy", "Netflix", list12);
        seriesController.registerSeries("As Crônicas de Nárnia 6", "1954", "2017","The Chronicles of Narnia: The Magician's Nephew", "Disney+", list13);
        seriesController.registerSeries("As Crônicas de Nárnia 7", "1700", "2025","The Chronicles of Narnia: The Last Battle", "Prime Video", list8);

        seriesController.sortListByTopRated();
        seriesController.listSeries();

        seriesController.openSeries(1);
        seriesController.openSeries(33);

        seriesController.searchSeriesByTitle("potter    ");
        seriesController.searchSeriesByTitle("a");
        seriesController.searchSeriesByTitle("a câmera de ouro");

        seriesController.evaluateSeason(1, 1,"4.3", "19/05/2002", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(19,1, "4.3gh", "19/05/2002", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(33,1, "4.3", "19/05/2002", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(19,1, "1.3", "19/05/1999", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(20,1, "3.7", "14/11/2027", "História interessante.");
        seriesController.evaluateSeason(21,1, "5.0", "28/02/2006", "Temporada agradável.");
        seriesController.evaluateSeason(20,1, "7.8", "28/02/2006", "Temporada agradável.");
        seriesController.evaluateSeason(20,1, "4.8", "28/02/2006", "Temporada agradável.");
        seriesController.evaluateSeason(1, 2,"4.3", "19/05/2002", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(19, 2, "4.3gh", "19/05/2002", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(33, 2, "4.3", "19/05/2002", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(19, 2, "1.3", "19/05/1999", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(20, 2, "3.7", "14/11/2027", "História interessante.");
        seriesController.evaluateSeason(21, 2, "5.0", "28/02/2006", "Temporada agradável.");
        seriesController.evaluateSeason(20, 2, "7.8", "28/02/2006", "Temporada agradável.");
        seriesController.evaluateSeason(20, 2, "4.8", "28/02/2006", "Temporada agradável.");
        seriesController.evaluateSeason(1, 3,"4.3", "19/05/2002", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(19,3,"4.3gh", "19/05/2002", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(33,3,"4.3", "19/05/2002", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(19,3, "1.3", "19/05/1999", "Excelente temporada, recomendo!");
        seriesController.evaluateSeason(20,3, "3.7", "14/11/2027", "História interessante.");
        seriesController.evaluateSeason(21,3, "5.0", "28/02/2006", "Temporada agradável.");
        seriesController.evaluateSeason(20,3, "7.8", "28/02/2006", "Temporada agradável.");
        seriesController.evaluateSeason(20,3, "4.8", "28/02/2006", "Temporada agradável.");

        seriesController.evaluateSeasonAgain(21, 2, "5.0", "28/02/2006", "Temporada agradável.");
        seriesController.evaluateSeasonAgain(20, 2, "7.8", "28/02/2006", "Temporada agradável.");
        seriesController.evaluateSeasonAgain(20, 2, "4.8", "28/02/2006", "Temporada agradável.");
        seriesController.evaluateSeasonAgain(3, 3,"4.3", "19/05/2002", "Excelente temporada, recomendo!");seriesController.evaluateSeasonAgain(20, 2, "7.8", "28/02/2006", "Temporada agradável.");
        seriesController.evaluateSeasonAgain(14, 2, "4.8", "28/02/2006", "Temporada agradável.");
        seriesController.evaluateSeasonAgain(15, 3,"4.3", "19/05/2002", "Excelente temporada, recomendo!");
        seriesController.evaluateSeasonAgain(19,3,"4.3gh", "19/05/2002", "Excelente temporada, recomendo!");

        seriesController.filterListOfSeriesByYearOfRelease("  ");
        seriesController.filterListOfSeriesByYearOfRelease("  202");
        seriesController.filterListOfSeriesByYearOfRelease("  2027");
        seriesController.filterListOfSeriesByYearOfRelease("  dsad");
        seriesController.filterListOfSeriesByYearOfRelease("  2000");

        seriesController.filterListOfSeriesByGenre("asd");
        seriesController.filterListOfSeriesByGenre("ass33d");
        seriesController.filterListOfSeriesByGenre(" ");
        seriesController.filterListOfSeriesByGenre("fanta");
        seriesController.filterListOfSeriesByGenre("fantasia");
        seriesController.filterListOfSeriesByGenre("FANTASIa         ");

        seriesController.listSeries();

        seriesController.changeSeasonViewingStatus(16, 1,"não");
        seriesController.changeSeasonViewingStatus(17, 1, "não");
        seriesController.changeSeasonViewingStatus(20, 1, "sei não");
        seriesController.changeSeasonViewingStatus(1, 1, "sim");
        seriesController.changeSeasonViewingStatus(3, 1, "sim");
        seriesController.changeSeasonViewingStatus(244, 1, "não");

        seriesController.openSeries(2);
        seriesController.openSeries(22);
        seriesController.openSeries(1);
        seriesController.openSeries(4);
        seriesController.openSeries(7);
        seriesController.openSeries(21);
        seriesController.openSeries(17);
        seriesController.openSeries(18);
    } // Teste de integração geral
}