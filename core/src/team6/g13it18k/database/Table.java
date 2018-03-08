package team6.g13it18k.database;

import java.util.List;

public interface Table {

    List<ScoresTable.Record> getDataFromFields();
	String getCreateFields();
	void dispose();

}