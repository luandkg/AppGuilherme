package com.luandkg.guilherme.escola.tempo;

public class SemanaContinuaCarregada {

    private String mNome;
    private String mStatus;

    private int mTodos;
    private int mFizeram;

    private int mNumero;

    public SemanaContinuaCarregada(int eNumero,String eNome,String eStatus,int todos,int fizeram){
        mNumero=eNumero;
        mNome=eNome;
        mStatus=eStatus;
        mTodos=todos;
        mFizeram=fizeram;
    }

    public int getNumero(){return mNumero;}

    public String getNome(){return mNome;}
    public String getStatus(){return mStatus;}
    public int getTodos(){return mTodos;}
    public int getFizeram(){return mFizeram;}

    public void setTodos(int t){mTodos=t;}
}
