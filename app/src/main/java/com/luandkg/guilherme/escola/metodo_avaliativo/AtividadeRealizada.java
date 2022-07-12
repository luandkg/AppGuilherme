package com.luandkg.guilherme.escola.metodo_avaliativo;

public class AtividadeRealizada {

    private String mData;
    private boolean mStatus;

    private String mArquivo;
    private String mDataEntregue;
private boolean misAtrasada;

    public AtividadeRealizada(String eData,String eArquivo,String eDataEntregue,boolean eStatus){
        mData=eData;
        mStatus=eStatus;
        mArquivo=eArquivo;
        mDataEntregue=eDataEntregue;
        misAtrasada=false;
    }

    public String getArquivo(){return mArquivo;}
    public String getDataEntregue(){return mDataEntregue;}

    public String getData(){return mData;}
    public boolean getStatus(){return mStatus;}

    public void marcarAtrasada(){
        misAtrasada=true;
    }
    public boolean isAtrasada(){return misAtrasada;}
}
