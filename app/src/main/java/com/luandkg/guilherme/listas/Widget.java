package com.luandkg.guilherme.listas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Widget {

    private View mView;

    public Widget(View eView) {
        mView = eView;
    }

    public Widget(int recurso_id, LayoutInflater inflater, ViewGroup parent) {
        mView = inflater.inflate(recurso_id, parent, false);
    }

    public ImageView getImageView(int recursoID) {
        return (ImageView) mView.findViewById(recursoID);
    }

    public TextView getTextView(int recurso_i) {
        return (TextView) mView.findViewById(recurso_i);
    }

    public Button getButton(int recurso_i) {
        return (Button) mView.findViewById(recurso_i);
    }

    public View getView(){return mView;}

    public void setAuto(){
        if (mView!=null){
            mView.setTag(mView);
        }
    }
}
