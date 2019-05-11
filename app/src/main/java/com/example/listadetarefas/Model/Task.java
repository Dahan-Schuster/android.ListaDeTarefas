package com.example.listadetarefas.Model;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class Task {

    private int ID;
    private String title;
    private String tag;


    public Task(int id, String title, String tag) {
        this.ID = id;
        this.title = title;
        this.tag = tag;
    }

    public int getID() {
        return ID;
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
        try {

            String consulta = "SELECT * FROM " + TarefasDataBase.TABLE_TASKS_NAME;
            Cursor cursor = TarefasDataBase.database.rawQuery(consulta, null);

            if (cursor.getCount() == 0){

                tarefas.add(new Task(0, "Adicione novas tarefas!", "ToDo"));

            } else {
                cursor.moveToFirst();
                while (cursor != null) {
                    tarefas.add(instanciarTaskPorTupla(cursor));
                    cursor.moveToNext();
                }
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

        int columnTituloPosition = cursor.getColumnIndex("titulo");
        String title = cursor.getString(columnTituloPosition);

        int columnTagPosition = cursor.getColumnIndex("tag");
        String tag = cursor.getString(columnTagPosition);

        return new Task(id, title, tag);
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

    public boolean adicionarOuAtualizar() {
        if (this.getID() > 0)
            return atualizar(this.getID());

        return adicionar();
    }

    private boolean adicionar() {
        try {
            String sql = String.format("INSERT INTO " + TarefasDataBase.TABLE_TASKS_NAME + "(titulo, tag) VALUES('%s', '%s')",
                    this.title,
                    this.tag);
            TarefasDataBase.database.execSQL(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean atualizar(int id) {
        try {
            String sql = String.format("UPDATE " + TarefasDataBase.TABLE_TASKS_NAME + " SET titulo = '%s', tag = '%s' WHERE ID = %d",
                    this.title,
                    this.tag,
                    id);
            TarefasDataBase.database.execSQL(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean deletar(int id) {
        try {
            String sql = String.format("DELETE FROM " + TarefasDataBase.TABLE_TASKS_NAME + " WHERE ID = %d", id);
            TarefasDataBase.database.execSQL(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
