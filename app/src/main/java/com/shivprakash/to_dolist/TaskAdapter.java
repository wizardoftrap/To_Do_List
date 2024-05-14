package com.shivprakash.to_dolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Context mContext;
    private List<MainActivity.Data> mTaskList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
        void onCheckboxClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public TaskAdapter(Context context, List<MainActivity.Data> taskList) {
        mContext = context;
        mTaskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.task_card_layout, parent, false);
        return new TaskViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        MainActivity.Data currentItem = mTaskList.get(position);

        holder.mTextViewName.setText(currentItem.getName());
        holder.mTextViewDate.setText("Date: "+currentItem.getDate());
        holder.mTextViewTime.setText("Time: "+currentItem.getTime());
        holder.text_category.setText("Category: "+currentItem.getCategory());
        holder.text_priority.setText("Priority: "+currentItem.getPriority());
        holder.text_notes.setText("Note: "+currentItem.getNotes());
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewName;
        public TextView mTextViewDate;
        public TextView mTextViewTime;
        public TextView text_category;
        public TextView text_priority;
        public TextView text_notes;
        public CheckBox mCheckBox;
        public Button mButtonEdit;
        public Button mButtonDelete;

        public TaskViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.text_name);
            mTextViewDate = itemView.findViewById(R.id.text_date);
            mTextViewTime = itemView.findViewById(R.id.text_time);
            text_category = itemView.findViewById(R.id.text_category);
            text_priority = itemView.findViewById(R.id.text_priority);
            text_notes= itemView.findViewById(R.id.text_notes);


            mCheckBox = itemView.findViewById(R.id.check_box);
            mButtonEdit = itemView.findViewById(R.id.btn_edit);
            mButtonDelete = itemView.findViewById(R.id.btn_delete);

            mButtonEdit.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onEditClick(position);
                    }
                }
            });

            mButtonDelete.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position);
                    }
                }
            });

            mCheckBox.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onCheckboxClick(position);
                    }
                }
            });
        }
    }
}
