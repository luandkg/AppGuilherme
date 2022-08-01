package com.luandkg.guilherme.listas;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.luandkg.guilherme.R;
import com.luandkg.guilherme.libs.tempo.Data;
import com.luandkg.guilherme.libs.verkuz.Commit;
import com.luandkg.guilherme.utils.Widget;

import java.util.ArrayList;

public class Lista_Commits extends BaseAdapter {

    private Context context;
    private ArrayList<Commit> mLista;
    private static LayoutInflater inflater = null;
    private String mData;

    public Lista_Commits(Context mainActivity, ArrayList<Commit> eLista, String eData) {
        context = mainActivity;
        mLista = eLista;
        mData = eData;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mLista.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        Commit eCommit = mLista.get(position);

        Widget mWidget = new Widget(R.layout.item_commit, inflater, parent);

        TextView texto = mWidget.getTextView(R.id.item_commit_texto);
        Button data = mWidget.getButton(R.id.item_commit_data);


        texto.setText(eCommit.getComentario());
        data.setText(Data.toData(eCommit.getData()).getFluxo());

        if (eCommit.isData(mData)) {
            data.setBackgroundColor(Color.parseColor("#ffb300"));
        } else {
            data.setBackgroundColor(Color.parseColor("#4caf50"));
        }

        //System.out.println(eCommit.getData() + " -->> " + eCommit.isData(mData));

        mWidget.setAuto();


        return mWidget.getView();

    }


}