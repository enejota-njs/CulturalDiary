package culturaldiary.series.series;

import java.util.ArrayList;

public class SeriesRepository {
    private ArrayList<SeriesModel> listOfSeries = new ArrayList<SeriesModel>(); //  Lista de séries

    public void addSeries(SeriesModel series) {
        listOfSeries.add(series);
    } // Adiciona série à lista

    // Método Getter e Setter
    public ArrayList<SeriesModel> getListOfSeries() {
        return listOfSeries;
    }

    public void setListOfSeries(ArrayList<SeriesModel> listOfSeries) {
        this.listOfSeries = listOfSeries;
    }
}
