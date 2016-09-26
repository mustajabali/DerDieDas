package com.example.must.my.derdiedas;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.authentication.*;
import com.google.gson.reflect.TypeToken;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.net.InetAddress;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 *
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_select);
 }
 */
public class SelectActivity extends ActionBarActivity {

    public SelectActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        CacheUtils.configureCache(this);

        new InternetCheck().execute("Check for Internet");
        setView();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static void fileRead() {
        System.out.println("INTERNET: " + Constants.istate + " fileRead()");
        List<Noun> wordsListFromFile = new ArrayList<>();
        System.out.println("My Class List" + wordsListFromFile);

        Constants.setWords((ArrayList<com.example.must.my.derdiedas.Noun>) CacheUtils.readObjectFile(Constants.getFileName(), new TypeToken<List<Noun>>() {
        }.getType()));
        //getWords() == null ? 0: getWords().size()
        if (Constants.getWords() != null) {
            System.out.println("WORDS: " + Constants.getWords().size() + " fileRead()");
        }
    }

    public static void firebaseinit() {
        System.out.println("INTERNET: " + Constants.istate + " firebaseinit()");
        Firebase myFirebaseRef = new Firebase("https://db4derdiedas.firebaseio.com/androidapp/");

        myFirebaseRef = new Firebase("https://db4derdiedas.firebaseio.com/");
        String childName  = Constants.level.equals(Constants.LEVEL.ZERO) ? "androidapp" : "level1";
        myFirebaseRef.child(childName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Noun> nouns = new ArrayList<Noun>();
                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."

                System.out.println("There are " + snapshot.getChildrenCount() + " words");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Noun noun = postSnapshot.getValue(Noun.class);
                    nouns.add(noun);
                    System.out.println(noun.getArtikel() + " - " + noun.getMeaning()
                            + "-" + noun.getPlural() + " - " + noun.getNoun()
                    );
                }
                Constants.setWords((ArrayList<Noun>) nouns);
                CacheUtils.writeObjectFile(Constants.getFileName(), Constants.getWords());
                System.out.println("WORDS: " + Constants.getWords().size() + " firebaseinit()");

            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
    }

    public void setView() {
        setContentView(wordView());
        System.out.println("Total word list"+ Constants.getWords().size());
    }

