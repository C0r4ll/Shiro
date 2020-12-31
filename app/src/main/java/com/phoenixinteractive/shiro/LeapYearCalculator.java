package com.phoenixinteractive.shiro;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

import opennlp.tools.postag.POSSample;

public class LeapYearCalculator {

    Context context;
    float year;

    String days;
    String month;
    String yearS;


    //Constructor
    public LeapYearCalculator(Context context) {
        this.context = context;
    }

    //Find Year and Month Date
    public String[] findDate(POSSample outputSample) {

        //Save "Word" and according "Tag" in Array
        String posTokens[] = outputSample.getSentence();
        String posTags[] = outputSample.getTags();

        //For through all Words
        for (int i = 0; i < posTokens.length; i++) {
            //is the Word a "year" (Kardinalzahl)
            if (posTags[i].toUpperCase().equals("CARD")) {
                //Save year as float in "year"-Variable
                year = Integer.parseInt(posTokens[i]);
                yearS =  posTokens[i];
            }
            //Is the Word a "Month" (Eigenname)
            else if (posTags[i].toUpperCase().equals("NN")) {
                switch (posTokens[i].toLowerCase()) {
                    case "januar":
                    case "märz":
                    case "mai":
                    case "juli":
                    case "august":
                    case "oktober":
                    case "dezember":
                        month = posTokens[i];
                        days = "31";
                        break;
                    case "april":
                    case "juni":
                    case "september":
                    case "november":
                        month = posTokens[i];
                        days = "30";
                        break;
                    case "februar":
                        month = posTokens[i];
                        days = "28";
                        break;
                }
            }
            //September gets recognized as Adverb
            else if (posTokens[i].toLowerCase().equals("september")) {
                month = posTokens[i];
                days = "30";
            }
        }
        String[] yearInfo = {days,month,yearS};
        return yearInfo;
    }
}
