package com.luandkg.guilherme.libs.verkuz;

public class Commit {

    private String mData;
    private String mComentario;

    public Commit(String eData,String eComentario){
        mData=eData;
        mComentario=eComentario;
    }

    public String getData(){return mData;}
    public String getComentario(){return mComentario;}

}
