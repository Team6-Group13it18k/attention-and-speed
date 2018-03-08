package team6.g13it18k.database;

import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

class AppDatabase {

    private Database dbHandler;

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    AppDatabase(String tableName, Table table) {
        String databaseCreate = "CREATE TABLE IF NOT EXISTS `" + tableName + "` " + table.getCreateFields();
        dbHandler = DatabaseFactory.getNewDatabase(DATABASE_NAME, DATABASE_VERSION, databaseCreate, null);
        dbHandler.setupDatabase();
        try {
            dbHandler.openOrCreateDatabase();
            dbHandler.execSQL(databaseCreate);
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }

    Database getDbHandler() {
        return dbHandler;
    }
}
