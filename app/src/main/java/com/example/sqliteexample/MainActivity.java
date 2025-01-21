package com.example.sqliteexample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.sqliteexample.notesFragments.ReadFragment;
import com.example.sqliteexample.notesFragments.addFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button readNotes = findViewById(R.id.read_notes);
        Button addNotes = findViewById(R.id.add_notes);
        NavigateToListFragment(new ReadFragment());
        readNotes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                readNotes.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.sky_500));
                addNotes.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
                NavigateToListFragment(new ReadFragment());
            }
        });

        addNotes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                addNotes.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.sky_500));
                readNotes.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
                NavigateToListFragment(new addFragment());
            }
        });


    }
    private void NavigateToListFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).addToBackStack(null).commit();
    }
}