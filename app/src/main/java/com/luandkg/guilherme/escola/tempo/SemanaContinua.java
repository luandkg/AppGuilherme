package com.luandkg.guilherme.escola.tempo;

import com.luandkg.guilherme.utils.tempo.Data;

import java.util.ArrayList;

public class SemanaContinua {

    private ArrayList<Data> mDatas;
    private String mNome;

    public SemanaContinua(String eNome, ArrayList<Data> eDatas) {
        mNome = eNome;
        mDatas = eDatas;
    }


    public ArrayList<Data> getDatas() {
        return mDatas;
    }

    public String getNome() {
        return mNome;
    }

    public String getStatus() {

      //  System.out.println("QTD :: " + mDatas.size());
        if (mDatas.size() > 0) {
            System.out.println(mDatas.get(0).getTempoLegivel());
            System.out.println(mDatas.get(mDatas.size() - 1).getTempoLegivel());

            return mDatas.get(0).getTempoLegivel() + " - " + mDatas.get(mDatas.size() - 1).getTempoLegivel();
        } else {
            return "";
        }

    }

    public boolean temData(String eData) {
        boolean ret = false;

        for (Data data : mDatas) {
            if (data.isIgual(eData)) {
                ret = true;
                break;
            }
        }


        return ret;
    }
}
