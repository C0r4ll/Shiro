package com.phoenixinteractive.shiro;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import opennlp.tools.postag.POSSample;


public class VoiceRecognizer extends AppCompatActivity {

    //Field Declaration
    MainActivity mainActivity;
    Context context;
    EditText editText;
    SpeechRecognizer speechRecognizer;
    Intent speechRecognizerIntent;


    public VoiceRecognizer(Context context, EditText editText, MainActivity mainActivity) {

        //initialize Objects
        this.mainActivity = mainActivity;
        this.context = context;
        this.editText = editText;
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this.context);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    }

    public void checkPermission() {
        if ((ContextCompat.checkSelfPermission(context,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) && (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)) {
            ActivityCompat.requestPermissions(mainActivity ,new String[]{Manifest.permission.RECORD_AUDIO},1);
        }
    }

    public void setupSpeechRecognizer(){

        //Setup Intent
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        //setup SpeechRecognizer
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                editText.setText("");
                editText.setHint("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                if(bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) != null) {
                    ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    mainActivity.receiveData(data.get(0));
                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });


    }

    public void startSpeechRecognizer()
    {
        speechRecognizer.startListening(speechRecognizerIntent);
    }

    public void stopSpeechRecognizer()
    {
        speechRecognizer.stopListening();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }


}

