package com.example.listadetarefas.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.listadetarefas.Adapter.AdapterTasks;
import com.example.listadetarefas.Model.TarefasDataBase;
import com.example.listadetarefas.Model.Task;
import com.example.listadetarefas.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerTasks;
    private ArrayList<Task> tasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TarefasDataBase.conectar(getApplicationContext());
        tasks = Task.recuperarTarefas();

        recyclerTasks = findViewById(R.id.recyclerTasks);
        configurarRecycler();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaNovaTarefa();
            }

            private void abrirTelaNovaTarefa() {
                Intent intent = new Intent(getApplicationContext(), NovaTarefaActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

    }

    private void configurarRecycler() {
        recyclerTasks.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerTasks.setLayoutManager(layoutManager);

        AdapterTasks adapterTasks = new AdapterTasks(tasks);
        recyclerTasks.setAdapter(adapterTasks);
    }


}
