package com.example.sqliteexample.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sqliteexample.R;
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{
    private final Context context;
    private Cursor cursor;
    private final OnDeleteClickListener onDeleteClickListener;

    public NotesAdapter(Context context, Cursor cursor, OnDeleteClickListener onDeleteClickListener) {
        this.context = context;
        this.cursor = cursor;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_notes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }
        String title = cursor.getString(1);
        String note = cursor.getString(2);
        String date = cursor.getString(3);
        holder.tvNote.setText(note);
        holder.tvDate.setText(date);
        holder.tvNoteTitle.setText(title);
        holder.btnDelete.setOnClickListener(v -> onDeleteClickListener.onDeleteClick(note));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    public interface OnDeleteClickListener{
        void onDeleteClick(String Note);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNote;
        TextView tvDate, tvNoteTitle;

        ImageButton btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNote = itemView.findViewById(R.id.adminNoteDetail);
            tvDate = itemView.findViewById(R.id.adminNoteDate);
            tvNoteTitle = itemView.findViewById(R.id.adminNoteTitle);
            btnDelete = itemView.findViewById(R.id.adminNotesDelete);
        }
    }
}
