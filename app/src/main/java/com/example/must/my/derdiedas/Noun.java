package com.example.must.my.derdiedas;

/**
 * Created by mustajab on 9/26/16.
 */

public class Noun {

    private String noun;
    private String meaning = "";
    private String plural = "";
    private Artikel artikel;
    private Correct correct = Correct.NOT_SELECTED;
    private boolean shown = false;


    public Noun() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }

    public Noun(String noun, Artikel artikel) {
        this.noun = noun;
        this.artikel = artikel;
    }

    public Noun(String noun, Artikel artikel, String plural) {
        this.noun = noun;
        this.artikel = artikel;
        this.plural = plural;
    }

    public Noun(String noun, String meaning, Artikel artikel) {
        this.noun = noun;
        this.artikel = artikel;
        this.meaning = meaning;
    }

    public Noun(String noun, String meaning, Artikel artikel, String plural) {
        this.noun = noun;
        this.artikel = artikel;
        this.meaning = meaning;
        this.plural = plural;
    }

    public String getNoun() {
        return noun;
    }

    public Artikel getArtikel() {
        return artikel;
    }
    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public Correct getCorrect() {
        return correct;
    }

    public void setCorrect(Correct correct) {
        this.correct = correct;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getPlural() {
        return plural;
    }
}

 enum Correct {
    NOT_SELECTED,
    CORRECT,
    INCORRECT;
}


 enum Artikel {
    DER,
    DIE,
    DAS;
}