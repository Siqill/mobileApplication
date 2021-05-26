package com.example.zaliczenie;

public class Vocabulary {
    private String firstForm;
    private String secondForm;
    private String thirdForm;

    public Vocabulary(String firstForm, String secondForm, String thirdForm) {
        this.firstForm = firstForm;
        this.secondForm = secondForm;
        this.thirdForm = thirdForm;
    }

    public String getFirstForm() {
        return firstForm;
    }

    public String getSecondForm() {
        return secondForm;
    }

    public String getThirdForm() {
        return thirdForm;
    }

}
