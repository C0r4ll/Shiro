package com.phoenixinteractive.shiro;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import androidx.appcompat.app.AppCompatActivity;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class NaturalLanguageProcessing {

    //Field Declaration
    Context context;
    POSTaggerME tagger;
    WhitespaceTokenizer whitespaceTokenizer;
    String[] tags;
    String[] tokens;
    POSSample sample;


    public NaturalLanguageProcessing(Context context){
        this.context = context;
    }

    public POSSample analyse(String sentence){
        //try to Load Parts of speech-maxent model
        InputStream inputStream = null;
        POSModel model = null;
        try {
            inputStream = context.getAssets().open("de-pos-maxent.bin");
            model = new POSModel(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Instantiating POSTaggerME class
        tagger = new opennlp.tools.postag.POSTaggerME(model);

        //Instantiating whitespaceTokenizer
        whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;

        //Tokenizing the sentence using WhitespaceTokenizer class
        tokens = whitespaceTokenizer.tokenize(sentence);

        //Generating tags
        tags = tagger.tag(tokens);
        //Instantiating the POSSample class
        sample = new POSSample(tokens, tags);
 
        //Print out Result
        return sample;
    }
}
