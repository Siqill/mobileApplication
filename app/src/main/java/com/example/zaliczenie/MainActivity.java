package com.example.zaliczenie;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Vocabulary> vocabularies;
    private ListView mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainList = findViewById(R.id.mainList);

        vocabularies = new ArrayList<>();

        vocabularies.add(new Vocabulary("become", "became", "become"));
        vocabularies.add(new Vocabulary("begin", "began", "begun"));
        vocabularies.add(new Vocabulary("break", "broke", "broken"));
        vocabularies.add(new Vocabulary("bring", "brought", "brought"));
        vocabularies.add(new Vocabulary("build", "built", "built"));

        VocabularyAdapter adapter = new VocabularyAdapter(this, vocabularies);

        mainList.setAdapter(adapter);
    }
}