package team6.g13it18k.database;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.SQLiteGdxException;

import java.util.ArrayList;
import java.util.List;

public class ScoresTable implements Table {

    private Database dbHandler;

    private static final String TABLE_NAME = "scores";

    public class Record {
        public int id;
        int level;
        int stage;
        int experience;

        Record(int id, int level, int stage, int experience) {
            this.id = id;
            this.level = level;
            this.stage = stage;
            this.experience = experience;
        }

        @Override
        public String toString() {
            return "Уровень: " + level +
                    "\nЭтап: " + stage +
                    "\nОпыт: " + experience;
        }
    }


    public ScoresTable() {
        Gdx.app.log("ScoresTable", "creation started");
        dbHandler = new AppDatabase(TABLE_NAME, this).getDbHandler();
        Gdx.app.log("ScoresTable", "created successfully");
    }

    @Override
    public String getCreateFields() {
        return "(" +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`level_num` INTEGER NOT NULL, " +
                "`stage_num` INTEGER NOT NULL, " +
                "`experience_num` INTEGER NOT NULL" +
                ")";
    }

    public List<Record> getDataFromFields(){
        List<Record> records = new ArrayList<>();

        try {
            DatabaseCursor cursor = dbHandler.rawQuery("SELECT * FROM " + TABLE_NAME);

            while (cursor.next()) {
                records.add(new Record(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3)
                ));
            }

        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }

        return records;
    }

    public void dispose(){
        try {
            dbHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
        dbHandler = null;
        Gdx.app.log("ScoresTable", "dispose");
    }
}
