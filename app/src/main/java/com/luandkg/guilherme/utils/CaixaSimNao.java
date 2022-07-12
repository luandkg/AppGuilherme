package com.luandkg.guilherme.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.luandkg.guilherme.utils.Acao;

public class CaixaSimNao {

    public static void perguntar(Context eContexto, String tilulo, String pergunta, Acao acao) {

        AlertDialog.Builder builder = new AlertDialog.Builder(eContexto);

        // Set the message show for the Alert time
        builder.setMessage(pergunta);

        // Set Alert Title
        builder.setTitle(tilulo);

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder.setPositiveButton(
                "Sim",
                new DialogInterface
                        .OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        acao.fazer();
                    }
                });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
        builder.setNegativeButton(
                "Não",
                new DialogInterface
                        .OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }


}
