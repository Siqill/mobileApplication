package com.example.zaliczenie;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.util.ArrayList;


public class VocabularyAdapter extends ArrayAdapter<Vocabulary> {
    private Context context;
    private ArrayList<Vocabulary> vocabularies;


    public VocabularyAdapter(Context context, ArrayList<Vocabulary> vocabularies) {
        super(context, R.layout.main_layout, vocabularies);
        this.context = context;
        this.vocabularies = vocabularies;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainList = inflater.inflate(R.layout.main_layout, parent, false);

        TextView firstForm = mainList.findViewById(R.id.firstForm);
        TextView secondForm = mainList.findViewById(R.id.secondForm);
        TextView thirdForm = mainList.findViewById(R.id.thirdForm);

        Vocabulary verb = vocabularies.get(position);

        firstForm.setText(verb.getFirstForm());
        secondForm.setText(verb.getSecondForm());
        thirdForm.setText(verb.getThirdForm());

        RadioGroup radioButtons = ((Activity) context).findViewById(R.id.radio_group);
        Button test = ((Activity) context).findViewById(R.id.test_button);
        Button reset = ((Activity) context).findViewById(R.id.reset_button);

        radioButtons.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case -1:
                    for (int i = 0; i < parent.getChildCount(); i++) {
                        View row = parent.getChildAt(i);
                        row.findViewById(R.id.firstForm).setVisibility(View.VISIBLE);
                        row.findViewById(R.id.infinitive_text).setVisibility(View.GONE);
                        row.findViewById(R.id.secondForm).setVisibility(View.VISIBLE);
                        row.findViewById(R.id.paste_tense_text).setVisibility(View.GONE);
                        row.findViewById(R.id.thirdForm).setVisibility(View.VISIBLE);
                        row.findViewById(R.id.participle_text).setVisibility(View.GONE);
                    }
                    break;
                case R.id.infinitive:
                    restart(parent);
                    for (int i = 0; i < parent.getChildCount(); i++) {
                        View row = parent.getChildAt(i);
                        row.findViewById(R.id.firstForm).setVisibility(View.GONE);
                        row.findViewById(R.id.infinitive_text).setVisibility(View.VISIBLE);
                        row.findViewById(R.id.secondForm).setVisibility(View.VISIBLE);
                        row.findViewById(R.id.paste_tense_text).setVisibility(View.GONE);
                        row.findViewById(R.id.thirdForm).setVisibility(View.VISIBLE);
                        row.findViewById(R.id.participle_text).setVisibility(View.GONE);
                    }
                    break;
                case R.id.paste_tense:
                    restart(parent);
                    for (int i = 0; i < parent.getChildCount(); i++) {
                        View row = parent.getChildAt(i);
                        row.findViewById(R.id.firstForm).setVisibility(View.VISIBLE);
                        row.findViewById(R.id.infinitive_text).setVisibility(View.GONE);
                        row.findViewById(R.id.secondForm).setVisibility(View.GONE);
                        row.findViewById(R.id.paste_tense_text).setVisibility(View.VISIBLE);
                        row.findViewById(R.id.thirdForm).setVisibility(View.VISIBLE);
                        row.findViewById(R.id.participle_text).setVisibility(View.GONE);
                    }
                    break;
                case R.id.participle:
                    restart(parent);
                    for (int i = 0; i < parent.getChildCount(); i++) {
                        View row = parent.getChildAt(i);
                        row.findViewById(R.id.firstForm).setVisibility(View.VISIBLE);
                        row.findViewById(R.id.infinitive_text).setVisibility(View.GONE);
                        row.findViewById(R.id.secondForm).setVisibility(View.VISIBLE);
                        row.findViewById(R.id.paste_tense_text).setVisibility(View.GONE);
                        row.findViewById(R.id.thirdForm).setVisibility(View.GONE);
                        row.findViewById(R.id.participle_text).setVisibility(View.VISIBLE);
                    }
                    break;
            }
        });

        reset.setOnClickListener(v -> {
            radioButtons.clearCheck();
            restart(parent);
        });

        test.setOnClickListener(v -> {
            for (int i = 0; i < parent.getChildCount(); i++) {
                View row = parent.getChildAt(i);
                int checkedId = radioButtons.getCheckedRadioButtonId();

                if (checkedId != -1) {
                    EditText inputEditText;
                    String answer = "";
                    String correctAnswer = "";

                    switch (checkedId) {
                        case R.id.infinitive:
                            inputEditText = row.findViewById(R.id.infinitive_text);
                            answer = String.valueOf(inputEditText.getText());
                            correctAnswer = vocabularies.get(i).getFirstForm();
                            break;
                        case R.id.paste_tense:
                            inputEditText = row.findViewById(R.id.paste_tense_text);
                            answer = String.valueOf(inputEditText.getText());
                            correctAnswer = vocabularies.get(i).getSecondForm();
                            break;
                        case R.id.participle:
                            inputEditText = row.findViewById(R.id.participle_text);
                            answer = String.valueOf(inputEditText.getText());
                            correctAnswer = vocabularies.get(i).getThirdForm();
                            break;
                    }

                    if (answer.equals(correctAnswer)) {
                        row.setBackgroundColor(Color.GREEN);
                    } else {
                        row.setBackgroundColor(Color.RED);
                    }
                }
            }
        });
        return mainList;
    }

    public void restart(ViewGroup parent) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View row = parent.getChildAt(i);
            row.setBackgroundColor(Color.WHITE);

            EditText inf = row.findViewById(R.id.infinitive_text);
            EditText simp = row.findViewById(R.id.paste_tense_text);
            EditText part = row.findViewById(R.id.participle_text);

            inf.setText("");
            simp.setText("");
            part.setText("");
        }
    }
}

