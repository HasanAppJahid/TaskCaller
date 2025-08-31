package com.apptech.taskcaller.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apptech.taskcaller.ModelClass.TaskModel;
import com.apptech.taskcaller.R;

import java.util.List;

public class TaskCallerAdapter extends RecyclerView.Adapter<TaskCallerAdapter.ViewHolder> {

    private Context context;
    private List<TaskModel> taskList;
    private OnTaskActionListener listener;

    public TaskCallerAdapter(Context context, List<TaskModel> taskList, OnTaskActionListener listener) {
        this.context = context;
        this.taskList = taskList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskModel task = taskList.get(position);

        holder.tvTaskTitle.setText(task.getTitle());
        holder.tvTaskDateTime.setText(task.getDate() + " • " + task.getTime());

        // Show TextView or Icon based on completion
        if (task.isCompleted()) {
            holder.btnCompleteText.setVisibility(View.GONE);
            holder.btnCompleteIcon.setVisibility(View.VISIBLE);
        } else {
            holder.btnCompleteText.setVisibility(View.VISIBLE);
            holder.btnCompleteIcon.setVisibility(View.GONE);
        }

        // Click on Complete TextView
        holder.btnCompleteText.setOnClickListener(v -> {
            task.setCompleted(true);
            holder.btnCompleteText.setVisibility(View.GONE);
            holder.btnCompleteIcon.setVisibility(View.VISIBLE);
            listener.onComplete(task, position);
        });

        // Long press to show Edit/Delete
        holder.itemView.setOnLongClickListener(v -> {
            holder.hiddenActions.setVisibility(holder.hiddenActions.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            return true;
        });

        // Edit/Delete click
        holder.btnEdit.setOnClickListener(v -> listener.onEdit(task, position));
        holder.btnDelete.setOnClickListener(v -> listener.onDelete(task, position));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTaskTitle, tvTaskDateTime, btnCompleteText;
        ImageView btnCompleteIcon, btnEdit, btnDelete;
        LinearLayout hiddenActions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTaskTitle = itemView.findViewById(R.id.tvTaskTitle);
            tvTaskDateTime = itemView.findViewById(R.id.tvTaskDateTime);
            btnCompleteText = itemView.findViewById(R.id.btnCompleteText);
            btnCompleteIcon = itemView.findViewById(R.id.btnCompleteIcon);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            hiddenActions = itemView.findViewById(R.id.hiddenActions);
        }
    }

    public interface OnTaskActionListener {
        void onComplete(TaskModel task, int position);
        void onEdit(TaskModel task, int position);
        void onDelete(TaskModel task, int position);
    }
}
