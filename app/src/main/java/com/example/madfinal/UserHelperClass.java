package com.example.madfinal;

import android.widget.EditText;

public class UserHelperClass {

    public String mName,mEmail;
    public Integer mRadio;


    public UserHelperClass(String name, String email) {

    }


    public UserHelperClass(String mName, String mEmail, Integer mRadio) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mRadio = mRadio;

    }

}