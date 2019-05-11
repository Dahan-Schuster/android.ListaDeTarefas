package com.example.listadetarefas.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String APP_DATABASE_NAME = "AppListaDeTarefas";
    public static final String TABLE_TASKS_NAME  = "tarefas";

    private static final String sqlCreateTableTarefas = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS_NAME + " (" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "titulo varchar(255)," +
            "tag varchar (255))";

    public DbHelper(Context context) {
        super(context, APP_DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(sqlCreateTableTarefas);
            Log.i("INFO DB", "Sucesso ao criar a tabela");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar a tabela: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            db.execSQL(sqlCreateTableTarefas);
            Log.i("INFO DB", "Sucesso ao atualizar o APP");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao atualizar o APP: " + e.getMessage());
        }
    }
}
