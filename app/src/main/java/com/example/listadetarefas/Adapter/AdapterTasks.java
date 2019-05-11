package com.example.listadetarefas.Adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.listadetarefas.Model.Task;
import com.example.listadetarefas.R;

import java.util.ArrayList;

public class AdapterTasks extends RecyclerView.Adapter<AdapterTasks. ViewHolderTasks> {


    /** ViewHolder */
    public class ViewHolderTasks extends RecyclerView.ViewHolder {

        public TextView titulo;
        public TextView tag;
        /** Construtor */
        public ViewHolderTasks(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.textTitulo);
            tag = itemView.findViewById(R.id.textTag);
        }
    }



    /** Atributos */
    private ArrayList<Task> tasks;

    /** Construtor */
    public AdapterTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Método chamado ao criar o viewHolder, responsável por configurar as visualizações
     * Deve comunicar-se com um arquivo XML
     * @param parent
     * @param viewTipe
     * @return
     */
    @NonNull
    @Override
    public ViewHolderTasks onCreateViewHolder(@NonNull ViewGroup parent, int viewTipe) {

        /**
         * Converte o layout XML em um objeto View a partir da classe LayoutInflater
         */
        View layout_filmes = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_tasks, parent, false);


        return new ViewHolderTasks(layout_filmes);
    }


    /**
     * Método chamado após a criação do view holder sempre que este se torna visível novamente.
     * Responsável por atualizar os dados do view holder sem recriá-lo
     * @param viewHolderTasks
     * @param position
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderTasks viewHolderTasks, int position) {
        Task task = tasks.get(position);

        String title = task.getTitle();
        String tag = task.getTag();

        viewHolderTasks.titulo.setText(title);
        viewHolderTasks.tag.setText(tag);
    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }

}
