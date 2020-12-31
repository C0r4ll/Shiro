package com.phoenixinteractive.shiro;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class TextToSpeechOutput {

    Context context;
    TextToSpeech t1;

    //Constructor
    public TextToSpeechOutput(Context context) {
        this.context = context;
        t1 = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                t1.setLanguage(Locale.GERMAN);
            }
        });
    }

    public void say(String toSpeak)
        {
            t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
        }

    public void onPause() {
            if(t1 != null){
                t1.stop();
                t1.shutdown();
            }
        }




}
