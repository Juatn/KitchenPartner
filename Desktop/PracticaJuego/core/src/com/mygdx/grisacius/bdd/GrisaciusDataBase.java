package com.mygdx.grisacius.bdd;


//General class that needs to be implemented on Android and Desktop Applications
public abstract class GrisaciusDataBase {
    private static int databaseVersion;
    private static String databaseName;
    private static String SCORE_TABLENAME;
    private static String SCORE_FIELD;
    private static String STARTDATE_FIELD;
    private static String ENDDATE_FIELD;
    private static String databaseCreationQuery;
    private static String databaseUpdateQuery;

    //-----------------------------------------------------------------------------------
    //Métodos para que usen las clases del juego
    //-----------------------------------------------------------------------------------

    public abstract GrisaciusScores getCurrentGame();

    public abstract void saveCurrentGame(int score);

    public abstract void endCurrentGame(int score);

    public abstract GrisaciusScores[] getTop3Games();

    //-----------------------------------------------------------------------------------
    //Métodos para que usen los gestores de bases de datos específicos de cada plataforma
    //-----------------------------------------------------------------------------------

    public GrisaciusDataBase() {
        databaseVersion = 1;//
        databaseName = "GrisaciusDB";//
        SCORE_TABLENAME = "totalScore";
        SCORE_FIELD = "score";
        STARTDATE_FIELD = "gameStartDate";
        ENDDATE_FIELD = "gameEndDate";
        databaseCreationQuery = "CREATE TABLE " + SCORE_TABLENAME + " (" +
                STARTDATE_FIELD + " TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                SCORE_FIELD + " INT DEFAULT 0 ," +
                ENDDATE_FIELD + " TIMESTAMP DEFAULT NULL" +
                ");";
        databaseUpdateQuery = "";
    }

    public static int getDatabaseVersion() {
        return databaseVersion;
    }

    public static String getScoreFieldName() {
        return SCORE_FIELD;
    }

    public static String getStartdateFieldName() {
        return STARTDATE_FIELD;
    }

    public static String getEnddateFieldName() {
        return ENDDATE_FIELD;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static String getDatabaseCreationQuery() {
        return databaseCreationQuery;
    }

    public static String getDatabaseUpdateQuery() {
        return databaseUpdateQuery;
    }

    public static String getScoreTablename() {
        return SCORE_TABLENAME;
    }


}