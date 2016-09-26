package com.example.must.my.derdiedas;

/**
 * Created by mustajab on 9/21/16.
 */
public class Word {
    private String word;
    private String plural;
    private String meaning;
    private String derDieDas;
    public Word() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }
    public String getWord() {
        return word;
    }
    public String getPlural() {
        return plural;
    }
    public String getMeaning() {
        return meaning;
    }
    public String getDerDieDas() {
        return derDieDas;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setDerDieDas(String derDieDas) {
        this.derDieDas = derDieDas;
    }
}