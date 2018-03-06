package com.mygdx.grisacius.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mygdx.grisacius.Constantes;

import java.sql.Timestamp;


public class GrisaciusDataBaseAndroid extends GrisaciusDataBase {
    private GrisaciusAndroidOpenHelper openHelper;
    private SQLiteDatabase database;

    public GrisaciusDataBaseAndroid(Context context) {
        super();
        openHelper = new GrisaciusAndroidOpenHelper(context);
        database = openHelper.getWritableDatabase();
    }


    @Override
    public GrisaciusScores getCurrentGame() {
        String[] fields = {getStartdateFieldName(), getScoreFieldName(), getEnddateFieldName()};
        Cursor c = database.query(getScoreTablename(), fields, getEnddateFieldName() + " is null", null, null, null, getStartdateFieldName() + " DESC");
        if (c.getCount() == 0) {
            //Si no se obtiene un juego actual, se crea uno nuevo con end Date a null
            Log.d("Getting Current Game", "New Game was created");
            ContentValues cv = new ContentValues();
            Timestamp startTime = new Timestamp(System.currentTimeMillis());
            cv.put(getStartdateFieldName(), startTime.toString());
            cv.put(getScoreFieldName(), "0");
            database.insert(getScoreTablename(), null, cv);
            return new GrisaciusScores(startTime, Constantes.SCORE, null);
        }
        //Sabemos que solo debería haber una partida en curso
        Log.d("Getting Current Game", "Old Game was reused");
        c.moveToFirst();

        return new GrisaciusScores(Timestamp.valueOf(c.getString(0)), c.getInt(1), (c.getString(2) == null ? null : Timestamp.valueOf(c.getString(2))));
    }

    @Override
    public void endCurrentGame(int score) {
        GrisaciusScores currentGame = getCurrentGame();
        currentGame.setScore(score);
        currentGame.setGameEndDate(new Timestamp(System.currentTimeMillis()));
        Log.d("ENDING GAME WITH SCORE", Integer.toString(score));


        ContentValues values = new ContentValues();
        values.put(getScoreFieldName(), currentGame.getScore());
        values.put(getEnddateFieldName(), currentGame.getGameEndDate().toString());
        String[] args = {currentGame.getGameStartDate().toString()};
        Log.d("SAVE RESULT", Integer.toString(database.update(getScoreTablename(), values, getStartdateFieldName() + "= ?", args)));
    }

    @Override
    public void saveCurrentGame(int score) {
        GrisaciusScores currentGame = getCurrentGame();
        currentGame.setScore(score);
        Log.d("SAVING GAME WITH SCORE", Integer.toString(score));

        ContentValues values = new ContentValues();
        values.put(getScoreFieldName(), currentGame.getScore()); //En este caso no se actualiza la fecha de fin
        String[] args = {currentGame.getGameStartDate().toString()};
        Log.d("SAVE RESULT", Integer.toString(database.update(getScoreTablename(), values, getStartdateFieldName() + "= ?", args)));
    }

    @Override
    public GrisaciusScores[] getTop3Games() {
        String[] fields = {getStartdateFieldName(), getScoreFieldName(), getEnddateFieldName()};
        Cursor c = database.query(getScoreTablename(), fields, getEnddateFieldName() + " is not null", null, null, null, getScoreFieldName() + " DESC", "3");
        //Si son menos de tres, no vale la pena obtenerlos.

        //Sabemos que solo debería haber una partida en curso.
        c.moveToFirst();

        //Rellenamos el array, con las tres últimas puntuaciones
        GrisaciusScores[] ret = new GrisaciusScores[3];
        for (int i = 0; i < 3; i++) {
            ret[i] = new GrisaciusScores(Timestamp.valueOf(c.getString(0)), c.getInt(1), (c.getString(2) == null ? null : Timestamp.valueOf(c.getString(2))));
            c.moveToNext();
        }

        return ret;

    }
}
