package com.example.gjg;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Notes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        getSupportActionBar().hide();
    }

    public void saveNote(View view){
        EditText editText = findViewById(R.id.editText);
        String notesData = editText.getText().toString();
        if(!notesData.trim().isEmpty()) {
            Database db = new Database(this);
            NotesFields notesFields = new NotesFields(notesData);
            db.addNote(notesFields);
        }

        Intent intent = new Intent(Notes.this, MainActivity.class);
        startActivity(intent);
    }

    public void cancelNote(View view){
        Intent intent = new Intent(Notes.this, MainActivity.class);
        startActivity(intent);
    }
}
