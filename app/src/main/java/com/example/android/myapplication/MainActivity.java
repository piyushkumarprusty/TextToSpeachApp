package com.example.android.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    EditText e1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.e1);
        b1 = findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOn();
            }
        });
    }
// Validation and checking

    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Toast.makeText(getApplicationContext(), "Language Not Supported", Toast.LENGTH_SHORT).show();
            }else {
                b1.setEnabled(true);
                speakOn();
            }
        }
    }

    // To speak

    private void speakOn() {
        String txt = e1.getText().toString();
        tts.speak(txt,TextToSpeech.QUEUE_FLUSH,null);  // here it will analyse than speak using queueFlush
        tts.setSpeechRate(1.0f);
    }
}