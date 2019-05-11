package com.example.listadetarefas.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listadetarefas.Helper.TaskDAO;
import com.example.listadetarefas.Model.Task;
import com.example.listadetarefas.R;

public class AddOrEditTaskActivity extends AppCompatActivity {

    private TextInputEditText editTitulo;
    private TextInputEditText editTag;
    private int taskId;
    private boolean taskIsDone= false;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);

        editTitulo = findViewById(R.id.editTextTitulo);
        editTag = findViewById(R.id.editTextTag);

        taskId  = getIntent().getExtras().getInt("taskId");

       // atualizarEditTexts();

    }
    /*
    private void atualizarEditTexts() {
        try {
            if (taskId > 0) {



                editTitulo.setText(task.getTitle());
                editTag.setText(task.getTag());
            }
        } catch (Exception e) {
            // Handle ?
        }

    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_nova_tarefa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salvar:
                salvar();
                return true;
            case R.id.excluir:
                excluir();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void salvar() {
        try {

            TaskDAO taskDAO = new TaskDAO(getApplicationContext());

            String titulo = editTitulo.getText().toString();
            String tag = editTag.getText().toString();

            Task task = new Task(taskId, titulo, tag);

            taskDAO.salvar(task);

            doDefaultToast(getApplicationContext(), getString(R.string.feito));
            finish();

        } catch (Exception e) {
            doDefaultToast(getApplicationContext(), getString(R.string.erro_ao_salvar));
        }
    }

    private void excluir() {
        try {
            TaskDAO taskDAO = new TaskDAO(getApplicationContext());

            taskDAO.deletar(taskId);

            doDefaultToast(getApplicationContext(), getString(R.string.feito));
            finish();
        } catch (Exception e) {
            doDefaultToast(getApplicationContext(), getString(R.string.erro_ao_excluir));
        }
    }

    public static void doDefaultToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