    private View wordView() {
        final LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        final TextView numOfWords = new TextView(this);
        numOfWords.setTextSize(20);
        numOfWords.setPadding(20, 0, 30, 0);
        numOfWords.setTextColor(Color.DKGRAY);

        final TextView wordMeaning = new TextView(this);
        wordMeaning.setTextSize(20);
        wordMeaning.setTextColor(Color.DKGRAY);

        final TextView wordPlural = new TextView(this);
        wordPlural.setTextSize(20);
        wordPlural.setTextColor(Color.DKGRAY);

        final TextView correctWords = new TextView(this);
        correctWords.setTextSize(20);
        correctWords.setPadding(50, 0, 0, 0);
        correctWords.setTextColor(Color.DKGRAY);
        correctWords.setText("Correct:0");
        correctWords.setVisibility(View.INVISIBLE);




        LinearLayout wordCount = new LinearLayout(this);
        wordCount.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wordCount.setOrientation(LinearLayout.HORIZONTAL);

        Button showMeaning = new Button(this);
        showMeaning.setText("Show Meaning");


        showMeaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHideView(wordMeaning);
            }
        });

        final Button showPlural = new Button(this);
        showPlural.setText("Show Plural");

        showPlural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHideView(wordPlural);
            }
        });

        LinearLayout showHideButtons = new LinearLayout(this);
        wordCount.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        wordCount.setOrientation(LinearLayout.HORIZONTAL);



        final TextView nounView = new TextView(this);
        nounView.setText(Constants.LOADING);
        nounView.setTextSize(40);


        System.out.println("Noun Size() --> "+ Constants.getWords().size());


        Button derBtn = new Button(this);
        derBtn.setText("DER");
        derBtn.setTextSize(55);
        derBtn.setPadding(10, 30, 20, 15);
        derBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String noun = nounView.getText().toString();
                derDieDas(Constants.getWords(), noun, nounView, Artikel.DER);
                correctCalc(Constants.getWords(), correctWords);
            }
        });

        Button dieBtn = new Button(this);
        dieBtn.setText("DIE");
        dieBtn.setTextSize(55);
        dieBtn.setPadding(20, 30, 20, 15);
        dieBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String noun = nounView.getText().toString();

                derDieDas(Constants.getWords(), noun, nounView, Artikel.DIE);
                correctCalc(Constants.getWords(), correctWords);
            }
        });

        Button dasBtn = new Button(this);
        dasBtn.setText("DAS");
        dasBtn.setTextSize(55);
        dasBtn.setPadding(20, 30, 20, 15);
        dasBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String noun = nounView.getText().toString();

                derDieDas(Constants.getWords(), noun, nounView, Artikel.DAS);
                correctCalc(Constants.getWords(), correctWords);
            }
        });


        Button btn = new Button(this);
        btn.setTextSize(70);
        btn.setPadding(20, 30, 30, 0);
        btn.setTextColor(Color.BLUE);
        btn.setText("Next");
        btn.setOnClickListener(new View.OnClickListener() {
            int wordsNum = 0;

            @Override
            public void onClick(View v) {
                if(Constants.getWords() != null && Constants.getWords().size() > 0 && Constants.getWords().size() == wordsNum) {
                    nounView.setText("FINISHED :-) ");
                    nounView.setTextColor(Color.GREEN);
                    wordPlural.setText("");
                    wordMeaning.setText("");
                } else {
                    //nounView.setText("No Word in Database! Try Connecting to internet and restart App!");
                }
                if ( (Constants.getWords() != null && Constants.getWords().size() > 0) && (wordsNum < (Constants.getWords().size())) ) {
                    int i;
                    wordsNum++;
                    do {
                        i = (int) (Math.random() * Constants.getWords().size());

                        if (getAllShown(Constants.getWords())) {
                            setAllNotShown(Constants.getWords());
                        }
                    } while (Constants.getWords().get(i).isShown());

                    nounView.setText(Constants.getWords().get(i).getNoun());
                    wordMeaning.setText(Constants.getWords().get(i).getMeaning());
                    wordPlural.setText(Constants.getWords().get(i).getPlural());
                    nounView.setTextColor(Color.BLACK);
                    Constants.getWords().get(i).setShown(true);
                    numOfWords.setText("Number:" + wordsNum + "/" + Constants.getWords().size());
                    correctWords.setVisibility(View.VISIBLE);
                }


            }
        });

        mainLayout.addView(nounView);
        mainLayout.addView(wordMeaning);
        mainLayout.addView(wordPlural);

        mainLayout.addView(derBtn);
        mainLayout.addView(dieBtn);
        mainLayout.addView(dasBtn);

        mainLayout.addView(btn);

        wordCount.addView(numOfWords);
        wordCount.addView(correctWords);
        mainLayout.addView(wordCount);


        showHideButtons.addView(showMeaning);
        showHideButtons.addView(showPlural);
        mainLayout.addView(showHideButtons);

        return mainLayout;
    }

    private void derDieDas(final List<Noun> nouns, final String noun, final TextView nounView, final Artikel artikel) {

        for (int i = 0; i < nouns.size(); i++) {
            if (noun.equalsIgnoreCase(nouns.get(i).getNoun())) {
                if (!nouns.get(i).getArtikel().equals(artikel)) {
                    nounView.setTextColor(Color.RED);
                    if (nouns.get(i).getCorrect().equals(Correct.NOT_SELECTED)) {
                        nouns.get(i).setCorrect(Correct.INCORRECT);
                    }
                } else {
                    nounView.setTextColor(Color.GREEN);
                    if (nouns.get(i).getCorrect().equals(Correct.NOT_SELECTED)) {
                        nouns.get(i).setCorrect(Correct.CORRECT);
                    }
                }
            }
        }

    }

    /**
     *
     * @param nouns
     * @param correctWords
     */
    private void correctCalc(final List<Noun> nouns, final TextView correctWords) {
        ///chECKING CORRECT IN-CORRECT
        int crckt = 0;
        for (int j = 0; j < nouns.size(); j++) {
            if (nouns.get(j).getCorrect().equals(Correct.CORRECT)) {
                crckt++;
            }
        }
        correctWords.setText("Correct:" + crckt);
    }

    private boolean getAllShown(final List<Noun> nouns) {
        boolean allShown = true;
        for (int j = 0; j < nouns.size(); j++) {
            if (!nouns.get(j).isShown()) {
                allShown = false;
            }
        }
        return allShown;
    }

    private void setAllNotShown(final List<Noun> nouns){
        for (int j = 0; j < nouns.size(); j++) {
            nouns.get(j).setShown(false);
        }
    }

    private void showHideView(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.INVISIBLE);
        } else if(view.getVisibility() == View.INVISIBLE) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }



}
