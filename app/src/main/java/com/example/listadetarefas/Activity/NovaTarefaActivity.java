package com.example.listadetarefas.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listadetarefas.Model.Task;
import com.example.listadetarefas.R;

public class NovaTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTitulo;
    private TextInputEditText editTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);

        editTitulo = findViewById(R.id.editTextTitulo);
        editTag = findViewById(R.id.editTextTag);

    }

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void salvar() {
        try {
            String titulo = editTitulo.getText().toString();
            String tag = editTag.getText().toString();
            boolean isFeita = false;

            Task task = new Task(0, isFeita, titulo, tag);
            task.adicionar();

            Toast.makeText(getApplicationContext(), getString(R.string.tarefa_salva), Toast.LENGTH_LONG);
            abrirTelaPrincipal();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getString(R.string.erro_ao_salvar), Toast.LENGTH_LONG);
        }
    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
