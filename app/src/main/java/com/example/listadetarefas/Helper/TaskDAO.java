package com.example.listadetarefas.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listadetarefas.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements CRUD {

    private SQLiteDatabase writer;
    private SQLiteDatabase reader;

    public TaskDAO(Context context) {
        DbHelper db = new DbHelper(context);
        writer = db.getWritableDatabase();
        reader = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Task task) {

        try {
            ContentValues values = new ContentValues();
            values.put("titulo", task.getTitle());
            values.put("tag", task.getTag());

            writer.insert(DbHelper.TABLE_TASKS_NAME, "tag", values);
        } catch (Exception e) {
            Log.e("DATABASE", "Erro ao inserir dados na tabela " + DbHelper.TABLE_TASKS_NAME + ": " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Task task) {
        return false;
    }

    @Override
    public boolean deletar(int taskId) {
        return false;
    }


    @Override
    public ArrayList<Task> listar() {

        ArrayList<Task> tarefas = new ArrayList<>();

        String sql = "SELECT ID FROM " + DbHelper.TABLE_TASKS_NAME + ";";
        try {

            Cursor cursor = reader.rawQuery(sql, null);

            cursor.moveToFirst();
            while (cursor.moveToNext()) {

                int id = cursor.getInt(cursor.getColumnIndex("ID"));
                String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                String tag = cursor.getString(cursor.getColumnIndex("tag"));

                Task tarefa = new Task(id, titulo, tag);
                tarefas.add(tarefa);
            }

            return tarefas;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Task recuperarPorID(int taskID) {

        try {

            //reader.execSQL("SE");
            return null;

        } catch (Exception e) {
            return null;
        }
    }
}
