package team6.g13it18k.database;

import java.util.List;

public interface Table {

    void addData();
    List<ScoresTable.Record> getDataFromFields();
    void dispose();

}