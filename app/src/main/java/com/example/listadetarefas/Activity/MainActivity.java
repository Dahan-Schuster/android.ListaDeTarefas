package com.example.listadetarefas.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.listadetarefas.Adapter.AdapterTasks;
import com.example.listadetarefas.Model.TarefasDataBase;
import com.example.listadetarefas.Model.Task;
import com.example.listadetarefas.R;
import com.example.listadetarefas.events.RecyclerItemClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerTasks;
    private AdapterTasks adapterTasks;
    private ArrayList<Task> tasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recuperarDadosDoBanco();

        recyclerTasks = findViewById(R.id.recyclerTasks);
        adapterTasks = new AdapterTasks(tasks);

        configurarRecycler();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaAddOrEditTask(0);
            }

        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        atualizarDados();
    }

    private void atualizarDados() {
        recuperarDadosDoBanco();
        atualizarAdapterItemList();
    }

    private void atualizarAdapterItemList() {
        adapterTasks = new AdapterTasks(tasks);
        recyclerTasks.setAdapter(adapterTasks);
    }

    private void recuperarDadosDoBanco() {
        TarefasDataBase.conectar(getApplicationContext());
        tasks = Task.recuperarTarefas();
    }

    private void configurarRecycler() {
        recyclerTasks.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerTasks.setLayoutManager(layoutManager);
        recyclerTasks.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        recyclerTasks.setAdapter(adapterTasks);

        recyclerTasks.addOnItemTouchListener( new RecyclerItemClickListener(getApplicationContext(), recyclerTasks, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                       int taskId = tasks.get(position).getID();
                       abrirTelaAddOrEditTask(taskId);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        int taskId = tasks.get(position).getID();
                        confimarExclusao(taskId);
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                })
        );
    }

    private void confimarExclusao(final int taskId) {
        AlertDialog dialogExcluir;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.excluir));
        builder.setMessage(R.string.confirmar_excluir);
        builder.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                excluir(taskId);
            }
        });

        builder.setNegativeButton(getString(R.string.nao), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), getString(R.string.cancelada), Toast.LENGTH_LONG).show();
            }
        });


        dialogExcluir = builder.create();
        dialogExcluir.show();

    }

    private void excluir(int taskId) {
        try {
            Task.deletar(taskId);
            atualizarDados();
            Toast.makeText(getApplicationContext(), getString(R.string.feito), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getString(R.string.erro_ao_excluir), Toast.LENGTH_LONG).show();
        }
    }

    private void abrirTelaAddOrEditTask(int taskId) {
        Intent intent = new Intent(getApplicationContext(), AddOrEditTaskActivity.class);
        intent.putExtra("taskId", taskId);
        startActivity(intent);
    }


}
