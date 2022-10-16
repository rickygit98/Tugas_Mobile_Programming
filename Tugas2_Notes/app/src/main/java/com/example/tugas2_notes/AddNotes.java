package com.example.tugas2_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import io.realm.Realm;

public class AddNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        EditText titleInput = findViewById(R.id.titleInput);
        EditText descInput = findViewById(R.id.descriptionInput);
        MaterialButton saveBtn = findViewById(R.id.saveBtn);
        MaterialButton hearBtn = findViewById(R.id.hearBtn);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleInput.getText().toString();
                String description = descInput.getText().toString();
                long createdAt = System.currentTimeMillis();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setTitle(title);
                note.setDescription(description);
                note.setCreatedAt(createdAt);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"Note Saved",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        hearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"id ID");
                try {
                    descInput.setText("");
                    startActivityForResult(intent,1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }

    protected void onActivityResult(int RequestCode, int ResultCode, Intent intent) {
        super.onActivityResult(RequestCode, ResultCode, intent);

        EditText descInput = findViewById(R.id.descriptionInput);

        switch (RequestCode){
            case 1: {
                if (ResultCode == RESULT_OK && intent != null){
                    ArrayList<String> outputSpeech = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    descInput.setText(outputSpeech.get(0));
                }
                break;
            }
        }
    }

}