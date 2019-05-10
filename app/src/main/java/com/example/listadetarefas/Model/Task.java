package com.example.listadetarefas.Model;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class Task {

    private int ID;
    private boolean isDone;
    private String title;
    private String tag;


    public Task(int id, boolean isDone, String title, String tag) {
        this.ID = id;
        this.isDone = isDone;
        this.title = title;
        this.tag = tag;
    }

    public int getID() {
        return ID;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    /**Recupera uma lista com todas as tarefas salvas
     *
     * @return
     */
    public static ArrayList<Task> recuperarTarefas() {

        ArrayList<Task> tarefas = new ArrayList<>();
        tarefas.add(new Task(0, false, "Adicione novas tarefas!", "ToDo"));
        try {

            String consulta = "SELECT * FROM " + TarefasDataBase.TABLE_TASKS_NAME;
            Cursor cursor = TarefasDataBase.database.rawQuery(consulta, null);

            cursor.moveToFirst();
            tarefas.clear();
            while (cursor != null) {
                tarefas.add(instanciarTaskPorTupla(cursor));

                    cursor.moveToNext();
            }

        } catch (Exception e) {
            // Handle ?
        }
        finally {
            return tarefas;
        }
    }

    /** Retorna uma <i>task</i> instaciada com os dados do registro<br>
     * atual do Cursor enviado como parâmetro
     *
     * @param cursor
     * @return
     */
    private static Task instanciarTaskPorTupla(Cursor cursor) {

        int columnIdPosition = cursor.getColumnIndex("id");
        int id = cursor.getInt(columnIdPosition);

        int columnFeitaPosition = cursor.getColumnIndex("feita");
        boolean isDone = cursor.getInt(columnFeitaPosition) == 1 ? true : false;

        int columnTituloPosition = cursor.getColumnIndex("titulo");
        String title = cursor.getString(columnTituloPosition);

        int columnTagPosition = cursor.getColumnIndex("tag");
        String tag = cursor.getString(columnTagPosition);

        return new Task(id, isDone, title, tag);
    }

    /** Recupera uma <i>task</i> individual com o id enviado,<br> ou <i>null</i> se nenhuma for encontrada
     *
     * @param id
     * @return
     */
    public static Task recuperarTarefaPorId(int id) {
        try {
            String consulta = "SELECT * FROM " + TarefasDataBase.TABLE_TASKS_NAME + " WHERE id = " + id;
            Cursor cursor = TarefasDataBase.database.rawQuery(consulta, null);

            cursor.moveToFirst();

            if (cursor != null)
                return instanciarTaskPorTupla(cursor);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean adicionar() {

        if (this.getID() > 0)
            return atualizar(this.getID());

        try {
            String sql = String.format("INSERT INTO " + TarefasDataBase.TABLE_TASKS_NAME + "(feita, titulo, tag) VALUES(%s, '%s', '%s')",
                    (this.isDone ? "1" : "0"),
                    this.title,
                    this.tag);
            TarefasDataBase.database.execSQL(sql);
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    public boolean deletar(int id) {
        try {
            String sql = String.format("DELETE FROM" + TarefasDataBase.TABLE_TASKS_NAME + " WHERE ID = %d", id);
            TarefasDataBase.database.execSQL(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean atualizar(int id) {
        try {
            String sql = String.format("UPDATE " + TarefasDataBase.TABLE_TASKS_NAME + " SET feita = %s, titulo = '%s', tag '%s'",
                    (this.isDone ? "1" : "0"),
                    this.title,
                    this.tag);
            TarefasDataBase.database.execSQL(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
