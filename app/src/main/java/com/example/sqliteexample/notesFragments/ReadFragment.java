package com.example.sqliteexample.notesFragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sqliteexample.R;
import com.example.sqliteexample.adapters.NotesAdapter;
import com.example.sqliteexample.database.DatabaseHelper;

public class ReadFragment extends Fragment implements NotesAdapter.OnDeleteClickListener {
    private DatabaseHelper databaseHelper;

    private NotesAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.adminNotesForNew);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        databaseHelper = new DatabaseHelper(requireActivity());
        Cursor cursor = databaseHelper.getNoteData();
        adapter = new NotesAdapter(requireActivity(), cursor, this);
        recyclerView.setAdapter(adapter);

        return view;
    }
    public void onDeleteClick(String note){
        databaseHelper.deleteNoteById(note);

        Cursor newCursor = databaseHelper.getNoteData();
        adapter.swapCursor(newCursor);
    }
}