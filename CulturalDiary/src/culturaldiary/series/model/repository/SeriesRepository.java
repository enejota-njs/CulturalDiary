package culturaldiary.series.model.repository;

import culturaldiary.series.model.SeriesModel;

import java.util.ArrayList;

public class SeriesRepository {
    private ArrayList<SeriesModel> listOfSeries = new ArrayList<SeriesModel>();

    public void addSeries(SeriesModel series) {
        listOfSeries.add(series);
    }

    public ArrayList<SeriesModel> getListOfSeries() {
        return listOfSeries;
    }

    public void setListOfSeries(ArrayList<SeriesModel> listOfSeries) {
        this.listOfSeries = listOfSeries;
    }
}