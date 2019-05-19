package com.example.gjg;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spannable text = new SpannableString("Notes");
        text.setSpan(new ForegroundColorSpan(0xED8BC34A), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(text);

        final Context context = this;
        Database database = new Database(this);
        ListView listView = findViewById(R.id.listView);
        final List<String> stringList = database.fetchAll();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_list_item_1,
                stringList
        );
        listView.setAdapter(arrayAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Database database = new Database(context);
                database.deleteNote(stringList.get(position));
                stringList.remove(position);
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Note Deleted", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    public void addNotes(View view){
        Intent intent = new Intent(MainActivity.this, Notes.class);
        startActivity(intent);
    }
}
