package com.proyectofinal.trabajoseguro;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceHandler {
    private Context context;
    private SharedPreferences myPrefs;
    public SharePreferenceHandler(Context context){
        this.context=context;
        myPrefs=context.getSharedPreferences("my_preferred_choices", Activity.MODE_PRIVATE);
    }
    public void saveValue(String pwd,String value ){
        SharedPreferences.Editor editor=myPrefs.edit();
        editor.putString(pwd,value);
        editor.commit();
    }
    public void saveValue(String pwd,boolean value ){
        SharedPreferences.Editor editor=myPrefs.edit();
        editor.putBoolean(pwd,value);
        editor.commit();
    }
    public void deleteValue(String pwd){
        myPrefs.edit().remove(pwd).commit();
    }

    public void upgradeValue(String pwd,String value ){
        saveValue(pwd,value);
    }

    public String getValue(String pwd){
        String token= myPrefs.getString(pwd,"");
        return token;
    }
    public boolean getValueBoolean(String pwd){
        boolean token= myPrefs.getBoolean(pwd,false);
        return token;
    }
}
