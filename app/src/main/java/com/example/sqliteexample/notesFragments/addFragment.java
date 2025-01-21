package com.example.sqliteexample.notesFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqliteexample.R;
import com.example.sqliteexample.adapters.NotesAdapter;
import com.example.sqliteexample.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class addFragment extends Fragment {

    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        databaseHelper = new DatabaseHelper(requireActivity());

        EditText noteTitle = view.findViewById(R.id.note_title);
        EditText noteDescription = view.findViewById(R.id.note_desc);
        Button addNote = view.findViewById(R.id.add_notes_btn);
        Date date = new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM,yyyy");
        String formattedDate = formatter.format(date);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String notesTitle = noteTitle.getText().toString();
                String notesDesc = noteDescription.getText().toString();
                boolean setData = databaseHelper.setNoteData(notesDesc,formattedDate,notesTitle);
                if(setData){
                    Toast.makeText(requireActivity(), "Note has been added", Toast.LENGTH_SHORT).show();
                    noteTitle.setText("");
                    noteDescription.setText("");
                }else {
                    Toast.makeText(requireActivity(), "Error while adding note", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}