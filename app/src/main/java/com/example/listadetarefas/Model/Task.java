package com.example.listadetarefas.Model;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class Task extends Object{

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

}
