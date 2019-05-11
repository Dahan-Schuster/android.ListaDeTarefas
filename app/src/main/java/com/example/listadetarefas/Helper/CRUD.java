package com.example.listadetarefas.Helper;

import com.example.listadetarefas.Model.Task;

import java.util.List;

public interface CRUD {

    public boolean salvar(Task task);
    public boolean atualizar(Task task);
    public boolean deletar(int taskId);
    public List<Task> listar();
    public Task recuperarPorID(int taskID);
}
