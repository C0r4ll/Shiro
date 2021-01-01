package com.phoenixinteractive.shiro;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import opennlp.tools.postag.POSSample;

public class MainActivity extends AppCompatActivity {

    //Field Declaration
    RelativeLayout rLayout;
    EditText editText;;

    VoiceRecognizer voiceRec;
    NaturalLanguageProcessing nlp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initialize Objects
        rLayout = findViewById(R.id.mainlayout);
        editText = findViewById(R.id.editText);
        voiceRec = new VoiceRecognizer(this, editText, this);
        nlp = new NaturalLanguageProcessing(this);

        //Check Permissions
        voiceRec.checkPermission();

        //Create Speech Recognizer
         voiceRec.setupSpeechRecognizer();

        //Check if Screen is pressed
        rLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //If you Press the Screen
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    voiceRec.startSpeechRecognizer();
                }
                //If you Release it
                if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    voiceRec.stopSpeechRecognizer();
                }

                return false;
            }
        });

    }

    public void receiveData(String data) {
        POSSample outputSample = nlp.analyse(data);
        editText.setText(outputSample.toString());
    }
}