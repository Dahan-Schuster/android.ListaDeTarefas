package com.example.listadetarefas.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TarefasDataBase {

    public static final String APP_DATABASE_NAME = "AppListaDeTarefas";
    public static final String TABLE_TASKS_NAME  = "tarefas";

    public static SQLiteDatabase database;

    public static void conectar(Context context) {
        database = context.openOrCreateDatabase(APP_DATABASE_NAME, context.MODE_PRIVATE, null);
        createTables();
    }

    public static void createTables() {
        database.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TASKS_NAME + " (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "feita INT(1)," +
                "titulo varchar(255)," +
                "tag varchar (255))");
    }






}
