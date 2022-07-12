package com.luandkg.guilherme.listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListaGenerica extends BaseAdapter {

    private Context mContext;
    private int mQuantidade;
    private Itenizador mItenizador;

    private static LayoutInflater inflater = null;

    public ListaGenerica(Context eContext, int eQuantidade, Itenizador eIternizador) {
        mContext = eContext;
        mQuantidade = eQuantidade;
        mItenizador = eIternizador;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mQuantidade;
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

        View rowView = mItenizador.onItem(inflater, parent, position);

        return rowView;
    }


}