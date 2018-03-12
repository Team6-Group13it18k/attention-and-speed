package team6.g13it18k.database;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

public class AppDatabase {

    private Database dbHandler;

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_SCORES = "CREATE TABLE IF NOT EXISTS `scores` (" +
            "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "`level_num` INTEGER NOT NULL, " +
            "`stage_num` INTEGER NOT NULL, " +
            "`experience_num` INTEGER NOT NULL" +
            ")";

    public AppDatabase() {
        Gdx.app.log("AppDatabase", "Start create tables");
        dbHandler = DatabaseFactory.getNewDatabase(DATABASE_NAME, DATABASE_VERSION, null, null);
        dbHandler.setupDatabase();
        try {
            dbHandler.openOrCreateDatabase();
            dbHandler.execSQL(CREATE_TABLE_SCORES);
            Gdx.app.log("AppDatabase", "End created table 'scores'");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
            Gdx.app.log("AppDatabase", "Error created tables");
        }
        Gdx.app.log("AppDatabase", "End success created tables");
    }

    public Database getDbHandler() {
        return dbHandler;
    }
}
